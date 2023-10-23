package ch.zkmf2024.server.repository;

import ch.zkmf2024.server.AbstractIntegrationTest;
import ch.zkmf2024.server.jooq.generated.tables.pojos.HelperRegistrationPojo;
import ch.zkmf2024.server.util.DateUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static ch.zkmf2024.server.dto.Aufgaben.EGAL;
import static ch.zkmf2024.server.dto.Einsatzzeit.MITTAG;
import static org.assertj.core.api.Assertions.assertThat;

class HelperRegistrationRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private HelperRegistrationRepository repository;

    @BeforeEach
    void setUp() {
        var pojo = new HelperRegistrationPojo();
        pojo.setVorname("Helfer");
        pojo.setName("McHelp");
        pojo.setEmail("helfer@zkmf2024.ch");
        pojo.setTelefon("044 000 00 00");
        pojo.setAdresse("Bahnhofstrasse 1");
        pojo.setPlzOrt("8000 Zürich");
        pojo.setGeburtsdatum(LocalDate.of(2000, 1, 1));
        pojo.setVereinszugehoerigkeit("MV Urdorf");
        pojo.setAufgaben(EGAL.name());
        pojo.setAnzahlEinsaetze("4");
        pojo.setEinsatzMittwoch(MITTAG.name());
        pojo.setEinsatzDonnerstag(MITTAG.name());
        pojo.setEinsatzFreitag(MITTAG.name());
        pojo.setEinsatzSamstag(MITTAG.name());
        pojo.setEinsatzSonntag("");
        pojo.setEinsatzMontag("");
        pojo.setEinsatzDienstag("");
        pojo.setGroesseShirt("XL");
        pojo.setRegisteredAt(DateUtil.now());
        repository.insert(new HelperRegistrationPojo(pojo));
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findAll() {
        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    void existsByEmail() {
        assertThat(repository.existsByEmail("helper@zkmf2024.ch")).isFalse();
        assertThat(repository.existsByEmail("helfer@zkmf2024.ch")).isTrue();
    }
}
