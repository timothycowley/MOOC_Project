package mooc.model;

public class School_to_MOOC {
	protected int SchoolID;
	protected int MOOCID;
	
	public School_to_MOOC(int SchoolID,int MOOCID){
		this.SchoolID=SchoolID;
		this.MOOCID=MOOCID;
	}



	public int getSchoolID() {
		return SchoolID;
	}

	public void setSchoolID(int schoolID) {
		SchoolID = schoolID;
	}

	public int getMOOCID() {
		return MOOCID;
	}

	public void setMOOCID(int mOOCID) {
		MOOCID = mOOCID;
	}
}
