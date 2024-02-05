package ch.zkmf2024.server.service;

import ch.zkmf2024.server.util.ValidationUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidationUtilTest {

    @Test
    void isValidEmail() {
        assertThat(ValidationUtil.isValidEmail(null)).isFalse();
        assertThat(ValidationUtil.isValidEmail("null")).isFalse();
        assertThat(ValidationUtil.isValidEmail("")).isFalse();
        assertThat(ValidationUtil.isValidEmail("@")).isFalse();
        assertThat(ValidationUtil.isValidEmail(" @ ")).isFalse();
        assertThat(ValidationUtil.isValidEmail("@.com")).isFalse();
        assertThat(ValidationUtil.isValidEmail("@test.com")).isFalse();
        assertThat(ValidationUtil.isValidEmail("test.com")).isFalse();
        assertThat(ValidationUtil.isValidEmail("test test@test.com")).isFalse();
        assertThat(ValidationUtil.isValidEmail("test@test.com")).isTrue();
        assertThat(ValidationUtil.isValidEmail("test-test@test.com")).isTrue();
        assertThat(ValidationUtil.isValidEmail("test.test@test.com")).isTrue();
        assertThat(ValidationUtil.isValidEmail("test.test+test@test.com")).isTrue();
    }

    @Test
    void isPositive() {
        assertThat(ValidationUtil.isPositive(null)).isFalse();
        assertThat(ValidationUtil.isPositive(0)).isFalse();
        assertThat(ValidationUtil.isPositive(Integer.MIN_VALUE)).isFalse();
        assertThat(ValidationUtil.isPositive(-1)).isFalse();
        assertThat(ValidationUtil.isPositive(1)).isTrue();
        assertThat(ValidationUtil.isPositive(Integer.MAX_VALUE)).isTrue();
    }

}
