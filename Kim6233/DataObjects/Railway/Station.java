package Railway;

public enum Station {
	IGNORE("Ignore"), SAIGON("Sài Gòn"), PHANTHIET("Phan Thiết"), NHATRANG("Nha Trang"), DANANG("Đà Nẵng"),
	HUE("Huế"), QUANGNGAI("Quảng Ngãi");

	private final String _station;

	private Station(String station) {
		this._station = station;
	}

	public String getStationName() {
		return this._station;
	}
}
