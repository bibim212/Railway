package Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import Railway.NewTicket;

public class DateTimeHelper {

	public static Date randomDate(int amountDate) {

		try {

			Date currentDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.add(Calendar.DATE, amountDate);

			return cal.getTime();

		} catch (Exception e) {

			return null;

		}
	}

	public static String addDays(String date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		Date dDate;
		try {
			dDate = new SimpleDateFormat("M/d/yyyy").parse(date);
			cal.setTime(dDate);
			cal.add(Calendar.DATE, days);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SimpleDateFormat("M/d/yyyy").format(cal.getTime());

	}

	public static String getCurrentDate() {
		Date myDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Calendar cal = Calendar.getInstance();
		myDate = cal.getTime();
		return dateFormat.format(myDate);
	}
}
