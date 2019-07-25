package NewRailway;

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

	public void submitBookTicketForm(Ticket tk) {
		ddlDepartedDate.selectItemByVisibleText(tk._departedDate);
		ddlDepartedFrom.selectItemByVisibleText(tk._departedFrom);
		ddlSeatType.selectItemByVisibleText(tk._seatType);
		ddlTicketAmount.selectItemByVisibleText(Integer.toString(tk._ticketAmount));
		ddlArrivedAt.selectItemByVisibleText(tk._arrivedAt);
		btnBookTicket.click();
	}

	public BookTicketPage bookTicket(Ticket tk) {
		submitBookTicketForm(tk);
		return this;
	}

	public Ticket[] bookTickets(int amount) {
		Ticket[] ntk = new Ticket[amount - 1];
		for (int i = 0; i < amount; i++) {
			Ticket tk = new Ticket();
			this.bookTicket(tk);
			bookTicket(ntk[i]);
		}
		return ntk;
	}

	public Ticket getTicketInfo() {
		String dDate = ddlDepartedDate.getFirstSelectedOption();
		String dFrom = ddlDepartedFrom.getFirstSelectedOption();
		String aAt = ddlArrivedAt.getFirstSelectedOption();
		String sType = ddlSeatType.getFirstSelectedOption();
		String tAmount = ddlTicketAmount.getFirstSelectedOption();
		int amount = Integer.parseInt(tAmount);

		Ticket tk = new Ticket(dDate, dFrom, aAt, sType, amount);
		return tk;
	}

	public String getDepartedDateAllowed() {
		return ddlDepartedDate.getFirstSelectedOption();
	}
}
