package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Constant;

public class BookTicketPage extends GeneralPage {

	
	// Locators

	private final By cbbDepartedDate = By.xpath("//select[@name='Date']");
	private final By cbbDepartedFrom = By.xpath("//select[@name='DepartStation']");
	private final By cbbArrivedAt = By.xpath("//select[@name='ArriveStation']");
	private final By cbbSeatType = By.xpath("//select[@name='SeatType']");
	private final By cbbTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");

	private final By lblErrorMessage = By.xpath("//p[@class='message error']");
	private final By lblErrorTicketAmount = By
			.xpath("//select[@name='TicketAmount']//following-sibling::label[@class ='validation-error']");

	// Elements

	protected Select getCbbDepartedDate() {
		return new Select(Constant.WEBDRIVER.findElement(cbbDepartedDate));
	}

	protected Select getCbbDepartFrom() {
		return new Select(Constant.WEBDRIVER.findElement(cbbDepartedFrom));
	}

	protected Select getCbbArriveAt() {
		return new Select(Constant.WEBDRIVER.findElement(cbbArrivedAt));
	}

	protected Select getCbbTicketAmount() {
		return new Select(Constant.WEBDRIVER.findElement(cbbTicketAmount));
	}

	protected Select getCbbSeatType() {
		return new Select(Constant.WEBDRIVER.findElement(cbbSeatType));
	}

	protected WebElement getBtnBookTicket() {
		return Constant.WEBDRIVER.findElement(btnBookTicket);
	}

	protected WebElement getLblErrorMessage() {
		return Constant.WEBDRIVER.findElement(lblErrorMessage);
	}

	protected WebElement getLblErrorTicketAmount() {
		return Constant.WEBDRIVER.findElement(lblErrorTicketAmount);
	}

	// Methods

	public String getErrorTicket() {
		return this.getLblErrorMessage().getText();
	}

	public String getErrorTicketAmount() {
		return this.getLblErrorTicketAmount().getText();
	}

	
	public void submitBookTicketForm(NewTicket tk) {
		this.getCbbDepartedDate().selectByVisibleText(tk._departedDate);
		this.getCbbDepartFrom().selectByVisibleText(tk._departedFrom);
		this.getCbbSeatType().selectByVisibleText(tk._seatType);
		this.getCbbTicketAmount().selectByVisibleText(Integer.toString(tk._ticketAmount));
		this.getCbbArriveAt().selectByVisibleText(tk._arrivedAt);
		this.getBtnBookTicket().click();
	}

	
	public BookTicketPage bookTicket(NewTicket tk) {
		submitBookTicketForm(tk);
		return this;
	}
	public NewTicket[] bookTickets(int amount) {
		NewTicket[] ntk = new NewTicket[amount-1];
		for (int i = 0; i < amount; i++) {
			NewTicket tk = new NewTicket();
			this.bookTicket(tk);
			bookTicket(ntk[i]);
		}
		return ntk;
	}
	
/*	public BookTicketPage bookTickets(int amount) {
		for (int i = 0; i < amount; i++) {
			NewTicket tk = new NewTicket();
			this.bookTicket(tk);
		}
		return this;
	}
*/
	public NewTicket getTicketInfo() {
		String dDate = getCbbDepartedDate().getFirstSelectedOption().getText();
		String dFrom = this.getCbbDepartFrom().getFirstSelectedOption().getText();
		String aAt = this.getCbbArriveAt().getFirstSelectedOption().getText();
		String sType = this.getCbbSeatType().getFirstSelectedOption().getText();
		String tAmount = this.getCbbTicketAmount().getFirstSelectedOption().getText();
		int amount = Integer.parseInt(tAmount);

		NewTicket tk = new NewTicket(dDate, dFrom, aAt, sType, amount);
		return tk;
	}

	public String getDepartedDateAllowed() {
		return getCbbDepartedDate().getFirstSelectedOption().getText();
	}
}
