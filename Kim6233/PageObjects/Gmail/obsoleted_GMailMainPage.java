package Gmail;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import Common.Constant;
import Railway.GeneralPage;
import Railway.LoginPage;
import Railway.RegisterConfirmedPage;

public class obsoleted_GMailMainPage extends GeneralPage {
	// LOCATORS

	private final By _activateLink = By.xpath("//a[contains(@href,'confirmationCode')]");
	private final By _resetLink = By.xpath("//a[contains(@href,'PasswordReset')]");
	private final By _txtSearchEmail = By.xpath("//input[contains(@class, 'gb')]");

	private String _activateSubject = "Please confirm your account";
	private String _resetPWSubject = "Please reset your password";

	private String _dynamicLink = "(//span[@class='bog']/span[contains(text(),\"%s %s\")])[2]";

	// ELEMENTS

	protected WebElement getActivateEmailSubject(String email) {
		return Constant.WEBDRIVER.findElement(By.xpath(String.format(_dynamicLink, _activateSubject, email)));
	}

	protected WebElement getResetPasswordSubject(String email) {
		return Constant.WEBDRIVER.findElement(By.xpath(String.format(_dynamicLink, _resetPWSubject, email)));
	}

	protected WebElement linkActivateAccount() {
		return this.waitForElement(_activateLink);
	}

	protected WebElement linkResetPassword() {
		return this.waitForElement(_resetLink);
	}

	private WebElement txtSearchEmail() {
		return this.waitForElement(_txtSearchEmail);
	}

	// METHODS

	public void openEmail(String sSubject, String email) {
		this.waitForClickAble(_txtSearchEmail);
		System.out.println("Account for activating/ resetting PW: " + email);
		txtSearchEmail().sendKeys(sSubject + " " + email + Keys.ENTER);
		this.waitForClickAble(By.xpath(String.format(_dynamicLink, sSubject, email)));
	}

	public void openLinkOnEmail(String email) {
		String lnk = getActivateEmailSubject(email).toString();
		//Constant.WEBDRIVER.navigate().to(lnk);
		System.out.println(lnk);
	}

	public void activeRailwayAccount(String email) {
		openEmail(_activateSubject, email);
		System.out.println(_activateSubject + " " + email);
		openLinkOnEmail(email);
	}

	public void resetRailwayAccount(String email) {
		openEmail(_resetPWSubject, email);
		System.out.println(_resetPWSubject + " " + email);
		openLinkOnEmail(email);
	}

	/**
	 * This is used for clicking on Activate Link of Railway Account in GMail,
	 * please enter the email for activating
	 * 
	 * @param email
	 * @return Register Confirmation Page will return after clicking on the Activate
	 *         link
	 */
	public RegisterConfirmedPage activateAccount(String email) {
		activeRailwayAccount(email);

		/*
		 * this.clickLinkEmail(_activateSubject, email); 
		 * // Switch tab chrome ---------
		 * ArrayList<String> arr = new
		 * ArrayList<String>(Constant.WEBDRIVER.getWindowHandles());
		 * Constant.WEBDRIVER.switchTo().window(arr.get(1)); // End Switch tab chrome
		 * --------- //closeWindow(); //switchWindow();
		 */

		ArrayList<String> tabs = new ArrayList<String>(Constant.WEBDRIVER.getWindowHandles());
		Constant.WEBDRIVER.switchTo().window(tabs.get(0));
		return new RegisterConfirmedPage();
	}

	/**
	 * This is used for clicking on Reset Password Link of Railway Account in GMail,
	 * please enter the email for reseting
	 * 
	 * @param email
	 * @return Login Page will return after clicking on the Reset link
	 */
	public LoginPage resetPasswordGmail(String email) {
		ArrayList<String> tabs = new ArrayList<String>(Constant.WEBDRIVER.getWindowHandles());
		Constant.WEBDRIVER.switchTo().window(tabs.get(1));

		resetRailwayAccount(email);
		ArrayList<String> newtabs = new ArrayList<String>(Constant.WEBDRIVER.getWindowHandles());
		Constant.WEBDRIVER.switchTo().window(newtabs.get(3));
		return new LoginPage();
		/*
		 * this.clickLinkEmail(_resetPWSubject, email); // Switch tab chrome ---------
		 * ArrayList<String> arr = new
		 * ArrayList<String>(Constant.WEBDRIVER.getWindowHandles());
		 * Constant.WEBDRIVER.switchTo().window(arr.get(2)); // End Switch tab chrome
		 * ---------
		 * 
		 * 
		 * closeWindow(); switchWindow();
		 */

	}
}
