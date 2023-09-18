package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.HasDescription;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.TitelDTO;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.dto.VereinProgrammDTO;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jooq.tools.StopWatch;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static ch.zkmf2024.server.dto.Modul.B;
import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class ExportService {

    private final VereinService vereinService;

    public ExportService(VereinService vereinService) {
        this.vereinService = vereinService;
    }

    public File exportVereine() throws IOException {
        var stopWatch = new StopWatch();
        try (var wb = new XSSFWorkbook()) {
            var dtos = vereinService.findAllForExport();

            stopWatch.splitInfo("findAllForExport");

            fillVereineSheet(wb, dtos);
            stopWatch.splitInfo("fillVereineSheet");

            fillDoppeleinsatzSheet(wb, dtos);
            stopWatch.splitInfo("fillDoppeleinsatzSheet");

            fillProgramme(wb, dtos);
            stopWatch.splitInfo("fillProgramme");

            var temp = Files.createTempFile(null, ".xlsx");
            try (var fileOut = new FileOutputStream(temp.toFile())) {
                wb.write(fileOut);
                stopWatch.splitInfo("write");

                return temp.toFile();
            }
        }
    }

    private void fillProgramme(XSSFWorkbook wb, List<VereinDTO> vereine) {
        Map<Modul, List<VereinProgrammDTO>> perModul = vereine.stream()
                                                              .flatMap(vereinDTO -> vereinDTO.programme().stream())
                                                              .collect(groupingBy(VereinProgrammDTO::modul, toList()));

        Map<Long, String> toVereine = new HashMap<>();
        for (var vereinDTO : vereine) {
            for (var vereinProgrammDTO : vereinDTO.programme()) {
                toVereine.put(vereinProgrammDTO.id(), vereinDTO.angaben().vereinsname());
            }
        }

        int sheetIndex = 2;
        for (var modul : Modul.values()) {
            var sheet = wb.createSheet();
            wb.setSheetName(sheetIndex++, "Modul %s".formatted(modul.name()));

            var programme = perModul.getOrDefault(modul, new ArrayList<>());
            var maxTitel = programme.stream()
                                    .mapToInt(vereinProgrammDTO -> vereinProgrammDTO.ablauf().size())
                                    .max()
                                    .orElse(0);

            int rowIndex = 1;
            addModulHeader(modul, sheet.createRow(0), maxTitel);
            for (var vereinProgrammDTO : programme.stream()
                                                  .sorted(comparing(VereinProgrammDTO::klasse, nullsLast(naturalOrder()))
                                                                  .thenComparing(VereinProgrammDTO::besetzung, nullsLast(naturalOrder()))
                                                                  .thenComparing(VereinProgrammDTO::id))
                                                  .toList()) {
                var row = sheet.createRow(rowIndex++);
                addModulData(modul, wb, row, vereinProgrammDTO, toVereine.get(vereinProgrammDTO.id()));
            }
        }
    }

    private void addModulData(Modul modul, XSSFWorkbook wb, XSSFRow row, VereinProgrammDTO vereinProgrammDTO, String vereinsname) {
        var columnIndex = 0;
        columnIndex = setCellValue(columnIndex, row, vereinsname, wb);

        switch (modul) {
            case A, B -> {
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.klasse(), wb);
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.besetzung(), wb);
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.titel(), wb);
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.infoModeration(), wb);

                if (modul == B) {
                    columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.unterhaltungPA(), wb);
                    columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.unterhaltungEGitarre(), wb);
                    columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.unterhaltungEBass(), wb);
                    columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.unterhaltungKeyboard(), wb);
                    columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.unterhaltungGesang(), wb);
                }

                for (var programmTitelDTO : vereinProgrammDTO.ablauf()) {
                    columnIndex = setCellValue(columnIndex, row, getFormattedTitel(programmTitelDTO.titel()), wb);
                    columnIndex = setCellValue(columnIndex, row, programmTitelDTO.titel().grad(), wb);
                    columnIndex = setCellValue(columnIndex, row, getDuration(programmTitelDTO.titel().durationInSeconds()), wb);
                    columnIndex = setCellValue(columnIndex, row, programmTitelDTO.applausInSeconds(), wb);
                    columnIndex = setCellValue(columnIndex, row, programmTitelDTO.titel().infoModeration(), wb);
                }
            }
            case C -> {
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.infoModeration(), wb);
                for (var programmTitelDTO : vereinProgrammDTO.ablauf()) {
                    columnIndex = setCellValue(columnIndex, row, getFormattedTitel(programmTitelDTO.titel()), wb);
                }
            }
            case D -> {
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.besetzung(), wb);
                columnIndex = setCellValue(columnIndex, row, getFormattedTitel(vereinProgrammDTO.parademusikTitel1()), wb);
                columnIndex = setCellValue(columnIndex, row, getDuration(vereinProgrammDTO.parademusikTitel1().durationInSeconds()), wb);
                columnIndex = setCellValue(columnIndex, row, getFormattedTitel(vereinProgrammDTO.parademusikTitel2()), wb);
                setCellValue(columnIndex, row, getDuration(vereinProgrammDTO.parademusikTitel2().durationInSeconds()), wb);
            }
            case E -> {
                for (var programmTitelDTO : vereinProgrammDTO.ablauf()) {
                    columnIndex = setCellValue(columnIndex, row, getFormattedTitel(programmTitelDTO.titel()), wb);
                    columnIndex = setCellValue(columnIndex, row, getDuration(programmTitelDTO.titel().durationInSeconds()), wb);
                }
            }
            case F -> {
                // not used
            }
            case G -> {
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.tambourenKatA(), wb);
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.tambourenKatAGrundlage1(), wb);
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.tambourenKatAGrundlage2(), wb);

                columnIndex = setCellValue(columnIndex, row, getFormattedTitel(vereinProgrammDTO.tambourenKatATitel1()), wb);
                columnIndex = setCellValue(columnIndex, row, getDuration(vereinProgrammDTO.tambourenKatATitel1().durationInSeconds()), wb);

                columnIndex = setCellValue(columnIndex, row, getFormattedTitel(vereinProgrammDTO.tambourenKatATitel2()), wb);
                columnIndex = setCellValue(columnIndex, row, getDuration(vereinProgrammDTO.tambourenKatATitel2().durationInSeconds()), wb);

                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.tambourenKatB(), wb);
                columnIndex = setCellValue(columnIndex, row, getFormattedTitel(vereinProgrammDTO.tambourenKatBTitel()), wb);
                columnIndex = setCellValue(columnIndex, row, getDuration(vereinProgrammDTO.tambourenKatBTitel().durationInSeconds()), wb);

                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.tambourenKatC(), wb);
                columnIndex = setCellValue(columnIndex, row, getFormattedTitel(vereinProgrammDTO.tambourenKatCTitel()), wb);
                setCellValue(columnIndex, row, getDuration(vereinProgrammDTO.tambourenKatCTitel().durationInSeconds()), wb);
            }
            case H -> {
                columnIndex = setCellValue(columnIndex, row, vereinProgrammDTO.klasse(), wb);
                for (var programmTitelDTO : vereinProgrammDTO.ablauf()) {
                    columnIndex = setCellValue(columnIndex, row, getFormattedTitel(programmTitelDTO.titel()), wb);
                    columnIndex = setCellValue(columnIndex, row, programmTitelDTO.titel().schwierigkeitsgrad(), wb);
                    columnIndex = setCellValue(columnIndex, row, getDuration(programmTitelDTO.titel().durationInSeconds()), wb);
                    columnIndex = setCellValue(columnIndex, row, programmTitelDTO.applausInSeconds(), wb);
                }
            }
        }
    }

    private String getFormattedTitel(TitelDTO titel) {
        if (titel.isValid()) {
            return "%s (%s)".formatted(titel.titelName(), titel.composer());
        }
        return "";
    }

    private Duration getDuration(Integer seconds) {
        if (seconds != null) {
            return Duration.ofSeconds(seconds);
        }
        return Duration.ZERO;
    }

    private void addModulHeader(Modul modul, XSSFRow headerRow, int maxTitel) {
        var columnIndex = 0;
        headerRow.createCell(columnIndex++).setCellValue("Vereinsname");
        switch (modul) {
            case A, B -> {
                headerRow.createCell(columnIndex++).setCellValue("Klasse");
                headerRow.createCell(columnIndex++).setCellValue("Besetzung");
                headerRow.createCell(columnIndex++).setCellValue("Programmtitel");
                headerRow.createCell(columnIndex++).setCellValue("Erläuterungen zum Selbstwahlprogramm");

                if (modul == B) {
                    headerRow.createCell(columnIndex++).setCellValue("PA wird benötigt");
                    headerRow.createCell(columnIndex++).setCellValue("E-Gitarre");
                    headerRow.createCell(columnIndex++).setCellValue("Bass");
                    headerRow.createCell(columnIndex++).setCellValue("Keyboard");
                    headerRow.createCell(columnIndex++).setCellValue("Gesang");
                }

                for (var i = 0; i < maxTitel; i++) {
                    headerRow.createCell(columnIndex++).setCellValue("Musikstück");
                    headerRow.createCell(columnIndex++).setCellValue("Grad");
                    headerRow.createCell(columnIndex++).setCellValue("Dauer Stück");
                    headerRow.createCell(columnIndex++).setCellValue("Dauer Applaus");
                    headerRow.createCell(columnIndex++).setCellValue("Info");
                }
            }
            case C -> {
                headerRow.createCell(columnIndex++).setCellValue("Kommentar");
                for (var i = 0; i < maxTitel; i++) {
                    headerRow.createCell(columnIndex++).setCellValue("Musikstück");
                }
            }
            case D -> {
                headerRow.createCell(columnIndex++).setCellValue("Besetzung");
                headerRow.createCell(columnIndex++).setCellValue("Komposition 1 (Schweizer Marsch)");
                headerRow.createCell(columnIndex++).setCellValue("Dauer");
                headerRow.createCell(columnIndex++).setCellValue("Komposition 2");
                headerRow.createCell(columnIndex).setCellValue("Dauer");
            }
            case E -> {
                for (var i = 0; i < maxTitel; i++) {
                    headerRow.createCell(columnIndex++).setCellValue("Musikstück");
                    headerRow.createCell(columnIndex++).setCellValue("Dauer");
                }
            }
            case F -> {
                // not used
            }
            case G -> {
                headerRow.createCell(columnIndex++).setCellValue("Kategorie A");
                headerRow.createCell(columnIndex++).setCellValue("Grundlage 1");
                headerRow.createCell(columnIndex++).setCellValue("Grundlage 2");
                headerRow.createCell(columnIndex++).setCellValue("Frei gewählter Baslermarsch (BM) oder Marsch (M)");
                headerRow.createCell(columnIndex++).setCellValue("Dauer");
                headerRow.createCell(columnIndex++).setCellValue("Frei gewählte Komposition");
                headerRow.createCell(columnIndex++).setCellValue("Dauer");
                headerRow.createCell(columnIndex++).setCellValue("Kategorie B");
                headerRow.createCell(columnIndex++).setCellValue("Komposition");
                headerRow.createCell(columnIndex++).setCellValue("Dauer");
                headerRow.createCell(columnIndex++).setCellValue("Kategorie C");
                headerRow.createCell(columnIndex++).setCellValue("Komposition");
                headerRow.createCell(columnIndex).setCellValue("Dauer");
            }
            case H -> {
                headerRow.createCell(columnIndex++).setCellValue("Klasse");
                for (var i = 0; i < maxTitel; i++) {
                    headerRow.createCell(columnIndex++).setCellValue("Musikstück");
                    headerRow.createCell(columnIndex++).setCellValue("Schierigkeitsgrad");
                    headerRow.createCell(columnIndex++).setCellValue("Dauer Stück");
                    headerRow.createCell(columnIndex++).setCellValue("Dauer Applaus");
                }
            }
        }
    }

    private void fillVereineSheet(XSSFWorkbook wb, List<VereinDTO> vereine) {
        var sheet = wb.createSheet();
        wb.setSheetName(0, "Vereine");

        var rowIndex = 0;
        // headers
        var headerRow = sheet.createRow(rowIndex++);

        var columnIndex = 0;
        headerRow.createCell(columnIndex++).setCellValue("Vereinsname");
        headerRow.createCell(columnIndex++).setCellValue("Email");
        headerRow.createCell(columnIndex++).setCellValue("Phase 1");
        headerRow.createCell(columnIndex++).setCellValue("Phase 2");
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

        for (var vereinDTO : vereine) {
            var row = sheet.createRow(rowIndex++);
            columnIndex = 0;

            columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.angaben().vereinsname());
            columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.email());
            columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.phase1Done());
            columnIndex = setCellValue(columnIndex, vereinDTO, row, dto -> dto.phase2Done());
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
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, columnIndex));
    }

    private void fillDoppeleinsatzSheet(XSSFWorkbook wb, List<VereinDTO> vereine) {
        var sheet = wb.createSheet();
        wb.setSheetName(1, "Doppeleinsätze");

        var rowIndex = 0;

        for (var vereinDTO : vereine) {
            if (!vereinDTO.doppelEinsatz().isEmpty()) {
                var row = sheet.createRow(rowIndex++);
                setCellValue(0, row, vereinDTO.angaben().vereinsname(), wb);

                for (var doppelEinsatzDTO : vereinDTO.doppelEinsatz()) {
                    row = sheet.createRow(rowIndex++);
                    setCellValue(1, row, doppelEinsatzDTO.otherVerein().name(), wb);
                    setCellValue(2, row, doppelEinsatzDTO.mitspielerName(), wb);
                }
            }

        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private <T> int setCellValue(int columnIndex, VereinDTO vereinDTO, XSSFRow row, Function<VereinDTO, T> getter) {
        return setCellValue(columnIndex, row, getter.apply(vereinDTO), null);
    }

    private <T> int setCellValue(int columnIndex, XSSFRow row, T value, XSSFWorkbook wb) {
        var cell = row.createCell(columnIndex++);
        if (value != null) {
            if (value instanceof String stringValue) {
                cell.setCellValue(stringValue);
            } else if (value instanceof Double numberValue) {
                cell.setCellValue(numberValue);
            } else if (value instanceof Integer integerValue) {
                cell.setCellValue(integerValue);
            } else if (value instanceof Float floatValue) {
                cell.setCellValue(floatValue);
            } else if (value instanceof Boolean booleanValue) {
                cell.setCellValue(booleanValue ? "x" : "");
            } else if (value instanceof Klasse klasseValue) {
                cell.setCellValue(klasseValue.getDescription());
            } else if (value instanceof Duration durationValue) {
                if (!durationValue.isZero()) {
                    var durationCellStyle = wb.createCellStyle();
                    var creationHelper = wb.getCreationHelper();
                    durationCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("mm:ss"));

                    var seconds = durationValue.getSeconds();
                    cell.setCellValue("%02d:%02d".formatted(seconds / 60, seconds % 60));
                    cell.setCellStyle(durationCellStyle);
                }
            } else if (value instanceof HasDescription hasDescriptionValue) {
                cell.setCellValue(hasDescriptionValue.getDescription());
            }
        }
        return columnIndex;
    }
}
