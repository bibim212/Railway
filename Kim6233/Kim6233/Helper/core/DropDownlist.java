package core;

import org.openqa.selenium.support.ui.Select;

public class DropDownlist extends Element {

	public DropDownlist(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	Select se = new Select(findElementBy());

	public void selectItemBy(String by, String s) {
		switch (by) {
		case "ByVisibleText":
			se.selectByVisibleText(s);
			break;
		case "ByValue":
			se.selectByValue(s);
		}
	}

	public void selectByIndex(int i) {
		se.selectByIndex(i);
	}

	public void deselectBy(String by, String s) {
		switch (by) {
		case "ByVisibleText":
			se.deselectByVisibleText(s);
			break;
		case "ByValue":
			se.deselectByValue(s);
		}
	}
	
	public String getFirstSelectedOption() {
		return se.getFirstSelectedOption().getText();
	}
}
