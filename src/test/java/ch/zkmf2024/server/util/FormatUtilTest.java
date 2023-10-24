package ch.zkmf2024.server.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class FormatUtilTest {

    @Test
    void formatDate() {
        assertThat(FormatUtil.formatDate(null)).isEmpty();
        assertThat(FormatUtil.formatDate(LocalDate.of(2023, 10, 23))).isEqualTo("23.10.2023");
        assertThat(FormatUtil.formatDate(LocalDate.of(2023, 10, 23), true)).isEqualTo("Mo., 23.10.2023");
    }

    @Test
    void formatLocalTime() {
        assertThat(FormatUtil.formatTime(null)).isEmpty();
        assertThat(FormatUtil.formatTime(LocalTime.of(10, 30))).isEqualTo("10:30");
        assertThat(FormatUtil.formatTime(LocalTime.of(18, 45))).isEqualTo("18:45");
    }

}
