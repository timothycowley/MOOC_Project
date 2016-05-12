
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
@WebServlet("/findstudent")
public class FindStudent extends HttpServlet {
	protected StudentsDao studentsDao;
	
	@Override
	public void init() throws ServletException {
		studentsDao = StudentsDao.getInstance();
	}
	
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Students> students = new ArrayList<Students>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        
        ////
        String studentID = req.getParameter("studentID");
        String schoolID = req.getParameter("schoolID");
        
        try {
        if (studentID != null && !studentID.trim().isEmpty()){
        //	Students students = new Students(Integer.parseInt(studentID))
        	students = java.util.Arrays.asList(studentsDao.getStudentByID(Integer.parseInt(studentID)));
        	messages.put("success", "Displaying results for " + studentID);
        }
        else if (schoolID != null && !schoolID.trim().isEmpty()) {
        	students = studentsDao.getStudentbySchool(Integer.parseInt(schoolID));
        	messages.put("success", "Displaying results for " + schoolID);
        } else {
            messages.put("success", "Please enter a valid student or school ID.");
        }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
        }
        req.setAttribute("students", students);
        req.getRequestDispatcher("/FindStudent.jsp").forward(req, resp);
	}
	
	// above -> need to figure out how to handle integers
	// below need to update for Students
	// need to change jsp file to be getting by schoolID and not studentID
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Students> students = new ArrayList<Students>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String studentID = req.getParameter("studentID");
        String schoolID = req.getParameter("schoolID");
        try {
        	if (studentID != null && !studentID.trim().isEmpty()) {
        		students = java.util.Arrays.asList(studentsDao.getStudentByID(Integer.parseInt(studentID)));
        		messages.put("success", "Displaying results for " + studentID);
        	}
        	else if (schoolID != null && !schoolID.trim().isEmpty()) {
        		students = studentsDao.getStudentbySchool(Integer.parseInt(schoolID));
        		messages.put("success", "Displaying results for " + schoolID);
        	} else {
        		messages.put("success", "Please enter a valid student or school ID.");
        	}
        	 } catch (SQLException e) {
     			e.printStackTrace();
     			throw new IOException(e);
             }
        req.setAttribute("students", students);
        req.getRequestDispatcher("/FindStudent.jsp").forward(req, resp);
    }
}
