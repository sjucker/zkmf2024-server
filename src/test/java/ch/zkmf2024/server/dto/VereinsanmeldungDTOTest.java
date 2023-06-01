package ch.zkmf2024.server.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static ch.zkmf2024.server.dto.Klasse.KLASSE_1;
import static org.assertj.core.api.Assertions.assertThat;

class VereinsanmeldungDTOTest {

    @ParameterizedTest
    @MethodSource("isValidArguments")
    void isValid(VereinsanmeldungDTO dto, boolean expected) {
        assertThat(dto.isValid()).isEqualTo(expected);
    }

    private static Stream<Arguments> isValidArguments() {
        return Stream.of(
                Arguments.of(new VereinsanmeldungDTO(false, false, false, false, false, false, false, false, null, null, null, false, false, false, false, false, false, false, false), false),
                Arguments.of(new VereinsanmeldungDTO(false, false, false, false, false, false, false, false, null, null, null, false, false, false, true, false, false, false, false), false),
                Arguments.of(new VereinsanmeldungDTO(true, false, false, false, false, false, false, false, null, null, null, false, false, false, true, false, false, false, false), false),
                Arguments.of(new VereinsanmeldungDTO(true, false, false, false, false, false, false, false, KLASSE_1, null, null, false, false, false, true, false, false, false, false), true),
                Arguments.of(new VereinsanmeldungDTO(true, false, false, false, false, false, true, false, KLASSE_1, null, null, false, false, false, true, false, false, false, false), false),
                Arguments.of(new VereinsanmeldungDTO(true, false, false, false, false, false, true, false, KLASSE_1, null, null, false, false, false, true, false, false, true, false), false),
                Arguments.of(new VereinsanmeldungDTO(true, false, false, false, false, false, true, false, KLASSE_1, null, null, false, true, false, true, false, false, true, false), true)
        );
    }

}
