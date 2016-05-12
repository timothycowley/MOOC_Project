package mooc.tools;

import mooc.dal.*;
import mooc.model.*;
import mooc.servlet.*;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

	
public class Runner {

	/** code testing: code that failed the tests are commented out 
	 * please try to resolve these errors. I tried as best I could to interpret
	 * what the error means and where it is
	 */

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {
		Active_ClassesDao activeclass = Active_ClassesDao.getInstance();
		City_CountyDao citycounty = City_CountyDao.getInstance();
		CompaniesDao company = CompaniesDao.getInstance();
		HistoryDao history = HistoryDao.getInstance();
		Main_MOOCDao mmooc = Main_MOOCDao.getInstance();
		School_to_MOOCDao schoolmooc = School_to_MOOCDao.getInstance();
		SchoolsDao school = SchoolsDao.getInstance();
		StudentsDao student = StudentsDao.getInstance();
		Volunteer_to_Current_MOOCDao volmooc = Volunteer_to_Current_MOOCDao.getInstance();
		Volunteer_to_SchoolDao volschool = Volunteer_to_SchoolDao.getInstance();
		VolunteerDao volun = VolunteerDao.getInstance();
		
		Date startdate = new Date(222222222);
		Date enddate = new Date(222225555);
		
		System.out.print(CreateActiveClass.convertStringToSQLDate("2014-04-26").getClass());
		
		// City_County
		/**
		City_County cc1 = new City_County("Aberdeen","Grays Harbor");
		cc1 = citycounty.create(cc1);
		City_County cc2 = new City_County("Adna","Lewis");
		cc2 = citycounty.create(cc2);
		City_County cc3 = new City_County("Amanda Park","Grays Harbor");
		cc3 = citycounty.create(cc3);
		City_County cc4 = new City_County("Anacortes","Skagit");
		cc4 = citycounty.create(cc4);
		
				
		// Schools
		Schools school1 = new Schools("Lind-Ritzville High School", true, true,"","","Anacortes");
		school1 = school.create(school1);
		Schools school2 = new Schools("Othello High School", true, true,"","","Aberdeen");
		school2 = school.create(school2);
		Schools school3 = new Schools("Clarkston High School", true, true,"","","Adna");
		school3 = school.create(school3);
		Schools school4 = new Schools("Test", true, true,"","","Adna");
		school4 = school.create(school4);
		
		// Companies
		Companies comp1 = new Companies("10kInfo","Bellevue",	"www.10kinfo.com");
		comp1 = company.create(comp1);
		Companies comp2 = new Companies("17-Bit","Seattle",	"17-bit.com/");
		comp2 = company.create(comp2);
		Companies comp3 = new Companies("3Sharp_LLC","Redmond",	"www.3sharp.com");
		comp3 = company.create(comp3);
		Companies comp4 = new Companies("Abilius","Bellevue",	"www.prithvisolutions.com/");
		comp4 = company.create(comp4);
		
		// Students
		Students student1 = new Students("Gail","Nichols","GailMNichols@fleckens.hu",2513,"freshman");
		student1 = student.create(student1);
		Students student2 = new Students("Dawn","Fremont","DawnJFremont@jourrapide.com",2513,"senior");
		student2 = student.create(student2);
		Students student3 = new Students("Rebecca","Pettigrew","RebeccaRPettigrew@einrot.comu",2513,"senior");
		student3 = student.create(student3);
		Students student4 = new Students("James","Johnson","JamesRJohnson@dayrep.com",2513,"junior");
		student4 = student.create(student4);
		Students student5 = new Students("Test","Test","test@dayrep.com",2513,"junior");
		student5 = student.create(student5);
		
		
		// Main_MOOC
		Main_MOOC mainmooc1 = new Main_MOOC("Programming Mobile Applications for Android Handheld Systems",
				"This course provides a comprehensive introduction to the design and implementation of Android applications for handheld systems, such as smartphones and tablets.",
				"Cousera","https://www.coursera.org/course/androidpart1", true,4,"programming",4);
		mainmooc1 = mmooc.create(mainmooc1);
		Main_MOOC mainmooc2 = new Main_MOOC("Programming Mobile Applications for Android Handheld Systems: Part 2",
				"This course provides a comprehensive introduction to the design and implementation of Android applications for handheld systems, such as smartphones and tablets.",
				"Cousera","https://www.coursera.org/course/androidpart2",true,5,"programming",4);
		mainmooc2 = mmooc.create(mainmooc2);
		*/
		// Volunteer
		/** note the last field must reference an existing company by its company ID*/
		//Volunteer vol0= new Volunteer("James","Kirk","jamesT.kirk@starship.enterprise.com");
		//vol0 = volun.create(vol0);
		/**
		Volunteer vol1= new Volunteer("Dominic","Black","dominic.black@volunteer.moocprogram.com",511);
		vol1 = volun.create(vol1);
		Volunteer vol2= new Volunteer("Issac","Lawerence","isaac.lawrence@volunteer.moocprogram.com",511);
		vol2 = volun.create(vol2);
		Volunteer vol3= new Volunteer("Jennifer","Wallace","jennifer.wallace@volunteer.moocprogram.com",511);
		vol3 = volun.create(vol3);
		Volunteer vol4= new Volunteer("Jonathan","Macleod","jonathan.macleod@volunteer.moocprogram.com",511);
		vol4 = volun.create(vol4);
		*/
		// Active Classes
		/** Note that first field must reference an existing student by the student's ID*/
		/** Note the 2nd field must reference an exisiting MOOC by the MOOC's ID */
		/**
 		Active_Classes ac1 = new Active_Classes(1571017,1,startdate,enddate);
		ac1 = activeclass.create(ac1);
		Active_Classes ac2 = new Active_Classes(1571017,1,startdate,enddate);
		ac2 = activeclass.create(ac2);
		Active_Classes ac3 = new Active_Classes(1571017,2,startdate,enddate);
		ac3 = activeclass.create(ac3);
		Active_Classes ac4 = new Active_Classes(1571017,2,startdate,enddate);
		ac4 = activeclass.create(ac4);
		*/
		// History
		/** 1st field a valid student ID, 2nd a valid moocID, 3rd a valid volunteerID*/
		/**
		History hist1 = new History(1571017,1,5,startdate,enddate);
		hist1 = history.create(hist1);
		History hist2 = new History(1571018,1,6,startdate,enddate);
		hist2 = history.create(hist2);
		History hist3 = new History(1571017,2,7,startdate,enddate);
		hist3 = history.create(hist3);
		History hist4 = new History(1571018,2,8,startdate,enddate);
		hist4 = history.create(hist4);
*/
		
		//School_to_MOOC
		
		/** Note 1st field requires a valid mooc ID, 2nd field a valid school ID */
		/**
		School_to_MOOC schoolmooc1 = new School_to_MOOC(2513, 1);
		schoolmooc1 = schoolmooc.create(schoolmooc1);
		School_to_MOOC schoolmooc2 = new School_to_MOOC(2514, 1);
		schoolmooc2 = schoolmooc.create(schoolmooc2);
		School_to_MOOC schoolmooc3 = new School_to_MOOC(2513, 2);
		schoolmooc3 = schoolmooc.create(schoolmooc3);
		School_to_MOOC schoolmooc4 = new School_to_MOOC(2514, 2);
		schoolmooc4 = schoolmooc.create(schoolmooc4); 
		*/
		
		// Volunteers_to_Current_MOOC
		/** Note 1st field must be a valid volunteer ID and 2nd field a valid MOOC ID */
		/**
		Volunteer_to_Current_MOOC volmooc1 = new Volunteer_to_Current_MOOC(5,1);
		volmooc1 = volmooc.create(volmooc1);
		Volunteer_to_Current_MOOC volmooc2 = new Volunteer_to_Current_MOOC(8,1);
		volmooc2 = volmooc.create(volmooc2);
		Volunteer_to_Current_MOOC volmooc3 = new Volunteer_to_Current_MOOC(29,2);
		volmooc3 = volmooc.create(volmooc3);
		Volunteer_to_Current_MOOC volmooc4 = new Volunteer_to_Current_MOOC(29,2);
		volmooc4 = volmooc.create(volmooc4);
		*/
		
		// Volunteer_to_School
		/** 1st field needs to be a valid volunteer ID, 2nd a valid schoolID */
		/**
		Volunteer_to_School volschool1= new Volunteer_to_School(24,2513);
		volschool1 = volschool.create(volschool1);
		Volunteer_to_School volschool2= new Volunteer_to_School(25,2513);
		volschool2 = volschool.create(volschool2);
		Volunteer_to_School volschool3= new Volunteer_to_School(26,2514);
		volschool3 = volschool.create(volschool3);
		Volunteer_to_School volschool4= new Volunteer_to_School(27,2514);
		volschool4 = volschool.create(volschool4);
		*/
		
		// READ
		
		Schools schl = school.getSchoolByID(2513);
		System.out.format("Reading School: %s %s \n",
		schl.getID(), schl.getName());
		
		/** requires valid MOOCID -> code not working */
		/**
		List<Active_Classes> result = activeclass.getClassListbyMOOCID(2);
		for(Active_Classes re : result){
			System.out.format("Reading Active_Class: u:%s f:%s l:%s d:%s \n", 
					re.getMOOCID(),re.getStudentID(),re.getStartDate(),re.getEndDate());
		} 
	*/
	/**
		City_County ccre= citycounty.getCityByCounty("Lewis");
		System.out.format("Reading City_County: %s \n", 
				ccre.getCity());
				*/
		// this function is wrong, it should be a list
				
		
		Students std1= student.getStudentByID(1571017);
		System.out.format("Reading Student: %s %s \n", std1.getID(), std1.getFirst_Name());
		
		Main_MOOC mm1= mmooc.getMainMOOCByID(51);
		System.out.format("Reading MOOC: %s %s \n", mm1.getID(), mm1.getName());
		
		/**
		List<Companies> companyre= company.getCompanyByCounty("Lewis");
		for(Companies re : companyre){
			System.out.format("Reading Companies: u:%s f:%s l:%s d:%s \n", 
					re.getName(),re.getLocation(),re.getWebsite());
		}
		
		
		List<History> histre =  history.getStudentHistory(1500000);
		for(History re : histre){
			System.out.format("Reading History: u:%s f:%s l:%s d:%s \n", 
					re.getMOOCID(),re.getVolunteerID(),re.getStartDate(),re.getEndDate());
		}
		
		List<Main_MOOC> moocre = mmooc.getMOOCByType("programming");
		for(Main_MOOC re : moocre){
			System.out.format("Reading Main_MOOC: u:%s f:%s l:%s d:%s \n", 
					re.getName(),re.getSource(),re.getDirect_Link(),re.getLength());
		}
		
	*/
		List<School_to_MOOC> schoolre = schoolmooc.getMOOCsofSchool(2513);
		for(School_to_MOOC re : schoolre){
			System.out.format("Reading School_to_MOOC: %s %s", //u:%s f:%s l:%s d:%s \n", 
					re.getMOOCID(),re.getSchoolID());
		}
		
		
		
	/**
		List<Schools> schoolpart = school.getParticipatingSchools();
		for(Schools re : schoolpart){
			System.out.format("Reading Schools: u:%s f:%s l:%s d:%s \n", 
					re.getName(),re.getPOC_Name(),re.getPOC_Email());
		}
		
		
		List<Students> studentre = student.getStudentbySchool(2000);
		for(Students re : studentre){
			System.out.format("Reading Students: u:%s f:%s l:%s d:%s \n", 
					re.getFirst_Name(),re.getLast_Name(),re.getEmail(),re.getYear());
		}
		*/
		
		/** must be a valid Volunteer_To_Current_MOOC_ID */
	
		/**
		// formating problem '%s'
		Volunteer_to_Current_MOOC volre = volmooc.getVolunteer_to_Current_MOOCFromID(1);
		System.out.format("Reading Volunteer_to_Current_MOOC: u:%s f:%s l:%s d:%s \n", 
				volre.getMOOCID(),volre.getVolunteerID());
			
		
		// formating broken
		Volunteer_to_School volschre= volschool.getVolunteer_to_SchoolFromID(5);
		System.out.format("Reading Volunteer_to_School: u:%s f:%s l:%s d:%s \n", 
				volschre.getSchoolID(),volschre.getVolunteerID());
	
		
	
		// formating problem
		Volunteer volre1 = volun.getVolunteerFromID(5);
		System.out.format("Reading Volunteer: u:%s f:%s l:%s d:%s \n", 
				volre1.getFirst_Name(),volre1.getLast_Name(),volre1.getEmail());
		*/
		/**
		// UPDATE
		ac4 = activeclass.updateEndDate(ac4, new Date(222266666));
		System.out.format("Reading updated Active_Classes EndDate: %s\n",
				ac4.getEndDate());
				

		cc3 = citycounty.updateCounties(cc3, "Skagit");
		System.out.format("Reading updated City_County County: %s\n",
				cc3.getCounty());
	
		comp4 = company.updateLocation(comp4, "Amanda Park");
		System.out.format("Reading updated Company Location: %s\n",
				comp4.getLocation());
		
		hist4 = history.updateEndDate(hist4, new Date(222266666));
		System.out.format("Reading updated History EndDate: %s\n",
				hist4.getEndDate());
				
		
		mainmooc2 = mmooc.updateMain_MOOC(mainmooc2, "No longer available");
		System.out.format("Reading updated Main_MOOC Descripition: %s\n",
				mainmooc2.getDescription());
				
		school3 = school.updateParticipation(school3, false);
		System.out.format("Reading updated Schools Participation: %s\n",
				school3.isParticipation());
			
		student4 = student.updateSchoolID(student4, 2003);
		System.out.format("Reading updated Students SchoolID: %s\n",
				student4.getSchoolID());
		
		volmooc4 = volmooc.updateVolunteer(volmooc4, vol4);
		System.out.format("Reading updated Volunteer_to_Current_MOOC to Volunteer: %s\n",
				volmooc4.getVolunteerID());
		
		volschool4 = volschool.updateSchool(volschool4, 2513);
		System.out.format("Reading updated Volunteer_to_School to School: %s\n",
				volschool4.getSchoolID());
		
		vol4=volun.updateAssociation(vol4, 511);
		System.out.format("Reading updated Volunteer to Association: %s\n",
				vol4.getAssociation());
				
		
		// DELETE 
		for(Active_Classes re : result){
			System.out.format("Deleting Active_Class: %s\n", re.getActiveID());
			activeclass.delete(re);		
		} 
		
		System.out.format("Deleting City_County: %s\n", ccre.getCity());
		citycounty.delete(ccre); 
		
		
		for(Companies re : companyre){
			System.out.format("Deleting Active_Class: %s\n", re.getID());
			company.delete(re);		
		}
		
		
		for(History re : histre){
			System.out.format("Deleting Active_Class: %s\n", re.getHistoryID());
			history.delete(re);		
		}

		for(Main_MOOC re : moocre){
			System.out.format("Deleting Active_Class: %s\n", re.getID());
			mmooc.delete(re);		
		}
	
		for(School_to_MOOC re : schoolre){
			System.out.format("Deleting Active_Class: %s %s\n", re.getMOOCID(),re.getSchoolID());
			schoolmooc.delete(re);	
			
		}
	
		for(Schools re : schoolpart){
			System.out.format("Deleting Active_Class: %s\n", re.getID());
			school.delete(re);		
		}
		
		for(Students re : studentre){
			System.out.format("Deleting Active_Class: %s\n", re.getID());
			student.delete(re);		
		}
		
		System.out.format("Deleting City_County: %s\n", volre.getVolunteerMOOCID());
		volmooc.delete(volre);
		
		System.out.format("Deleting City_County: %s\n", volschre.getID());
		volschool.delete(volschre);
		
		System.out.format("Deleting City_County: %s\n", volre1.getID());
		volun.delete(volre1);	
		*/
	}

}
