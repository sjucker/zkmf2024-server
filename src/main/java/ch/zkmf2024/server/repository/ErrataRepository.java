package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.admin.ErrataDTO;
import ch.zkmf2024.server.dto.admin.VereinErrataDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.ErrataDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.ErrataPojo;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

import static ch.zkmf2024.server.jooq.generated.Tables.ERRATA;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Repository
public class ErrataRepository {

    public static final Comparator<ErrataDTO> COMPARATOR = Comparator.comparing(ErrataDTO::modul)
                                                                     .thenComparing(ErrataDTO::klasse)
                                                                     .thenComparing(ErrataDTO::besetzung);

    private final DSLContext jooqDsl;
    private final ErrataDao errataDao;

    public ErrataRepository(DSLContext jooqDsl, DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.errataDao = new ErrataDao(jooqConfig);
    }

    public List<ErrataPojo> findAllPojos() {
        return errataDao.findAll();
    }

    public List<ErrataDTO> findAll() {
        return errataDao.findAll().stream()
                        .map(ErrataRepository::toDTO)
                        .toList();
    }

    public void update(ErrataPojo pojo) {
        errataDao.update(pojo);
    }

    public List<VereinErrataDTO> getRelevantErrata(Long vereinId) {
        return jooqDsl.select()
                      .from(ERRATA)
                      .join(VEREIN_PROGRAMM).on(VEREIN_PROGRAMM.MODUL.eq(ERRATA.MODUL),
                                                VEREIN_PROGRAMM.KLASSE.eq(ERRATA.KLASSE),
                                                VEREIN_PROGRAMM.BESETZUNG.eq(ERRATA.BESETZUNG))
                      .where(VEREIN_PROGRAMM.FK_VEREIN.eq(vereinId))
                      .fetch(ErrataRepository::toDTO)
                      .stream()
                      .filter(err -> isNotBlank(err.text()))
                      .sorted(COMPARATOR)
                      .map(it -> new VereinErrataDTO(it.description(), it.text()))
                      .toList();
    }

    private static ErrataDTO toDTO(Record it) {
        var modul = Modul.valueOf(it.get(ERRATA.MODUL));
        var klasse = Klasse.valueOf(it.get(ERRATA.KLASSE));
        var besetzung = Besetzung.valueOf(it.get(ERRATA.BESETZUNG));
        return new ErrataDTO(it.get(ERRATA.ID),
                             modul,
                             klasse,
                             besetzung,
                             getDescription(modul, klasse, besetzung),
                             it.get(ERRATA.TEXT));
    }

    private static ErrataDTO toDTO(ErrataPojo it) {
        var modul = Modul.valueOf(it.getModul());
        var klasse = Klasse.valueOf(it.getKlasse());
        var besetzung = Besetzung.valueOf(it.getBesetzung());
        return new ErrataDTO(it.getId(),
                             modul,
                             klasse,
                             besetzung,
                             getDescription(modul, klasse, besetzung),
                             it.getText());
    }

    private static String getDescription(Modul modul, Klasse klasse, Besetzung besetzung) {
        return new StringJoiner(", ").add(modul.getFullDescription())
                                     .add(klasse.getDescription())
                                     .add(besetzung.getDescription())
                                     .toString();
    }

}
