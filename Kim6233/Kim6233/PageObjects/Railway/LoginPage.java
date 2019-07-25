package Railway;

import core.Button;
import core.Label;
import core.Link;
import core.Textbox;

public class LoginPage extends GeneralPage {

	/*********** LOCATORS ***********/
	private Textbox txtUsername = new Textbox("//input[@id='username']");
	private Textbox txtPassword = new Textbox("//input[@id='password']");
	private Button btnLogin = new Button("//input[@value='login']");
	private Label lblLoginErrorMsg = new Label("//p[@class='message error']");

	private Label lblLoginErrorMsg1 = new Label("//p[@class='message error LoginForm']");
	private Link lnkForgotPassword = new Link("//a[@href='/Account/ForgotPassword.cshtml']");
	private Textbox txtEmail = new Textbox("//input[@id='email']");
	private Button btnSendInstructions = new Button("//input[@value='Send Instructions']");
	private Textbox txtNewPassword = new Textbox("//input[@id='newPassword']");
	private Textbox txtConfirmPassword = new Textbox("//input[@id='confirmPassword']");
	private Button btnResetPassword = new Button("//input[@value='Reset Password']");
	private Textbox txtPasswordResetToken = new Textbox("//input[@id='resetToken']");
	private Label lblErrorResetToken = new Label(
			"//li[@class='reset-token']//following-sibling::label[@class ='validation-error']");

	/*********** METHODS ***********/
	public ForgotPasswordPage gotoForgotPasswordPage() {
		lnkForgotPassword.click();
		return new ForgotPasswordPage();
	}

	public void submitLoginForm(String username, String password) {
		txtUsername.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
	}

	/**
	 * This is used for Login to Railway with correct credentials
	 * 
	 * @param username
	 * @param password
	 */
	public HomePage loginRailway(String username, String password) {
		submitLoginForm(username, password);
		return new HomePage();
	}

	public LoginPage loginFail(String username, String password) {
		submitLoginForm(username, password);
		return this;
	}

	/**
	 * This is used for Login to Railway with incorrect credentials several times
	 * 
	 * @param attempts
	 * @param username
	 * @param password
	 * @return page Login Page still opens after trying login fail several times
	 */
	public LoginPage loginAttempts(int attempts, String username, String password) {
		for (int i = 1; i <= attempts; i++) {
			loginFail(username, password);
		}
		return this;
	}

	public LoginPage sendResetPasswordInstructions(String email) {
		txtEmail.sendKeys(email);
		btnSendInstructions.click();
		return this;
	}

	public String getLoginErrorMsg1() {
		return lblLoginErrorMsg1.getText();
	}

	public void submitResetPasswordForm(String newPassword, String confirmPassword) {
		txtNewPassword.sendKeys(newPassword);
		txtConfirmPassword.sendKeys(confirmPassword);
		btnResetPassword.click();
	}

	public ChangePasswordPage resetPassword(String newPassword, String confirmPassword) {

		UserAccount uc = new UserAccount();
		System.out.println(UserAccount._email);
		System.out.println(UserAccount._password);
		submitResetPasswordForm(newPassword, confirmPassword);
		uc.setPassword(newPassword);
		return new ChangePasswordPage();
	}

	public LoginPage resetInvalidValue(String password) {
		submitResetPasswordForm(password, password);
		txtPasswordResetToken.clear();
		btnResetPassword.click();
		return this;
	}

	public String getErrorResetToken() {
		return lblErrorResetToken.getText();
	}

	public String getLoginErrorMsg() {
		return lblLoginErrorMsg.getText();
	}
}
