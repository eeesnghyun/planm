package com.app.planm.common.util;

public class CommonUtil {

	public static String null2Str(Object str, String strDefault) {
		if (str == null || str == "null" || "null".equals(str.toString()) || 
				"undefined".equals(str.toString()) || str.toString().length() == 0 ||
				"".equals(str.toString())) {
			return strDefault;
		} else {
			return str.toString();
		}
	}
}
