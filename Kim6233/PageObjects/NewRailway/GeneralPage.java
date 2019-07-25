package NewRailway;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Link;

public class GeneralPage {
	WebDriver driver;

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
	public void gotoPage(String sPath) {
		switch (sPath) {
		case "LoginPage": {
			tabLogin.click();
			PageFactory.initElements(driver, LoginPage.class);
			break;
		}
		case "ContactPage": {
			tabContact.click();
			PageFactory.initElements(driver, ContactPage.class);
		}
		case "MyTicketPage": {
			tabMyTicket.click();
			PageFactory.initElements(driver, MyTicketPage.class);
		}
		case "BookTicketPage": {
			tabBookTicket.click();
			PageFactory.initElements(driver, BookTicketPage.class);
		}
		case "RegisterPage": {
			tabRegister.click();
			PageFactory.initElements(driver, RegisterPage.class);
		}
		case "TicketPricePage": {
			tabTicketPrice.click();
			PageFactory.initElements(driver, TicketPricePage.class);
		}
		case "ChangePasswordPage": {
			tabChangePassword.click();
			PageFactory.initElements(driver, ChangePasswordPage.class);
		}
		case "TrainTimablePage": {
			tabTrainTimeable.click();
			PageFactory.initElements(driver, TrainTimablePage.class);
		}
		case "Logout": {
			tabLogout.click();
			PageFactory.initElements(driver, LoginPage.class);
		}
		default:
			PageFactory.initElements(driver, HomePage.class);
		}
	}
}