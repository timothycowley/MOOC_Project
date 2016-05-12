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

@WebServlet("/deleteactiveclass")
public class DeleteActiveClass extends HttpServlet {
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Active Class");        
        req.getRequestDispatcher("/DeleteActiveClass.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String activeid = req.getParameter("activeid");
        if (activeid == null || activeid.trim().isEmpty()) {
            messages.put("title", "Invalid Active Class ID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	       // Students student = new Students(id);
	        try {
	        	Active_Classes active = activeDao.getActiveClassByID(Integer.parseInt(activeid));
	        	active = activeDao.delete(active);
	        	// Update the message.
		        if (active == null) {
		            messages.put("title", "Successfully deleted Active Class " + activeid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete Active Class " + activeid);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteActiveClass.jsp").forward(req, resp);
    }
}

