package NewBase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import NewRailway.HomePage;
import core.DriverFactory;
import core.LogHelper;

public class BaseTest {
	protected LogHelper logHelper = new LogHelper(this.getClass());
	protected DriverFactory driver = new DriverFactory();
	protected HomePage hp = new HomePage();

	@BeforeMethod
	public void setUp() {
		DriverFactory.webDriverDefinition();
		DriverFactory.maximize();
		
		//homepage.openRailway();
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}
