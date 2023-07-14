package ch.zkmf2024.server.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidationUtilTest {

    @Test
    void isValidEmail() {
        assertThat(ValidationUtil.isValidEmail(null)).isFalse();
        assertThat(ValidationUtil.isValidEmail("")).isFalse();
        assertThat(ValidationUtil.isValidEmail("null")).isFalse();
        assertThat(ValidationUtil.isValidEmail("test.com")).isFalse();
        assertThat(ValidationUtil.isValidEmail("test@test.com")).isTrue();
    }

}
