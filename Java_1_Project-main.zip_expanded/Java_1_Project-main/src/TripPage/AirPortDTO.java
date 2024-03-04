package TripPage;

public class AirPortDTO {
	
	private String code;
	private String nation;
	private String city;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "AirPortDTO [code=" + code + ", nation=" + nation + ", city=" + city + "]";
	}
}
