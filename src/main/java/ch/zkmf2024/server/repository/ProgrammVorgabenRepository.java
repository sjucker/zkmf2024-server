package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static ch.zkmf2024.server.jooq.generated.Tables.PROGRAMM_VORGABEN;

@Repository
public class ProgrammVorgabenRepository {

    private final DSLContext jooqDsl;

    public ProgrammVorgabenRepository(DSLContext jooqDsl) {
        this.jooqDsl = jooqDsl;
    }

    public Optional<MinMaxDuration> findMinMaxDuration(Modul modul, Klasse klasse, Besetzung besetzung) {
        if (klasse != null && besetzung != null) {
            return jooqDsl.selectFrom(PROGRAMM_VORGABEN)
                          .where(
                                  PROGRAMM_VORGABEN.MODUL.eq(modul.name()),
                                  PROGRAMM_VORGABEN.KLASSE.eq(klasse.name()),
                                  PROGRAMM_VORGABEN.BESETZUNG.eq(besetzung.name())
                          )
                          .fetchOptional()
                          .map(it -> new MinMaxDuration(it.get(PROGRAMM_VORGABEN.MIN_DURATION_IN_SECONDS),
                                                        it.get(PROGRAMM_VORGABEN.MAX_DURATION_IN_SECONDS)));
        } else {
            return switch (modul) {
                case C -> Optional.of(new MinMaxDuration(30 * 60, 45 * 60));
                case E, F -> Optional.of(new MinMaxDuration(8 * 60, 10 * 60));
                default -> Optional.empty();
            };
        }

    }

    public record MinMaxDuration(Integer minDurationInSeconds, Integer maxDurationInSeconds) {
    }

}
