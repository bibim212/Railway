package Railway;

public enum TicketStatus {
	IGNORE("Ignore"), EXPIRED("Expired"), NEW("New"), PAID("Paid");

	private final String _ticketStatus;

	private TicketStatus(String ticketStatus) {
		this._ticketStatus = ticketStatus;
	}

	public String getStationName() {
		return this._ticketStatus;
	}

	
}
