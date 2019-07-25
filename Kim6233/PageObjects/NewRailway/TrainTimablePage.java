package NewRailway;

public class TrainTimablePage extends GeneralPage{
	// LOCATORS
		// Train Timetable

		// Check Price or Book Ticket link on Train Timable page
		String selectionLink = "//td[contains(text(),'%s')]//following-sibling::td[contains(text(),'%s')]//ancestor::tr//a[contains(@href,'%s')]";

		// Book Ticket button on Ticket Price page
		String bookTicketLink = "//td[contains(text(),'%s')]//ancestor::tr//a[contains(@href,'BookTicket')]";

		
		//String priceTicket = "//li[contains(text(),'%s to %s')]//ancestor::tr//a";
		
		/**
		 * This is used for select Ticket Price link of a tour on Train TimeTable page
		 * @param departedFrom
		 * @param arrivedAt
		 */
		public void selectTicketPrice(String departedFrom, String arrivedAt) {
			System.out.println(departedFrom + " | " + arrivedAt);
			System.out.println(String.format(selectionLink, departedFrom, arrivedAt, "TicketPrice"));
			findControl(String.format(selectionLink, departedFrom, arrivedAt, "TicketPrice")).click();
		}

		/**
		 * This is used for select Book Ticket link of a tour on Train TimeTable page
		 * @param departedFrom
		 * @param arrivedAt
		 */
		public void selectBookTicket(String seat) {
			System.out.println(String.format(bookTicketLink, seat));
			findControl(String.format(bookTicketLink, seat)).click();
		}
}
