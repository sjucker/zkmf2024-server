package ch.zkmf2024.server.util;

import ch.zkmf2024.server.dto.CoordinatesDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

class DistanceUtilTest {

    @Test
    void calculateDistanceBetweenPointsInMeters() {
        var pointA = new CoordinatesDTO(new BigDecimal("47.396504093984"), new BigDecimal("8.445715444734"));
        var pointB = new CoordinatesDTO(new BigDecimal("47.395782297746"), new BigDecimal("8.446502565075"));

        assertThat(DistanceUtil.calculateDistanceBetweenPointsInMeters(pointA, pointB)).isCloseTo(new BigDecimal("99.76"), offset(new BigDecimal("0.005")));
    }
}
