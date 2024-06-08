package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.JudgeReportModulCategory;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import ch.zkmf2024.server.dto.RankingListDTO;
import ch.zkmf2024.server.dto.RankingListEntryDTO;
import ch.zkmf2024.server.dto.RankingStatus;
import ch.zkmf2024.server.dto.admin.RankingSummaryDTO;
import ch.zkmf2024.server.jooq.generated.tables.daos.RankingDao;
import ch.zkmf2024.server.jooq.generated.tables.daos.RankingEntryDao;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingEntryPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.RankingPojo;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Predicate;

import static ch.zkmf2024.server.dto.RankingStatus.PENDING;
import static ch.zkmf2024.server.jooq.generated.Tables.LOCATION;
import static ch.zkmf2024.server.jooq.generated.Tables.RANKING;
import static ch.zkmf2024.server.jooq.generated.Tables.RANKING_ENTRY;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN;
import static ch.zkmf2024.server.jooq.generated.Tables.VEREIN_PROGRAMM;

@Repository
public class RankingRepository {

    private final DSLContext jooqDsl;
    private final RankingDao rankingDao;
    private final RankingEntryDao rankingEntryDao;

    public RankingRepository(DSLContext jooqDsl,
                             DefaultConfiguration jooqConfig) {
        this.jooqDsl = jooqDsl;
        this.rankingDao = new RankingDao(jooqConfig);
        this.rankingEntryDao = new RankingEntryDao(jooqConfig);
    }

    public void insert(RankingPojo pojo) {
        rankingDao.insert(pojo);
    }

    public void insert(RankingEntryPojo pojo) {
        rankingEntryDao.insert(pojo);
    }

    public Optional<RankingPojo> findById(Long id) {
        return rankingDao.findOptionalById(id);
    }

    public Optional<RankingPojo> find(String modul, String klasse, String besetzung, JudgeReportModulCategory category, Long locationId) {
        return jooqDsl.selectFrom(RANKING)
                      .where(RANKING.MODUL.eq(modul),
                             (klasse == null ? RANKING.KLASSE.isNull() : RANKING.KLASSE.eq(klasse)),
                             (besetzung == null ? RANKING.BESETZUNG.isNull() : RANKING.BESETZUNG.eq(besetzung)),
                             (category == null ? RANKING.CATEGORY.isNull() : RANKING.CATEGORY.eq(category.name())),
                             RANKING.FK_LOCATION.eq(locationId))
                      .fetchOptionalInto(RankingPojo.class);
    }

    public List<RankingEntryPojo> findAllByRankingId(Long rankingId) {
        return rankingEntryDao.fetchByFkRanking(rankingId);
    }

    public void update(RankingPojo ranking) {
        rankingDao.update(ranking);
    }

    public void update(RankingEntryPojo rankingEntry) {
        rankingEntryDao.update(rankingEntry);
    }

    public List<RankingListDTO> getAllRankingLists() {
        return getAllRankingLists(dto -> true);
    }

    public List<RankingListDTO> getAllRankingLists(Predicate<RankingListDTO> predicate) {
        return jooqDsl.select()
                      .from(RANKING)
                      .join(LOCATION).on(LOCATION.ID.eq(RANKING.FK_LOCATION))
                      .orderBy(RANKING.MODUL, RANKING.KLASSE.nullsLast(), RANKING.BESETZUNG.nullsLast(), RANKING.CATEGORY.nullsLast())
                      .fetch(it -> toDTO(it, false))
                      .stream()
                      .filter(predicate)
                      .toList();
    }

    private RankingListDTO toDTO(Record it) {
        return toDTO(it, true);
    }

    private RankingListDTO toDTO(Record it, boolean loadEntries) {
        var modul = Modul.valueOf(it.get(RANKING.MODUL));
        var klasse = Klasse.fromString(it.get(RANKING.KLASSE));
        var besetzung = Besetzung.fromString(it.get(RANKING.BESETZUNG));
        var modulCategory = JudgeReportModulCategory.fromString(it.get(RANKING.CATEGORY));
        return new RankingListDTO(
                it.get(RANKING.ID),
                modul,
                modul.getFullDescription(),
                klasse.orElse(null),
                klasse.map(Klasse::getDescription).orElse(null),
                besetzung.orElse(null),
                besetzung.map(Besetzung::getDescription).orElse(null),
                modulCategory.orElse(null),
                modulCategory.map(JudgeReportModulCategory::getDescription).orElse(null),
                LocationRepository.toDTO(it),
                getDescription(it),
                RankingStatus.valueOf(it.get(RANKING.STATUS)),
                loadEntries ? getEntries(it.get(RANKING.ID)) : List.of()
        );
    }

    private String getDescription(Record it) {
        var joiner = new StringJoiner(", ");
        var modul = Modul.valueOf(it.get(RANKING.MODUL)).getFullDescription();
        if (it.get(RANKING.CATEGORY) != null) {
            modul += " (%s)".formatted(JudgeReportModulCategory.valueOf(it.get(RANKING.CATEGORY)).getDescription());
        }
        joiner.add(modul);

        if (it.get(RANKING.KLASSE) != null) {
            joiner.add(Klasse.valueOf(it.get(RANKING.KLASSE)).getDescription());
        }

        if (it.get(RANKING.BESETZUNG) != null) {
            joiner.add(Besetzung.valueOf(it.get(RANKING.BESETZUNG)).getDescription());
        }

        return "%s (%s)".formatted(joiner.toString(),
                                   it.get(LOCATION.NAME));
    }

    private List<RankingListEntryDTO> getEntries(Long rankingId) {
        return jooqDsl.select()
                      .from(RANKING_ENTRY)
                      .join(VEREIN).on(VEREIN.ID.eq(RANKING_ENTRY.FK_VEREIN))
                      .where(RANKING_ENTRY.FK_RANKING.eq(rankingId))
                      .orderBy(RANKING_ENTRY.RANK)
                      .fetch(it -> new RankingListEntryDTO(it.get(RANKING_ENTRY.RANK),
                                                           it.get(VEREIN.VEREINSNAME),
                                                           it.get(RANKING_ENTRY.SCORE),
                                                           it.get(RANKING_ENTRY.ADDITIONAL_INFO)));
    }

    public Set<ConfirmedScoreIdentifier> getConfirmedScores() {
        return jooqDsl.select()
                      .from(RANKING)
                      .join(RANKING_ENTRY).on(RANKING_ENTRY.FK_RANKING.eq(RANKING.ID))
                      .fetchSet(it -> new ConfirmedScoreIdentifier(Modul.valueOf(it.get(RANKING.MODUL)),
                                                                   Klasse.fromString(it.get(RANKING.KLASSE)).orElse(null),
                                                                   Besetzung.fromString(it.get(RANKING.BESETZUNG)).orElse(null),
                                                                   JudgeReportModulCategory.fromString(it.get(RANKING.CATEGORY)).orElse(null),
                                                                   it.get(RANKING.FK_LOCATION),
                                                                   it.get(RANKING_ENTRY.FK_VEREIN)));
    }

    public boolean hasPublishedRankings() {
        return jooqDsl.fetchExists(RANKING, RANKING.STATUS.ne(PENDING.name()));
    }

    public Optional<RankingListDTO> findRankingListById(Long rankingId) {
        return jooqDsl.select()
                      .from(RANKING)
                      .join(LOCATION).on(LOCATION.ID.eq(RANKING.FK_LOCATION))
                      .where(RANKING.ID.eq(rankingId),
                             RANKING.STATUS.ne(PENDING.name()))
                      .fetchOptional(this::toDTO);
    }

    public List<RankingListDTO> findRankingsByVerein(String vereinIdentifier) {
        return jooqDsl.select()
                      .from(RANKING)
                      .join(LOCATION).on(LOCATION.ID.eq(RANKING.FK_LOCATION))
                      .join(RANKING_ENTRY).on(RANKING_ENTRY.FK_RANKING.eq(RANKING.ID))
                      .join(VEREIN).on(VEREIN.ID.eq(RANKING_ENTRY.FK_VEREIN))
                      .where(RANKING.STATUS.ne(PENDING.name()),
                             VEREIN.IDENTIFIER.equalIgnoreCase(vereinIdentifier))
                      .fetch(it -> toDTO(it, false));
    }

    public List<RankingSummaryDTO> getAllRankingsPerVerein() {
        return jooqDsl.select()
                      .from(VEREIN_PROGRAMM)
                      .join(VEREIN).on(VEREIN.ID.eq(VEREIN_PROGRAMM.FK_VEREIN))
                      .leftJoin(RANKING).on(RANKING.MODUL.eq(VEREIN_PROGRAMM.MODUL),
                                            RANKING.KLASSE.eq(VEREIN_PROGRAMM.KLASSE),
                                            RANKING.BESETZUNG.eq(VEREIN_PROGRAMM.BESETZUNG))
                      .join(RANKING_ENTRY).on(RANKING_ENTRY.FK_RANKING.eq(RANKING.ID))
                      .fetch(it -> new RankingSummaryDTO(it.get(VEREIN.VEREINSNAME),
                                                         // TODO
                                                         "TODO", List.of()));

    }

    public record ConfirmedScoreIdentifier(Modul modul, Klasse klasse, Besetzung besetzung, JudgeReportModulCategory category, Long locationId, Long vereinId) {
    }
}
