package core;

import org.openqa.selenium.support.ui.Select;

public class DropDownlist extends Element {
	Select se = new Select(findElementBy());

	public DropDownlist(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	public void selectItemByVisibleText(String s) {
		se.selectByVisibleText(s);
	}

	public void selectByIndex(int i) {
		se.selectByIndex(i);
	}

	public void deselectByVisibleText(String s) {
		se.deselectByVisibleText(s);
	}

	public String getFirstSelectedOption() {
		return se.getFirstSelectedOption().getText();
	}
}
