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

@WebServlet("/deletemooc")
public class DeleteMOOC extends HttpServlet {
	protected Main_MOOCDao moocDao;
	
	@Override
	public void init() throws ServletException {
		moocDao = Main_MOOCDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete MOOC");        
        req.getRequestDispatcher("/DeleteMOOC.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String moocid = req.getParameter("moocid");
        if (moocid == null || moocid.trim().isEmpty()) {
            messages.put("title", "Invalid MOOC ID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	       // Students student = new Students(id);
	        try {
	        	Main_MOOC mooc = moocDao.getMainMOOCByID(Integer.parseInt(moocid));
	        	mooc = moocDao.delete(mooc);
	        	// Update the message.
		        if (mooc == null) {
		            messages.put("title", "Successfully deleted MOOC " + moocid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete MOOC " + moocid);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteMOOC.jsp").forward(req, resp);
    }
}

