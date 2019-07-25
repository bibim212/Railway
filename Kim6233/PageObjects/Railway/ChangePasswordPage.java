package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Constant;

public class ChangePasswordPage extends GeneralPage {

	// Locators
	private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By _txtPasswordResetToken = By.xpath("//input[@id='resetToken']");
	private final By _btnSubmit = By.xpath("//input[@type='submit']");
	private final By _lblSuccessMessage = By.xpath("//p[@class='message success']");
	private final By _lblErrorMessage = By.xpath("//p[@class='message error']");
	private final By _lblErrorConfirmPasswordMessage = By
			.xpath("//li[@class='confirm-password']//label[@class='validation-error']");
	private final By lblForgotPasswordMsg = By.xpath("//p[@class='message success']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error']");

	// Elements
	public WebElement getNewPasswordTextbox() {
		return Constant.WEBDRIVER.findElement(_txtNewPassword);
	}

	public WebElement getConfirmPasswordTextbox() {
		return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
	}

	public WebElement getPasswordResetTokenTextbox() {
		return Constant.WEBDRIVER.findElement(_txtPasswordResetToken);
	}

	public WebElement getSubmitButton() {
		return Constant.WEBDRIVER.findElement(_btnSubmit);
	}

	public WebElement getSuccessMessageLabel() {
		return Constant.WEBDRIVER.findElement(_lblSuccessMessage);
	}

	public WebElement getErrorMessageLabel() {
		return Constant.WEBDRIVER.findElement(_lblErrorMessage);
	}

	public WebElement getErrorConfirmPasswordMessageLabel() {
		return Constant.WEBDRIVER.findElement(_lblErrorConfirmPasswordMessage);
	}

	private WebElement getLblForgotPasswordMsg() {
		return Constant.WEBDRIVER.findElement(lblForgotPasswordMsg);
	}

	public WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
	}

	public boolean checkSuccessMessageDisplayed() {

		return getSuccessMessageLabel().isDisplayed();

	}

	public String getErrorLoginMsg() {
		return this.getLblLoginErrorMsg().getText();
	}

	public String getForgotPasswordStatus() {
		return this.getLblForgotPasswordMsg().getText();
	}

	public void submitResetPassword(String password) {
		this.getNewPasswordTextbox().sendKeys(password);
		this.getConfirmPasswordTextbox().sendKeys(password);
		//
		this.getSubmitButton().click();
	}

	public LoginPage resetInvalidValue(String password) {
		submitResetPassword(password);
		this.getPasswordResetTokenTextbox().clear();
		return new LoginPage();
	}

	public boolean isPasswordResetTokenEmpty() {
		String a = getPasswordResetTokenTextbox().getAttribute("value");
		System.out.println("Password Token: " + a);
		if (a.isEmpty()) {
			return true;
		}
		return false;

	}

	public ChangePasswordPage resetPassword(String password) {
		submitResetPassword(password);
		GeneralPage.waitControlDisplayed(this.getSuccessMessageLabel(), 100);
		return this;
	}

	protected boolean isPasswordChangeForm() {
		return this.isElementDisplay(getLblPasswordChangeForm());
	}
}
