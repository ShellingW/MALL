package com.dayuanit.util;

import java.math.BigDecimal;

public class PriceUtil {
	
	public static String mul(String num1, String num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
	}
	
	public static String div(String num1, String num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN).toString();
	}
	
	
	public static void main(String[] args) {
		String result = PriceUtil.mul("1.99888", "1");
		result = PriceUtil.div("1.99888", "2.9999");
		System.out.println(result);
	}

}
