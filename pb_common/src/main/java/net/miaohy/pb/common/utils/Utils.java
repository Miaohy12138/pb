
package net.miaohy.pb.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常用工具类
 * 
 * @author Jerry Cheng 
 * @date Dec 13, 2019   
 * @version
 */
public final class Utils {

	// The static logger
	protected static Logger logger = LoggerFactory.getLogger(Utils.class);

	/**
	 * Returns system property value
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getStringSystemPropertyValue(String key, String defaultValue) {
		String rel = null;

		try {
			if (!Utils.isEmpty(key)) {
				rel = System.getProperty(key);
			}
		} catch (Exception e) {
		}

		if (Utils.isEmpty(rel)) {
			rel = defaultValue;
		}

		return rel;
	}

	/**
	 * Returns string property value. Get it by System.getProperty first, then
	 * get it from properties.
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getStringSystemPropertyValue(Properties props, String key, String defaultValue) {
		String rel = null;

		try {
			if (!Utils.isEmpty(key)) {
				rel = System.getProperty(key);
			}
		} catch (Exception e) {
		}

		if (Utils.isEmpty(rel)) {
			rel = getStringPropertyValue(props, key, defaultValue);
		}

		return rel;
	}

	/**
	 * Returns boolean property value. Get it by System.getProperty first, then
	 * get it from properties.
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanSystemPropertyValue(Properties props, String key, boolean defaultValue) {
		String rel = null;
		try {
			if (!Utils.isEmpty(key)) {
				rel = System.getProperty(key);
			}
		} catch (Exception e) {
		}

		if (rel == null) {
			rel = (props != null) ? props.getProperty(key) : null;
		}

		if (!isEmpty(rel)) {
			return Boolean.valueOf(rel.trim());
		}

		return defaultValue;
	}

	/**
	 * Returns int property value. Get it by System.getProperty first, then get
	 * it from properties.
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getIntSystemPropertyValue(Properties props, String key, int defaultValue) {
		String rel = null;
		try {
			if (!Utils.isEmpty(key)) {
				rel = System.getProperty(key);
			}
		} catch (Exception e) {
		}

		if (rel == null) {
			rel = (props != null) ? props.getProperty(key) : null;
		}

		int intVal = -1;
		try {
			if (rel != null) {
				intVal = Integer.parseInt(rel.trim());
			}
		} catch (Exception e) {
		}

		return (intVal < 0) ? defaultValue : intVal;
	}

	/**
	 * Returns string property value.
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getStringPropertyValue(Properties props, String key, String defaultValue) {
		String rel = (props != null) ? props.getProperty(key, defaultValue) : null;
		if (Utils.isEmpty(rel)) {
			rel = defaultValue;
		}

		return rel;
	}

	/**
	 * Returns int property value.
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getIntPropertyValue(Properties props, String key, int defaultValue) {
		int rel = 0;
		try {
			if (props != null) {
				rel = Integer.valueOf(props.getProperty(key));
			}
		} catch (Exception e) {
		}

		if (rel <= 0) {
			rel = defaultValue;
		}

		return rel;
	}

	/**
	 * Returns boolean property value.
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanPropertyValue(Properties props, String key, boolean defaultValue) {
		String rel = (props != null) ? props.getProperty(key) : null;
		if (!isEmpty(rel)) {
			return Boolean.valueOf(rel);
		}

		return defaultValue;
	}

	/**
	 * Returns string option value.
	 * 
	 * @param options
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getStringOptionValue(Map<String, String> options, String key, String defaultValue) {
		String rel = (options != null) ? options.get(key) : null;
		if (rel == null) {
			rel = defaultValue;
		}

		return rel;
	}

	/**
	 * Returns int option value.
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getIntOptionValue(Map<String, String> options, String key, int defaultValue) {
		int rel = 0;
		try {
			if (options != null) {
				rel = Integer.valueOf(options.get(key));
			}
		} catch (Exception e) {
		}

		if (rel <= 0) {
			rel = defaultValue;
		}

		return rel;
	}

	/**
	 * Returns long option value.
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static long getLongOptionValue(Map<String, String> options, String key, long defaultValue) {
		long rel = 0;
		try {
			if (options != null) {
				rel = Long.valueOf(options.get(key));
			}
		} catch (Exception e) {
		}

		if (rel <= 0) {
			rel = defaultValue;
		}

		return rel;
	}

	/**
	 * Returns boolean option value.
	 * 
	 * @param options
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanOptionValue(Map<String, String> options, String key, boolean defaultValue) {
		String rel = (options != null) ? options.get(key) : null;
		if (!isEmpty(rel)) {
			return Boolean.valueOf(rel);
		}

		return defaultValue;
	}

	/**
	 * Whether is null or blank string.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() <= 0;
	}

	/**
	 * Whether is not empty.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	/**
	 * Clear spaces.
	 * 
	 * @param s
	 * @return
	 */
	public static String clearSpaces(String s) {
		if (s != null) {
			Pattern p = Pattern.compile("\\s+");
			Matcher m = p.matcher(s);

			return m.replaceAll(" ").trim();
		}

		return s;
	}

	/**
	 * Returns local host name.
	 * 
	 * @return
	 */
	public static String getHostName() {
		if (System.getenv("COMPUTERNAME") != null) {
			return System.getenv("COMPUTERNAME");
		} else {
			try {
				return (InetAddress.getLocalHost()).getHostName();
			} catch (UnknownHostException uhe) {
				// host = "hostname: hostname"
				String host = uhe.getMessage();
				if (host != null) {
					int colon = host.indexOf(':');
					if (colon > 0) {
						return host.substring(0, colon);
					}
				}
			}
		}

		return null;
	}

	/**
	 * Prepare JSON String.
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static String prepareJSONString(String jsonStr) {
		String dest = null;
		if (jsonStr != null) {
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(jsonStr);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * Upper the first char.
	 * 
	 * @param str
	 * @return
	 */
	public static String captureString(String str) {
		if (isEmpty(str)) {
			return str;
		}

		str = str.trim().toLowerCase();
		char[] cs = str.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	/**
	 * Returns string option value.
	 * 
	 * @param options
	 * @param key
	 * @return
	 */
	public static String getStringOptionValue(Map<?, ?> options, Object key) {
		Object rel = (options != null) ? options.get(key) : null;
		return (rel != null) ? rel.toString() : null;
	}

	/**
	 * Get current age from cert no.
	 * 
	 * @param cardCode
	 * @return
	 */
	public static int getAgeFromCertNo(String certNo) {
		if (isEmpty(certNo)) {
			return -1;
		}

		int len = certNo.length();
		int birthYearSpan = -1;

		// if old card, the length is 15
		if (len == 18) {
			birthYearSpan = 4;
		} else if (len == 15) {
			birthYearSpan = 2;
		} else {
			return -1;
		}

		// Parse year/month/day
		String year = (birthYearSpan == 2 ? "19" : "") + certNo.substring(6, 6 + birthYearSpan);
		String month = certNo.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
		String day = certNo.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);

		Calendar certCal = Calendar.getInstance();
		Calendar currentCal = Calendar.getInstance();
		certCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

		// Get base year age
		int yearAge = currentCal.get(Calendar.YEAR) - certCal.get(Calendar.YEAR);

		// If current calendar is before cert calendar, subtract one.
		certCal.set(currentCal.get(Calendar.YEAR), Integer.parseInt(month) - 1, Integer.parseInt(day));
		int subVal = (currentCal.before(certCal) ? 1 : 0);

		return yearAge - subVal;
	}
}
