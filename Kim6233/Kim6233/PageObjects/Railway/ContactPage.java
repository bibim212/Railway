package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Constant;

public class ContactPage extends GeneralPage{
	// Locators
	private final By lblEmailAddress = By.xpath("//a[@href='mailto:thanh.viet.le@logigear.com']");

	// Elements

	public WebElement getLblEmailAddress() {
		return Constant.WEBDRIVER.findElement(lblEmailAddress);
	}

	// Methods

	public String getEmailAddress() {
		return this.getLblEmailAddress().getText();
	}

}
