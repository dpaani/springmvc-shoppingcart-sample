package com.codetosalvation.shoppingcart.util;

import java.math.BigDecimal;


public final class NumberUtils {
	private static final int ROUND_DECIMAL_PLACE = 2;

	public static BigDecimal toBigDecimal(double input) {
        BigDecimal bd = new BigDecimal(input);
        bd = bd.setScale(ROUND_DECIMAL_PLACE,BigDecimal.ROUND_FLOOR);
        return bd;
	}

	public static double round(double input) {
        return (toBigDecimal(input).doubleValue());
    }

}
