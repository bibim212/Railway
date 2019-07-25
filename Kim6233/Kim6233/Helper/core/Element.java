package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Constant;

public class Element {
	WebDriver driver;
	private static By _by;

	public Element(String string) {
		_by = By.xpath(string);
	}

	protected WebElement findElementBy() {
		return DriverFactory.findElement(_by);
	}

	public void click() {
		findElementBy().click();
	}

	public boolean isDisplayed() {
		return findElementBy().isDisplayed();
	}

	public String getAttribute(String value) {
		return findElementBy().getAttribute(value);
	}

	public void waitControlDisplayed(WebElement e, int time_out) {
		new WebDriverWait(driver, time_out).until(ExpectedConditions.visibilityOf(e));
	}
	
	public boolean isElementDisplay(WebElement e) {
		boolean isPresent = false;
		if (e.isDisplayed()) {
			isPresent = true;
		}
		return isPresent;
	}

}
