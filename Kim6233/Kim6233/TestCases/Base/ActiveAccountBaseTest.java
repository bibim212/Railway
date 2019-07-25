package Base;

import org.testng.annotations.BeforeMethod;

import Railway.RegisterPage;
import Railway.UserAccount;

public class ActiveAccountBaseTest extends BaseTest {

	@BeforeMethod
	public void CreateAndActiveAccountBeforeMethod() {
		hp.openRailway();
		RegisterPage rp = hp.gotoRegisterPage().registerAccount();
		rp.activateAccountAPI(UserAccount._email);
	}
}
