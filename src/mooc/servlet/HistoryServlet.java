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


@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
	
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
		
        List<History> histories = new ArrayList<History>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String historyID = req.getParameter("historyid");
        String studentID = req.getParameter("studentid");
        String moocID = req.getParameter("moocid");
        
        try {
        	if (historyID != null && !historyID.trim().isEmpty()) {
        		histories = java.util.Arrays.asList(historyDao.getHistoryByID(Integer.parseInt(historyID)));
        		messages.put("title", "Information for History " + historyID);
        } else if (studentID != null && !studentID.trim().isEmpty()) {
        	histories = historyDao.getStudentHistory(Integer.parseInt(studentID));
         	messages.put("title", "Class History for Student " + studentID);
        }
        else if (moocID != null && !moocID.trim().isEmpty()) {
        	histories = historyDao.getMOOCHistory(Integer.parseInt(moocID));
         	messages.put("title", "Past Classes for MOOC " + moocID);
        }
        else {
            messages.put("title", "Invalid Student ID.");
        }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        req.setAttribute("histories", histories);
        req.getRequestDispatcher("/History.jsp").forward(req, resp);
    }	
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<History> histories = new ArrayList<History>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String historyID = req.getParameter("historyid");
        String studentID = req.getParameter("studentid");
        String moocID = req.getParameter("moocid");
        try {
        	if (historyID != null && !historyID.trim().isEmpty()) {
        		histories = java.util.Arrays.asList(historyDao.getHistoryByID(Integer.parseInt(historyID)));
        		messages.put("title", "Information for History " + historyID);
            } else if (studentID != null && !studentID.trim().isEmpty()) {
             	histories = historyDao.getStudentHistory(Integer.parseInt(studentID));
              	messages.put("title", "Class History for Student " + studentID);
             }
        	 else if (moocID != null && !moocID.trim().isEmpty()) {
             	histories = historyDao.getMOOCHistory(Integer.parseInt(moocID));
              	messages.put("title", "Past Classes for MOOC " + moocID);
             }
             else {
                 messages.put("title", "Invalid Student ID.");
        }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        req.setAttribute("histories", histories);
        req.getRequestDispatcher("/History.jsp").forward(req, resp);
    }
}
	
