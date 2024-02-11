package ch.zkmf2024.server.dto.geojson;

import java.util.Map;

public record FeatureItem(String type, Geometry geometry, Map<String, String> properties) {
}
