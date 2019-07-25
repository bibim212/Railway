package Helper;

public class StringHelper {
	/**
	 * This is used for generating random email address with Mailinator domain
	 * 
	 * @return string This returns a random email
	 */
	public static String generateEmailAddress() {
		// String temp = new
		// SimpleDateFormat("MMdd_HHmmss").format(Calendar.getInstance().getTime());
		String temp = randomAlphaNumeric(6);
		String emailSample = "K." + temp + "@mailinator.com";
		System.out.println(emailSample);
		return emailSample;
	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789qwertyuioplkjhgfdsazxcvbnm";

	/**
	 * This is provide a random string, the length is based on your input
	 * 
	 * @param count
	 * @return string This returns a random string
	 */
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static String printTicketInfo(String departedDate, String departedFrom, String arrivedAt, String seatType,
			int ticketAmount) {
		String ticketInfo = "Ticket Information: In %s | From %s | To %s | Type %s | Amount %s";
		String fullTicketInfo = String.format(ticketInfo, departedDate, departedFrom, arrivedAt, seatType,
				ticketAmount);
		System.out.println(fullTicketInfo);
		return fullTicketInfo;
	}
}
