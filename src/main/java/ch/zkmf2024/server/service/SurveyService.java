package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.SurveyAnswerDTO;
import ch.zkmf2024.server.repository.SurveyAnswerRepository;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static ch.zkmf2024.server.mapper.DTOMapper.INSTANCE;
import static ch.zkmf2024.server.service.SurveyService.RegisterSurveyAnswer.INVALID_EMAIL;
import static ch.zkmf2024.server.service.SurveyService.RegisterSurveyAnswer.REGISTERED;
import static ch.zkmf2024.server.service.ValidationUtil.isValidEmail;
import static org.springframework.data.domain.Sort.Order.desc;
import static org.springframework.data.domain.Sort.by;

@Service
public class SurveyService {

    private final SurveyAnswerRepository surveyAnswerRepository;

    public SurveyService(SurveyAnswerRepository surveyAnswerRepository) {
        this.surveyAnswerRepository = surveyAnswerRepository;
    }

    public RegisterSurveyAnswer register(SurveyAnswerDTO dto) {
        if (!isValidEmail(dto.kontaktEmail())) {
            return INVALID_EMAIL;
        }

        var surveyAnswer = INSTANCE.fromDTO(dto);
        surveyAnswer.setTimestamp(LocalDateTime.now());
        surveyAnswerRepository.save(surveyAnswer);

        return REGISTERED;
    }

    public List<SurveyAnswerDTO> getAll() {
        return surveyAnswerRepository.findAll().stream()
                                     .map(INSTANCE::toDTO)
                                     .toList();
    }

    public void delete(Long id) {
        surveyAnswerRepository.deleteById(id);
    }

    public File export() throws IOException {
        var wb = new XSSFWorkbook();
        var sheet = wb.createSheet();
        wb.setSheetName(0, "Konsultativumfrage");

        var creationHelper = wb.getCreationHelper();
        var dateCellStyle = wb.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd.MM.yyyy hh:mm"));

        var rowIndex = 0;
        // headers
        var headerRow = sheet.createRow(rowIndex++);

        var columnIndex = 0;
        headerRow.createCell(columnIndex++).setCellValue("");
        headerRow.createCell(columnIndex++).setCellValue("Vereinsname");
        headerRow.createCell(columnIndex++).setCellValue("Besetzung");
        headerRow.createCell(columnIndex++).setCellValue("Stärkeklasse");
        headerRow.createCell(columnIndex++).setCellValue("Anzahl Mitglieder");
        headerRow.createCell(columnIndex++).setCellValue("Kontakt Name");
        headerRow.createCell(columnIndex++).setCellValue("Kontakt Email");
        headerRow.createCell(columnIndex++).setCellValue("Kontakt Telefon");
        headerRow.createCell(columnIndex++).setCellValue("Modul-Auswahl");
        headerRow.createCell(columnIndex++).setCellValue("Zusage Kommentar");
        headerRow.createCell(columnIndex++).setCellValue("Absage?");
        headerRow.createCell(columnIndex++).setCellValue("Absage Kommentar");
        headerRow.createCell(columnIndex++).setCellValue("Absage Kontaktaufnahme");
        headerRow.createCell(columnIndex++).setCellValue("Helfer");

        for (var surveyAnswer : surveyAnswerRepository.findAll(by(desc("timestamp")))) {

            var row = sheet.createRow(rowIndex++);

            columnIndex = 0;

            XSSFCell cell = row.createCell(columnIndex++);
            cell.setCellValue(DateUtil.getExcelDate(surveyAnswer.getTimestamp()));
            cell.setCellStyle(dateCellStyle);

            row.createCell(columnIndex++).setCellValue(surveyAnswer.getVereinsName());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getBesetzung());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getStaerkeKlasse());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getAnzahlMitglieder());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getKontaktName());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getKontaktEmail());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getKontaktTelefon());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getModulAuswahl());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getZusageKommentar());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.isAbsage());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getAbsageKommentar());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getAbsageKontaktaufnahme());
            row.createCell(columnIndex++).setCellValue(surveyAnswer.getHelfer());
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

    public enum RegisterSurveyAnswer {
        REGISTERED,
        INVALID_EMAIL
    }
}
