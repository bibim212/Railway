package Gmail;

import org.openqa.selenium.WebElement;

import Common.Constant;
import Helper.LogHelper;
import Railway.GeneralPage;

public class obsoleted_GmailLoginPage extends GeneralPage {
	// LOCATORS
	private LogHelper logHelper = new LogHelper(this.getClass());

	// ELEMENTS
	protected WebElement txtEmail() {
		return this.waitForElement("//input[@type='email']");
	}

	protected WebElement txtPass() {
		return this.waitForElement("//input[@type='password']");
	}

	protected WebElement btnNextUsername() {
		return this.waitForElement("//div[@id='identifierNext']//span");
	}

	protected WebElement btnNextPass() {
		return this.waitForElement("//div[@id='passwordNext']//span");
	}

	// METHODS
	public obsoleted_GmailLoginPage openGmail() {
		Constant.WEBDRIVER.navigate().to(Constant.GMAIL_URL);
		return this;
	}

	/**
	 * This is used for: Enter credentials to login to GMail
	 * 
	 * @param email
	 * @param pass
	 * @return redirects to Gmail Main Page if users provide correct credentials
	 */
	public obsoleted_GMailMainPage loginGmail(String email, String pass) {
		logHelper.logInfo("Login to Gmail.....");
		txtEmail().sendKeys(email);
		btnNextUsername().click();
		txtPass().sendKeys(pass);
		btnNextPass().click();
		return new obsoleted_GMailMainPage();
	}
}
