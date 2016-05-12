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

@WebServlet("/createhistory")
public class CreateHistory extends HttpServlet {
	protected HistoryDao historyDao;
	
	@Override
	public void init() throws ServletException {
		historyDao = HistoryDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateHistory.jsp").forward(req, resp);
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
        	String volunteerID = req.getParameter("volunteerID");
        	
        	
        	//converting to java.sql.date.format
        	String startDate = req.getParameter("startdate");
        	java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        	
        	String endDate = req.getParameter("enddate");
        	java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);
	        try {
	        	History hist = new History(Integer.parseInt(studentID), Integer.parseInt(moocID), Integer.parseInt(volunteerID), sqlStartDate, sqlEndDate);
	        	hist = historyDao.create(hist);
	        	messages.put("success", "Successfully created History for Student " + studentID);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e); 
	        } 
        }
        
        req.getRequestDispatcher("/CreateHistory.jsp").forward(req, resp);
    }
	
}

