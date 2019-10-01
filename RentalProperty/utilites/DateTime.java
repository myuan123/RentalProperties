package utilites;
/**
 * Created by ZhiweiXu on 2018/8/23.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class DateTime {

	private long advance;
	private long time;

	public DateTime() {
		time = System.currentTimeMillis();
	}

	/**
	 * @param setClockForwardInDays
	 *            delay days
	 */
	public DateTime(int setClockForwardInDays) {
		advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
		time = System.currentTimeMillis() + advance;
	}

	/**
	 * @param startDate
	 *            start date
	 * @param setClockForwardInDays
	 *            delay days
	 */
	public DateTime(DateTime startDate, int setClockForwardInDays) {
		advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
		time = startDate.getTime() + advance;
	}

	public DateTime(int day, int month, int year) {
		setDate(day, month, year);
	}

	/** Get time */
	public long getTime() {
		return time;
	}

	/** format */
	public String toString() {
		return getFormattedDate();
	}

	public static String getCurrentTime() {
		Date date = new Date(System.currentTimeMillis()); // returns current
															// Date/Time
		return date.toString();
	}

	/** format */
	public String getFormattedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		long currentTime = getTime();
		Date gct = new Date(currentTime);

		return sdf.format(gct);
	}

	/** format */
	public String getEightDigitDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		long currentTime = getTime();
		Date gct = new Date(currentTime);

		return sdf.format(gct);
	}

	/** Gets the number of days between */
	public static int diffDays(DateTime endDate, DateTime startDate) {
		final long HOURS_IN_DAY = 24L;
		final int MINUTES_IN_HOUR = 60;
		final int SECONDS_IN_MINUTES = 60;
		final int MILLISECONDS_IN_SECOND = 1000;
		long convertToDays = HOURS_IN_DAY * MINUTES_IN_HOUR
				* SECONDS_IN_MINUTES * MILLISECONDS_IN_SECOND;
		long hirePeriod = endDate.getTime() - startDate.getTime();
		double difference = (double) hirePeriod / (double) convertToDays;
		int round = (int) Math.round(difference);
		return round;
	}

	private void setDate(int day, int month, int year) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, 0, 0);

		java.util.Date date = calendar.getTime();

		time = date.getTime();
	}

	/** get week */
	public int getInWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(time));
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	// Advances date/time by specified days, hours and mins for testing purposes
	public void setAdvance(int days, int hours) {
		advance = ((days * 24L + hours) * 60L) * 60000L;
	}
	/**confirm date format*/
	public static boolean isValidDate(String str) {
	       SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
	       try {
	          format.setLenient(false);
	          format.parse(str);
	          return true;
	       } catch (ParseException e) {
	    	   return false;
	       }
	}
}