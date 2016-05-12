package mooc.model;

import java.sql.Date;


public class History {
	protected int HistoryID;
	protected int StudentID;
	protected int MOOCID;
	protected int VolunteerID;
	protected Date StartDate;
	protected Date EndDate;
	
	public History(int HistoryID,int StudentID,int MOOCID,int VolunteerID,
			Date StartDate,Date EndDate){
		this.HistoryID=HistoryID;
		this.StudentID=StudentID;
		this.MOOCID=MOOCID;
		this.VolunteerID=VolunteerID;
		this.StartDate=StartDate;
		this.EndDate=EndDate;
	}
	
	// constructor without autoincremented key
	public History(int StudentID,int MOOCID,int VolunteerID,
			Date StartDate,Date EndDate){
		this.StudentID=StudentID;
		this.MOOCID=MOOCID;
		this.VolunteerID=VolunteerID;
		this.StartDate=StartDate;
		this.EndDate=EndDate;
	}
	
	

	public int getHistoryID() {
		return HistoryID;
	}

	public void setHistoryID(int historyID) {
		HistoryID = historyID;
	}

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public int getMOOCID() {
		return MOOCID;
	}

	public void setMOOCID(int mOOCID) {
		MOOCID = mOOCID;
	}

	public int getVolunteerID() {
		return VolunteerID;
	}

	public void setVolunteerID(int volunteerID) {
		VolunteerID = volunteerID;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
}
