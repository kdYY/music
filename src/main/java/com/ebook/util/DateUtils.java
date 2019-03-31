package com.ebook.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期时间工具
 * 
 * @author yunzi7758
 *
 */
public class DateUtils {

	/**
	 * Default time format : yyyy-MM-dd HH:mm:ss
	 */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Time format : yyyy-MM-dd HH:mm
	 */
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	/**
	 * HH:mm
	 */
	public static final String TIME_FORMAT = "HH:mm";

	/**
	 * Default date format yyyy-MM-dd
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * Default month format yyyy-MM
	 */
	public static final String MONTH_FORMAT = "yyyy-MM";
	/**
	 * Default day format dd
	 */
	public static final String DAY_FORMAT = "dd";

	// Date pattern
	public static final String DATE_PATTERN = "^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$";

	/**
	 * 判断字符串是否日期格式
	 * 
	 * @param dateAsText
	 * @return
	 */
	public static boolean isDate(String dateAsText) {
		return StringUtils.isNotEmpty(dateAsText) && dateAsText.matches(DATE_PATTERN);
	}

	/**
	 * 获取当前时间
	 * @return
	 * <pre>DateUtils.now() = Thu Jul 12 10:58:12 CST 2018</pre>
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 将时间转换为 yyyy-MM-dd 格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateText(Date date) {
		return toDateText(date, DATE_FORMAT);
	}

	/**
	 * 将时间转换为 pattern 格式的字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toDateText(Date date, String pattern) {
		if (date == null || pattern == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * yyyy-MM-dd格式字符串转为Date
	 * 
	 * @param dateText
	 * @return
	 */
	public static Date getDate(String dateText) {
		return getDate(dateText, DATE_FORMAT);
	}

	/**
	 * pattern格式字符串转为Date
	 * @param dateText
	 * @param pattern
	 * @return
	 */
	public static Date getDate(String dateText, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(dateText);
		} catch (ParseException e) {
			throw new IllegalStateException("Parse date from [" + dateText + "," + pattern + "] failed", e);
		}
	}

	/**
	 * 
	 * 将时间转换为 yyyy-MM-dd HH:mm 格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateTime(Date date) {
		return toDateText(date, DATE_TIME_FORMAT);
	}

	/**
	 * 当前年份
	 * 
	 * @return Current year
	 * <pre>DateUtils.currentYear() = 2018</pre>
	 */
	public static int currentYear() {
		return calendar().get(Calendar.YEAR);
	}

	public static Calendar calendar() {
		return Calendar.getInstance();
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 提取一个月中的最后一天
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date3hm = date.getTime() - 3600000 * 34 * day;
		Date date3hmdate = new Date(date3hm);
		return date3hmdate;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 * <pre>DateUtils.getHour() = 10</pre>
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 * <pre>DateUtils.getTime() = 58</pre>
	 */
	public static String getMinute() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 */
	public static String getTwoHourInterval(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0])) {
			return "0";
		} else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
			return (y - u) > 0 ? y - u + "" : "0";
		}
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDayInterval(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 时间前推或后推分钟,其中interval表示分钟.
	 */
	public static String getTimeByInterval(String sj1, String interval) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long time = (date1.getTime() / 1000) + Integer.parseInt(interval) * 60;
			date1.setTime(time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * <pre>DateUtils.getNextDay("Thu Jul 12 10:58:12 CST 2018","2") = Mon Jul 16 10:58:12 CST 2018</pre>
	 */
	public static String getDayByInterval(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = getDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static Date getDayByInterval(Date d, int delay) {
		try {
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			return d;
		} catch (Exception e) {
			return new Date();
		}
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = getDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0) {
			return true;
		} else if ((year % 4) == 0) {
			return (year % 100) == 0 ? false : true;
		} else {
			return false;
		}
	}

	/**
	 * 获取下delay个年
	 * @param date
	 * @param delay
	 * @return
	 */
	public static Date nextYear(Date date, Integer delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, delay);
		return calendar.getTime();
	}

	/**
	 * 获取一个月的最后一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1) {
			week = "0" + week;
		}
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = getDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if ("1".equals(num)) {
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		} else if ("2".equals(num)) {
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		} else if ("3".equals(num)) {
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		} else if ("4".equals(num)) {
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		} else if ("5".equals(num)) {
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		} else if ("6".equals(num)) {
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		} else if ("0".equals(num)) {
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = getDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static void main(String[] args) {// yyyy-MM-dd HH:mm:ss
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date()));
		// Date d1 = df.parse(df.format(new Date()));
		System.out.println(getWeek(df.format(new Date())));
		System.out.println(getWeekStr(df.format(new Date())));
		df = new SimpleDateFormat("HH mm ss dd MM yyyy");

		System.out.println(df.format(new Date()));
	}

	/**
	 * 获取当前时间对应的中午日期
	 * @param sdate
	 * @return
	 */
	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateUtils.getWeek(sdate);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || "".equals(date1)) {
			return 0;
		}
		if (date2 == null || "".equals(date2)) {
			return 0;
		}
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号
		sdate = sdate.substring(0, 8) + "01";

		// 得到这个月的1号是星期几
		Date date = getDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateUtils.getDayByInterval(sdate, (1 - u) + "");
		return newday;
	}

	
	/**
	 * 如果时间字符串符合yyyy-MM-dd hh:mm:ss yyyy-MM-dd格式返回true
	 * @param date
	 * @return
	 */
	public static boolean rightDate(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (date == null) {
			return false;
		}
		if (date.length() > 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			sdf.parse(date);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	/**
	 * 一天中一共的毫秒数
	 */
	public static long millionSecondsOfDay = 86400000;
	/**
	 * 
	 * 一小时中一共得毫秒数
	 */
	public static long millionSecondsOfHour = 3600000;

	private static final String FORMAT_DATE_STR = "yyyy-MM-dd";

	/**
	 * 得到两个日期之间相差的年数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferYear(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		return c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
	}

	/**
	 * 得到两个日期之间相差的年数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferYear(String date1, String date2) {
		Date dateTime1tmp = DateUtils.parse(date1, "yyyy-MM-dd");
		Date dateTime2tmp = DateUtils.parse(date2, "yyyy-MM-dd");
		return getDifferYear(dateTime1tmp, dateTime2tmp);
	}

	/**
	 * 得到两个日期之间相差的月数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferMonth(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		int year = getDifferYear(date1, date2);
		int months = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH) + year * 12;
		if (c2.get(Calendar.DATE) < c1.get(Calendar.DATE)) {
			months = months - 1;
		}
		return months;
	}

	/**
	 * 得到两个日期之间相差的月数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferMonth(String date1, String date2) {
		Date dateTime1tmp = DateUtils.parse(date1, "yyyy-MM-dd");
		Date dateTime2tmp = DateUtils.parse(date2, "yyyy-MM-dd");
		return getDifferMonth(dateTime1tmp, dateTime2tmp);
	}

	/**
	 * 得到两个日期之间相差的天数,两头不算,取出数据后，可以根据需要再加
	 * 
	 * @deprecated 此方法在计算相邻两天的日期时结果为0
	 * @param date1
	 * @param date2
	 * @return
	 */
	private static int getDifferDay(Date date1, Date date2) {
		Long d1 = date1.getTime();
		Long d2 = date2.getTime();
		return (int) ((d2 - d1) / millionSecondsOfDay);
	}

	/**
	 * 得到两个日期之间相差的天数,两头不算,取出数据后，可以根据需要再加
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDifferDay(String date1, String date2) {
		Date dateTime1tmp = DateUtils.parse(date1, "yyyy-MM-dd");
		Date dateTime2tmp = DateUtils.parse(date2, "yyyy-MM-dd");
		Long d1 = dateTime1tmp.getTime();
		Long d2 = dateTime2tmp.getTime();
		return (int) ((d2 - d1) / millionSecondsOfDay);
	}

	/**
	 * 
	 * 计算2个时间之间的相差的小时和分钟数，返回XX小时XX分 注意date1格式为yyyy-MM-dd 注意date2格式为yyyy-MM-dd
	 * 注意time1格式为HH:mm 注意time2格式为HH:mm date1<date2
	 * 
	 * @param date1
	 * @param time1
	 * @param date2
	 * @param time2
	 * @return resultHM[hours,mins]
	 */
	public static int[] getDifferHourAndMinute(String date1, String time1, String date2, String time2) {
		Date dateTime1tmp = DateUtils.parse(date1 + " " + time1, "yyyy-MM-dd HH:mm");
		Date dateTime2tmp = DateUtils.parse(date2 + " " + time2, "yyyy-MM-dd HH:mm");
		Long d2 = dateTime2tmp.getTime();
		Long d1 = dateTime1tmp.getTime();
		int hours = (int) ((d2 - d1) / millionSecondsOfHour);
		int mins = (int) ((d2 - d1) % millionSecondsOfHour);
		mins = mins / 60000;
		int[] resultHM = new int[2];
		resultHM[0] = hours;
		resultHM[1] = mins;
		return resultHM;
	}

	/**
	 * 
	 * 计算2个时间之间的相差的小时和分钟数，返回XX小时XX分 注意date1格式为yyyy-MM-dd HH:mm
	 * 注意date2格式为yyyy-MM-dd HH:mm date1<date2
	 * 
	 * @param date1
	 * @param date2
	 * @return resultHM[hours,mins]
	 */
	public static int[] getDifferHourAndMinute(Date date1, Date date2) {
		Long d2 = date2.getTime();
		Long d1 = date1.getTime();
		int hours = (int) ((d2 - d1) / millionSecondsOfHour);
		int mins = (int) ((d2 - d1) % millionSecondsOfHour);
		mins = mins / 60000;
		int[] resultHM = new int[2];
		resultHM[0] = hours;
		resultHM[1] = mins;
		return resultHM;
	}

	/**
	 * 
	 * 计算2个时间之间的相差的小时和分钟数，返回XX小时XX分 注意date1格式为yyyy-MM-dd HH:mm
	 * 注意date2格式为yyyy-MM-dd HH:mm date1<date2
	 * 
	 * @param date1
	 * @param date2
	 * @return resultHM[hours,mins]
	 */
	public static int[] getDifferHourAndMinute(String date1, String date2) {
		Date dateTime1tmp = DateUtils.parse(date1, "yyyy-MM-dd HH:mm");
		Date dateTime2tmp = DateUtils.parse(date2, "yyyy-MM-dd HH:mm");
		Long d2 = dateTime2tmp.getTime();
		Long d1 = dateTime1tmp.getTime();
		int hours = (int) ((d2 - d1) / millionSecondsOfHour);
		int mins = (int) ((d2 - d1) % millionSecondsOfHour);
		mins = mins / 60000;
		int[] resultHM = new int[2];
		resultHM[0] = hours;
		resultHM[1] = mins;
		return resultHM;
	}

	/**
	 * 
	 * 计算2个时间之间的相差的小时数(Date,Date) date1<date2

	 */
	public static int getDifferHour(Date date1, Date date2) {
		Long d1 = date1.getTime();
		Long d2 = date2.getTime();
		int hours = (int) ((d2 - d1) / millionSecondsOfHour);
		return hours;
	}

	/**
	 * 
	 * 计算2个时间之间的相差的小时(String,String) date1<date2
	 * 
	 * @param String
	 *            date1
	 * @param String
	 *            date2
	 * @return
	 */
	public static int getDifferHour(String date1, String date2) {
		Date dateTime1tmp = DateUtils.parse(date1, "yyyy-MM-dd HH:mm");
		Date dateTime2tmp = DateUtils.parse(date2, "yyyy-MM-dd HH:mm");
		Long d2 = dateTime2tmp.getTime();
		Long d1 = dateTime1tmp.getTime();
		int hours = (int) ((d2 - d1) / millionSecondsOfHour);
		return hours;
	}

	/**
	 * 返回两日期相差的分钟数[日期格式为: "yyyy-MM-dd HH:mm"]
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @author hai
	 * @date 2014年1月7日下午1:58:10
	 */
	public static int getDifferMinute(String date1, String date2) {
		Date dateTime1tmp = DateUtils.parse(date1, "yyyy-MM-dd HH:mm");
		Date dateTime2tmp = DateUtils.parse(date2, "yyyy-MM-dd HH:mm");
		return getDifferMinute(dateTime1tmp, dateTime2tmp);
	}

	/**
	 * 返回两日期相差的分钟数[日期格式为: "yyyy-MM-dd HH:mm"]
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @author hai
	 * @date 2014年1月7日下午1:58:10
	 */
	public static int getDifferMinute(Date date1, Date date2) {
		Long d2 = date2.getTime();
		Long d1 = date1.getTime();
		int hours = (int) ((d2 - d1) / 60000);
		return hours;
	}

	/**
	 * 计算日期加年
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addYear(Date date, int years) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.YEAR, years);
		return c.getTime();
	}

	/**
	 * 计算日期加月数
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date date, int months) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		return c.getTime();
	}

	/**
	 * 计算日期加天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, int days) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	/**
	 * 计算日期减天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date minusDay(Date date, int days) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) - days);
		return c.getTime();
	}

	/**
	 * 计算日期加分钟数
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	/**
	 * 计算日期小时
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.HOUR, hour);
		return c.getTime();
	}

	/**
	 * 格式化日期为String型(yyyy-MM-dd)
	 * 
	 * @param date
	 * @param formater
	 * @return
	 * <pre>DateUtils.format("Thu Jul 12 10:58:12 CST 2018") = 2018-07-12</pre>
	 */
	public static String format(Date date) {
		return format(date, FORMAT_DATE_STR);
	}

	/**
	 * 根据指定日期格式格式化日期为String型
	 * 
	 * @param date
	 * @param formater
	 * @return
	 */
	public static String format(Date date, String formater) {
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(date);
	}

	/**
	 * 格式化日期为Date型(yyyy-MM-dd)
	 * 
	 * @param date
	 * @param formater
	 * @return
	 */
	public static Date parse(String date) {
		return parse(date, FORMAT_DATE_STR);
	}

	/**
	 * 根据指定日期格式格式化日期为Date型
	 * 
	 * @param date
	 * @param formater
	 * @return
	 */
	public static Date parse(String date, String formater) {
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		Date result = null;
		try {
			result = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据日期取出是星期几,数字
	 * 
	 * @param date
	 * @return int 返回1-7
	 */
	public static int getWeekOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 根据日期取出是星期几,中文
	 * 
	 * @param date
	 * @return int 返回1-7
	 */
	public static String getWeekTextOfDate(Date date) {
		String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int t = getWeekOfDate(date);
		if (t == 7) {
			t = 0;
		}
		return dayNames[t];
	}

	/**
	 * 得到当前的日期，格式为：yyyy-MM-dd
	 * 
	 * @return 为一个字符串
	 */
	public static String getCurrenDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		return sdf.format(d).toString();
	}

	/**
	 * 得到当前的时间，精确到毫秒，格式为：yyyy-MM-dd hh:mm:ss
	 * 
	 * @return 为一个字符串
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		return sdf.format(d).toString();
	}

	/**
	 * 将java时间转为sql时间
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date convertUtilDateToSQLDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		java.sql.Date jd = new java.sql.Date(cl.getTimeInMillis());
		return jd;
	}

	/**
	 * 将sql时间转为java时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date convertSQLDateToUtilDate(java.sql.Date date) {
		if (date == null) {
			return null;
		}
		Calendar cl = Calendar.getInstance();

		cl.setTime(date);
		Date jd = new Date(cl.getTimeInMillis());
		return jd;
	}

	/**
	 * 是否为闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 400) == 0) {
			return true;
		} else if ((year % 4) == 0) {
			return (year % 100) == 0 ? false : true;
		} else {
			return false;
		}
	}

	/**
	 * 是否为当天
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		Calendar day = Calendar.getInstance();
		day.setTime(date);
		if (today.get(Calendar.YEAR) == day.get(Calendar.YEAR) && today.get(Calendar.MONTH) == day.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) == day.get(Calendar.DAY_OF_MONTH)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 取Java虚拟机系统时间, 返回当前时间戳
	 * 
	 * @return Timestamp类型的时间
	 */
	public static Timestamp getSysTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 取Java虚拟机系统时间, 返回当前Date
	 * 
	 * @return Date类型的时间
	 */
	public static Date getSysDate() {
		Calendar cl = Calendar.getInstance();
		return cl.getTime();
	}

	/**
	 * 当前日期是否在指定区间日期范围内-- 闭区间
	 * 
	 * @param date1
	 *            Date类型
	 * @param date2
	 *            Date类型
	 * @return
	 */
	public static boolean isBetweenDateByClosedInterval(Date date1, Date date2) {
		String nowDate = DateUtils.format(new Date(), "yyyy-MM-dd");
		String sDate1 = DateUtils.format(date1, "yyyy-MM-dd");
		String sDate2 = DateUtils.format(date2, "yyyy-MM-dd");
		if (java.sql.Date.valueOf(sDate1).before(java.sql.Date.valueOf(sDate2))
				|| java.sql.Date.valueOf(sDate1).equals(java.sql.Date.valueOf(sDate2))) {
			if ((java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(sDate1))
					|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(sDate1)))
					&& (java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(sDate2))
							|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(sDate2)))) {
				return true;
			} else {
				return false;
			}
		} else {
			if ((java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(sDate2))
					|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(sDate2)))
					&& (java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(sDate1)))
					|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(sDate1))) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 当前日期是否在指定区间日期范围内-- 开区间
	 * 
	 * @param date1
	 *            Date类型
	 * @param date2
	 *            Date类型
	 * @return
	 */
	public static boolean isBetweenDateByOpenInterval(Date date1, Date date2) {
		String nowDate = DateUtils.format(new Date(), "yyyy-MM-dd");
		String sDate1 = DateUtils.format(date1, "yyyy-MM-dd");
		String sDate2 = DateUtils.format(date2, "yyyy-MM-dd");
		if (java.sql.Date.valueOf(sDate1).before(java.sql.Date.valueOf(sDate2))) {
			if (java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(sDate1))
					&& java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(sDate2))) {
				return true;
			} else {
				return false;
			}
		} else if (java.sql.Date.valueOf(sDate1).after(java.sql.Date.valueOf(sDate2))) {
			if (java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(sDate2))
					&& java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(sDate1))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 当前日期是否在指定区间日期范围内-- 闭区间
	 * 
	 * @param sDate1
	 *            String类型（格式为：yyyy-MM-dd）
	 * @param date2
	 *            String类型（格式为：yyyy-MM-dd）
	 * @return
	 */
	public static boolean dateisBetweenDateByClosedInterval(Date curDate, String sDate1, String date2) {
		String nowDate = DateUtils.format(curDate, "yyyy-MM-dd");
		if (java.sql.Date.valueOf(sDate1).before(java.sql.Date.valueOf(date2))
				|| java.sql.Date.valueOf(sDate1).equals(java.sql.Date.valueOf(date2))) {
			if ((java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(sDate1))
					|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(sDate1)))
					&& (java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(date2))
							|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(date2)))) {
				return true;
			} else {
				return false;
			}
		} else {
			if ((java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(date2))
					|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(date2)))
					&& (java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(sDate1)))
					|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(sDate1))) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 当前日期是否在指定区间日期范围内-- 开区间
	 * 
	 * @param sDate1
	 *            String类型（格式为：yyyy-MM-dd）
	 * @param date2
	 *            String类型（格式为：yyyy-MM-dd）
	 * @return
	 */
	public static boolean dateisBetweenDateByOpenInterval(Date curDate, String sDate1, String date2) {
		String nowDate = DateUtils.format(curDate, "yyyy-MM-dd");
		if (java.sql.Date.valueOf(sDate1).before(java.sql.Date.valueOf(date2))) {
			if (java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(sDate1))
					&& java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(date2))) {
				return true;
			} else {
				return false;
			}
		} else if (java.sql.Date.valueOf(sDate1).after(java.sql.Date.valueOf(date2))) {
			if (java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(date2))
					&& java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(sDate1))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 当前日期是否在指定区间日期范围内-- 闭区间
	 * 
	 * @param sDate1
	 *            String类型（格式为：yyyy-MM-dd）
	 * @param date2
	 *            String类型（格式为：yyyy-MM-dd）
	 * @return
	 */
	public static boolean isBetweenDateByClosedInterval(String sDate1, String date2) {
		String nowDate = DateUtils.format(new Date(), "yyyy-MM-dd");
		if (java.sql.Date.valueOf(sDate1).before(java.sql.Date.valueOf(date2))
				|| java.sql.Date.valueOf(sDate1).equals(java.sql.Date.valueOf(date2))) {
			if ((java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(sDate1))
					|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(sDate1)))
					&& (java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(date2))
							|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(date2)))) {
				return true;
			} else {
				return false;
			}
		} else {
			if ((java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(date2))
					|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(date2)))
					&& (java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(sDate1)))
					|| java.sql.Date.valueOf(nowDate).equals(java.sql.Date.valueOf(sDate1))) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 当前日期是否在指定区间日期范围内-- 开区间
	 * 
	 * @param sDate1
	 *            String类型（格式为：yyyy-MM-dd）
	 * @param date2
	 *            String类型（格式为：yyyy-MM-dd）
	 * @return
	 */
	public static boolean isBetweenDateByOpenInterval(String sDate1, String date2) {
		String nowDate = DateUtils.format(new Date(), "yyyy-MM-dd");
		if (java.sql.Date.valueOf(sDate1).before(java.sql.Date.valueOf(date2))) {
			if (java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(sDate1))
					&& java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(date2))) {
				return true;
			} else {
				return false;
			}
		} else if (java.sql.Date.valueOf(sDate1).after(java.sql.Date.valueOf(date2))) {
			if (java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(date2))
					&& java.sql.Date.valueOf(nowDate).before(java.sql.Date.valueOf(sDate1))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String formatToEng(String date) {
		DateFormat df = new SimpleDateFormat("MMM", Locale.ENGLISH);
		return df.format(parse(date));
	}

	/**
	 * 得到当前的时间，精确到毫秒，格式为：yyyyMMddHHmmssSSS
	 * 
	 * @return 为一个字符串
	 */
	public static String getCurrentTimeNum() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date d = new Date();
		return sdf.format(d).toString();
	}

	/**
	 * 时间转换为年月日时分秒 周
	 * 
	 * @param date
	 * @return
	 */
	public static int[] getTimeByCalendar(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);// 获取年份

		int month = (cal.get(Calendar.MARCH)) + 1;// 获取月份

		int day = cal.get(Calendar.DATE);// 获取日

		int hour = cal.get(Calendar.HOUR_OF_DAY);// 小时

		int minute = cal.get(Calendar.MINUTE);// 分

		int second = cal.get(Calendar.SECOND);
		// 秒
		/*
		 * int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
		 */ int[] str = new int[] { year, month, day, hour, minute, second };
		System.out.println("现在的时间是：公元" + year + "年" + (month) + "月" + day + "日      " + hour + "时" + minute + "分"
				+ second + "秒       星期");
		return str;

	}

}