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

@WebServlet("/updateschool")
public class UpdateSchool extends HttpServlet {
	protected SchoolsDao schoolsDao;
	
	@Override
	public void init() throws ServletException {
		schoolsDao = schoolsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String schoolid = req.getParameter("schoolid");
        if (schoolid == null || schoolid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid school ID.");
        } else {
        	try {
        		Schools school = schoolsDao.getSchoolByID(Integer.parseInt(schoolid));
        		if(school == null) {
        			messages.put("success", "ID does not exist.");
        		}
        		req.setAttribute("school", school);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateSchool.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String schoolid = req.getParameter("schoolid");
        if (schoolid == null || schoolid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid School ID.");
        } else {
        	try {
        		Schools school = schoolsDao.getSchoolByID(Integer.parseInt(schoolid));
        		if(school == null) {
        			messages.put("success", "ID does not exist. No update to perform.");
        		} else {
        			String newParticipation = req.getParameter("participating");
        			if (newParticipation == null || newParticipation.trim().isEmpty()) {
        	            messages.put("success", "Please enter true or false.");
        	        } else {
        	        	school = schoolsDao.updateParticipation(school, Boolean.parseBoolean(newParticipation));
        	        	messages.put("success", "Successfully updated school " + schoolid);
        	        }
        		}
        		req.setAttribute("school", school);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateSchool.jsp").forward(req, resp);
    }
}