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

@WebServlet("/deleteschool")
public class DeleteSchool extends HttpServlet {
	protected SchoolsDao schoolsDao;
	
	@Override
	public void init() throws ServletException {
		schoolsDao = SchoolsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete School");        
        req.getRequestDispatcher("/DeleteSchool.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String schoolid = req.getParameter("schoolid");
        if (schoolid == null || schoolid.trim().isEmpty()) {
            messages.put("title", "Invalid School ID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	       // Students student = new Students(id);
	        try {
	        	Schools school = schoolsDao.getSchoolByID(Integer.parseInt(schoolid));
	        	school = schoolsDao.delete(school);
	        	// Update the message.
		        if (school == null) {
		            messages.put("title", "Successfully deleted school " + schoolid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete school " + schoolid);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteSchool.jsp").forward(req, resp);
    }
}

