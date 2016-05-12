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

@WebServlet("/updateactiveclass")
public class UpdateActiveClass extends HttpServlet {
	protected Active_ClassesDao activeDao;
	
	@Override
	public void init() throws ServletException {
		activeDao = activeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String activeid = req.getParameter("activeid");
        if (activeid == null || activeid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Active ID.");
        } else {
        	try {
        		Active_Classes active = activeDao.getActiveClassByID(Integer.parseInt(activeid));
        		if(active == null) {
        			messages.put("success", "ID does not exist.");
        		}
        		req.setAttribute("active", active);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateActiveClass.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
       
        
        String activeid = req.getParameter("activeid");
        if (activeid == null || activeid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid MOOC ID.");
        } else {
        	try {
        		Active_Classes active = activeDao.getActiveClassByID(Integer.parseInt(activeid));
        		if(active == null) {
        			messages.put("success", "ID does not exist. No update to perform.");
        		} else {
        			
        			String newEndDate = req.getParameter("enddate");
        		   
        			
        			if (newEndDate == null || newEndDate.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid end date (YYYY-MM-DD).");
        	        } else {
        	        	java.sql.Date sqlEndDate = java.sql.Date.valueOf(newEndDate);
        	        	active = activeDao.updateEndDate(active, sqlEndDate);
        	        	messages.put("success", "Successfully updated Active Class " + activeid);
        	        }
        		}
        		req.setAttribute("active", active);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateActiveClass.jsp").forward(req, resp);
    }
}