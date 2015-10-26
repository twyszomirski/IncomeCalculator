package pl.twyszomirski.util;

import java.math.BigDecimal;

/**
 * Created by Tomasz
 * Utility class for math operations
 */
public class MathUtils {

    /**
     * Rounds a given BigDecimal to two places after decimal point
     * @param toBeRounded
     * @return
     */
    public static BigDecimal roundTwoPlaces(BigDecimal toBeRounded){
        return toBeRounded.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
