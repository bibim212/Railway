package Railway;

import org.testng.annotations.Test;

import Base.BaseTest;
import Common.AssertThat;
import Common.Constant;

public class LoginTests extends BaseTest {

	@Test
	public void TC01_User_can_log_into_Railway_with_valid_username_and_password() {
		logHelper.logInfo("TC01 - Login successfully with valid credentials");

		logHelper.logStep("1- Navigate to Railway site");
		logHelper.logStep("2- Click on \"Login\" tab");
		logHelper.logStep("3- Enter valid Email and Password");
		logHelper.logStep("4- Click on \"Login\" button");
		hp.openRailway().gotoLoginPage().loginRailway(Constant.RAILWAY_USERNAME, Constant.RAILWAY_PASSWORD);

		logHelper.logVerify("User is logged into Railway. Welcome user message is displayed.");
		String actualMsg = hp.getWelcomeMessage();
		String expectedMsg = String.format("Welcome %s", Constant.RAILWAY_USERNAME);
		AssertThat.verifyEquals(actualMsg, expectedMsg,
				"User is logged into Railway. Welcome user message is displayed.");

		AssertThat.verifyAll();
	}

	@Test
	public void TC02_User_cannot_login_with_blank_Username_textbox() {
		logHelper.logInfo("TC02 - Login fails when leaving Username as blank");

		logHelper.logStep("1- Navigate to Railway site");
		logHelper.logStep("2- Click on \"Login\" tab");
		logHelper.logStep("3- Leave Username as blank, enter Password");
		logHelper.logStep("4- Click on \"Login\" button");
		hp.openRailway();

		LoginPage loginPage = hp.gotoLoginPage().loginFail("", Constant.RAILWAY_PASSWORD);

		logHelper.logVerify(
				"User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");
		String actualMsg = loginPage.getLoginErrorMsg1();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		AssertThat.verifyEquals(actualMsg, expectedMsg, "Unable to login and warning message appears.");

		AssertThat.verifyAll();
	}

	@Test
	public void TC03_User_cannot_log_into_Railway_with_invalid_password() {
		logHelper.logInfo("TC03 - Login fails when leaving Password as blank");

		logHelper.logStep("1- Navigate to Railway site");
		logHelper.logStep("2- Click on \"Login\" tab");
		logHelper.logStep("3- Enter Username and leave Password as blank");
		logHelper.logStep("4- Click on \"Login\" button");
		hp.openRailway();

		LoginPage lp = hp.gotoLoginPage().loginFail(Constant.RAILWAY_USERNAME, "INVALID_PASSWORD");

		logHelper.logVerify(
				"User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");

		String actualMsg = lp.getLoginErrorMsg();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		AssertThat.verifyEquals(actualMsg, expectedMsg, "Unable to login and warning message appears.");

		// report bug
		if (actualMsg != expectedMsg)
			logHelper.printBug("Bug_TC03_01",
					"'There was a problem with your login and/or errors exits in your form' message does not appear");

		AssertThat.verifyAll();
	}

	@Test
	public void TC05_System_shows_message_when_user_enters_wrong_password_several_times() {
		logHelper.logInfo("TC05 - System shows message when user enters wrong password several times");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Click on \"Login\" tab");
		logHelper.logStep("3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
		logHelper.logStep("4. Click on \"Login\" button");
		logHelper.logStep("5. Repeat step 3 three more times.");
		hp.openRailway();

		LoginPage lp = hp.gotoLoginPage().loginAttempts(4, Constant.RAILWAY_USERNAME, "attempts");

		logHelper.logVerify(
				"User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");

		String actualMsg = lp.getLoginErrorMsg();
		String expectedMsg = "User can't login and message \\\"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\\\" appears.";
		AssertThat.verifyEquals(actualMsg, expectedMsg, "Unable to login and warning message appears.");

		// report bug
		if (actualMsg != expectedMsg)
			logHelper.printBug("Bug_TC05_01",
					"'You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.' message does not appear");

		AssertThat.verifyAll();
	}

	@Test
	public void TC06_Additional_pages_display_once_user_logged_in() {
		logHelper.logInfo("TC06 - Additional pages display once user logged in");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Click on \"Login\" tab");
		logHelper.logStep("3. Login with valid account");
		hp.openRailway().gotoLoginPage().loginRailway(Constant.RAILWAY_USERNAME, Constant.RAILWAY_PASSWORD);

		AssertThat.verifyTrue(hp.isTabChangePasswordDisplayed(), "'Change Password' Tab does not display as expected");
		AssertThat.verifyTrue(hp.isTabMyTicketDisplayed(), "'MyTicket' tab does not display as expected");
		AssertThat.verifyTrue(hp.isTabLogoutDisplayed(), "'Logout' tab does not display as expected");

		logHelper.logStep("4- Click \"My ticket\" tab");
		hp.gotoMyTicketPage();

		logHelper.logVerify("User will be directed to My ticket page");
		MyTicketPage mtp = new MyTicketPage();

		AssertThat.verifyEquals(mtp.getPageTitle(), "Manage ticket", "My Ticket Page does not display as expected");

		logHelper.logStep("Click \"Change password\" tab");
		mtp.gotoChangePasswordPage();

		ChangePasswordPage cpp = new ChangePasswordPage();
		AssertThat.verifyEquals(cpp.getPageTitle(), "Change password",
				"Change password Page does not display as expected");
		AssertThat.verifyAll();
	}

}
