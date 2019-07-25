package NewRailway;

import core.Link;

public class GeneralPage {

	/*********** LOCATORS ***********/
	private Link tabLogin = new Link("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private Link tabContact = new Link("//div[@id='menu']//a[@href='/Page/Contact.cshtml']");
	private Link tabLogout = new Link("//div[@id='menu']//a[@href='/Account/Logout']");
	private Link tabRegister = new Link("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	private Link tabMyTicket = new Link("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
	private Link tabBookTicket = new Link("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
	private Link tabTicketPrice = new Link("//div[@id='menu']//a[@href='/Page/TrainPriceListPage.cshtml']");
	private Link tabTrainTimeable = new Link("//a[@href=\"TrainTimeListPage.cshtml\"]");
	private Link tabChangePassword = new Link("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");

	/*********** METHODS ***********/

	public LoginPage gotoLoginPage() {
		tabLogin.click();
		return new LoginPage();
	}

	public ContactPage gotoContactPage() {
		tabContact.click();
		return new ContactPage();
	}

	public MyTicketPage gotoMyTicketPage() {
		tabMyTicket.click();
		return new MyTicketPage();
	}

	public BookTicketPage gotoBookTicketPage() {
		tabBookTicket.click();
		return new BookTicketPage();
	}

	public RegisterPage gotoRegisterPage() {
		tabRegister.click();
		return new RegisterPage();
	}

	public TicketPricePage gotoTicketPricePage() {
		tabTicketPrice.click();
		return new TicketPricePage();
	}

	public ChangePasswordPage gotoChangePasswordPage() {
		tabChangePassword.click();
		return new ChangePasswordPage();
	}

	public TrainTimablePage gotoTrainTimeablePage() {
		tabTrainTimeable.click();
		return new TrainTimablePage();
	}

	public LoginPage gotoLogout() {
		tabLogout.click();
		return new LoginPage();
	}
}
