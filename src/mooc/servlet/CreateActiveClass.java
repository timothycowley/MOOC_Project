package mooc.servlet;

import mooc.dal.*;
import mooc.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createactiveclass")
public class CreateActiveClass extends HttpServlet {
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
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateActiveClass.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate id.
        {
        	// Create the Active Class
        	String studentID = req.getParameter("studentID");
        	String moocID = req.getParameter("moocID");
        	
        	
        	//converting to java.sql.date.format
        	String startDate = req.getParameter("startdate");
        	java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        	
        	String endDate = req.getParameter("enddate");
        	java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);
	        try {
	        	Active_Classes active = new Active_Classes(Integer.parseInt(studentID), Integer.parseInt(moocID), sqlStartDate, sqlEndDate);
	        	active = activeDao.create(active);
	        	messages.put("success", "Successfully created Active Class for Student " + studentID);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e); 
	        } 
        }
        
        req.getRequestDispatcher("/CreateActiveClass.jsp").forward(req, resp);
    }
	
	private static java.sql.Date convertStringToSQLDate(String date)
	{
		return java.sql.Date.valueOf(date);
	}
}



