package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

public class LogHelper {
	private Logger _log = LogManager.getLogger();
	private String _className;

	public LogHelper(Class<?> className) {
		_className = className.getName() + " - ";
	}

	public LogHelper() {
		_className = "";
	}

	public void logInfo(String message) {
		_log.info(_className + message);
		Reporter.log("<b>INFO: </b>" + message);
		System.out.println(message);
		// ExtentTestManager.getTest().log(Status.INFO, "<span
		// style=word-break:break-word>" + message + "</span>");
	}

	public void logPostCondition() {
		_log.info(_className, "Post-condition: Close browser");
		Reporter.log("Post-Condition: Close browser");
		System.out.println("Post-condition");
	}

	public void logError(String message) {
		_log.error(_className + message);
		Reporter.log("<b>Error: </b>" + message);
		System.out.println("Error: " + message);
		// ExtentTestManager.getTest().log(Status.INFO, "<span
		// style=word-break:break-word>" + message + "</span>");
	}

	public void logVerify(String message) {
		Reporter.log("<b style=\"color: #44aa44;\">VERIFY POINT: </b>" + message);
		_log.info("VERIFY POINT: " + message);
		System.out.println("VP: " + message);
	}

	public void logStep(String message) {
		Reporter.log("STEP: </b>" + message);
		_log.info("STEP: " + message);
		System.out.println("STEP: " + message);
	}

	public void printBug(String bugId, String bugSummary) {
		String bugInfo = String.format(" %s-%s ", bugId, bugSummary);
		_log.info(bugInfo);
		System.out.println(bugInfo);
		String msg = "<a target=\"_blank\" href=\"" + bugSummary
				+ "\" style=\"color:#DF0101;font-size:14px;word-break:break-word;\">" + bugInfo + "</a>";
		Reporter.log("BUG: " + msg);
	}
}
