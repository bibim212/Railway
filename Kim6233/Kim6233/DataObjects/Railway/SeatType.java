package Railway;

public enum SeatType {
	HARDSEAT("Hard seat"), SOFTSEAT("Soft seat"), SOFTSEATWITHAIR("Soft seat with air conditioner"),
	HARDBED("Hard bed"), SOFTBED("Soft bed"), SOFTBEDWITHAIR("Soft bed with air conditioner");

	private String st;

	private SeatType(String seatType) {
		this.st = seatType;
	}

	public String getSeatType() {
		return this.st;
	}
}
