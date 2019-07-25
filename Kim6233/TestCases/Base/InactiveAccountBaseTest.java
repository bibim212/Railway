package Base;

import org.testng.annotations.BeforeMethod;

public class InactiveAccountBaseTest extends BaseTest {
	@BeforeMethod
	public void CreateAccountBeforeMethod() {
		hp.openRailway().gotoRegisterPage().registerAccount();
	}
}
