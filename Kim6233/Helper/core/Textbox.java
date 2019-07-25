package core;

public class Textbox extends Element {

	public Textbox(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	public void sendKeys(String s) {
		clear();
		findElementBy().sendKeys(s);
	}
	
	public void clear() {
		findElementBy().clear();
	}
}
