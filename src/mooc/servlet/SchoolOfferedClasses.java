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


@WebServlet("/schoolofferedclasses")
public class SchoolOfferedClasses extends HttpServlet {
	
	protected School_to_MOOCDao schoolMoocDao;
	protected Main_MOOCDao moocDao;
	
	@Override
	public void init() throws ServletException {
		schoolMoocDao = School_to_MOOCDao.getInstance();
		moocDao = Main_MOOCDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
       // List<School_to_MOOC> schoolclasses = new ArrayList<School_to_MOOC>();
        List<Main_MOOC> moocs = new ArrayList<Main_MOOC>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
          String moocID = req.getParameter("moocid");
          String schoolID = req.getParameter("schoolid");
        try {
        	if (moocID != null && !moocID.trim().isEmpty()) {
        		moocs = java.util.Arrays.asList(moocDao.getMainMOOCByID(Integer.parseInt(moocID)));
        		messages.put("title", "Information for MOOC " + moocID);
        	}
        else if (schoolID != null && !schoolID.trim().isEmpty()) {
        	moocs = schoolMoocDao.getMOOCsBySchool(Integer.parseInt(schoolID));
         	messages.put("title", "Currently Offered Classes for School " + schoolID);
        } else {
            messages.put("title", "Invalid School ID.");
        }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        req.setAttribute("moocs", moocs);
        req.getRequestDispatcher("/SchoolOfferedClasses.jsp").forward(req, resp);
    }	
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

       // List<School_to_MOOC> schoolclasses = new ArrayList<School_to_MOOC>();
        List<Main_MOOC> moocs = new ArrayList<Main_MOOC>();
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String moocID = req.getParameter("moocid");
        String schoolID = req.getParameter("schoolid");
      try {
      	if (moocID != null && !moocID.trim().isEmpty()) {
      		moocs = java.util.Arrays.asList(moocDao.getMainMOOCByID(Integer.parseInt(moocID)));
      		messages.put("title", "Information for MOOC " + moocID);
      	}
      else if (schoolID != null && !schoolID.trim().isEmpty()) {
        	moocs = schoolMoocDao.getMOOCsBySchool(Integer.parseInt(schoolID));
         	messages.put("title", "Currently Offered Classes for School " + schoolID);
        } else {
            messages.put("title", "Invalid School ID.");
        }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        req.setAttribute("moocs", moocs);
        req.getRequestDispatcher("/SchoolOfferedClasses.jsp").forward(req, resp);
    }
}
	

