package core;

public class PathHelper {
	public static String getProjectPath() {
		String workingDir = System.getProperty("user.dir");
		return workingDir;
	}
}
