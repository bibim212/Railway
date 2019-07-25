package Railway;

import org.openqa.selenium.By;

import Helper.GMailAPIHelper;

public class RegisterPage extends GeneralPage {

	// LOCATORS
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtPID = By.xpath("//input[@id='pid']");
	private final By btnRegister = By.xpath("//input[@value='Register']");
	private final By lblCreateAccountError = By.xpath("//p[@class='message error']");
	private final By lblSuccessMsg = By.xpath("//h1[.='Thank you for registering your account']");
	private final By lblRegisterPasswordError = By
			.xpath("//li[@class='password']//following-sibling::label[@class = \"validation-error\"]");
	private final By lblRegisterPIDError = By
			.xpath("//li[@class='pid-number']//following-sibling::label[@class = \"validation-error\"]");
	private final By txtConfirmationCode = By.xpath("//input[@id='confirmationCode']");
	private final By lnkConfirmCode = By.xpath("//a[@href='Confirm.cshtml']");
	private final By btnConfirmCode = By.xpath("//input[@value='Confirm']");

	// ELEMENTS

	protected WebElement getTxtEmail() {
		return Constant.WEBDRIVER.findElement(txtEmail);
	}

	protected WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(txtPassword);
	}

	protected WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(txtConfirmPassword);
	}

	protected WebElement getTxtPID() {
		return Constant.WEBDRIVER.findElement(txtPID);
	}

	protected WebElement getBtnRegister() {
		return Constant.WEBDRIVER.findElement(btnRegister);
	}

	protected WebElement getLblSuccessMsg() {
		return Constant.WEBDRIVER.findElement(lblSuccessMsg);
	}

	protected WebElement getLblCreateAccountError() {
		return Constant.WEBDRIVER.findElement(lblCreateAccountError);
	}

	protected WebElement getLblRegisterPasswordError() {
		return Constant.WEBDRIVER.findElement(lblRegisterPasswordError);
	}

	protected WebElement getLblRegisterPIDError() {
		return Constant.WEBDRIVER.findElement(lblRegisterPIDError);
	}

	protected WebElement getTxtConfirmationCode() {
		return Constant.WEBDRIVER.findElement(txtConfirmationCode);
	}

	protected WebElement getLnkConfirmCode() {
		return Constant.WEBDRIVER.findElement(lnkConfirmCode);
	}

	protected WebElement getBtnConfirmCode() {
		return Constant.WEBDRIVER.findElement(btnConfirmCode);
	}

	// METHODS
	public String getSuccessMsgText() {
		return this.getLblSuccessMsg().getText();
	}

	public String getRegisterError() {
		return this.getLblCreateAccountError().getText();
	}

	public String getRegisterPasswordError() {
		return this.getLblRegisterPasswordError().getText();
	}

	public String getRegisterPIDError() {
		return this.getLblRegisterPIDError().getText();
	}

	/**
	 * This is used to entering data to Register Account form
	 * 
	 * @param username
	 * @param password
	 * @param confirmPassword
	 * @param pID
	 */
	public void submitRegisterAccountForm(String username, String password, String confirmPassword, String pID) {
		System.out.println("Account for registering: " + username);
		this.getTxtEmail().sendKeys(username);
		this.getTxtPassword().sendKeys(password);
		this.getTxtConfirmPassword().sendKeys(password);
		this.getTxtPID().sendKeys(pID);
		this.getBtnRegister().click();
	}

	public RegisterPage registerFail(String username, String password, String confirmPassword, String pId) {
		submitRegisterAccountForm(username, password, confirmPassword, pId);
		return this;
	}

	/**
	 * This is used to register an new account after initialing account info
	 * 
	 * @return
	 */
	public RegisterPage registerAccount() {
		UserAccount user = new UserAccount();
		submitRegisterAccountForm(user.getEmail(), user.getPassword(), user.getPassword(), user.getPid());
		return this;
	}

	public RegisterPage goToConfirmCode() {
		this.getLnkConfirmCode().click();
		return new RegisterPage();
	}

	public RegisterPage confirmCode(String code) {
		this.getTxtConfirmationCode().sendKeys(code);
		this.getBtnConfirmCode().click();
		return new RegisterPage();
	}

	public RegisterPage activateAccountAPI(String registeredEmail) {
		GMailAPIHelper api = new GMailAPIHelper();
		String activationlink = api.getActivationLink(registeredEmail);
		Constant.WEBDRIVER.get(activationlink);
		return new RegisterPage();
	}
}
