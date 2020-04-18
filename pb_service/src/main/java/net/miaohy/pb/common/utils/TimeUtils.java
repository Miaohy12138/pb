package net.miaohy.pb.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeUtils {

	// 一天的毫秒数
	private static final long ONE_DAY_MILLS = 86400000L;

	// 一星期的毫秒数
	private static final long ONE_WEEK_MILLS = 7 * 86400000L;

	// 时区：CTT Asia/Shanghai GMT+08:00
	private static final String TIMEZONE_ID_CTT = "CTT";

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static Timestamp getMaxTimestamp() {
		return new Timestamp(Long.MAX_VALUE);
	}

	/**
	 * 获取当前UTC时间，springmvc启动时已经修改默认时区为UTC时间
	 *
	 * @return
	 */
	public static Timestamp getCurrentUtcTimestamp() {

		return new Timestamp(System.currentTimeMillis());

	}

	public static long getCurrentBeijingMillSeconds() {
		return System.currentTimeMillis() + 28800000;
	}

	/**
	 * 获取当前北京时间
	 *
	 * @return
	 */
	public static Timestamp getCurrentBeijingTimestamp() {

		return new Timestamp(System.currentTimeMillis() + 28800000);

	}


	/**
	 * 比较当前时间是否在是否在报名开始和结束之间
	 *
	 * @param currentUtcTimestamp 当前UTC时间
	 * @param signupStartTime     报名开始UTC时间
	 * @param signupEndTime       报名结束UTC时间
	 * @return
	 */
	public static boolean compareEventTime(Date currentUtcTimestamp, Date signupStartTime, Date signupEndTime) {

		if (currentUtcTimestamp.before(signupStartTime) || currentUtcTimestamp.after(signupEndTime)) {
			return false;
		}

		return true;

	}

	/**
	 * 当前时间的前一天时间
	 *
	 * @return
	 */
	public static Timestamp getBeforeOneDayTimestamp() {

		return new Timestamp(System.currentTimeMillis() - ONE_DAY_MILLS);
	}

	/**
	 * 当前时间的前七天时间
	 *
	 * @return
	 */
	public static Timestamp getBeforeOneWeekTimestamp() {

		return new Timestamp(System.currentTimeMillis() - ONE_WEEK_MILLS);
	}

	/**
	 * 时间小值
	 *
	 * @return
	 */
	public static Timestamp getlastTimestamp() {

		return new Timestamp(0);

	}

	/**
	 * 根据生日计算年龄
	 *
	 * @param birthday 出生日期
	 * @return
	 */
	public static int getAgeByBirthday(Date birthday) {

		Calendar calendar = Calendar.getInstance();
		// 如果生日为空或者在当前日期之后，返回0岁
		if (null == birthday || TimeUtils.getCurrentUtcTimestamp().before(birthday)) {
			return 0;
		}

		// 计算年龄只是单纯年份相减，没有精确到日期
		int yearNow = calendar.get(Calendar.YEAR);

		calendar.setTime(birthday);

		int yearBirth = calendar.get(Calendar.YEAR);

		return yearNow - yearBirth;

	}

	/**
	 * 计算两个日期的相隔天数
	 *
	 * @param updateTime 修改的日期
	 */
	public static long daysBetween(Date updateTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(TimeUtils.getCurrentUtcTimestamp());
		long time1 = cal.getTimeInMillis();
		Assert.isNull(updateTime,"修改的日期不能为空");
		cal.setTime(updateTime);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time1 - time2) / (1000 * 3600 * 24);

		return betweenDays;
	}

	public static Date getBeijingDayStartTime() {
		final TimeZone beijing = TimeZone.getTimeZone("GMT+08:00");
		Calendar todayStart = Calendar.getInstance(beijing);
		//todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return new Date(todayStart.getTimeInMillis());
	}

	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return new Date(todayStart.getTime().getTime());
	}

	public static Date getMonthStartTime() {
		Calendar monthStart = Calendar.getInstance();
		monthStart.set(Calendar.DAY_OF_MONTH, 1);
		monthStart.set(Calendar.HOUR, 0);
		monthStart.set(Calendar.MINUTE, 0);
		monthStart.set(Calendar.SECOND, 0);
		monthStart.set(Calendar.MILLISECOND, 0);
		return new Date(monthStart.getTime().getTime());
	}

	public static Date getYearStartTime() {
		Calendar monthStart = Calendar.getInstance();
		monthStart.set(Calendar.DAY_OF_YEAR, 0);
		monthStart.set(Calendar.HOUR, 0);
		monthStart.set(Calendar.MINUTE, 0);
		monthStart.set(Calendar.SECOND, 0);
		monthStart.set(Calendar.MILLISECOND, 0);
		return new Date(monthStart.getTime().getTime());
	}

	public static Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return new Date(todayEnd.getTime().getTime());
	}

	public static Date dayAdd(Date date, int dayOffset) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dayOffset);
		return calendar.getTime();
	}

	public static Date hourAdd(Date dateTime, int hourOffset) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateTime);
		calendar.add(Calendar.HOUR_OF_DAY, hourOffset);
		return calendar.getTime();
	}

	public static Date secondAdd(Date dateTime, int secondOffset) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateTime);
		calendar.add(Calendar.SECOND, secondOffset);
		return calendar.getTime();
	}

	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return null;
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/***
	 * 北京时间字符串转utc日期
	 * @param strTime
	 * @return
	 */
	public static Date formatUtcTime(String strTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(strTime);
			System.out.println(date);
		} catch (Exception e) {
			//
		}
		return new Date(date.getTime() - 28800000);
	}

	/**
	 * 转北京日期格式
	 *
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String formatDateTimeToCTT(Date dateTime, String pattern) {
		return formatDateTime(dateTime, TIMEZONE_ID_CTT, pattern);
	}

	public static String formatDateTimeToCTT(Date dateTime) {
		return formatDateTime(dateTime, TIMEZONE_ID_CTT, null);
	}

	/**
	 * 格式化日期
	 *
	 * @param dateTime 时间
	 * @param zoneId   时区ID
	 * @param pattern  格式
	 * @return
	 */
	public static String formatDateTime(Date dateTime, String zoneId, String pattern) {
		if (StringUtils.isEmpty(pattern))
			pattern = "yyyy-MM-dd HH:mm:ss";
		String date = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			if (StringUtils.isNotEmpty(zoneId))
				simpleDateFormat.setTimeZone(TimeZone.getTimeZone(zoneId));
			date = simpleDateFormat.format(dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/***
	 * utc时间转北京日期 2018-12-06 上午
	 * @param dateTime
	 * @return
	 */
	public static String formatUtcToDateAmPm(Date dateTime) {
		TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE_ID_CTT);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setTimeZone(timeZone);
		String date = null;
		try {
			Calendar calendar = Calendar.getInstance(timeZone);
			calendar.setTime(dateTime);
			date = simpleDateFormat.format(calendar.getTime());
			if (calendar.get(Calendar.AM_PM) == Calendar.AM)
				date += " 上午";
			else
				date += " 下午";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/***
	 * utc时间转北京日期 2018-12-06 周四 上午/下午/晚上
	 * @param dateTime
	 * @return
	 */
	public static String formatUtcToDateWeekAmPm(Date dateTime) {
		TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE_ID_CTT);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setTimeZone(timeZone);
		String date = null;
		try {
			Calendar calendar = Calendar.getInstance(timeZone);
			calendar.setTime(dateTime);
			date = simpleDateFormat.format(calendar.getTime());
			switch (calendar.get(Calendar.DAY_OF_WEEK)) {
				case Calendar.SUNDAY:
					date += " 周日";
					break;
				case Calendar.MONDAY:
					date += " 周一";
					break;
				case Calendar.TUESDAY:
					date += " 周二";
					break;
				case Calendar.WEDNESDAY:
					date += " 周三";
					break;
				case Calendar.THURSDAY:
					date += " 周四";
					break;
				case Calendar.FRIDAY:
					date += " 周五";
					break;
				case Calendar.SATURDAY:
					date += " 周六";
					break;
			}
			switch (calendar.get(Calendar.HOUR_OF_DAY)) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
				case 12:
					date += " 上午";
					break;
				case 13:
				case 14:
				case 15:
				case 16:
				case 17:
				case 18:
					date += " 下午";
					break;
				default:
					date += " 晚上";
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 校验时间非空
	 *
	 * @param dateTime
	 * @return
	 */
	public static boolean checkTimeNotEmpty(Date dateTime) {
		return null != dateTime && dateTime.getTime() > 0;
	}


	//一下为报表要用的通用函数

	public static Date addDate(Date dt, int type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		if (type == 1) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
		} else if (type == 7) {
			cal.add(Calendar.WEEK_OF_YEAR, 1);
		} else if (type == 30) {
			cal.add(Calendar.MONTH, 1);
		}
		return cal.getTime();
	}

	public static String NoConvertToDate(String no, int type) {
		int year = Integer.parseInt(no.substring(0, 4));
		int subNo = Integer.parseInt(no.substring(4, 6));
		int temp = 0;
		if (no.length() > 6) {
			temp = Integer.parseInt(no.substring(6));
		}

		if (type == 7) {
			return getStartDayOfWeekNo(year, subNo);
		} else if (type == 30) {
			return getStartDayOfMonthNo(year, subNo);
		} else {
			return String.valueOf(year) + "-" + String.valueOf(subNo) + "-" + String.valueOf(temp);
		}
	}

	public static String dateConverToString(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		//传进来是0时区，要转换为东八区的字符
		cal.add(Calendar.MILLISECOND, 8 * 60 * 60 * 1000);
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
				cal.get(Calendar.DAY_OF_MONTH);
	}

	public static Date getStartDayOfWeekNo(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	public static String getStartDayOfWeekNo(int year, int weekNo) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		long x = cal.getTime().getTime();
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
				cal.get(Calendar.DAY_OF_MONTH);

	}

	public static Date getStartDayOfMonthNo(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();

	}

	public static String getStartDayOfMonthNo(int year, int monthNo) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, monthNo);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
				cal.get(Calendar.DAY_OF_MONTH);

	}
	// 报表要用的通用函数 结束

	public static Date trimTimeToNull(Date dateTime) {
		return checkTimeNotEmpty(dateTime) ? dateTime : null;
	}

}
