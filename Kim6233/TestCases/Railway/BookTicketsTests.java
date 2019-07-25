package Railway;

import org.testng.annotations.Test;

import Base.ActiveAccountBaseTest;
import Common.AssertThat;

public class BookTicketsTests extends ActiveAccountBaseTest {

	@Test
	public void TC14_User_cannot_book_more_than_10_tickets() {
		logHelper.logInfo("TC14 - User can't book more than 10 tickets");

		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Click on \"Book ticket\"\r\n tab");
		logHelper.logStep("4. Book 10 tickets");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password);

		BookTicketPage btp = hp.gotoBookTicketPage();
		btp.bookTickets(10);

		logHelper.logStep("5. Click on \"Book ticket\" tab again");
		NewTicket tk = new NewTicket();
		btp.bookTicket(tk);

		logHelper.logVerify(
				"Error message \"There're errors in the form. Please correct the errors and try again.\" displays above the form.");
		logHelper.logVerify(
				"Error message \"You have booked 10 tickets. You can book no more.\" displays next to Ticket amount field.");

		String actualBookTicketMsg = btp.getErrorTicket();
		String expectedBookTicketMsg = "There're errors in the form. Please correct the errors and try again.";
		String actualAmountMsg = btp.getErrorTicketAmount();
		String expectedAmountMsg = "You have booked 10 tickets. You can book no more.";
		AssertThat.verifyEquals(actualBookTicketMsg, expectedBookTicketMsg,
				"There're errors in the form. Please correct the errors and try again.");
		AssertThat.verifyEquals(actualAmountMsg, expectedAmountMsg,
				"You have booked 10 tickets. You can book no more.");
		AssertThat.verifyAll();

	}

	@Test
	public void TC15_User_can_open_Book_ticket_page_by_clicking_on_Book_ticket_link_in_Ticket_price() {
		NewTicket nk = new NewTicket();
		String departedFrom = nk.getDepartedFrom();
		String arrivedAt = nk.getArrivedAt();
		String seatType = nk.getSeatType();

		logHelper.logInfo(
				"TC15 - User can open \"Book ticket\" page by click on \"Book ticket\" link in \"Ticket price\"");

		logHelper.logStep("Pre-Condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Click on \"Ticket price\" tab");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password);

		TicketPricePage tpp = hp.gotoTicketPricePage();

		logHelper.logStep("4. Click on any ticket from list");
		logHelper.logStep("5. Click on \"Book ticket\" for any seat type");

		tpp.selectTicketPrice(departedFrom, arrivedAt);
		tpp.selectBookTicket(seatType);

		logHelper.logVerify("Book ticket page displays with correct values which user selected.");

		BookTicketPage btp = new BookTicketPage();
		NewTicket nk_selected = btp.getTicketInfo();
		String actualDepartedFrom = nk_selected.getDepartedFrom();
		String actualArrivedAt = nk_selected.getArrivedAt();
		String actualSeatType = nk_selected.getSeatType();

		AssertThat.verifyEquals(actualDepartedFrom, nk.getDepartedFrom(),
				"Book ticket page displays with correct values which user selected");
		AssertThat.verifyEquals(actualArrivedAt, nk.getArrivedAt(),
				"Book ticket page displays with correct values which user selected");
		AssertThat.verifyEquals(actualSeatType, nk.getSeatType(),
				"Book ticket page displays with correct values which user selected");

		AssertThat.verifyAll();
	}
}
