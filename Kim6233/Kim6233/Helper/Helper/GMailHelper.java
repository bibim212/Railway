package Helper;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Constant;
import Common.PathHelper;
import Railway.GeneralPage;
import Railway.ResetPasswordPage;

public class GMailHelper extends GeneralPage {
	// Locators

	private final By _txtEmailSearch = By.xpath("//input[@aria-label='Search mail']");
	private final By _btnEmailSearch = By.xpath("//button[@aria-label='Search Mail']");
	private final By _lnkLinkInEmail = By.xpath("//div[@role='list']//a");
	private final String EmailItemDynamicXpath = "//div[@class='AO']//div[@class='nH']//div[@role='main']//tr[.//span[contains(text(),'%s')]]";
	private String _activateSubject = "Please confirm your account";
	private String _resetPWSubject = "Please reset your password";
	private String _dynamicLink = "//span[@class='bog']/span[contains(text(),\"%s %s\")])";

	// Elements

	protected WebElement btnNextPass() {
		return this.waitForElement("//div[@id='passwordNext']//span");
	}

	protected WebElement txtPass() {
		return this.waitForElement("//input[@type='password']");
	}

	protected WebElement btnNextUsername() {
		return this.waitForElement("//div[@id='identifierNext']//span");
	}

	private WebElement getEmailSearchTextbox() {
		return Constant.WEBDRIVER.findElement(_txtEmailSearch);
	}

	private WebElement getEmailSearchButton() {
		return Constant.WEBDRIVER.findElement(_btnEmailSearch);
	}

	private WebElement getLinkInEmailLink() {
		return Constant.WEBDRIVER.findElement(_lnkLinkInEmail);
	}

	protected WebElement getActivateEmailSubject(String email) {
		return Constant.WEBDRIVER.findElement(By.xpath(String.format(_dynamicLink, _activateSubject, email)));
	}

	// Methods

	public void openGmailandActivationEmail(String EmailAddress) {
		loginGmail();
		getEmail(_activateSubject, EmailAddress, "confirm");
	}

	public void activeNewAccount(String EmailAddress) {

		loginGmail();

		getEmail(_activateSubject, EmailAddress, "confirm");

		// Click on active link in active email
		clickLinkInEmail(EmailAddress);

		ArrayList<String> tabs = new ArrayList<String>(Constant.WEBDRIVER.getWindowHandles());
		Constant.WEBDRIVER.switchTo().window(tabs.get(0));

	}
	
	
	

	public ResetPasswordPage clickResetPasswordLinkOnGMail(String EmailAddress) {

		ArrayList<String> tabs = new ArrayList<String>(Constant.WEBDRIVER.getWindowHandles());
		Constant.WEBDRIVER.switchTo().window(tabs.get(1));
		getEmail(_resetPWSubject, EmailAddress, "reset");

		// Click on active link in active email
		clickLinkInEmail(EmailAddress);

		ArrayList<String> newtabs = new ArrayList<String>(Constant.WEBDRIVER.getWindowHandles());
		Constant.WEBDRIVER.switchTo().window(newtabs.get(3));
		return new ResetPasswordPage();
	}

	/*
	 * @param: EmailType as "reset" or "confirm" (reset password/confirm account)
	 */
	public void getEmail(String Subject, String EmailAddress, String EmailType) {

		// Search correct email with key word as email address of new user
		PathHelper.waitControlDisplayed(Constant.WEBDRIVER.findElement(_txtEmailSearch), 100);
		getEmailSearchTextbox().click();
		getEmailSearchTextbox().sendKeys(Subject + " " + EmailAddress);
		getEmailSearchButton().click();

		String xpath = String.format(EmailItemDynamicXpath, EmailType);
		PathHelper.waitControlDisplayed(Constant.WEBDRIVER.findElement(By.xpath(xpath)), 100);
		WebElement EmailItem = Constant.WEBDRIVER.findElement(By.xpath(xpath));

		boolean breakIt = true;
		while (true) {
			breakIt = true;
			try {
				EmailItem.click();
			} catch (Exception e) {
				if (e.getMessage().contains("element is not attached")) {
					breakIt = false;
				}
			}
			if (breakIt) {
				break;
			}
		}

		breakIt = true;
		while (true) {
			breakIt = true;
			try {
				PathHelper.waitControlDisplayed(getLinkInEmailLink(), 100);
			} catch (Exception e) {
				if (e.getMessage().contains("element is not attached")) {
					breakIt = false;
				}
			}
			if (breakIt) {
				break;
			}
		}

	}

	public void openLinkOnEmail(String email) {
		String lnk = getActivateEmailSubject(email).toString();
		// Constant.WEBDRIVER.navigate().to(lnk);
		System.out.println(lnk);
	}

	public void clickLinkInEmail(String type) {

		String LinkInEmail = getLinkInEmailLink().getAttribute("href");
		System.out.println(LinkInEmail);
		PathHelper.openNewPage(LinkInEmail);
	}

	protected WebElement txtEmail() {
		return this.waitForElement("//input[@type='email']");
	}

	public void loginGmail() {
		GeneralPage.openNewPage("http://gmail.com");
		ArrayList<String> tabs = new ArrayList<String>(Constant.WEBDRIVER.getWindowHandles());
		Constant.WEBDRIVER.switchTo().window(tabs.get(1));

		// Enter email & password
		Constant.WEBDRIVER.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		txtEmail().sendKeys(Constant.GMAIL_ADMIN_USERNAME);
		btnNextUsername().click();
		txtPass().sendKeys(Constant.GMAIL_ADMIN_PASSWORD);
		btnNextPass().click();
	}
}
