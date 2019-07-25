package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Constant;

public class TicketPricePage extends GeneralPage {
	// LOCATORS
	String bookTicket = "//td[contains(text(),'%s')]//ancestor::tr//a";

	// td[contains(text(),'Sài Gòn')]//following-sibling::td[contains(text(),'Nha
	// Trang')]//ancestor::tr//a[contains(@href,'BookTicket')]
	//// td[contains(text(),'Sài Gòn')]//following-sibling::td[contains(text(),'Nha
	// Trang')]//ancestor::tr//a[contains(@href,'TicketPrice')]

	String priceTicket = "//li[contains(text(),'%s to %s')]//ancestor::tr//a";

	/********************************/
	private final By lblSelectedStation = By.xpath("//tr[@class='TableSmallHeader']/th");

	protected WebElement getLblSelectedStation() {
		return Constant.WEBDRIVER.findElement(lblSelectedStation);
	}

	public String getSelectedStation() {
		return this.getLblSelectedStation().getText();
	}

	// METHODS
	/**
	 * This is used to get trip info in Ticket Price page after selecting from Train
	 * Timetable page
	 * 
	 * @param str
	 * @return a trip with departed from and arrived at
	 */
	public static String[] getTrip(String str) {
		String subString = str.substring(18);
		int firstIndex = subString.indexOf("to");
		String from = subString.substring(0, firstIndex);
		String to = subString.substring(firstIndex + 3);

		String trip[] = new String[2];
		trip[0] = from;
		trip[1] = to;

		return trip;
	}

	public void selectTicketPrice(String departedFrom, String arrivedAt) {
		System.out.println("select ticket price of: " + departedFrom + " | " + arrivedAt);
		findControl(String.format(priceTicket, departedFrom, arrivedAt)).click();
	}

	public void selectBookTicket(String seat) {

		findControl(String.format(bookTicket, seat)).click();
	}
}
