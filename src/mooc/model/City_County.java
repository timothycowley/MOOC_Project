package mooc.model;

public class City_County {
	protected String City;
	protected String County;
	
	public City_County(String City,String County){
		this.City=City;
		this.County=County;		
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCounty() {
		return County;
	}

	public void setCounty(String county) {
		County = county;
	}
}
