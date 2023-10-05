package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.Aufgaben;
import ch.zkmf2024.server.dto.Einsatzzeit;
import ch.zkmf2024.server.dto.RegisterHelperRequestDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.HelperRegistrationPojo;
import ch.zkmf2024.server.mapper.HelperRegistrationMapper;
import ch.zkmf2024.server.repository.HelperRegistrationRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static ch.zkmf2024.server.mapper.HelperRegistrationMapper.LIST_DELIMITER;
import static ch.zkmf2024.server.service.DateUtil.now;
import static ch.zkmf2024.server.service.DateUtil.toLocalDateTime;
import static ch.zkmf2024.server.service.HelperRegistrationService.RegisterHelperResult.ALREADY_REGISTERED;
import static ch.zkmf2024.server.service.HelperRegistrationService.RegisterHelperResult.INVALID_EMAIL;
import static ch.zkmf2024.server.service.HelperRegistrationService.RegisterHelperResult.REGISTERED;
import static ch.zkmf2024.server.service.ValidationUtil.isValidEmail;
import static java.util.Comparator.comparing;

@Service
public class HelperRegistrationService {

    private final HelperRegistrationRepository helperRegistrationRepository;
    private final MailService mailService;

    public HelperRegistrationService(HelperRegistrationRepository helperRegistrationRepository,
                                     MailService mailService) {
        this.helperRegistrationRepository = helperRegistrationRepository;
        this.mailService = mailService;
    }

    public static List<Aufgaben> getAufgabenAsList(HelperRegistrationPojo helperRegistration) {
        return Arrays.stream(StringUtils.split(helperRegistration.getAufgaben(), LIST_DELIMITER))
                     .map(Aufgaben::valueOf)
                     .toList();
    }

    public static List<Einsatzzeit> getEinsatzMittwochAsList(HelperRegistrationPojo helperRegistration) {
        return getEinsatzzeitAsList(helperRegistration.getEinsatzMittwoch());
    }

    public static List<Einsatzzeit> getEinsatzDonnerstagAsList(HelperRegistrationPojo helperRegistration) {
        return getEinsatzzeitAsList(helperRegistration.getEinsatzDonnerstag());
    }

    public static List<Einsatzzeit> getEinsatzFreitagAsList(HelperRegistrationPojo helperRegistration) {
        return getEinsatzzeitAsList(helperRegistration.getEinsatzFreitag());
    }

    public static List<Einsatzzeit> getEinsatzSamstagAsList(HelperRegistrationPojo helperRegistration) {
        return getEinsatzzeitAsList(helperRegistration.getEinsatzSamstag());
    }

    public static List<Einsatzzeit> getEinsatzSonntagAsList(HelperRegistrationPojo helperRegistration) {
        return getEinsatzzeitAsList(helperRegistration.getEinsatzSonntag());
    }

    public static List<Einsatzzeit> getEinsatzMontagAsList(HelperRegistrationPojo helperRegistration) {
        return getEinsatzzeitAsList(helperRegistration.getEinsatzMontag());
    }

    public static List<Einsatzzeit> getEinsatzDienstagAsList(HelperRegistrationPojo helperRegistration) {
        return getEinsatzzeitAsList(helperRegistration.getEinsatzDienstag());
    }

    private static List<Einsatzzeit> getEinsatzzeitAsList(String values) {
        return Arrays.stream(StringUtils.split(values, LIST_DELIMITER)).map(Einsatzzeit::valueOf).toList();
    }

    public RegisterHelperResult register(RegisterHelperRequestDTO request) {
        if (!isValidEmail(request.email())) {
            return INVALID_EMAIL;
        }

        if (helperRegistrationRepository.existsByEmail(request.email())) {
            return ALREADY_REGISTERED;
        }

        var helperRegistration = HelperRegistrationMapper.INSTANCE.fromDTO(request);
        helperRegistration.setRegisteredAt(now());
        helperRegistrationRepository.insert(helperRegistration);

        mailService.sendHelperRegistrationEmail(helperRegistration);

        return REGISTERED;
    }

    public enum RegisterHelperResult {
        REGISTERED,
        ALREADY_REGISTERED,
        INVALID_EMAIL
    }

    public File exportForImport() throws IOException {
        try (var wb = new XSSFWorkbook()) {
            var sheet = wb.createSheet();
            wb.setSheetName(0, "Helferanmeldung");

            var creationHelper = wb.getCreationHelper();
            var dateCellStyle = wb.createCellStyle();
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd.MM.yyyy"));

            var rowIndex = 0;
            // headers
            var headerRow = sheet.createRow(rowIndex++);

            var columnIndex = 0;
            headerRow.createCell(columnIndex++).setCellValue("Vorname");
            headerRow.createCell(columnIndex++).setCellValue("Nachname");
            headerRow.createCell(columnIndex++).setCellValue("Email");
            headerRow.createCell(columnIndex++).setCellValue("Telefon");
            headerRow.createCell(columnIndex++).setCellValue("Geburtsdatum");
            headerRow.createCell(columnIndex++).setCellValue("T-Shirt");
            headerRow.createCell(columnIndex++).setCellValue("Vereinszugehörigkeit");

            for (var helperRegistration : helperRegistrationRepository.findAll()
                                                                      .stream()
                                                                      .sorted(comparing(HelperRegistrationPojo::getRegisteredAt).reversed())
                                                                      .toList()) {

                var row = sheet.createRow(rowIndex++);
                columnIndex = 0;

                row.createCell(columnIndex++).setCellValue(helperRegistration.getVorname());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getName());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getEmail());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getTelefon());

                var geburtstagCell = row.createCell(columnIndex++);
                geburtstagCell.setCellValue(DateUtil.getExcelDate(helperRegistration.getGeburtsdatum()));
                geburtstagCell.setCellStyle(dateCellStyle);

                row.createCell(columnIndex++).setCellValue(helperRegistration.getGroesseShirt());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getVereinszugehoerigkeit());
            }

            for (int i = 0; i < columnIndex; i++) {
                sheet.autoSizeColumn(i);
            }

            Path temp = Files.createTempFile(null, ".xlsx");
            try (var fileOut = new FileOutputStream(temp.toFile())) {
                wb.write(fileOut);
                return temp.toFile();
            }
        }
    }

    public File export() throws IOException {
        try (var wb = new XSSFWorkbook()) {
            var sheet = wb.createSheet();
            wb.setSheetName(0, "Helferanmeldung");

            var creationHelper = wb.getCreationHelper();
            var dateTimeCellStyle = wb.createCellStyle();
            dateTimeCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd.MM.yyyy hh:mm"));

            var dateCellStyle = wb.createCellStyle();
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd.MM.yyyy"));

            var rowIndex = 0;
            // headers
            var headerRow1 = sheet.createRow(rowIndex++);
            var headerRow2 = sheet.createRow(rowIndex++);

            var columnIndex = 0;
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of(""), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Email"), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Nachname"), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Vorname"), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Adresse"), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("PLZ / Ort"), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Geburtsdatum"), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Telefon-Nummer"), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Vereinszugehörigkeit"), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "Welche Aufgaben möchten Sie übernehmen?",
                                    Arrays.stream(Aufgaben.values()).map(Aufgaben::getDescription).toList(), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Anzahl möglicher Einsätze"), columnIndex);

            var einsatzzeiten = Arrays.stream(Einsatzzeit.values()).map(Einsatzzeit::getDescription).toList();
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "Mittwoch, 19.06.2024", einsatzzeiten, columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "Donnerstag, 20.06.2024", einsatzzeiten, columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "Freitag, 21.06.2024", einsatzzeiten, columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "Samstag, 22.06.2024", einsatzzeiten, columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "Sonntag, 23.06.2024", einsatzzeiten, columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "Montag, 24.06.2024", einsatzzeiten, columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "Dienstag, 25.06.2024", einsatzzeiten, columnIndex);

            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Grösse Helfer-T-Shirt"), columnIndex);
            columnIndex = addHeader(sheet, headerRow1, headerRow2, "", List.of("Bemerkungen"), columnIndex);

            for (var helperRegistration : helperRegistrationRepository.findAll()
                                                                      .stream()
                                                                      .sorted(comparing(HelperRegistrationPojo::getRegisteredAt).reversed())
                                                                      .toList()) {

                var row = sheet.createRow(rowIndex++);

                columnIndex = 0;

                var registeredAtCell = row.createCell(columnIndex++);
                registeredAtCell.setCellValue(DateUtil.getExcelDate(toLocalDateTime(helperRegistration.getRegisteredAt())));
                registeredAtCell.setCellStyle(dateTimeCellStyle);

                row.createCell(columnIndex++).setCellValue(helperRegistration.getEmail());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getName());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getVorname());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getAdresse());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getPlzOrt());

                var geburtstagCell = row.createCell(columnIndex++);
                geburtstagCell.setCellValue(DateUtil.getExcelDate(helperRegistration.getGeburtsdatum()));
                geburtstagCell.setCellStyle(dateCellStyle);

                row.createCell(columnIndex++).setCellValue(helperRegistration.getTelefon());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getVereinszugehoerigkeit());
                for (var aufgabe : Aufgaben.values()) {
                    var cell = row.createCell(columnIndex++);
                    if (getAufgabenAsList(helperRegistration).contains(aufgabe)) {
                        cell.setCellValue("x");
                    }
                }

                row.createCell(columnIndex++).setCellValue(helperRegistration.getAnzahlEinsaetze());

                columnIndex = fillEinssatzzeit(columnIndex, row, getEinsatzMittwochAsList(helperRegistration));
                columnIndex = fillEinssatzzeit(columnIndex, row, getEinsatzDonnerstagAsList(helperRegistration));
                columnIndex = fillEinssatzzeit(columnIndex, row, getEinsatzFreitagAsList(helperRegistration));
                columnIndex = fillEinssatzzeit(columnIndex, row, getEinsatzSamstagAsList(helperRegistration));
                columnIndex = fillEinssatzzeit(columnIndex, row, getEinsatzSonntagAsList(helperRegistration));
                columnIndex = fillEinssatzzeit(columnIndex, row, getEinsatzMontagAsList(helperRegistration));
                columnIndex = fillEinssatzzeit(columnIndex, row, getEinsatzDienstagAsList(helperRegistration));

                row.createCell(columnIndex++).setCellValue(helperRegistration.getGroesseShirt());
                row.createCell(columnIndex++).setCellValue(helperRegistration.getComment());
            }

            for (int i = 0; i < columnIndex; i++) {
                sheet.autoSizeColumn(i);
            }

            Path temp = Files.createTempFile(null, ".xlsx");
            try (var fileOut = new FileOutputStream(temp.toFile())) {
                wb.write(fileOut);
                return temp.toFile();
            }
        }
    }

    private static int fillEinssatzzeit(int columnIndex, XSSFRow row, List<Einsatzzeit> einsatz) {
        for (var einsatzzeit : Einsatzzeit.values()) {
            var cell = row.createCell(columnIndex++);
            if (einsatz.contains(einsatzzeit)) {
                cell.setCellValue("x");
            }
        }
        return columnIndex;
    }

    private int addHeader(XSSFSheet sheet, XSSFRow firstRow, XSSFRow secondRow, String first, List<String> second, int columnIndex) {
        var firstColumnIndex = columnIndex;
        for (int i = 0; i < second.size(); i++) {
            var cell1 = firstRow.createCell(columnIndex);
            var cell2 = secondRow.createCell(columnIndex++);
            if (i == 0) {
                cell1.setCellValue(first);
            } else {
                cell1.setCellValue("");
            }
            cell2.setCellValue(second.get(i));
        }
        if ((columnIndex - 1) > firstColumnIndex) {
            sheet.addMergedRegion(new CellRangeAddress(firstRow.getRowNum(), firstRow.getRowNum(), firstColumnIndex, columnIndex - 1));
        }
        return columnIndex;
    }
}
