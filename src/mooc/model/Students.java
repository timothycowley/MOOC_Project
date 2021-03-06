package mooc.model;

public class Students {
	protected int ID;
	protected String First_Name;
	protected String Last_Name;
	protected String Email;
	protected int SchoolID;
	protected String Year; // had to change class -> year. probably a protected word in Java
	
	public Students(int ID,String First_Name,String Last_Name,String Email,int SchoolID,String Class){
		this.ID=ID;
		this.First_Name=First_Name;
		this.Last_Name=Last_Name;
		this.Email=Email;
		this.SchoolID=SchoolID;
		this.Year=Class;
	}
	
	//constructor without ID, allowing it to be autogenerated
	public Students(String First_Name,String Last_Name,String Email,int SchoolID,String Class){
		this.First_Name=First_Name;
		this.Last_Name=Last_Name;
		this.Email=Email;
		this.SchoolID=SchoolID;
		this.Year=Class;
	}

	// for foreign key references and mysql lookup
		public Students(int ID){
			this.ID=ID;
		}

	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getSchoolID() {
		return SchoolID;
	}

	public void setSchoolID(int schoolID) {
		SchoolID = schoolID;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String class1) {
		Year = class1;
	}

}
