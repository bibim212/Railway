package Railway;

import Common.Constant;
import Helper.StringHelper;

public class UserAccount {
	// The instance variables
	public static String _email;
	public static String _password;
	public static String _pID;

	// The constructor
	public UserAccount(String email, String password, String pId) {
		_email = email;
		_password = password;
		_pID = pId;
	}

	// The public getters and setters for the private instance variables.
	// No setter for name and gender as they are not designed to be changed.
	public String getEmail() {
		return _email;
	}

	public String getPassword() {
		return _password;
	}

	public String getPid() {
		return _pID;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public void setPassword(String password) {
		_password = password;
	}

	// The toString() describes itself
	@Override
	public String toString() {
		return _email + " (" + _password + ") at " + _pID;
	}

	
	public UserAccount() {
		_email = StringHelper.generateEmailAddress();
		_password = Constant.RAILWAY_PASSWORD;
		_pID = Constant.RAILWAY_PID;

		System.out.println("Initial Random Email: "+ _email);
	}
}
