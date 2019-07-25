package Common;

import org.testng.asserts.SoftAssert;

public class AssertThat {
	public static SoftAssert softAssert = new SoftAssert();

	public static void verifyEquals(Object actual, Object expected, String message) {
		System.out.println(actual + " | " + expected);
		softAssert.assertEquals(actual, expected, message);
	}

	public static void verifyTrue(boolean condition, String message) {
		softAssert.assertTrue(condition, message);
	}

	public static void verifyAll() {
		softAssert.assertAll();
	}
}
