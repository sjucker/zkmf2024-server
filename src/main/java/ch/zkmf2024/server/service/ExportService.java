package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.VereinDTO;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Function;

@Service
public class ExportService {

    private final VereinService vereinService;

    public ExportService(VereinService vereinService) {
        this.vereinService = vereinService;
    }

    public File exportVereine() throws IOException {
        try (var wb = new XSSFWorkbook()) {
            var sheet = wb.createSheet();
            wb.setSheetName(0, "Vereine");

            var rowIndex = 0;
            // headers
            var headerRow = sheet.createRow(rowIndex++);

            var columnIndex = 0;
            headerRow.createCell(columnIndex++).setCellValue("Vereinsname");
            headerRow.createCell(columnIndex++).setCellValue("Email");
            headerRow.createCell(columnIndex++).setCellValue("Adresse");
            headerRow.createCell(columnIndex++).setCellValue("PLZ");
            headerRow.createCell(columnIndex++).setCellValue("Ort");
            headerRow.createCell(columnIndex++).setCellValue("Homepage");
            headerRow.createCell(columnIndex++).setCellValue("IBAN");
            headerRow.createCell(columnIndex++).setCellValue("Direktion Doppeleinsatz");
            headerRow.createCell(columnIndex++).setCellValue("Direktion Doppeleinsatz Verein");
            headerRow.createCell(columnIndex++).setCellValue("Mitspieler Doppeleinsatz");

            headerRow.createCell(columnIndex++).setCellValue("Präsident/in Vorname");
            headerRow.createCell(columnIndex++).setCellValue("Präsident/in Name");
            headerRow.createCell(columnIndex++).setCellValue("Präsident/in Adresse");
            headerRow.createCell(columnIndex++).setCellValue("Präsident/in PLZ");
            headerRow.createCell(columnIndex++).setCellValue("Präsident/in Ort");
            headerRow.createCell(columnIndex++).setCellValue("Präsident/in Email");
            headerRow.createCell(columnIndex++).setCellValue("Präsident/in Telefon privat");
            headerRow.createCell(columnIndex++).setCellValue("Präsident/in Telefon mobile");

            headerRow.createCell(columnIndex++).setCellValue("Dirigent/in Vorname");
            headerRow.createCell(columnIndex++).setCellValue("Dirigent/in Name");
            headerRow.createCell(columnIndex++).setCellValue("Dirigent/in Adresse");
            headerRow.createCell(columnIndex++).setCellValue("Dirigent/in PLZ");
            headerRow.createCell(columnIndex++).setCellValue("Dirigent/in Ort");
            headerRow.createCell(columnIndex++).setCellValue("Dirigent/in Email");
            headerRow.createCell(columnIndex++).setCellValue("Dirigent/in Telefon privat");
            headerRow.createCell(columnIndex++).setCellValue("Dirigent/in Telefon mobile");

            headerRow.createCell(columnIndex++).setCellValue("Definitiv angemeldet");
            headerRow.createCell(columnIndex++).setCellValue("Modul A");
            headerRow.createCell(columnIndex++).setCellValue("Modul B");
            headerRow.createCell(columnIndex++).setCellValue("Modul C");
            headerRow.createCell(columnIndex++).setCellValue("Modul D");
            headerRow.createCell(columnIndex++).setCellValue("Modul E");
            headerRow.createCell(columnIndex++).setCellValue("Modul F");
            headerRow.createCell(columnIndex++).setCellValue("Modul G");
            headerRow.createCell(columnIndex++).setCellValue("Modul H");
            headerRow.createCell(columnIndex++).setCellValue("Klasse Modul A");
            headerRow.createCell(columnIndex++).setCellValue("Klasse Modul B");
            headerRow.createCell(columnIndex++).setCellValue("Klasse Modul H");
            headerRow.createCell(columnIndex++).setCellValue("Harmonie");
            headerRow.createCell(columnIndex++).setCellValue("Brass Band");
            headerRow.createCell(columnIndex++).setCellValue("Fanfare");
            headerRow.createCell(columnIndex++).setCellValue("Tambouren");
            headerRow.createCell(columnIndex++).setCellValue("Perkussionsensemble");
            headerRow.createCell(columnIndex++).setCellValue("Tambouren Kat. A");
            headerRow.createCell(columnIndex++).setCellValue("Tambouren Kat. B");
            headerRow.createCell(columnIndex++).setCellValue("Tambouren Kat. C");

            for (var vereinDTO : vereinService.findAllForExport()) {
                var row = sheet.createRow(rowIndex++);
                columnIndex = 0;

                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().vereinsname());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.email());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().adresse());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().plz());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().ort());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().homepage());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().iban());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().direktionDoppeleinsatz());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().direktionDoppeleinsatzVerein());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().mitspielerDoppeleinsatz());

                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.praesident().vorname());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.praesident().nachname());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.praesident().adresse());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.praesident().plz());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.praesident().ort());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.praesident().email());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.praesident().telefonPrivat());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.praesident().telefonMobile());

                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.direktion().vorname());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.direktion().nachname());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.direktion().adresse());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.direktion().plz());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.direktion().ort());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.direktion().email());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.direktion().telefonPrivat());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.direktion().telefonMobile());

                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.registrationConfirmed());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().modulA());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().modulB());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().modulC());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().modulD());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().modulE());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().modulF());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().modulG());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().modulH());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().klasseModulA());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().klasseModulB());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().klasseModulH());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().harmonie());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().brassBand());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().fanfare());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().tambouren());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().perkussionsensemble());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().tambourenKatA());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().tambourenKatB());
                columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.anmeldung().tambourenKatC());
            }

            for (int i = 0; i < columnIndex; i++) {
                sheet.autoSizeColumn(i);
            }

            var temp = Files.createTempFile(null, ".xlsx");
            try (var fileOut = new FileOutputStream(temp.toFile())) {
                wb.write(fileOut);
                return temp.toFile();
            }
        }
    }

    private <T> int setCellValue(int columnIndex, VereinDTO vereinDTO, XSSFRow row, Function<VereinDTO, T> getter) {
        var cell = row.createCell(columnIndex++);
        var value = getter.apply(vereinDTO);
        if (value != null) {
            if (value instanceof String stringValue) {
                cell.setCellValue(stringValue);
            } else if (value instanceof Double numberValue) {
                cell.setCellValue(numberValue);
            } else if (value instanceof Integer integerValue) {
                cell.setCellValue(integerValue);
            } else if (value instanceof Boolean booleanValue) {
                cell.setCellValue(booleanValue ? "x" : "");
            } else if (value instanceof Klasse klasseValue) {
                cell.setCellValue(klasseValue.getDescription());
            }
        }
        return columnIndex;
    }
}
