package Railway;

import org.testng.annotations.Test;

import Base.InactiveAccountBaseTest;
import Common.AssertThat;

public class ManageAccountWithInactivatedAccount extends InactiveAccountBaseTest {

	@Test
	public void TC08_User_cannot_login_with_an_account_hasnot_been_activated() {
		LoginPage loginPage = new LoginPage();
		logHelper.logInfo("TC08 - User can't login with an account hasn't been activated");

		logHelper.logInfo("Pre-Condition: Create a new account but do not activate it");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Click on \"Login\" tab");
		logHelper.logStep("3. Enter username and password of account hasn't been activated.");
		logHelper.logStep("4. Click on \"Login\" button");
		hp.openRailway().gotoLoginPage().loginFail(UserAccount._email, UserAccount._password);

		logHelper
				.logVerify("User can't login and message \"Invalid username or password. Please try again.\" appears.");
		String actualMsg = loginPage.getLoginErrorMsg1();
		String expectedMsg = "Invalid username or password. Please try again.";
		AssertThat.verifyEquals(actualMsg, expectedMsg, "Unable to login and warning message appears.");

		AssertThat.verifyAll();
	}
}
