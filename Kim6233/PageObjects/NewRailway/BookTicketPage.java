package NewRailway;

import Railway.NewTicket;
import core.Button;
import core.DropDownlist;
import core.Label;

public class BookTicketPage extends GeneralPage {

	/*********** LOCATORS ***********/
	private DropDownlist ddlDepartedDate = new DropDownlist("//select[@name='Date']");
	private DropDownlist ddlDepartedFrom = new DropDownlist("//select[@name='DepartStation']");
	private DropDownlist ddlArrivedAt = new DropDownlist("//select[@name='ArriveStation']");
	private DropDownlist ddlSeatType = new DropDownlist("//select[@name='SeatType']");
	private DropDownlist ddlTicketAmount = new DropDownlist("//select[@name='TicketAmount']");
	private Button btnBookTicket = new Button("//input[@value='Book ticket']");

	private Label lblErrorMessage = new Label("//p[@class='message error']");
	private Label lblErrorTicketAmount = new Label(
			"//select[@name='TicketAmount']//following-sibling::label[@class ='validation-error']");

	/*********** METHODS ***********/
	public String getErrorTicket() {
		return lblErrorMessage.getText();
	}

	public String getErrorTicketAmount() {
		return lblErrorTicketAmount.getText();
	}

	public void submitBookTicketForm(NewTicket tk) {
		ddlDepartedDate.selectItemBy("ByVisibleText", tk._departedDate);
		ddlDepartedFrom.selectItemBy("ByVisibleText", tk._departedFrom);
		ddlSeatType.selectItemBy("ByVisibleText", tk._seatType);
		ddlTicketAmount.selectItemBy("ByVisibleText", Integer.toString(tk._ticketAmount));
		ddlArrivedAt.selectItemBy("ByVisibleText", tk._arrivedAt);
		btnBookTicket.click();
	}

	public BookTicketPage bookTicket(NewTicket tk) {
		submitBookTicketForm(tk);
		return this;
	}

	public NewTicket[] bookTickets(int amount) {
		NewTicket[] ntk = new NewTicket[amount - 1];
		for (int i = 0; i < amount; i++) {
			NewTicket tk = new NewTicket();
			this.bookTicket(tk);
			bookTicket(ntk[i]);
		}
		return ntk;
	}

	public NewTicket getTicketInfo() {
		String dDate = ddlDepartedDate.getFirstSelectedOption();
		String dFrom = ddlDepartedFrom.getFirstSelectedOption();
		String aAt = ddlArrivedAt.getFirstSelectedOption();
		String sType = ddlSeatType.getFirstSelectedOption();
		String tAmount = ddlTicketAmount.getFirstSelectedOption();
		int amount = Integer.parseInt(tAmount);

		NewTicket tk = new NewTicket(dDate, dFrom, aAt, sType, amount);
		return tk;
	}

	public String getDepartedDateAllowed() {
		return ddlDepartedDate.getFirstSelectedOption();
	}
}
