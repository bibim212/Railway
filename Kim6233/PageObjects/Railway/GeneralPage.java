package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Constant;

public class GeneralPage {

	// Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabContact = By.xpath("//div[@id='menu']//a[@href='/Page/Contact.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	private final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
	private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
	private final By tabTicketPrice = By.xpath("//div[@id='menu']//a[@href='/Page/TrainPriceListPage.cshtml']");
	private final By tabTrainTimeable = By.xpath("//a[@href=\"TrainTimeListPage.cshtml\"]");
	private final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
	
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
	private final By lblPasswordChangeForm = By.xpath("//legend[contains(.,'Password Change Form')]");
	private final By lblPageTitle = By.xpath("//body//div[@id = 'content']/h1");

	String welcomeEmail = "//strong[contains(.,' Welcome') and contains(.,'%s')]";

	// Elements
	protected WebElement getLblPageTitle() {
		return Constant.WEBDRIVER.findElement(lblPageTitle);
	}

	public String getPageTitle() {
		return getLblPageTitle().getText();
	}

	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}

	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}

	protected WebElement getTabContact() {
		return Constant.WEBDRIVER.findElement(tabContact);
	}

	protected WebElement getTabBookTicket() {
		return Constant.WEBDRIVER.findElement(tabBookTicket);
	}

	protected WebElement getTabTicketPrice() {
		return Constant.WEBDRIVER.findElement(tabTicketPrice);
	}

	protected WebElement getTabTimeable() {
		return Constant.WEBDRIVER.findElement(tabTrainTimeable);
	}

	protected WebElement getTabChangePassword() {
		return Constant.WEBDRIVER.findElement(tabChangePassword);
	}

	protected WebElement getTabRegister() {
		return Constant.WEBDRIVER.findElement(tabRegister);
	}

	protected WebElement getTabMyTicket() {
		return Constant.WEBDRIVER.findElement(tabMyTicket);
	}

	protected WebElement getLblPasswordChangeForm() {
		return Constant.WEBDRIVER.findElement(lblPasswordChangeForm);
	}

	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}

	// Methods
	
	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}

	public ContactPage gotoContactPage() {
		this.getTabContact().click();
		return new ContactPage();
	}

	public MyTicketPage gotoMyTicketPage() {
		this.getTabMyTicket().click();
		return new MyTicketPage();
	}

	public BookTicketPage gotoBookTicketPage() {
		this.getTabBookTicket().click();
		return new BookTicketPage();
	}

	public RegisterPage gotoRegisterPage() {
		this.getTabRegister().click();
		return new RegisterPage();
	}

	public TicketPricePage gotoTicketPricePage() {
		this.getTabTicketPrice().click();
		return new TicketPricePage();
	}

	public ChangePasswordPage gotoChangePasswordPage() {
		this.getTabChangePassword().click();
		return new ChangePasswordPage();
	}

	public TrainTimablePage gotoTrainTimeablePage() {
		this.getTabTimeable().click();
		return new TrainTimablePage();
	}

	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}

	public boolean isTabMyTicketDisplayed() {
		return this.getTabMyTicket().isDisplayed();
	}

	public boolean isTabChangePasswordDisplayed() {
		return this.getTabChangePassword().isDisplayed();
	}

	public boolean isTabLogoutDisplayed() {
		return this.getTabLogout().isDisplayed();
	}

	// Method for web
	protected WebElement findControl(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception e) {

			return null;
		}
	}

	public static void waitControlDisplayed(WebElement ele, int time_out) {
		new WebDriverWait(Constant.WEBDRIVER, time_out).until(ExpectedConditions.visibilityOf(ele));
	}

	protected void confirmPopup() {
		Constant.WEBDRIVER.switchTo().alert().accept();
	}

	protected boolean isElementDisplay(WebElement el) {
		boolean isPresent = false;

		if (el.isDisplayed()) {
			isPresent = true;
		}
		return isPresent;
	}

	public boolean isWebElementExist(By locator) {
		try {
			return Constant.WEBDRIVER.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
