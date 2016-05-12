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


@WebServlet("/studentschool")
public class StudentSchool extends HttpServlet {
	
	protected SchoolsDao schoolsDao;
	
	@Override
	public void init() throws ServletException {
		schoolsDao = SchoolsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        List<Schools> schools = new ArrayList<Schools>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String schoolid = req.getParameter("schoolid");
        String moocid = req.getParameter("moocid");
        
        try {
        if (schoolid != null && !schoolid.trim().isEmpty()) {
        	schools = java.util.Arrays.asList(schoolsDao.getSchoolByID(Integer.parseInt(schoolid)));
         	messages.put("title", "Information for School " + schoolid);
        } else if (moocid != null && !moocid.trim().isEmpty()) {
        	schools = schoolsDao.getSchoolsForMOOCID(Integer.parseInt(moocid));
        	messages.put("title", "Schools Offering " + moocid);
        }
        else {
            messages.put("title", "Invalid Search Parameters.");
        }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        req.setAttribute("schools", schools);
        req.getRequestDispatcher("/StudentSchool.jsp").forward(req, resp);
    }	
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Schools> schools = new ArrayList<Schools>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String schoolid = req.getParameter("schoolid");
        String moocid = req.getParameter("moocid");
        try {
        if (schoolid != null && !schoolid.trim().isEmpty()) {
        	schools = java.util.Arrays.asList(schoolsDao.getSchoolByID(Integer.parseInt(schoolid)));
         	messages.put("title", "Information for School " + schoolid);
        } else if (moocid != null && !moocid.trim().isEmpty()) {
        	schools = schoolsDao.getSchoolsForMOOCID(Integer.parseInt(moocid));
        	messages.put("title", "Schools Offering " + moocid);
        }
         else {
            messages.put("title", "Invalid School ID.");
        }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        req.setAttribute("schools", schools);
        req.getRequestDispatcher("/StudentSchool.jsp").forward(req, resp);
    }
}
	

