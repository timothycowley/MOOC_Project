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

@WebServlet("/deletestudent")
public class DeleteStudent extends HttpServlet {
	protected StudentsDao studentsDao;
	
	@Override
	public void init() throws ServletException {
		studentsDao = StudentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Student");        
        req.getRequestDispatcher("/DeleteStudent.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("title", "Invalid Student ID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	       // Students student = new Students(id);
	        try {
	        	Students student = studentsDao.getStudentByID(Integer.parseInt(id));
	        	student = studentsDao.delete(student);
	        	// Update the message.
		        if (student == null) {
		            messages.put("title", "Successfully deleted " + id);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + id);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteStudent.jsp").forward(req, resp);
    }
}

