package Helper;

import java.util.Random;

public class IntHelper {
	/**
	 * This is used for generating random integer with input min and max
	 * @param min
	 * @param max
	 * @return int
	 */
	public static int generateRandomIntRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
