package NewRailway;

import core.Label;

public class ContactPage extends GeneralPage {

	/*********** LOCATORS ***********/
	private Label lblEmailAddress = new Label("//a[@href='mailto:thanh.viet.le@logigear.com']");

	/*********** METHODS ***********/
	public String getEmailAddress() {
		return lblEmailAddress.getText();
	}
}
