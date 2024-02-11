package ch.zkmf2024.server.dto.geojson;

import java.util.List;

public record GeoJSON(String type, List<FeatureItem> features) {
}
