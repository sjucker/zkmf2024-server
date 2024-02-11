package ch.zkmf2024.server.dto.geojson;

import java.math.BigDecimal;
import java.util.List;

public record Geometry(String type, List<BigDecimal> coordinates) {
}
