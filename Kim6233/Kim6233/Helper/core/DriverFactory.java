package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.PathHelper;

public class DriverFactory {
	private static WebDriver _driver;

	public static WebDriver getDriver() {
		return _driver;
	}

	public static void setDriver(WebDriver driver) {
		_driver = driver;
	}

	public static void webDriverDefinition() {
		if (_driver == null) {
			String chromePath = "\\drivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", PathHelper.getProjectPath() + chromePath);
			_driver = new ChromeDriver();
		}
	}

	public static void open(String url) {
		_driver.get(url);
	}

	public void close() {
		_driver.close();
	}

	public void quit() {
		_driver.quit();
	}

	public static void maximize() {
		_driver.manage().window().maximize();
	}

	/**
	 * This method makes the driver wait implicitly
	 **/
	public static void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	/**
	 * This method makes the driver wait till the web element is located
	 **/
	public static void explicitWait(WebElement wb, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(wb));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method handles alert windows
	 **/
	public static void handleAlert(String status, WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		if (status.equalsIgnoreCase("Y")) {
			alert.accept();
		} else if (status.equalsIgnoreCase("N")) {
			alert.dismiss();
		}
	}

	public static WebElement findElement(By _by) {
		return _driver.findElement(_by);
	}
}
