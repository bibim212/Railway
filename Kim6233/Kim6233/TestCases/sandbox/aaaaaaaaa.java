package sandbox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import Helper.IntHelper;

public class aaaaaaaaa {

	public static void main(String[] args) {
		// t1();

		//System.out.println("ll");
		for (int j = 0; j < 16; j++) {
			System.err.println(generateD());

			/*
			 * String a = randomTicketStatus(); System.out.println(a);
			 * System.out.println("_-------------------"); getCurrentDate();
			 * 
			 * String[] ticketStatus = { "Ignore", "Expired", "New", "Paid" };
			 * 
			 * String b = randomFromArray(ticketStatus); System.err.println(b);
			 */
		}
	}

	public static int generateD() {
		int a[] = { 1, 4, 5, 7, 6 };
		int b = IntHelper.generateRandomIntRange(1, 9);
		for (int i = 0; i < a.length; i++) {
			while (b == a[0]) {
				b = IntHelper.generateRandomIntRange(1, 9);
			//	System.err.println(b);
				//break;
			}
		}
		return b;
	}

	static int getMissingNo(int a[], int n) {
		int i, total;
		total = (n + 1) * (n + 2) / 2;
		for (i = 0; i < n; i++)
			total -= a[i];
		return total;
	}

	public static String randomFromArray(String[] array) {
		String returnedValue = "";
		Random r = new Random();
		int randomNumber = r.nextInt(array.length);
		returnedValue = array[randomNumber];

		return returnedValue;
	}

	public static String randomTicketStatus() {
		String status = "";
		String[] ticketStatus = { "Ignore", "Expired", "New", "Paid" };
		Random r = new Random();
		int randomNumber = r.nextInt(ticketStatus.length);
		status = ticketStatus[randomNumber];
		return status;
	}

	public static void getCurrentDate() {
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		DateFormat dformat = new SimpleDateFormat("M/d/yyyy");
		String ddd = dformat.format(cal.getTime());
		System.out.println(ddd);
	}
}
