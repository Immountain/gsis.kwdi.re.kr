package infomind.com.utils;

import org.apache.commons.lang.math.NumberUtils;

import java.util.Map;

public class StringUtil {
	
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

	public static String nvl(Object obj) {
		return nvl(obj, "");
	}

	public static String nvl(Object obj, String ifNull) {
		return ((obj != null) ? obj.toString() : ifNull);
	}

	public static String getMapValue(Map<String, Object> map, String key) {
		String value = "";
		if (map != null) {
			value = (String) map.get(key);
		}
		return nvl(value);
	}

	public static boolean isNumeric(String s) {
		return NumberUtils.isNumber(s);
	}

	public static byte[] getByteArrayFromHex(String hex) {
		if ((hex == null) || (hex.length() == 0)) {
			return null;
		}
		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; ++i) {
			ba[i] = (byte) Integer
					.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	public static String getPhoneNumberHyphenAdd(String num, String mask) {

		String formatNum = "";
		if (nvl(num).equals("")) return formatNum;
		num = num.replaceAll("-","");

		if (num.length() == 11) {
			if (mask.equals("Y")) {
				formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
			}else{
				formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
			}
		}else if(num.length()==8){
			formatNum = num.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
		}else{
			if(num.indexOf("02")==0){
				if(mask.equals("Y")){
					formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-****-$3");
				}else{
					formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
				}
			}else{
				if(mask.equals("Y")){
					formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
				}else{
					formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
				}
			}
		}
		return formatNum;
	}


}
