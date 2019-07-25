package Common;



public class PathHelper {
	public static String getProjectPath() {
		String workingDir = System.getProperty("user.dir");
		return workingDir;
	}

	/*public static void waitControlDisplayed(WebElement ele, int time_out) {
		new WebDriverWait(Constant.WEBDRIVER, time_out).until(ExpectedConditions.visibilityOf(ele));
	}*/
}
