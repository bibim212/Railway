package Railway;

import Common.Constant;

public class HomePage extends GeneralPage{
	public HomePage openRailway() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}

	
	public HomePage openURL(String url) {
		Constant.WEBDRIVER.navigate().to(url);
		return this;
	}
}
