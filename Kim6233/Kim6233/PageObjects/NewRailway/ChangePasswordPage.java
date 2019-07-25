package NewRailway;

import core.Button;
import core.Label;
import core.Textbox;

public class ChangePasswordPage extends GeneralPage {

	/*********** LOCATORS ***********/
	private Textbox txtNewPassword = new Textbox("//input[@id='newPassword']");
	private Textbox txtConfirmPassword = new Textbox("//input[@id='confirmPassword']");
	private Textbox txtPasswordResetToken = new Textbox("//input[@id='resetToken']");
	private Button btnSubmit = new Button("//input[@type='submit']");
	private Label lblSuccessMessage = new Label("//p[@class='message success']");
	private Label lblErrorMessage = new Label("//p[@class='message error']");
	private Label lblErrorConfirmPasswordMessage = new Label(
			"//li[@class='confirm-password']//label[@class='validation-error']");
	private Label lblForgotPasswordMsg = new Label("//p[@class='message success']");
	private Label lblLoginErrorMsg = new Label("//p[@class='message error']");

	/*********** METHODS ***********/
	public boolean checkSuccessMessageDisplayed() {

		return lblSuccessMessage.isDisplayed();
	}

	public String getErrorLoginMsg() {
		return lblLoginErrorMsg.getText();
	}

	public String getForgotPasswordStatus() {
		return lblForgotPasswordMsg.getText();
	}

	public void submitResetPassword(String password) {
		txtNewPassword.sendKeys(password);
		txtConfirmPassword.sendKeys(password);
		btnSubmit.click();
	}

	public LoginPage resetInvalidValue(String password) {
		submitResetPassword(password);
		txtPasswordResetToken.clear();
		return new LoginPage();
	}

	public boolean isPasswordResetTokenEmpty() {
		String a = txtPasswordResetToken.getAttribute("value");
		System.out.println("Password Token: " + a);
		if (a.isEmpty()) {
			return true;
		}
		return false;

	}

	public ChangePasswordPage resetPassword(String password) {
		submitResetPassword(password);
		
		GeneralPage.waitControlDisplayed(lblSuccessMessage, 100);
		return this;
	}

	protected boolean isPasswordChangeForm() {
		return isElementDisplay(lblPasswordChangeForm);
	}
}
