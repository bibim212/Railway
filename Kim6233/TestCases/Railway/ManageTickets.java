package Railway;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import Base.ActiveAccountBaseTest;
import Common.AssertThat;
import Helper.DateTimeHelper;
import Helper.IntHelper;

public class ManageTickets extends ActiveAccountBaseTest {
	@Test
	public void TC16_User_can_cancel_ticket() {
		logHelper.logInfo("TC16 - User can cancel a ticket");

		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password);

		BookTicketPage btp = hp.gotoBookTicketPage();

		logHelper.logStep("3. Book a ticket");
		NewTicket tk = new NewTicket();
		btp.bookTicket(tk);

		logHelper.logStep("4. Click on \"My ticket\" tab");
		logHelper.logStep("5. Click on \"Cancel\" button of ticket which user\r\n" + " want to cancel");
		logHelper.logStep("6. Click on \"OK\" button on Confirmation message\r\n" + " Are you sure?\"");

		MyTicketPage mtp = btp.gotoMyTicketPage();

		mtp.cancelTicket(tk);

		logHelper.logVerify("The canceled ticket is disappeared.");

		boolean actualStatus = mtp.checkTicketRemove(tk);
		AssertThat.verifyTrue(actualStatus, "The canceled ticket is disappeared");

		if (actualStatus == false) {
			logHelper.printBug("Bug #04", "User cannot cancel the ticket.");
		}

		AssertThat.verifyAll();
	}

	@Test
	public void TC17_User_receive_message_when_no_result_were_fount_after_filtering() {
		logHelper.logInfo("TC18 - Use can filter Manager ticket table with Status");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		btp.bookTickets(7);

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Enter any date before Depart Date for getting the message");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		Date d = DateTimeHelper.randomDate(IntHelper.generateRandomIntRange(1, 2));
		DateFormat dformat = new SimpleDateFormat("M/d/yyyy");

		String searchValue = dformat.format(d);

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Date", searchValue, "click");

		logHelper.logVerify(
				"Message appears \"Sorry, can't find any results that match your filter.\\r\\nPlease change the filters and try again.");

		AssertThat.verifyEquals(mtp.checkSearchResult("Status", searchValue), true, "Inconsistent data");
		AssertThat.verifyAll();

		String expected = "Sorry, can't find any results that match your filters.\r\nPlease change the filters and try again.";
		AssertThat.verifyEquals(mtp.getLblErrorMessage().getText(), expected, "Test case is failed");
		AssertThat.verifyAll();
	}

	@Test
	public void TC18_User_can_filter_Manage_Ticket_table_with_Status() {

		logHelper.logInfo("TC18 - Use can filter Manager ticket table with Status");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] tk = btp.bookTickets(7);

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Select any status from Status");
		logHelper.logStep("6. Click on \"Apply filter\" button");
		
		String searchValue = NewTicket.randomDataToFilter(tk, "Status");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Status", searchValue, "click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");

		AssertThat.verifyEquals(mtp.checkSearchResult("Status", searchValue), true, "Inconsistent data");
		AssertThat.verifyAll();
	}

	@Test
	public void TC19_User_can_filter_Manage_Ticket_table_with_Depart_Date() {
		logHelper.logInfo("TC19 - Use can filter Manage Ticket table with Depart Date");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different Depart Date");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] tk = btp.bookTickets(7);
		String searchValue = NewTicket.randomDataToFilter(tk, "Depart Date");

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Input one of booked Depart Date in \"Depart date\" textbox");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Date", searchValue, "click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Date", searchValue), true, "Inconsistent data");
		AssertThat.verifyAll();
	}

	@Test
	public void TC20_User_can_filter_Manage_Ticket_table_by_Arrive_Station() {
		logHelper.logInfo("TC20 - Use can filter Manage Ticket table by Arrive Station");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different Arrive Station");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] ticketInfo = btp.bookTickets(7);

		String searchValue = NewTicket.randomDataToFilter(ticketInfo, "Arrive Station");

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Input one of booked Arrive Station in \"Arrive Station\" textbox");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Arrive Station", searchValue, "click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Arrive Station", searchValue), true, "Inconsistent data");
		AssertThat.verifyAll();
	}

	@Test
	public void TC21_User_can_filter_Manage_Ticket_table_by_Depart_Station() {
		logHelper.logInfo("TC21 - Use can filter Manage Ticket table by Depart Station");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different Depart Station");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] ticketInfo = btp.bookTickets(7);

		String searchValue = NewTicket.randomDataToFilter(ticketInfo, "Depart Station");

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Input one of booked Depart Station in \"Depart Station\" textbox");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Station", searchValue, "click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Station", searchValue), true, "Inconsistent data");
		AssertThat.verifyAll();
	}

	@Test
	public void TC22_User_can_search_Manage_Ticket_table_by_Depart_Station_and_Arrive_Station() {
		logHelper.logInfo("TC22 - Use can filter Manage Ticket table by Depart Station and Arrive Station");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different Depart Station and Arrive Station");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] ticketInfo = btp.bookTickets(7);

		String searchValue1 = NewTicket.randomDataToFilter(ticketInfo, "Depart Station");
		String searchValue2 = NewTicket.randomDataToFilter(ticketInfo, "Arrive Station");

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Select Depart Station and Arrive Station");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Station", searchValue1, "ignore").searchWith("Arrive Station",
				searchValue2, "click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Station", searchValue1), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Arrive Station", searchValue2), true, "Inconsistent data");

		AssertThat.verifyAll();
	}

	@Test
	public void TC23_User_can_search_Manage_Ticket_table_by_Depart_Station_and_Depart_Date() {
		logHelper.logInfo("TC23 - Use can filter Manage Ticket table by Depart Station and Depart Date");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different info");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] ticketInfo = btp.bookTickets(7);

		String searchValue1 = NewTicket.randomDataToFilter(ticketInfo, "Depart Station");
		String searchValue2 = NewTicket.randomDataToFilter(ticketInfo, "Depart Date");

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Select Depart Station and enter Depart Date");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Station", searchValue1, "ignore").searchWith("Depart Date",
				searchValue2, "click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Station", searchValue1), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Date", searchValue2), true, "Inconsistent data");

		AssertThat.verifyAll();
	}

	@Test
	public void TC24_User_can_search_Manage_Ticket_table_by_Depart_Station_and_Status() {
		logHelper.logInfo("TC22 - Use can filter Manage Ticket table by Depart Station and Status");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different info");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] ticketInfo = btp.bookTickets(7);

		String searchValue1 = NewTicket.randomDataToFilter(ticketInfo, "Depart Station");
		String searchValue2 = NewTicket.randomTicketStatus().toString();

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Select Depart Station and Status");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Station", searchValue1, "ignore").searchWith("Status", searchValue2,
				"click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Station", searchValue1), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Status", searchValue2), true, "Inconsistent data");

		AssertThat.verifyAll();
	}

	@Test
	public void TC25_User_can_search_Manage_Ticket_table_by_Depart_Date_and_Status() {
		logHelper.logInfo("TC22 - Use can filter Manage Ticket table by Depart Station and Status");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different info");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] ticketInfo = btp.bookTickets(7);

		String searchValue1 = NewTicket.randomDataToFilter(ticketInfo,"Depart Date");
		String searchValue2 = NewTicket.randomTicketStatus().toString();

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Select Depart Date and Status");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Date", searchValue1, "ignore").searchWith("Status", searchValue2,
				"click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Date", searchValue1), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Status", searchValue2), true, "Inconsistent data");

		AssertThat.verifyAll();
	}

	@Test
	public void TC26_User_can_search_Manage_Ticket_table_by_Arrive_Station_and_Status() {
		logHelper.logInfo("TC26 - Use can filter Manage Ticket table by Arrive Station and Status");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different info");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] ticketInfo = btp.bookTickets(7);

		String searchValue1 = NewTicket.randomDataToFilter(ticketInfo, "Depart Station");
		String searchValue2 = NewTicket.randomTicketStatus().toString();

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Select Arrive Station and Status");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Arrive Station", searchValue1, "ignore").searchWith("Status", searchValue2,
				"click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Arrive Station", searchValue1), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Status", searchValue2), true, "Inconsistent data");

		AssertThat.verifyAll();
	}

	@Test
	public void TC27_User_can_search_Manage_Ticket_table_by_Depart_Date_Arrive_Station_and_Status() {
		logHelper.logInfo("TC26 - Use can filter Manage Ticket table by Depart Date, Arrive Station and Status");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different info");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] ticketInfo = btp.bookTickets(7);

		String searchValue1 = NewTicket.randomDataToFilter(ticketInfo,"Depart Date");
		String searchValue2 = NewTicket.randomDataToFilter(ticketInfo, "Depart Station");
		String searchValue3 = NewTicket.randomDataToFilter(ticketInfo, "Status");

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Select Depart Date, Arrive Station and Status");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Date", searchValue1, "ignore")
				.searchWith("Arrive Station", searchValue1, "ignore").searchWith("Status", searchValue2, "click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Date", searchValue1), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Arrive Station", searchValue2), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Status", searchValue3), true, "Inconsistent data");

		AssertThat.verifyAll();
	}

	@Test
	public void TC28_User_can_search_Manage_Ticket_table_by_4_criteria() {
		logHelper.logInfo("TC26 - Use can filter Manage Ticket table by Depart Date, Arrive Station and Status");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different info");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		NewTicket[] ticketInfo = btp.bookTickets(7);

		String searchValue1 = NewTicket.randomDataToFilter(ticketInfo, "Depart Station");
		String searchValue2 = NewTicket.randomDataToFilter(ticketInfo, "Arrive Station");
		String searchValue3 = NewTicket.randomDataToFilter(ticketInfo,"Depart Date");
		String searchValue4 = NewTicket.randomDataToFilter(ticketInfo,"Status");

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Enter and select data in 4 criteria");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Station", searchValue1, "ignore")
				.searchWith("Arrive Station", searchValue2, "ignore").searchWith("Depart Date", searchValue3, "ignore")
				.searchWith("Status", searchValue4, "click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Station", searchValue1), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Arrive Station", searchValue2), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Depart Date", searchValue3), true, "Inconsistent data");
		AssertThat.verifyEquals(mtp.checkSearchResult("Status", searchValue4), true, "Inconsistent data");

		AssertThat.verifyAll();
	}

	@Test
	public void TC29_User_can_not_filter_by_incorrect_format_date() {
		logHelper.logInfo("TC29 - Use can not filter by incorrect format date");
		logHelper.logInfo("Pre-condition: Create and activate a new account");

		logHelper.logStep("1. Navigate to QA Railway Website");
		logHelper.logStep("2. Login with a valid account");
		logHelper.logStep("3. Book more than 6 tickets with different info");

		hp.gotoLoginPage().loginRailway(UserAccount._email, UserAccount._password).gotoBookTicketPage();

		BookTicketPage btp = new BookTicketPage();
		btp.bookTickets(7);

		String searchValue = "incorrectformatdate";

		logHelper.logStep("4. Click on \"My ticket\" button");
		logHelper.logStep("5. Enter and select data in 4 criteria");
		logHelper.logStep("6. Click on \"Apply filter\" button");

		MyTicketPage mtp = btp.gotoMyTicketPage();
		mtp.gotoMyTicketPage().searchWith("Depart Date", searchValue, "click");

		logHelper.logVerify("Manage ticket table shows correct ticket(s)");

		String currentDate = DateTimeHelper.getCurrentDate();

		logHelper.logVerify(
				"Message 'The date format is wrong, data filter is ignored. Example of a proper date: Today is <mm/dd/yyyy>'");
		AssertThat.verifyEquals(mtp.getLblErrorMessage(),
				String.format(
						"The date format is wrong, date filter is ignored.\n" + "Example of a proper date: Today is %s",
						currentDate),
				"Message is displayed incorrectly");
		AssertThat.verifyAll();
	}
}
