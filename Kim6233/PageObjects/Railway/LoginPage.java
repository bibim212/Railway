package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Constant;
import Helper.GMailAPIHelper;

public class LoginPage extends GeneralPage {

	// LOCATOR
	private final By txtUsername = By.xpath("//input[@id='username']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@value='login']");

	private final By lblLoginErrorMsg1 = By.xpath("//p[@class='message error LoginForm']");

	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	private final By linkForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
	private final By lblForgotPasswordMsg = By.xpath("//p[@class='message success']");
	private final By txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By bntResetPassword = By.xpath("//input[@value='Reset Password']");
	private final By lblPasswordResetTokenErrMsg = By
			.xpath("//label[@for='resetToken'and @class = 'validation-error']");

	protected WebElement getLblPasswordResetTokenErrMsg() {
		return Constant.WEBDRIVER.findElement(lblPasswordResetTokenErrMsg);
	}

	// ELEMENTS

	protected WebElement getTxtUsername() {
		return Constant.WEBDRIVER.findElement(txtUsername);
	}

	protected WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(txtPassword);
	}

	protected WebElement getBtnLogin() {
		return Constant.WEBDRIVER.findElement(btnLogin);
	}

	public WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
	}

	private WebElement getLinkForgotPassword() {
		return Constant.WEBDRIVER.findElement(linkForgotPassword);
	}

	public WebElement getTxtEmail() {
		return Constant.WEBDRIVER.findElement(txtEmail);
	}

	private WebElement getBntSendInstructions() {
		return Constant.WEBDRIVER.findElement(btnSendInstructions);
	}

	private WebElement getLblForgotPasswordMsg() {
		return Constant.WEBDRIVER.findElement(lblForgotPasswordMsg);
	}

	private WebElement getLblLoginErrorMsg1() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg1);
	}

	private WebElement getTxtNewPassword() {
		return Constant.WEBDRIVER.findElement(txtNewPassword);
	}

	private WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(txtConfirmPassword);
	}

	private WebElement getBntResetPassword() {
		return Constant.WEBDRIVER.findElement(bntResetPassword);
	}

	private final By btnSubmit = By.xpath("//input[@type='submit']");

	public WebElement getSubmitButton() {
		return Constant.WEBDRIVER.findElement(btnSubmit);
	}

	public LoginPage forgotPassword(String EmailAddress) {
		this.getTxtEmail().sendKeys(EmailAddress);
		this.getSubmitButton().click();
		return new LoginPage();
	}

	// METHODS
	public LoginPage gotoForgotPasswordPage() {
		this.getLinkForgotPassword().click();
		return new LoginPage();
	}

	public String getLoginErrorMsg1() {
		return this.getLblLoginErrorMsg1().getText();
	}

	public String getErrorResetToken() {
		return this.getLblPasswordResetTokenErrMsg().getText();
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

	public void submitLoginForm(String username, String password) {
		this.getTxtUsername().clear();
		this.getTxtUsername().sendKeys(username);
		this.getTxtPassword().sendKeys(password);
		this.getBtnLogin().click();

		System.err.println("Submit Login Form: " + username + " | " + password);
	}

	public LoginPage loginFail(String username, String password) {
		submitLoginForm(username, password);
		return this;
	}

	public LoginPage sendResetPasswordInstructions(String email) {
		this.getTxtEmail().sendKeys(email);
		this.getBntSendInstructions().click();
		return new LoginPage();
	}

	public String getForgotPasswordStatus() {
		return this.getLblForgotPasswordMsg().getText();

	}

	public LoginPage recoverPassword(String registerEmail) {
		GMailAPIHelper api = new GMailAPIHelper();
		String resetPasswordLink = api.getResetPasswordLink(registerEmail);
		Constant.WEBDRIVER.get(resetPasswordLink);
		return new LoginPage();
	}

	public String getLoginErrorMsg() {
		return this.getLblLoginErrorMsg().getText();
	}

	public ChangePasswordPage resetPassword(String newPassword, String confirmPassword) {

		UserAccount uc = new UserAccount();
		System.out.println(UserAccount._email);
		System.out.println(UserAccount._password);
		this.getTxtNewPassword().sendKeys(newPassword);
		this.getTxtConfirmPassword().sendKeys(confirmPassword);
		this.getBntResetPassword().click();

		uc.setPassword(newPassword);

		return new ChangePasswordPage();

	}
}
