package sandbox;

import Common.Constant;
import Railway.GeneralPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Temporarily extends GeneralPage {
	protected void switchWindow() {
		for (String winHandle : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(winHandle);
		}
	}

	protected void closeWindow() {
		Constant.WEBDRIVER.close();
	}

	public static void openNewPage(String newurl) {
		((JavascriptExecutor) Constant.WEBDRIVER).executeScript(String.format("window.open('%s','_blank');", newurl));

	}

	private int _timeOut = 60;

	protected WebElement waitForElement(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, _timeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception e) {
			return null;
		}
	}

	private void wait(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	protected WebElement waitForElement(By locator) {
		while (_timeOut > 0) {
			if (waitForElementDisplay(locator)) {
				return Constant.WEBDRIVER.findElement(locator);
			}
			wait(1);
			_timeOut--;
		}
		return null;
	}

	protected boolean isExist(By locator) {
		// check if element exist;
		if (Constant.WEBDRIVER.findElement(locator) == null) {
			return false;
		}
		return true;
	}

	protected boolean waitForElementDisplay(By locator) {
		// check if element exist;
		while (_timeOut > 0) {
			if (isExist(locator)) {
				if (Constant.WEBDRIVER.findElement(locator).isDisplayed()) {
					return true;
				}
			}
			wait(1);
			_timeOut--;
		}
		return false;
	}

	protected void waitForClickAble(By by) {
		int timeout = 60;
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
}
