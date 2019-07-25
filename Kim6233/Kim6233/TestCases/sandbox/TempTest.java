package sandbox;

import org.testng.annotations.Test;

import Base.BaseTest;
import Common.AssertThat;
import Railway.MyTicketPage;
import Railway.NewTicket;

public class TempTest extends BaseTest {
	// @Test
	public void TC_DepartDate() {
		String url = "file:///E:/Selenium/Railway/Safe%20Railway%20-%20My%20Ticket.html";
		String searchField = "Depart Date";

		String searchValue = "7/26/2019";
		System.out.println("Row 14: " + searchField + " | " + searchValue);
		hp.openURL(url);

		MyTicketPage mtk = new MyTicketPage();

		int row = mtk.numberOfRowMyTicket();
		if (row >= 1) {
			AssertThat.verifyEquals(mtk.checkSearchResult(searchField, searchValue), true, "Inconsistent data");
		} else {
			String expected = "Sorry, can't find any results that match your filters.\r\nPlease change the filters and try again.";
			AssertThat.verifyEquals(mtk.getMsgNoResult().getText(), expected, "Test case is failed");
		}
		AssertThat.verifyAll();

	}

	// @Test
	public void TC_DepartStation() {
		String url = "file:///E:/Selenium/Railway/Safe%20Railway%20-%20My%20Ticket.html";
		String searchField = "Depart Station";

		String searchValue = "Sài Gòn";
		System.out.println("Row 14: " + searchField + " | " + searchValue);
		hp.openURL(url);

		MyTicketPage mtk = new MyTicketPage();

		int row = mtk.numberOfRowMyTicket();
		if (row >= 1) {
			AssertThat.verifyEquals(mtk.checkSearchResult(searchField, searchValue), true, "Inconsistent data");
		} else {
			String expected = "Sorry, can't find any results that match your filters.\r\nPlease change the filters and try again.";
			AssertThat.verifyEquals(mtk.getMsgNoResult().getText(), expected, "Test case is failed");
		}
		AssertThat.verifyAll();

	}

	// @Test
	public void TC_ArriveStation() {
		String url = "file:///E:/Selenium/Railway/Safe%20Railway%20-%20My%20Ticket.html";
		String searchField = "Arrive Station";

		String searchValue = "Phan Thiết";
		System.out.println("Search by: " + searchField + " | " + searchValue);
		hp.openURL(url);

		MyTicketPage mtk = new MyTicketPage();

		int row = mtk.numberOfRowMyTicket();
		if (row >= 1) {
			AssertThat.verifyEquals(mtk.checkSearchResult(searchField, searchValue), true, "Inconsistent data");
		} else {
			String expected = "Sorry, can't find any results that match your filters.\r\nPlease change the filters and try again.";
			AssertThat.verifyEquals(mtk.getMsgNoResult().getText(), expected, "Test case is failed");
		}
		AssertThat.verifyAll();

	}

	// @Test
	public void TC_Status() {
		String url = "file:///E:/Selenium/Railway/Safe%20Railway%20-%20My%20Ticket.html";
		String searchField = "Status";

		String searchValue = "Paid";
		System.out.println("Search by: " + searchField + " | " + searchValue);
		hp.openURL(url);

		MyTicketPage mtk = new MyTicketPage();

		int row = mtk.numberOfRowMyTicket();
		if (row >= 1) {
			AssertThat.verifyEquals(mtk.checkSearchResult(searchField, searchValue), true, "Inconsistent data");
		} else {
			String expected = "Sorry, can't find any results that match your filters.\r\nPlease change the filters and try again.";
			AssertThat.verifyEquals(mtk.getMsgNoResult().getText(), expected, "Test case is failed");
		}
		AssertThat.verifyAll();

	}

	// @Test
	public void TC_DepartArriveDate() {
		String url = "file:///E:/Selenium/Railway/Safe%20Railway%20-%20My%20Ticket.html";
		NewTicket tk = new NewTicket();
		String searchField = "Depart Station";
		String searchValue = tk.getDepartedFrom();

		String searchField1 = "Arrive Station";
		String searchValue1 = tk.getArrivedAt();

		String searchField2 = "Depart Date";
		String searchValue2 = "7/6/2019";

		System.out.println("Search by: " + searchField + " | " + searchValue);
		hp.openURL(url);

		MyTicketPage mtk = new MyTicketPage();

		int row = mtk.numberOfRowMyTicket();
		if (row >= 1) {
			AssertThat.verifyEquals(mtk.checkSearchResult(searchField, searchValue), true, "Inconsistent data");
			AssertThat.verifyEquals(mtk.checkSearchResult(searchField1, searchValue1), true, "Inconsistent data");
			AssertThat.verifyEquals(mtk.checkSearchResult(searchField2, searchValue2), true, "Inconsistent data");
		} else {
			String expected = "Sorry, can't find any results that match your filters.\r\nPlease change the filters and try again.";
			AssertThat.verifyEquals(mtk.getMsgNoResult().getText(), expected, "Test case is failed");
		}
		AssertThat.verifyAll();

	}

	@Test
	public void TC_DepartArrive() {
		String url = "file:///E:/Selenium/Railway/Safe%20Railway%20-%20My%20Ticket.html";
		String searchField = "Depart Station";
		NewTicket tk = new NewTicket();
		String searchValue = tk.getDepartedFrom();

		String searchField1 = "Arrive Station";
		String searchValue1 = tk.getArrivedAt();

		System.out.println("Search by: " + searchField + " | " + searchValue);
		hp.openURL(url);

		MyTicketPage mtk = new MyTicketPage();

		AssertThat.verifyEquals(mtk.checkSearchResult(searchField, searchValue), true, "Inconsistent data");
		AssertThat.verifyEquals(mtk.checkSearchResult(searchField1, searchValue1), true, "Inconsistent data");

		
		AssertThat.verifyAll();

	}
	
	
	
}
