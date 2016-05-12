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

@WebServlet("/updatemooc")
public class UpdateMOOC extends HttpServlet {
	protected Main_MOOCDao moocDao;
	
	@Override
	public void init() throws ServletException {
		moocDao = moocDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String moocid = req.getParameter("moocid");
        if (moocid == null || moocid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid MOOC ID.");
        } else {
        	try {
        		Main_MOOC mooc = moocDao.getMainMOOCByID(Integer.parseInt(moocid));
        		if(mooc == null) {
        			messages.put("success", "ID does not exist.");
        		}
        		req.setAttribute("mooc", mooc);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateMOOC.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String moocid = req.getParameter("moocid");
        if (moocid == null || moocid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid MOOC ID.");
        } else {
        	try {
        		Main_MOOC mooc = moocDao.getMainMOOCByID(Integer.parseInt(moocid));
        		if(mooc == null) {
        			messages.put("success", "ID does not exist. No update to perform.");
        		} else {
        			String newDescription = req.getParameter("description");
        			if (newDescription == null || newDescription.trim().isEmpty()) {
        	            messages.put("success", "Please enter a description.");
        	        } else {
        	        	mooc = moocDao.updateMain_MOOC(mooc, newDescription);
        	        	messages.put("success", "Successfully updated MOOC " + moocid);
        	        }
        		}
        		req.setAttribute("mooc", mooc);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateMOOC.jsp").forward(req, resp);
    }
}