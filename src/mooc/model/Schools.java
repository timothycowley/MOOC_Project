package mooc.model;

public class Schools {
	protected int ID;
	protected String Name;
	protected boolean Participation;
	protected boolean Contacted;
	protected String POC_Name;
	protected String POC_Email;
	protected String City;
	
	// full constructor
	public Schools(int ID,String Name,boolean Participation,boolean Contacted,String POC_Name
			,String POC_Email,String City){
		this.ID=ID;
		this.Name=Name;
		this.Participation=Participation;
		this.Contacted=Contacted;
		this.POC_Name=POC_Name;
		this.POC_Email=POC_Email;
		this.City=City;		
	}
	
	// constructor without Auto Generated Key
		public Schools(String Name,boolean Participation,boolean Contacted,String POC_Name
				,String POC_Email,String City){
			this.Name=Name;
			this.Participation=Participation;
			this.Contacted=Contacted;
			this.POC_Name=POC_Name;
			this.POC_Email=POC_Email;
			this.City=City;		
		}
	
	// minimal constructor
		public Schools(int ID){
		this.ID=ID;
		}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isParticipation() {
		return Participation;
	}

	public void setParticipation(boolean participation) {
		Participation = participation;
	}

	public boolean isContacted() {
		return Contacted;
	}

	public void setContacted(boolean contacted) {
		Contacted = contacted;
	}

	public String getPOC_Name() {
		return POC_Name;
	}

	public void setPOC_Name(String pOC_Name) {
		POC_Name = pOC_Name;
	}

	public String getPOC_Email() {
		return POC_Email;
	}

	public void setPOC_Email(String pOC_Email) {
		POC_Email = pOC_Email;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}
	
}
