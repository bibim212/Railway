package NewRailway;

public class LoginPage extends GeneralPage {
	// LOCATOR
	private final By txtUsername = By.xpath("//input[@id='username']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@value='login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error']");

	private final By lblLoginErrorMsg1 = By.xpath("//p[@class='message error LoginForm']");
	private final By linkForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
	private final By txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By bntResetPassword = By.xpath("//input[@value='Reset Password']");
	private final By txtPasswordResetToken = By.xpath("//input[@id='resetToken']");
	private final By lblErrorResetToken = By
			.xpath("//li[@class='reset-token']//following-sibling::label[@class ='validation-error']");

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

	public WebElement getLblLoginErrorMsg1() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg1);
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

	private WebElement getTxtNewPassword() {
		return Constant.WEBDRIVER.findElement(txtNewPassword);
	}

	private WebElement getLblErrorResetToken() {
		return Constant.WEBDRIVER.findElement(lblErrorResetToken);
	}

	private WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(txtConfirmPassword);
	}

	private WebElement getBntResetPassword() {
		return Constant.WEBDRIVER.findElement(bntResetPassword);
	}

	private WebElement getTxtPasswordResetToken() {
		return Constant.WEBDRIVER.findElement(txtPasswordResetToken);
	}

	// METHODS
	public ForgotPasswordPage gotoForgotPasswordPage() {
		this.getLinkForgotPassword().click();
		return new ForgotPasswordPage();
	}

	public void submitLoginForm(String username, String password) {
		this.getTxtUsername().clear();
		this.getTxtUsername().sendKeys(username);
		this.getTxtPassword().sendKeys(password);
		this.getBtnLogin().click();
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
		this.getTxtEmail().sendKeys(email);
		this.getBntSendInstructions().click();
		return this;
	}

	public String getLoginErrorMsg1() {
		return this.getLblLoginErrorMsg1().getText();
	}

	public void submitResetPasswordForm(String newPassword, String confirmPassword) {
		this.getTxtNewPassword().sendKeys(newPassword);
		this.getTxtConfirmPassword().sendKeys(confirmPassword);
		this.getBntResetPassword().click();
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
		this.getTxtPasswordResetToken().clear();
		this.getBntResetPassword().click();
		return this;
	}

	public String getErrorResetToken() {
		return this.getLblErrorResetToken().getText();
	}

	public String getLoginErrorMsg() {
		return this.getLblLoginErrorMsg().getText();
	}
}
