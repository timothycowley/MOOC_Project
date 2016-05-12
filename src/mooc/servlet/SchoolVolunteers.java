
package mooc.servlet;

import mooc.dal.*;
import mooc.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// note the required imports above


/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/schoolvolunteers")
public class SchoolVolunteers extends HttpServlet {
	protected VolunteerDao volunteerDao;
	
	@Override
	public void init() throws ServletException {
		volunteerDao = VolunteerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Volunteer> volunteers = new ArrayList<Volunteer>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        
        String volunteerID = req.getParameter("volunteerID");
        String schoolID = req.getParameter("schoolID");
        String moocID = req.getParameter("moocID");
        String companyID = req.getParameter("companyID");
        try {
        	if (volunteerID != null && !volunteerID.trim().isEmpty()){
            	volunteers = java.util.Arrays.asList(volunteerDao.getVolunteerFromID(Integer.parseInt(volunteerID)));
            	messages.put("success", "Displaying results for " + volunteerID);
        	}
        	else if (schoolID != null && !schoolID.trim().isEmpty()) {
        		volunteers = volunteerDao.getVolunteerbySchool(Integer.parseInt(schoolID));
        		messages.put("success", "Displaying results for " + schoolID);
        	}
        	else if (moocID != null && !moocID.trim().isEmpty()) {
        		volunteers = volunteerDao.getVolunteerbyMooc(Integer.parseInt(moocID));
        		messages.put("success", "Displaying results for " + moocID);
        	}
        	else if (companyID != null && !companyID.trim().isEmpty()) {
        		volunteers = volunteerDao.getVolunteerbyCompany(Integer.parseInt(companyID));
        		messages.put("success", "Displaying results for " + companyID);
        	} else {
        		messages.put("success", "Please enter a valid search paramater.");
        	}
        	 } catch (SQLException e) {
     			e.printStackTrace();
     			throw new IOException(e);
             }
        req.setAttribute("volunteers", volunteers);
        req.getRequestDispatcher("/SchoolVolunteers.jsp").forward(req, resp);
	}
	
	// above -> need to figure out how to handle integers
	// below need to update for volunteers
	// need to change jsp file to be getting by schoolID and not volunteerID
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Volunteer> volunteers = new ArrayList<Volunteer>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String volunteerID = req.getParameter("volunteerID");
        String schoolID = req.getParameter("schoolID");
        String moocID = req.getParameter("moocID");
        String companyID = req.getParameter("companyID");
        try {
        	if (volunteerID != null && !volunteerID.trim().isEmpty()){
            	volunteers = java.util.Arrays.asList(volunteerDao.getVolunteerFromID(Integer.parseInt(volunteerID)));
            	messages.put("success", "Displaying results for " + volunteerID);
        	}
        	else if (schoolID != null && !schoolID.trim().isEmpty()) {
        		volunteers = volunteerDao.getVolunteerbySchool(Integer.parseInt(schoolID));
        		messages.put("success", "Displaying results for " + schoolID);
        	}
        	else if (moocID != null && !moocID.trim().isEmpty()) {
        		volunteers = volunteerDao.getVolunteerbyMooc(Integer.parseInt(moocID));
        		messages.put("success", "Displaying results for " + moocID);
        	}
        	else if (companyID != null && !companyID.trim().isEmpty()) {
        		volunteers = volunteerDao.getVolunteerbyCompany(Integer.parseInt(companyID));
        		messages.put("success", "Displaying results for " + companyID);
        	} else {
        		messages.put("success", "Please enter a valid search paramater.");
        	}
        	 } catch (SQLException e) {
     			e.printStackTrace();
     			throw new IOException(e);
             }
        req.setAttribute("volunteers", volunteers);
        req.getRequestDispatcher("/SchoolVolunteers.jsp").forward(req, resp);
    }
}
