package sandbox;

import org.testng.annotations.Test;

import Base.BaseTest;
import Railway.BookTicketPage;
import Railway.NewTicket;
import Railway.TicketPricePage;
import Railway.TrainTimablePage;

public class Chk extends BaseTest {
	public static boolean compareTrip(String[] arrayIn, String[] arrayOut) {
		System.out.println(arrayIn[0] + arrayIn[1]);
		System.out.println(arrayOut[0] + arrayOut[1]);

		if (arrayIn[0] == arrayOut[0] && arrayIn[1] == arrayOut[1]) {
			return true;
		}
		return false;

	}

	public static String[] getTrip(String str) {
		String subString = str.substring(18);
		int firstIndex = subString.indexOf("to");
		String from = subString.substring(0, firstIndex);
		String to = subString.substring(firstIndex + 3);

		String trip[] = new String[2];
		trip[0] = from;
		trip[1] = to;

		return trip;
	}
	@Test
	public void ABC() {

		hp.openRailway();
//				.gotoLoginPage()
//				.loginRailway(Constant.RAILWAY_USERNAME, Constant.RAILWAY_PASSWORD)
//				.gotoTrainTimeablePage();

		NewTicket tk = new NewTicket();
		TrainTimablePage t = new TrainTimablePage();
		t.selectTicketPrice(tk.getDepartedFrom(), tk.getArrivedAt());
		t.selectBookTicket(tk.getSeatType());
	}
	
	public void c() {
		
		  String inTour[] = getTrip("Ticket price from Đà Nẵng to Huế");
		  System.out.println("1From: " + inTour[0] + " |" + "To: " + inTour[1]);
		  
		  String outTour[] = new String[2]; outTour[0] = "Đà Nẵng"; outTour[1] =
		  "Sài Gòn";
		  
		  boolean a = compareTrip(inTour, outTour);
		  
		  if (a == true) { System.out.println("Đúng"); } System.err.println("Sai");
		 
	}
	
	@Test
	public void XYZ() {
		hp.openRailway();
		/*String dFrom = "Đà Nẵng";
		String aAt = "Huế";*/
		TicketPricePage tk = new TicketPricePage();
		System.out.println(tk.getSelectedStation());
	}
	
	@Test
	public void NAK() {
		hp.openRailway();
		
		BookTicketPage bk = new BookTicketPage();
		bk.getTicketInfo();
	}
	
	@Test
	public void AKN() {
/*		String rowcount = "//table[@class='MyTable']//tr";
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-condensed table-hover event-list']/tbody/tr"));

		
		int count = rows.size();
		System.out.println("ROW COUNT : "+count);
		
		for(WebElement e : rows) {
	        assertEquals("expected text", e.getText());
	    }*/
	}

}
