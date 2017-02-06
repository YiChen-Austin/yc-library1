package com.mall.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.mall.common.constant.CommonConstant;

/**
 * @功能说明：日期处理工具类
 * @作者： 许可
 * @创建日期： 2010-6-8 @
 */
public class DateUtil {
	/**
	 * 将给定应用服务器日期按照给定格式化类型转换成字符串
	 * 
	 * @param date
	 *            -java日期对象
	 * @param format
	 *            -日期格式化类型
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(Date date, String format) {
		if (BaseUtil.isEmpty(date))
			date = DateUtil.getGMTDate();
		if (BaseUtil.isEmpty(format))
			format = CommonConstant.DATE_WITHMILLISECOND_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * @Description(功能描述) : 转换成(yyyy-MM-dd)
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月30日 上午9:58:53
	 * @param date
	 *            : 日期
	 * @return
	 */
	public static String dateToStringYYYYMMHH(Date date) {
		if (BaseUtil.isEmpty(date))
			date = DateUtil.getGMTDate();
		SimpleDateFormat sdf = new SimpleDateFormat(
				CommonConstant.DATE_SHORT_FORMAT);
		return sdf.format(date);
	}

	/**
	 * @Description(功能描述) : 转换成(yyyy-MM-dd)
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月30日 上午9:58:53
	 * @param date
	 *            : 日期
	 * @return
	 */
	public static String dateToStringYYYYMMHH(long millis) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				CommonConstant.DATE_SHORT_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 将给定应用服务器日期按照默认格式化类型转换成字符串
	 * 
	 * @param date
	 *            -java日期对象
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(Date date) {
		return dateToString(date, CommonConstant.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 将应用服务器当前日期按照给定格式化类型转换成字符串
	 * 
	 * @param format
	 *            -日期格式化类型
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(String format) {
		return dateToString(DateUtil.getGMTDate(), format);
	}

	/**
	 * 将应用服务器日期按照默认格式化类型转换成字符串
	 * 
	 * @return String -返回转换后的字符串
	 */

	public static String dateToString() {
		return dateToString(DateUtil.getGMTDate(),
				CommonConstant.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 获取GMT时间，避免8小时时差
	 * 
	 * @return String -返回时间
	 */

	public static Date getGMTDate() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getDefault());
		return c.getTime();
	}

	/**
	 * 将字符串转换成日期 注意：一定要选用匹配的格式，否则不能解析，将返回null
	 * 
	 * @param strDate
	 *            - 日期
	 * @param format
	 *            - 格式
	 * @return Date -转换后的日期
	 */
	public static Date stringToDate(String strDate, String format) {
		if (BaseUtil.isEmpty(strDate))
			return null;
		if (BaseUtil.isEmpty(format))
			format = CommonConstant.DATE_SHORT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将字符串转换成日期，默认格式：yyyy-MM-dd
	 * 
	 * @param strDate
	 *            - 日期
	 * @return Date -转换后的日期
	 */
	public static Date stringToDate(String strDate) {
		if (BaseUtil.isEmpty(strDate))
			return null;
		return stringToDate(strDate, CommonConstant.DATE_SHORT_FORMAT);
	}

	/**
	 * 获取当前日期（没转化格式）
	 * 
	 * @return Date -转换后的日期
	 */
	public static Date getCurrentDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * getCurrentTime与getLastYearTime组合使用，作为时间限制条件，用于默认返回到当前一年内的数据。
	 * 
	 */
	/**
	 * 获取当前时间，精确到秒（没转化格式）
	 * 
	 * @return Date -转换后的日期
	 */
	public static Date getCurrentTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取上一年时间，精确到秒（没转化格式）
	 * 
	 * @return Date -转换后的日期
	 */
	public static Date getLastYearTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 设置开始日期时分秒格式 00:00:00
	 * 
	 * @param date
	 *            -日期
	 * @return Date -转换后的日期
	 */
	public static Date setSatrtDate(Date date) {
		if (BaseUtil.isEmpty(date))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 设置结束日期时分秒格式 23:59:59
	 * 
	 * @param date
	 *            -日期
	 * @return Date -转换后的日期
	 */
	public static Date setEndDate(Date date) {
		if (BaseUtil.isEmpty(date))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/**
	 * 获取当前年份-长
	 * 
	 * @return String -当前年份
	 */
	public static String getCurrentLongYear() {
		Calendar c = Calendar.getInstance();
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year;
	}

	/**
	 * 获取当前年份-短
	 * 
	 * @return String -当前年份
	 */
	public static String getCurrentYear() {
		Calendar c = Calendar.getInstance();
		String year = Integer.toString(c.get(Calendar.YEAR));
		year = year.substring(2);
		return year;
	}

	/**
	 * 获取当前月份
	 * 
	 * @return String -当天月份
	 */
	public static String getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		String month = Integer.toString(c.get(Calendar.MONTH) + 1);
		return month;
	}

	public static String getCurrentMonth2() {
		String month = getCurrentMonth();
		return month.length() < 2 ? "0" + month : month;
	}

	public static String getYearMonth(int offset) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + offset);
		return dateToString(c.getTime(), CommonConstant.DATE_YEAR_MONTH);
	}

	/**
	 * 获取当天日期
	 * 
	 * @return String -日期
	 */
	public static String getCurrentDay() {
		Calendar c = Calendar.getInstance();
		String date = Integer.toString(c.get(Calendar.DATE));
		if (date.length() < 2)
			date = "0" + date;
		return date;
	}

	/**
	 * 获取指定时间的前一天的指定类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getBeforeDay(String date, String format) {
		if (BaseUtil.isEmpty(date))
			return null;
		if (BaseUtil.isEmpty(format))
			format = CommonConstant.DATE_SHORT_FORMAT;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(stringToDate(date));
		c.add(Calendar.DATE, -1);
		SimpleDateFormat myFormatter = new SimpleDateFormat(format);
		return myFormatter.format(c.getTime());
	}

	/**
	 * 获取指定日期偏移delayDays后的日期
	 * 
	 * @param startDate
	 *            开始日期
	 * @param delayDays
	 *            -延迟的天数
	 * @return Date -转换后的日期
	 */
	public static Date getDateAfterDays(Date startDate, int delayDays) {
		if (BaseUtil.isEmpty(startDate))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DAY_OF_MONTH, delayDays);
		return c.getTime();
	}

	/**
	 * 获取指定时间的前一天的默认类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getBeforeDay(String date) {
		return getBeforeDay(date, CommonConstant.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 获取指定时间的后一天的指定类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getAfterDay(String date, String format) {
		if (BaseUtil.isEmpty(date))
			return null;
		if (BaseUtil.isEmpty(format))
			format = CommonConstant.DATE_SHORT_FORMAT;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(stringToDate(date));
		c.add(Calendar.DATE, 1);
		SimpleDateFormat myFormatter = new SimpleDateFormat(format);
		return myFormatter.format(c.getTime());
	}

	/**
	 * 获取指定时间的后一天的默认类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getAfterDay(String date) {
		return getAfterDay(date, CommonConstant.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 获取指定时间前一天的最后时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return String
	 */
	public static String getBeforeDayLastTime(String date) {
		if (BaseUtil.isEmpty(date))
			return null;
		return dateToString(setEndDate(stringToDate(getBeforeDay(date))),
				CommonConstant.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 获取指定时间前一天的最后时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date getBeforeDayLastTime(Date date) {
		if (BaseUtil.isEmpty(date))
			return null;
		return setEndDate(stringToDate(getBeforeDay(dateToString(date,
				CommonConstant.DATE_WITHSECOND_FORMAT))));
	}

	/**
	 * 获取指定时间后一天的开始时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return String
	 */
	public static String getAfterDayFirstTime(String date) {
		if (BaseUtil.isEmpty(date))
			return null;
		return dateToString(setSatrtDate(stringToDate(getAfterDay(date))),
				CommonConstant.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 获取指定时间后一天的开始时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date getAfterDayFirstTime(Date date) {
		if (BaseUtil.isEmpty(date))
			return null;
		return setSatrtDate(stringToDate(getAfterDay(dateToString(date,
				CommonConstant.DATE_WITHSECOND_FORMAT))));
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return String
	 */
	public static String getWeek(String date) {
		if (BaseUtil.isEmpty(date))
			return null;
		Date sdate = stringToDate(date, CommonConstant.DATE_SHORT_FORMAT);
		Calendar c = Calendar.getInstance();
		c.setTime(sdate);

		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 返回当前小时
	 * 
	 * @return int
	 */
	public static int getHour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回当分前分钟（小时需要换算成分钟）
	 * 
	 * @return int
	 */
	public static int getMinute() {
		Calendar c = Calendar.getInstance();
		int minute = c.get(Calendar.MINUTE);
		minute += c.get(Calendar.HOUR_OF_DAY) * 60;
		return minute;
	}

	/**
	 * 返回当日期
	 * 
	 * @return int
	 */
	public static int getDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回周内日号
	 * 
	 * @return int
	 */
	public static int getDayOfWeek() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		return (day - 1) > 0 ? (day - 1) : 7;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static long getDays(String date1, String date2) {
		if (BaseUtil.isEmpty(date1))
			return 0;
		if (BaseUtil.isEmpty(date2))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat(
				CommonConstant.DATE_SHORT_FORMAT);
		Date date = null;
		Date mydate = null;

		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return (new BigDecimal(day).abs()).longValue();
	}
	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static long getDays(Date date, Date mydate) {
		if (BaseUtil.isEmpty(date))
			return 0;
		if (BaseUtil.isEmpty(mydate))
			return 0;
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return (new BigDecimal(day).abs()).longValue();
	}

	/**
	 * 给定一个年份判断该年份是否为闰年
	 * 
	 * @param year
	 * @return false:不是闰年 true:闰年
	 */
	public static boolean isLeapYear(int year) {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.isLeapYear(year);
	}

	// /**
	// * 获取数据库时间
	// *
	// * @return String
	// * @throws AGPException
	// */
	// public static Date getDBTime() throws FrameworkException {
	// return SystemResourceUtil.getInstance().getDBTime();
	// }

	/**
	 * 获取以当月为基础的日历格式 日 一 二 三 四 五 六
	 * 
	 * 28 29 31 1 2 3 4 5 6 6 7 9 10 11
	 * 
	 * @param int curYear 当年
	 * @param int curMon 当月
	 */
	public static int[] getCalendarData(int curYear, int curMon) {
		Calendar cur = Calendar.getInstance();
		cur.set(Calendar.YEAR, curYear);
		cur.set(Calendar.MONTH, curMon - 1);
		cur.set(Calendar.DAY_OF_MONTH, 1);
		int curWeek = cur.get(Calendar.DAY_OF_WEEK) - 1;
		int curDays = cur.getActualMaximum(Calendar.DAY_OF_MONTH);
		Calendar last = Calendar.getInstance();
		last.setTime(cur.getTime());
		last.set(Calendar.MONTH, -1);
		int lastDays = last.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 日历行
		int rows = (curWeek + curDays) % 7 == 0 ? (curWeek + curDays) / 7
				: (curWeek + curDays) / 7 + 1;
		// 需要显示数据
		int[] values = new int[rows * 7];
		int addr = 0;
		// 上月数据
		for (int i = curWeek; i > 0; i--) {
			values[addr++] = lastDays - i + 1;
		}
		// 当月数据
		for (int i = 1; i <= curDays; i++) {
			values[addr++] = i;
		}
		// 下月
		int nextDay = values.length - addr;
		for (int i = 1; i <= nextDay; i++) {
			values[addr++] = i;
		}
		return values;
	}

	/**
	 * @Description(功能描述) : 年月日日期
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年10月3日 下午5:43:22
	 * @return
	 */
	public static String getDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(DateUtil.getGMTDate());
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据日期计算所在周的周一和周日
	 * 
	 * @param time
	 *            指定的日期
	 */
	public static void convertWeekByDate(Date time) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		String imptimeBegin = sdf.format(cal.getTime());
		System.out.println("所在周星期一的日期：" + imptimeBegin);
		Date d1 = cal.getTime();
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		System.out.println("所在周星期日的日期：" + imptimeEnd);
		Date d2 = cal.getTime();
		System.out.println(">>>>>>:" + d1 + "," + d2);
	}

	/**
	 * 
	 * @Description(功能描述) : 获取php时间
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年10月3日 下午5:43:22
	 * @return
	 */
	public static int phpDataInt() {
		return (int) (Calendar.getInstance().getTimeInMillis() / 1000 - 28800);
	}

	/**
	 * @param curtime
	 *            java当前时间（毫秒级） 需要减去8小时时差
	 * @Description(功能描述) : java时间转php时间
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年10月3日 下午5:43:22
	 * @return
	 */
	public static int java2phpInt(long curtime) {
		return (int) (curtime / 1000 - 28800);
	}

	/**
	 * @param Date
	 *            java当前时间（毫秒级）
	 * @Description(功能描述) : java时间转php时间
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年10月3日 下午5:43:22
	 * @return
	 */
	public static int java2phpInt(Date curDate) {
		return java2phpInt(curDate.getTime());
	}

	/**
	 * @param curtime
	 *            php当前时间（秒级） 需要加8小时时差
	 * @Description(功能描述) : java时间转php时间
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年10月3日 下午5:43:22
	 * @return
	 */
	public static long php2javaLong(long curtime) {
		return (curtime + 28800) * 1000;
	}

	/**
	 * @param curtime
	 *            php当前时间（秒级）
	 * @Description(功能描述) : java时间转php时间
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年10月3日 下午5:43:22
	 * @return
	 */
	public static Date php2javaDate(long curtime) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(php2javaLong(curtime));
		return cal.getTime();
	}

	public static void main(String[] args) {
		// System.out.println(dateToStringYYYYMMHH(php2javaDate(1452745984)));
		System.out.println(DateUtil.dateToString(DateUtil.getGMTDate(),
				CommonConstant.DATE_WITHSECOND_G_FORMAT));
	}

	public static void main1(String[] args) {

		long result = java2phpInt(stringToDate("2016-01-28 18:01:00.0",
				CommonConstant.DATE_WITHMILLISECOND_FORMAT))
				- java2phpInt(DateUtil.getGMTDate());
		System.out.println(result);
		int day = (int) (result / (3600 * 24));
		String daystr = String.valueOf((int) (result / (3600 * 24)));
		int day1 = 0;
		int day2 = 0;
		if (daystr.length() > 1) {
			day1 = Integer.valueOf(daystr.substring(0, daystr.length() - 1));
			day2 = Integer.valueOf(daystr.substring(daystr.length() - 1,
					daystr.length()));
		} else {
			day2 = day;
		}
		int[] days = new int[] { day1, day2 };
		System.out.println("day1=" + day1);
		System.out.println("day2=" + day2);
		int hour = (int) ((result - day * 24 * 3600) / 3600);
		String hourStr = String
				.valueOf((int) ((result - day * 24 * 3600) / 3600));
		int hour1 = 0;
		int hour2 = 0;
		if (hourStr.length() > 1) {
			hour1 = Integer.valueOf(hourStr.substring(0, hourStr.length() - 1));
			hour2 = Integer.valueOf(hourStr.substring(hourStr.length() - 1,
					hourStr.length()));
		} else {
			hour2 = hour;
		}
		int[] hours = new int[] { hour1, hour2 };
		System.out.println("hours=" + hours);
		System.out.println("hours1=" + hour1);
		System.out.println("hours2=" + hour2);
		int minute = (int) ((result - (day * 24 * 3600) - (hour * 3600)) / 60);
		String minuteStr = String
				.valueOf((int) ((result - (day * 24 * 3600) - (hour * 3600)) / 60));
		int minute1 = 0;
		int minute2 = 0;
		if (minuteStr.length() > 1) {
			minute1 = Integer.valueOf(minuteStr.substring(0,
					minuteStr.length() - 1));
			minute2 = Integer.valueOf(minuteStr.substring(
					minuteStr.length() - 1, minuteStr.length()));
		} else {
			minute2 = minute;
		}
		int[] minutes = new int[] { minute1, minute2 };
		System.out.println("minutes=" + minutes);
		System.out.println("minutes1=" + minute1);
		System.out.println("minutes2=" + minute2);
		int seconds = (int) ((result - (day * 24 * 3600) - (hour * 3600) - (minute * 60)));
		String secondsStr = String
				.valueOf((int) ((result - (day * 24 * 3600) - (hour * 3600)) / 60));
		int seconds1 = 0;
		int seconds2 = 0;
		if (secondsStr.length() > 1) {
			seconds1 = Integer.valueOf(secondsStr.substring(0,
					secondsStr.length() - 1));
			seconds2 = Integer.valueOf(secondsStr.substring(
					secondsStr.length() - 1, secondsStr.length()));
		} else {
			seconds2 = seconds;
		}
		int[] secondss = new int[] { seconds1, seconds2 };
		System.out.println("secondss=" + secondss);
		System.out.println("secondss1=" + seconds1);
		System.out.println("secondss2=" + seconds2);
		System.out.println("day=" + day);
		System.out.println("hour=" + hour);
		System.out.println("minute=" + minute);
		System.out.println("seconds=" + seconds);
		System.out.println(dateToString(php2javaDate(result),
				CommonConstant.DATE_WITHSECOND_FORMAT));
	}
}
