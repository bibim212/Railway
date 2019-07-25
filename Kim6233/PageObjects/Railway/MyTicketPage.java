package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Constant;

public class MyTicketPage extends GeneralPage {

	/*************************** LOCATORS ***************************/

	private final By cbbFilterDepartStation = By.xpath("//select[@name='FilterDpStation']");
	private final By cbbFilterArriveStation = By.xpath("//select[@name='FilterArStation']");
	private final By cbbFilterStatus = By.xpath("//select[@name='FilterStatus']");
	private final By txtFilterDepartDate = By.xpath("//input[@name='FilterDpDate']");
	private final By btnApplyFilter = By.xpath("//input[@value='Apply filter']");

	String cancelTicket = "//td[.='%s']//following-sibling::td[.='%s']//following-sibling::td[.='%s']//following-sibling::td//input";
	private final By lblErrorMessage = By.xpath("//div[@class='error message']");

	/*************************** web elements ***************************/
	public WebElement getDynamicBtnCancelTicket(String dFrom, String aAt, int tAmount) {
		return findControl(String.format(cancelTicket, dFrom, aAt, tAmount));
	}

	public String getTextInMyTicketTab() {
		return this.getTabMyTicket().getText();
	}

	protected Select getDropDownList(By b) {
		return new Select(Constant.WEBDRIVER.findElement(b));
	}

	protected Select getCboFilterDepartStation() {
		return new Select(Constant.WEBDRIVER.findElement(cbbFilterDepartStation));
	}

	protected Select getCboFilterArriveStation() {
		return new Select(Constant.WEBDRIVER.findElement(cbbFilterArriveStation));
	}

	protected Select getCboFilterStatus() {
		return new Select(Constant.WEBDRIVER.findElement(cbbFilterStatus));
	}

	protected WebElement getTxtFilterDepartDate() {
		return Constant.WEBDRIVER.findElement(txtFilterDepartDate);
	}

	protected WebElement getBtnApplyFilter() {
		return Constant.WEBDRIVER.findElement(btnApplyFilter);
	}

	protected WebElement getLblErrorMessage() {
		return Constant.WEBDRIVER.findElement(lblErrorMessage);
	}

	/*************************** methods ***************************/

	public void cancelTicket(NewTicket tk) {

		getDynamicBtnCancelTicket(tk._departedFrom, tk._arrivedAt, tk._ticketAmount).click();
		this.confirmPopup();
	}

	public boolean checkTicketRemove(NewTicket tk) {
		if (getDynamicBtnCancelTicket(tk._departedFrom, tk._arrivedAt, tk._ticketAmount) == null) {
			return true;
		}
		return false;
	}

	public MyTicketPage searchWith(String searchBy, String searchString, String action) {

		switch (searchBy) {
		case "Depart Date":
			getTxtFilterDepartDate().sendKeys(searchString);
			break;
		case "Arrive Station":
			getDropDownList(cbbFilterArriveStation).selectByVisibleText(searchString);
			break;
		case "Depart Station":
			getDropDownList(cbbFilterDepartStation).selectByVisibleText(searchString);
			break;
		case "Status":
			getDropDownList(cbbFilterStatus).selectByVisibleText(searchString);
			break;
		default:
			break;
		}

		if (action == "click") {
			getBtnApplyFilter().click();
		}

		return this;
	}

	public NewTicket getMyTicketCellValue(int row) {
		String dDate = "";
		String dFrom = "";
		String aAt = "";
		String sType = "";
		int tAmount = 0;

		NewTicket ticket = new NewTicket(dDate, dFrom, aAt, sType, tAmount);
		String table = "MyTable";

		ticket.setDepartedDate(getCellValue(table, row, "Depart Date"));
		ticket.setDepartedFrom(getCellValue(table, row, "Depart Station"));
		ticket.setSeatType(getCellValue(table, row, "Seat Type"));
		ticket.setArrivedAt(getCellValue(table, row, "Arrive Station"));
		// ticket.setTicketAmount(Integer.parseInt(getCellValue(table, row, "Amount")));

		ticket.setStatus(getCellValue(table, row, "Status"));

		return ticket;
	}

	public static String getCellValue(String table, int row, String column) {
		String xpath = "//table[@class='%s']//tr[%s]/td[count(//th[.= '%s']/preceding-sibling::th) %s]";

		String x = "";

		switch (column) {
		case "Depart Date":
			x = "- 1";
			break;
		case "Arrive Station":
			x = "";
			break;
		case "Depart Station":
			x = "+ 1";
			break;
		case "Status":
			x = "- 2";
			break;
		default:
			break;
		}

		String finalXpath = String.format(xpath, table, String.valueOf(row), column, x);
		return Constant.WEBDRIVER.findElement(By.xpath(finalXpath)).getText();
	}

	public int numberOfRowMyTicket() {
		String xpath = "//table[@class='%s']//tr[contains(@class, 'Row')]";
		String finalXpath = String.format(xpath, "MyTable");
		java.util.List<WebElement> rows = Constant.WEBDRIVER.findElements(By.xpath(finalXpath));
		return rows.size();
	}

	public boolean checkSearchResult(String column, String value) {
		boolean result = false;

		for (int i = 2; i <= this.numberOfRowMyTicket() + 1; i++) {
			NewTicket tk = this.getMyTicketCellValue(i);

			switch (column) {
			case "Depart Date":
				if (!value.equals(tk.getDepartedDate())) {
					result = false;
				}
				result = true;

				break;
			case "Depart Station":
				if (!value.equals(tk.getDepartedFrom())) {
					result = false;
				}
				result = true;

				break;
			case "Arrive Station":
				if (!value.equals(tk.getArrivedAt())) {
					result = false;
				}
				result = true;

				break;
			case "Status":
				if (!value.equals(tk.getStatus())) {
					result = false;
				}
				result = true;

				break;
			default:
				break;
			}
		}
		System.err.println(result);
		return result;
	}
	
	

	/*
	 * public void verifyFilterWorksCorrectly(String keyword, String searchBy) {
	 * 
	 * if (this.isWebElementExist(msgNoResult)) { String expected =
	 * "Sorry, can't find any results that match your filters.\r\nPlease change the filters and try again."
	 * ; AssertThat.verifyEquals(getMsgNoResult().getText(), expected,
	 * "Test case is failed"); } else { for (int count = 2; count <=
	 * this.numberOfRowMyTicket(); count++) { NewTicket ticket =
	 * this.getMyTicketCellValue(count); if (searchBy == "Depart Station") {
	 * AssertThat.verifyEquals(keyword, ticket.getDepartedFrom(),
	 * "Inconsistent data"); } if (searchBy == "Arrive Station") {
	 * AssertThat.verifyEquals(keyword, ticket.getArrivedAt(), "Inconsistent data");
	 * } if (searchBy == "Depart Date") { AssertThat.verifyEquals(keyword,
	 * ticket.getDepartedDate(), "Inconsistent data"); } } } }
	 */

	/*
	 * public boolean check(String column) { int column_no = 0; boolean checka =
	 * false;
	 * 
	 * switch (column) { case "Depart Date": column_no = 5; break; case
	 * "Arrive Station": column_no = 3; break; case "Depart Station": column_no = 2;
	 * break; case "Status": column_no = 8; break; default: break; }
	 * 
	 * LinkedList<String> a = getTableColumnData(column_no); checka =
	 * isFilterStatusCorrect(a, "true"); return checka; }
	 */

	/*
	 * LinkedList<String> getTableColumnData(int column_no) { LinkedList<String>
	 * values = new LinkedList<String>(); WebElement table =
	 * Constant.WEBDRIVER.findElement(By.xpath("//table[@class='MyTable']"));
	 * List<WebElement> rows =
	 * table.findElements(By.xpath("tr[contains(@class, 'Row')]"));
	 * Iterator<WebElement> i = rows.iterator(); while (i.hasNext()) { WebElement
	 * row = i.next(); List<WebElement> columns = row.findElements(By.xpath("th"));
	 * Iterator<WebElement> j = columns.iterator(); int count = 0; while
	 * (j.hasNext()) { WebElement column = j.next(); if (count == column_no) {
	 * values.add(column.getText()); break; } count++; }
	 * 
	 * } return values; }
	 */

	/*
	 * boolean isFilterStatusCorrect(LinkedList<String> actual, String expected) {
	 * Iterator<String> itr = actual.iterator(); boolean result = true; if
	 * (actual.size() > 0) { while (itr.hasNext()) { if
	 * (!expected.equals(itr.next())) { result = false; break; } } } return result;
	 * }
	 */
	/*
	 * Sữa phần verify, thành check, return true nếu giống nhau, nếu khác thì return
	 * false; tách riêng phần check messge cho no result returns
	 */
}

/*
 * private String dynamicTicketInfo =
 * "//td[text()='%s']/following::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']//following-sibling::td[text()='%s']/parent::tr";
 * 
 * protected WebElement getDynamicTicketInfo(NewTicket ticket) { return
 * Constant.WEBDRIVER.findElement(By.xpath(String.format(dynamicTicketInfo,
 * ticket.getDepartedFrom(), ticket.getArrivedAt(), ticket.getSeatType(),
 * ticket.getDepartedDate(), ticket.getTicketAmount()))); }
 */
