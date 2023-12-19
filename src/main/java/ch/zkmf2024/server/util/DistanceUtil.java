package ch.zkmf2024.server.util;

import ch.zkmf2024.server.dto.CoordinatesDTO;

import java.math.BigDecimal;

import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

public final class DistanceUtil {

    private static final double RADIUS_OF_EARTH_IN_KM = 6371;

    private DistanceUtil() {
    }

    public static BigDecimal calculateDistanceBetweenPointsInMeters(CoordinatesDTO pointA, CoordinatesDTO pointB) {
        var longitudeA = toRadians(pointA.longitude().doubleValue());
        var longitudeB = toRadians(pointB.longitude().doubleValue());
        var latitudeA = toRadians(pointA.latitude().doubleValue());
        var latitudeB = toRadians(pointB.latitude().doubleValue());

        // Haversine formula
        double deltaLongitude = longitudeB - longitudeA;
        double deltaLatitude = latitudeB - latitudeA;
        double a = pow(sin(deltaLatitude / 2), 2)
                + (cos(latitudeA) * cos(latitudeB))
                * pow(sin(deltaLongitude / 2), 2);

        double c = 2 * asin(sqrt(a));

        return BigDecimal.valueOf(c * RADIUS_OF_EARTH_IN_KM * 1000);
    }
}
