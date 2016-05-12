package mooc.servlet;

import mooc.dal.*;
import mooc.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@WebServlet("/updatevolunteer")
public class UpdateVolunteer extends HttpServlet {
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

        // Retrieve user and validate.
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid volunteer ID.");
        } else {
        	try {
        		Volunteer volunteer = volunteerDao.getVolunteerFromID(Integer.parseInt(id));
        		if(volunteer == null) {
        			messages.put("success", "ID does not exist.");
        		}
        		req.setAttribute("volunteer", volunteer);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateVolunteer.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Volunteer ID.");
        } else {
        	try {
        		Volunteer volunteer = volunteerDao.getVolunteerFromID(Integer.parseInt(id));
        		if(volunteer == null) {
        			messages.put("success", "ID does not exist. No update to perform.");
        		} else {
        			String newComapny = req.getParameter("companyID");
        			if (newComapny == null || newComapny.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid School.");
        	        } else {
        	        	volunteer = volunteerDao.updateAssociation(volunteer, Integer.parseInt(newComapny));
        	        	messages.put("success", "Successfully updated " + id);
        	        }
        		}
        		req.setAttribute("volunteer", volunteer);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateVolunteer.jsp").forward(req, resp);
    }
}