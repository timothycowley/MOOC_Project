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


@WebServlet("/schoolactiveclasses")
public class SchoolActiveClasses extends HttpServlet {
	
	protected Active_ClassesDao activeDao;
	
	@Override
	public void init() throws ServletException {
		activeDao = Active_ClassesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
       // List<School_to_MOOC> schoolclasses = new ArrayList<School_to_MOOC>();
        List<Active_Classes> activeclasses = new ArrayList<Active_Classes>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
          String moocID = req.getParameter("moocid");
          String schoolID = req.getParameter("schoolid");
          String studentID = req.getParameter("studentid");
          
        try {
        	if (moocID != null && !moocID.trim().isEmpty()) {
        		activeclasses = activeDao.getClassListbyMOOCID(Integer.parseInt(moocID));
        		messages.put("title", "Active Classes for MOOC " + moocID);
        } else if (schoolID != null && !schoolID.trim().isEmpty()) {
        	activeclasses = activeDao.getActiveClassesForSchool(Integer.parseInt(schoolID));
         	messages.put("title", "Active Classes for School " + schoolID);
        } else if (studentID != null && !studentID.trim().isEmpty()) {
        	activeclasses = activeDao.getActiveClassesForStudent(Integer.parseInt(studentID));
            messages.put("title", "Active Classes for Student " + studentID);
        } else {
            messages.put("title", "Invalid Search Parameters.");
        }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        req.setAttribute("activeclasses", activeclasses);
        req.getRequestDispatcher("/SchoolActiveClasses.jsp").forward(req, resp);
    }	
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

       // List<School_to_MOOC> schoolclasses = new ArrayList<School_to_MOOC>();
        List<Active_Classes> activeclasses = new ArrayList<Active_Classes>();
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String activeID = req.getParameter("activeid");
        String moocID = req.getParameter("moocid");
        String schoolID = req.getParameter("schoolid");
        String studentID = req.getParameter("studentid");
        
        try {
        	if (activeID != null && !activeID.trim().isEmpty()) {
        		activeclasses = java.util.Arrays.asList(activeDao.getActiveClassByID(Integer.parseInt(activeID)));
        		messages.put("title", "Information for Active Class " + activeID);
        } else if (moocID != null && !moocID.trim().isEmpty()) {
        		activeclasses = activeDao.getClassListbyMOOCID(Integer.parseInt(moocID));
        		messages.put("title", "Active Classes for MOOC " + moocID);
        } else if (schoolID != null && !schoolID.trim().isEmpty()) {
        	activeclasses = activeDao.getActiveClassesForSchool(Integer.parseInt(schoolID));
         	messages.put("title", "Currently Offered Classes for School " + schoolID);
        } else if (studentID != null && !studentID.trim().isEmpty()) {
        	activeclasses = activeDao.getActiveClassesForStudent(Integer.parseInt(studentID));
            messages.put("title", "Active Classes for Student " + studentID); 	
        } else {
            messages.put("title", "Invalid Search Paramaters.");
        }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        req.setAttribute("activeclasses", activeclasses);
        req.getRequestDispatcher("/SchoolActiveClasses.jsp").forward(req, resp);
    }
}
	

