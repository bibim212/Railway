package Base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Common.Constant;
import Helper.LogHelper;
import Railway.HomePage;
import core.DriverFactory;

public class BaseTest {
	protected LogHelper logHelper = new LogHelper(this.getClass());
	protected HomePage hp = new HomePage();

	@BeforeMethod
	public void setUp() {
		DriverFactory.webDriverDefinition();
		DriverFactory.maximize();
	}

	@AfterMethod
	public void afterMethod() {
		logHelper.logPostCondition();
		Constant.WEBDRIVER.quit();
	}
}
