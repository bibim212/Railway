package ToolsQA;

import core.Link;

public class GeneralPage {
	private Link lnkHomePage = new Link("//a[@href='https://demoqa.com/'");	
	private Link lnkInteractions = new Link("//a[contains(@href,'interactions')]");
	private Link lnkTooltipDoubleClick = new Link("//li[@class='menu-item']//a[contains(@href,'tooltip-and-double-click')]");
	private Link lnkWidgets = new Link("//a[contains(@href,'widgets')]");
	
	public HomePage gotoHomePage() {
		lnkHomePage.click();
		return new HomePage();
	}

	public InteractionsPage gotoInteractionsPage() {
		lnkInteractions.click();
		return new InteractionsPage();
	}
	
	public TooltipDoubleClickPage gotoToolTipDoubleClickPage() {
		lnkTooltipDoubleClick.click();
		return new TooltipDoubleClickPage();
	}
	
	public WidgetsPage gotoWidgetsPage() {
		lnkWidgets.click();
		return new WidgetsPage();
	}
	
}
