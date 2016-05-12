package mooc.servlet;
import mooc.dal.*;
import mooc.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateHistory")
public class UpdateHistory extends HttpServlet {
	protected HistoryDao historyDao;

	public void init() throws ServletException {
		historyDao = HistoryDao.getInstance();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve user and validate.
		String historyid = req.getParameter("historyid");
		if (historyid == null || historyid.trim().isEmpty()) {
			messages.put("success", "Please enter a valid History ID.");
		} else {
			try {
				History history = historyDao.getHistoryByID(Integer.parseInt(historyid));
				if(history == null) {
					messages.put("success", "ID does not exist.");
				}
				req.setAttribute("history", history);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/UpdateHistory.jsp").forward(req, resp);
	}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve user and validate.
    String historyid = req.getParameter("historyid");
    if (historyid == null || historyid.trim().isEmpty()) {
        messages.put("success", "Please enter a valid History ID.");
    } else {
    	try {
    		History history = historyDao.getHistoryByID(Integer.parseInt(historyid));
    		if(history == null) {
    			messages.put("success", "ID does not exist. No update to perform.");
    		} else {
    			String newEndDate = req.getParameter("newdate");
    			
    			if (newEndDate == null || newEndDate.trim().isEmpty()) {
    	            messages.put("success", "Please enter a valid end date (YYYY-MM-DD).");
    	        } else {
    	        	java.sql.Date sqlEndDate = java.sql.Date.valueOf(newEndDate); 
    	        	history = historyDao.updateEndDate(history, sqlEndDate);
    	        	messages.put("success", "Successfully updated History " + historyid);
    	        }
    		}
    		req.setAttribute("history", history);
    	} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    }
    
    req.getRequestDispatcher("/UpdateHistory.jsp").forward(req, resp);

}

}
