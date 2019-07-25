package Railway;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Helper.DateTimeHelper;
import Helper.IntHelper;
import Helper.StringHelper;

public class NewTicket {
	// The instance variables
	public static String _departedDate;
	public static String _arrivedAt;
	public static String _departedFrom;
	public static String _seatType;
	public static int _ticketAmount;
	public static String _status;

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getDepartedDate() {
		return _departedDate;
	}

	public String getDepartedFrom() {
		return _departedFrom;
	}

	public String getArrivedAt() {
		return _arrivedAt;
	}

	public String getSeatType() {
		return _seatType;
	}

	public int getTicketAmount() {
		return _ticketAmount;
	}

	public void setDepartedFrom(String departedFrom) {
		_departedFrom = departedFrom;
	}

	public void setArrivedAt(String arrivedAt) {
		_arrivedAt = arrivedAt;
	}

	public void setDepartedDate(String departedDate) {
		_departedDate = departedDate;
	}

	public void setSeatType(String seatType) {
		_seatType = seatType;
	}

	public void setTicketAmount(int ticketAmount) {
		_ticketAmount = ticketAmount;
	}

	
	
	public NewTicket(String dDate, String dFrom, String aAt, String sType, int tAmount) {
		_departedDate = dDate;
		_departedFrom = dFrom;
		_arrivedAt = aAt;
		_seatType = sType;
		_ticketAmount = tAmount;
	}

	public NewTicket() {
		Date d = DateTimeHelper.randomDate(IntHelper.generateRandomIntRange(3, 30));
		DateFormat dformat = new SimpleDateFormat("M/d/yyyy");
		Station[] tour = NewTicket.randomTour();
		SeatType st = NewTicket.randomSeatType();

		_departedDate = dformat.format(d);
		_departedFrom = tour[0].getStationName();
		_arrivedAt = tour[1].getStationName();
		_seatType = st.getSeatType();
		// _ticketAmount = IntHelper.generateRandomIntRange(1, 9);
		_ticketAmount = 1;

		StringHelper.printTicketInfo(_departedDate, _departedFrom, _arrivedAt, _seatType, _ticketAmount);
	}

	private static Station[] stationList = { Station.DANANG, Station.HUE, Station.NHATRANG, Station.PHANTHIET,
			Station.QUANGNGAI, Station.SAIGON };

	public static Station randomStation() {
		int i = IntHelper.generateRandomIntRange(0, stationList.length - 1);
		Station station = stationList[i];
		return station;
	}

	private static SeatType[] seatTypeList = { SeatType.HARDSEAT, SeatType.SOFTSEAT, SeatType.SOFTSEATWITHAIR,
			SeatType.HARDBED, SeatType.SOFTBED, SeatType.SOFTBEDWITHAIR };

	public static SeatType randomSeatType() {
		int i = IntHelper.generateRandomIntRange(0, seatTypeList.length - 1);
		SeatType seatType = seatTypeList[i];
		return seatType;
	}

	public static Station[][] tours = { { Station.SAIGON, Station.NHATRANG }, { Station.SAIGON, Station.PHANTHIET },
			{ Station.SAIGON, Station.DANANG }, { Station.SAIGON, Station.HUE }, { Station.SAIGON, Station.QUANGNGAI },
			{ Station.PHANTHIET, Station.SAIGON }, { Station.PHANTHIET, Station.DANANG },
			{ Station.PHANTHIET, Station.NHATRANG }, { Station.NHATRANG, Station.SAIGON },
			{ Station.NHATRANG, Station.PHANTHIET }, { Station.NHATRANG, Station.DANANG },
			{ Station.NHATRANG, Station.HUE }, { Station.DANANG, Station.SAIGON }, { Station.DANANG, Station.NHATRANG },
			{ Station.DANANG, Station.HUE }, { Station.DANANG, Station.QUANGNGAI }, { Station.HUE, Station.SAIGON },
			{ Station.HUE, Station.NHATRANG }, { Station.HUE, Station.DANANG }, { Station.HUE, Station.QUANGNGAI },
			{ Station.QUANGNGAI, Station.SAIGON }, { Station.QUANGNGAI, Station.NHATRANG },
			{ Station.QUANGNGAI, Station.DANANG }, { Station.QUANGNGAI, Station.HUE } };

	public static Station[] randomTour() {

		int i = IntHelper.generateRandomIntRange(0, tours.length - 1);
		Station[] tour = tours[i];
		return tour;
	}

	public static TicketStatus[] ticketStatusList = { TicketStatus.IGNORE, TicketStatus.EXPIRED, TicketStatus.NEW,
			TicketStatus.PAID };

	public static TicketStatus randomTicketStatus() {
		int i = IntHelper.generateRandomIntRange(0, ticketStatusList.length - 1);
		TicketStatus ticketStatus = ticketStatusList[i];
		return ticketStatus;
	}

	public static String randomDataToFilter(NewTicket[] tk, String filter) {
		String[] st = new String[tk.length];

		for (int i = 0; i < tk.length; i++) {
			switch (filter) {
			case "Depart Date":
				st[i] = tk[0]._departedDate;
				break;
			case "Arrive Station":
				st[i] = tk[0]._arrivedAt;
				break;
			case "Depart Station":
				st[i] = tk[0]._departedFrom;
				break;
			case "Status":
				st[i] = NewTicket.randomTicketStatus().toString();
			default:
				break;
			}
		}
		int i = IntHelper.generateRandomIntRange(0, st.length - 1);
		String returnedFilter = st[i].toString();

		return returnedFilter;
	}

	/*public static String randomStationToFilter(NewTicket[] tk, String station) {

		String[] st = new String[tk.length];

		for (int i = 0; i < tk.length; i++) {
			if (station == "Depart Station") {
				st[i] = tk[0]._departedFrom;
			}
			st[i] = tk[0]._arrivedAt;
		}
		int i = IntHelper.generateRandomIntRange(0, st.length - 1);
		String returnedStation = st[i].toString();

		return returnedStation;
	}*/
}
