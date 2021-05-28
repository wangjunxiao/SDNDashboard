package cn.dlut.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static DateUtil dateUtil;
	
	private static final String LONG_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String STR_DATE_PATTERN = "yyyyMMdd";
	private DateUtil() {
		new SimpleDateFormat();
	}

	public static DateUtil getInstance() {
		if (null == dateUtil) {
			dateUtil = new DateUtil();
		}
		return dateUtil;
	}
	

	public String formatLongPattern(Date date) {
		return format(date, LONG_PATTERN);
	}

	public String flp(Date date) {
		return formatLongPattern(date);
	}

	public String format(Date date, String pattern) {
		if (date == null)
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static String getTodayDate() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		return f.format(d);
	}
	
	
	public static String getTodayTime() {
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		return f.format(d);
	}
	
	public static String getDate2String(Date d) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(d);
	}

	public static Date getTodayDay() {
		return new Date();
	}
	
	/**
	 * @param dateString
	 * @param dataFormat
	 * @return
	 */
	public static Date string2Date(String dateString, String dataFormat){
		DateFormat format = new SimpleDateFormat(dataFormat);
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return date;
	}
	

	/**
	 * @param date
	 * @return
	 */
	public static Integer getDay2Int(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = 0;
		day += calendar.get(Calendar.YEAR) * 10000;
		day += (calendar.get(Calendar.MONTH) + 1) * 100;
		day += calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static Integer getTime2Int(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = 0;
		day += calendar.get(Calendar.HOUR_OF_DAY) * 10000;
		day += calendar.get(Calendar.MINUTE) * 100;
		day += calendar.get(Calendar.SECOND);
		return day;
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date getDateFromNumber(long date) {
		int _date = (int) date;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, _date / 10000);
		calendar.set(Calendar.MONTH, _date % 10000 / 100 - 1);
		calendar.set(Calendar.DAY_OF_MONTH, _date % 100);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date getFirestTimeOfDay(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date getEndTimeOfDay(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * @param date
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean isBetween(Date date, Date from, Date to) {
		if (date == null || (from == null && to == null))
			return false;

		if (from == null)
			return date.compareTo(to) <= 0;
		if (to == null)
			return date.compareTo(from) >= 0;

		return date.compareTo(from) >= 0 && date.compareTo(to) <= 0;
	}

	public static Date getMonthBegin(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getYearBegin(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getWeekBegin(Date date) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return calendar.getTime();
	}


	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		if (month == 12 && week == 1) {
			year++;
		}
		return year * 100 + week;
	}


	public static int getMonthOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		return year * 100 + month;
	}
	

	public static int getBeginDayOfWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		int day = 0;
		day += calendar.get(Calendar.YEAR) * 10000;
		day += (calendar.get(Calendar.MONTH) + 1) * 100;
		day += calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	
	public static int getEndDayOfWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		int day = 0;
		day += calendar.get(Calendar.YEAR) * 10000;
		day += (calendar.get(Calendar.MONTH) + 1) * 100;
		day += calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	
	public static int getBeginDayOfMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH,1);
		int day = 0;
		day += calendar.get(Calendar.YEAR) * 10000;
		day += (calendar.get(Calendar.MONTH) + 1) * 100;
		day += calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	
	public static int getEndDayOfMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH,Calendar.DATE);
		int day = 0;
		day += calendar.get(Calendar.YEAR) * 10000;
		day += (calendar.get(Calendar.MONTH) + 1) * 100;
		day += calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	
	public static Date getDateFromInt(int date){
		String strDate = String.valueOf(date);
		SimpleDateFormat sdf = new SimpleDateFormat(STR_DATE_PATTERN);
		Date d = null;
		try {
			d = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static String getMinBetweenDate(String expire){
		Date now=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat(LONG_PATTERN);
		try {
			Date eDate=dateFormat.parse(expire);
			if (eDate.before(now)) {
				return "expired"+((now.getTime()-eDate.getTime())/1000/60);
			}
			if (eDate.after(now)) {
				return ((eDate.getTime()-now.getTime())/1000/60)+"";
			}
			if (eDate.equals(now)) {
				return "0";
			}
		} catch (ParseException e) {		
			e.printStackTrace();
		}
		return "0";
	}
}
