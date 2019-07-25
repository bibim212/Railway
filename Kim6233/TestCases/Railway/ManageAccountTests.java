package Railway;

import org.testng.annotations.Test;

import Base.BaseTest;
import Common.AssertThat;
import Helper.GMailAPIHelper;

public class ManageAccountTests extends BaseTest {

	@Test
	public void TC07_User_can_create_new_account() {
		logHelper.logInfo("TC07 - User can create new account");

		logHelper.logStep("1- Navigate to QA Railway Website");
		logHelper.logStep("2- Click on \"Register\" tab");
		logHelper.logStep("3- Enter valid information into all fields");
		logHelper.logStep("4- Click on \"Register\" button");
		hp.openRailway();

		RegisterPage rp = hp.gotoRegisterPage().registerAccount();

		logHelper.logVerify("Message \"Thank you for registering your account\" appears");
		String actualMsg = rp.getSuccessMsgText();
		String expectedMsg = "Thank you for registering your account";
		AssertThat.verifyEquals(actualMsg, expectedMsg,
				"User is logged into Railway. Welcome user message is displayed.");

		AssertThat.verifyAll();
	}

	@Test
	public void TC10_User_cannot_reset_password_if_enter_incorrect_email_address() {
		logHelper.logInfo("TC10 - User can't reset password if enter incorrect email address");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Click on \"Forgot Password page\" link");
		logHelper.logStep("3. Enter an un-existing email address");
		logHelper.logStep("4. Click on \"Send Instructions\" button");

		hp.openRailway().gotoLoginPage().gotoForgotPasswordPage();

		LoginPage lg = new LoginPage();
		lg.sendResetPasswordInstructions("incorrect@mail.com");

		logHelper.logVerify("Error message \"This email address doesn't exist.\" displays.");
		String actualMsg = lg.getLoginErrorMsg();
		String expectedMsg = "This email address doesn't exist.";

		AssertThat.verifyEquals(actualMsg, expectedMsg, "Invalid username or password");

		AssertThat.verifyAll();
	}

	@Test
	public void TC11_User_cannot_create_account_while_password_and_PID_fields_are_empty() {
		logHelper.logInfo("User can't create account while password and PID fields are empty");

		logHelper.logStep("1- Navigate to QA Railway Website");
		logHelper.logStep("2- Click on \"Register\" tab");
		logHelper.logStep("3- Enter valid email address and leave other fields empty");
		logHelper.logStep("4- Click on \"Register\" button");

		hp.openRailway();

		RegisterPage rp = hp.gotoRegisterPage().registerFail(UserAccount._email, "", "", "");

		logHelper.logVerify(
				"\"There're errors in the form. Please correct the errors and try again.\" appears above the form.");
		logHelper.logVerify("Next to password fields, error message 'Invalid password length.' displays");
		logHelper.logVerify("Next to PID field, error message 'Invalid ID length.' displays");

		String actualCreateAccountMsg = rp.getRegisterError();
		String actualPasswordMsg = rp.getRegisterPasswordError();
		String actualPIDMsg = rp.getRegisterPIDError();

		String expectedCreateAccountMsg = "There're errors in the form. Please correct the errors and try again.";
		String expectedPasswordMsg = "Invalid password length";
		String expectedPIDMsg = "Invalid ID length";

		AssertThat.verifyEquals(actualCreateAccountMsg, expectedCreateAccountMsg,
				"Message 'There're errors in the form. Please correct the errors and try again.' appears above the form.");
		AssertThat.verifyEquals(actualPasswordMsg, expectedPasswordMsg,
				"Next to password fields, error message 'Invalid password length.' displays");
		AssertThat.verifyEquals(actualPIDMsg, expectedPIDMsg,
				"Next to PID field, error message 'Invalid ID length.' displays");
		AssertThat.verifyAll();
	}

	@Test
	public void FTTC02_User_can_also_active_new_account_using_activate_code() {

		logHelper.logInfo("FTTC02 - User can also active new account using activate code");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Register new account with valid values");

		hp.openRailway();
		GMailAPIHelper gh = new GMailAPIHelper();
		hp.gotoRegisterPage().registerAccount();

		logHelper.logStep("3. Open mailbox and open the activation email");
		logHelper.logStep("4. Get the activation code");

		String activationCode = gh.getActivationCode(UserAccount._email);

		logHelper.logStep("5. Back to Register Page, click on \"here\" link to enter the activation code");
		logHelper.logStep("6. Enter the activation code from step 4 and click on \"Confirm\" button");
		hp.openRailway().gotoRegisterPage().goToConfirmCode().confirmCode(activationCode);

		logHelper.logVerify("Message \"Registration Confirmed! You can now log in to the site.\" displays");

		RegisterConfirmedPage rcp = new RegisterConfirmedPage();

		Boolean dConfirm = rcp.isLblConfirmDisplayed();

		AssertThat.verifyTrue(dConfirm, "Message \"Registration Confirmed! You can now log in to the site.\" displays");

		logHelper.logStep("7. Login with this account");

		rcp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password);

		logHelper.logVerify("User is logged into Railway. Welcome user message is displayed.");

		String actualMsg = hp.getWelcomeMessage();
		String expectedMsg = "Welcome " + UserAccount._email;
		AssertThat.verifyEquals(actualMsg, expectedMsg, "Welcome user message is displayed.");

		AssertThat.verifyAll();
	}
}
