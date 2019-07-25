package Railway;

import org.testng.annotations.Test;

import Base.BaseTest;
import Common.AssertThat;

public class ContactTests extends BaseTest {

	@Test
	public void TC04_Contact_Email_contains_correct_href_value_which_can_help_to_quickly_open_Outlook_Compose_Message_dialog() {
		logHelper.logInfo(
				"TC_04 - Contact Email contains correct href value which can help to quickly open Outlook Compose Message dialog");

		logHelper.logInfo("1. Navigate to QA Railway Website");
		logHelper.logInfo("2. Click on \"Contact\" tab");
		hp.openRailway().gotoContactPage();

		logHelper.logInfo("3. Check the email address");
		logHelper.logVerify("Email address's href is \"mailto:thanh.viet.le@logigear.com\"");

		ContactPage cp = new ContactPage();
		String actualMsg = cp.getEmailAddress();

		String expectedMsg = "thanh.viet.le@logigear.com";
		AssertThat.verifyEquals(actualMsg, expectedMsg, "Email address is 'mailto:thanh.viet.le@logigear.com'");
		AssertThat.verifyAll();
	}
}
