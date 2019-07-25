package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.Constant;
import Helper.GMailAPIHelper;

public class ForgotPasswordPage extends GeneralPage {
	// Locators
	private final By txtEmailAddress = By.xpath("//input[@id='email']");
	private final By btnSubmit = By.xpath("//input[@type='submit']");
	private final By lblSuccessMessage = By.xpath("//p[@class='message success']");

	// Elements
	public WebElement getEmailAddressTextbox() {
		return Constant.WEBDRIVER.findElement(txtEmailAddress);

	}

	public WebElement getSubmitButton() {
		return Constant.WEBDRIVER.findElement(btnSubmit);
	}

	public WebElement getSuccessMessageLabel() {
		return Constant.WEBDRIVER.findElement(lblSuccessMessage);
	}

	public ForgotPasswordPage forgotPassword(String EmailAddress) {
		this.getEmailAddressTextbox().sendKeys(EmailAddress);
		this.getSubmitButton().click();
		return this;
	}

	public LoginPage recoverPassword(String registerEmail) {
		GMailAPIHelper api = new GMailAPIHelper();
		String resetPasswordLink = api.getResetPasswordLink(registerEmail);
		Constant.WEBDRIVER.get(resetPasswordLink);
		return new LoginPage();
	}
}
