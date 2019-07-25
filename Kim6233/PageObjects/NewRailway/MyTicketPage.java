package NewRailway;

import Railway.NewTicket;
import core.Button;
import core.DropDownlist;
import core.Label;
import core.Textbox;

public class MyTicketPage extends GeneralPage {

	/*********** LOCATORS ***********/
	private DropDownlist cbbFilterDepartStation = new DropDownlist("//select[@name='FilterDpStation']");
	private DropDownlist cbbFilterArriveStation = new DropDownlist("//select[@name='FilterArStation']");
	private DropDownlist cbbFilterStatus = new DropDownlist("//select[@name='FilterStatus']");
	private Textbox txtFilterDepartDate = new Textbox("//input[@name='FilterDpDate']");
	private Button btnApplyFilter = new Button("//input[@value='Apply filter']");

	private Label lblErrorMessage = new Label("//div[@class='error message']");
	String cancelTicket = "//td[.='%s']//following-sibling::td[.='%s']//following-sibling::td[.='%s']//following-sibling::td//input";

	/*********** METHODS ***********/
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
			txtFilterDepartDate.sendKeys(searchString);
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
			btnApplyFilter.click();
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
		return Constant.WEBDRIVER.findElement(new Dropdownlist(finalXpath)).getText();
	}

	public int numberOfRowMyTicket() {
		String xpath = "//table[@class='%s']//tr[contains(@class, 'Row')]";
		String finalXpath = String.format(xpath, "MyTable");
		java.util.List<WebElement> rows = Constant.WEBDRIVER.findElements(new Dropdownlist(finalXpath));
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
}
