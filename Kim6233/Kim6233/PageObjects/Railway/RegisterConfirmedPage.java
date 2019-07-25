package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Constant;

public class RegisterConfirmedPage extends GeneralPage {
	// Locators
	
	private final By lblConfirmed = By.xpath("//div[@id='content']//p");

	// Elements
	public WebElement getLblConfirmed() {
		return Constant.WEBDRIVER.findElement(lblConfirmed);
	}

	// Methods
	/**
	 * This is returned the existence of Confirmation label after registering and activating successfully
	 * @return true this label is existed
	 */
	public boolean isLblConfirmDisplayed() {
		boolean d = isWebElementExist(lblConfirmed);
		System.out.println(d);
		if (d == true) {
			return true;
		}
		return false;
	}
}
