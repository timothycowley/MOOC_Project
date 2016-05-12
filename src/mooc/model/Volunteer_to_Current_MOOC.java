package mooc.model;

public class Volunteer_to_Current_MOOC {
	protected int VolunteerMOOCID;
	protected int VolunteerID;
	protected int MOOCID;
	
	public Volunteer_to_Current_MOOC(int VolunteerMOOCID,int VolunteerID,int MOOCID){
		this.VolunteerMOOCID=VolunteerMOOCID;
		this.VolunteerID=VolunteerID;
		this.MOOCID=MOOCID;
	}
	
	// constructor without autogenerated key
	public Volunteer_to_Current_MOOC(int VolunteerID,int MOOCID){
		this.VolunteerID=VolunteerID;
		this.MOOCID=MOOCID;
	}

	public int getVolunteerMOOCID() {
		return VolunteerMOOCID;
	}

	public void setVolunteerMOOCID(int volunteerMOOCID) {
		VolunteerMOOCID = volunteerMOOCID;
	}

	public int getVolunteerID() {
		return VolunteerID;
	}

	public void setVolunteerID(int volunteerID) {
		VolunteerID = volunteerID;
	}

	public int getMOOCID() {
		return MOOCID;
	}

	public void setMOOCID(int mOOCID) {
		MOOCID = mOOCID;
	}
}
