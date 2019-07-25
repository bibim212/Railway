package Railway;

import org.testng.annotations.Test;

import Base.ActiveAccountBaseTest;
import Common.AssertThat;
import Common.Constant;

public class ManageAccountWithActivatedAccount extends ActiveAccountBaseTest {

	@Test
	public void TC09_User_can_reset_password_successfully() {
		logHelper.logInfo("TC09 - User can reset password successfully");

		logHelper.logInfo("Pre-Condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Click on \"Forgot Password page\" link");
		logHelper.logStep("3. Enter the email address of the created account in Pre condition");
		logHelper.logStep("4. Click on \"Send Instructions\" button");
		logHelper.logStep("5. Navigate to mailbox and open the Please reset your password email");
		logHelper.logStep("6. Click on the reset password link");
		logHelper.logStep("7. Enter a new password for both password fields");
		logHelper.logStep("8. Click \"Reset Password\" button");

		String EmailAddress = UserAccount._email;

		hp.gotoLoginPage();

		ForgotPasswordPage fpp = hp.gotoLoginPage().gotoForgotPasswordPage().forgotPassword(EmailAddress);

		LoginPage lp = fpp.recoverPassword(EmailAddress);
		ChangePasswordPage cpp = lp.resetPassword(Constant.RAILWAY_Reset_Password, Constant.RAILWAY_Reset_Password);

		logHelper.logVerify("Message'Password changed! Click here to login.' displays.");
		AssertThat.verifyEquals(cpp.checkSuccessMessageDisplayed(), true,
				"message \"Password changed! Click here to login.\" displays. ");

		cpp.gotoLoginPage().loginRailway(EmailAddress, Constant.RAILWAY_Reset_Password);

		logHelper.logVerify("User can login with new password");
		String actualMsg = hp.getWelcomeMessage();

		String expectedMsg = String.format("Welcome %s", UserAccount._email);
		AssertThat.verifyEquals(actualMsg, expectedMsg,
				"User is logged into Railway. Welcome user message is displayed.");

		AssertThat.verifyAll();
	}

	@Test
	public void TC12_Errors_display_when_password_reset_token_is_blank() {
		logHelper.logInfo("TC12 - Errors display when password reset token is blank");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Click on 'Forgot Password page' link");
		logHelper.logStep("3. Enter the email address of the created account in Precondition");
		logHelper.logStep("4. Click on 'Send Instructions' button");
		logHelper.logStep("5. Open mailbox and click on reset password link");

		String EmailAddress = UserAccount._email;

		hp.gotoLoginPage();

		ForgotPasswordPage fpp = hp.gotoLoginPage().gotoForgotPasswordPage().forgotPassword(EmailAddress);
		fpp.recoverPassword(EmailAddress);

		ChangePasswordPage cpp = new ChangePasswordPage();

		logHelper.logVerify("Password Change Form\" page displays");

		boolean isPwdChangeForm = cpp.isPasswordChangeForm();
		AssertThat.verifyTrue(isPwdChangeForm, "\"Password Change Form\" page displays");

		logHelper.logStep("6. Enter new passwords and remove the Password Reset Token");
		logHelper.logStep("7. Click 'Reset Password' button");

		cpp.resetInvalidValue(Constant.RAILWAY_Reset_Password);

		Boolean isTokenEmpty = cpp.isPasswordResetTokenEmpty();
		AssertThat.verifyTrue(isTokenEmpty, "Password Reset Token is unable to clear");

		if (isTokenEmpty == false) {
			logHelper.printBug("Bug #03", "User cannot remove the Password Reset Token");
		}

		logHelper.logVerify(
				"Error message \"The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.\" displays above the form.");
		logHelper.logVerify(
				"Error message \"The password reset token is invalid.\" displays next to the \"Password Reset Token\" field.");

		LoginPage lp = new LoginPage();
		String actualTokenResetFormMsg = lp.getLoginErrorMsg();
		String expectedTokenResetFormMsg = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";
		String actualTokenResetFieldMsg = lp.getErrorResetToken();
		String expectedTokenResetFieldMsg = "The password reset token is invalid";
		AssertThat.verifyEquals(actualTokenResetFormMsg, expectedTokenResetFormMsg,
				"The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.");
		AssertThat.verifyEquals(actualTokenResetFieldMsg, expectedTokenResetFieldMsg,
				"The password reset token is invalid");
		AssertThat.verifyAll();

	}
}
