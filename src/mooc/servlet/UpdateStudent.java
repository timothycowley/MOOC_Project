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

@WebServlet("/updatestudent")
public class UpdateStudent extends HttpServlet {
	protected StudentsDao studentsDao;
	
	@Override
	public void init() throws ServletException {
		studentsDao = studentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid student ID.");
        } else {
        	try {
        		Students student = studentsDao.getStudentByID(Integer.parseInt(id));
        		if(student == null) {
        			messages.put("success", "ID does not exist.");
        		}
        		req.setAttribute("student", student);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateStudent.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Student ID.");
        } else {
        	try {
        		Students student = studentsDao.getStudentByID(Integer.parseInt(id));
        		if(student == null) {
        			messages.put("success", "ID does not exist. No update to perform.");
        		} else {
        			String newSchool = req.getParameter("schoolID");
        			if (newSchool == null || newSchool.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid School.");
        	        } else {
        	        	student = studentsDao.updateSchoolID(student, Integer.parseInt(newSchool));
        	        	messages.put("success", "Successfully updated " + id);
        	        }
        		}
        		req.setAttribute("student", student);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateStudent.jsp").forward(req, resp);
    }
}