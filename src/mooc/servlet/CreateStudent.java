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

@WebServlet("/createstudent")
public class CreateStudent extends HttpServlet {
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
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateStudent.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate id.
        {
        	// Create the Student.
        	String firstName = req.getParameter("firstname");
        	String lastName = req.getParameter("lastname");
        	String email = req.getParameter("email");
        	String schoolID = req.getParameter("schoolID");
        	String year = req.getParameter("class");
        	
	        try {
	        	Students student = new Students(firstName, lastName, email, (Integer.parseInt(schoolID)), year);
	        	student = studentsDao.create(student);
	        	messages.put("success", "Successfully created Student " + firstName + " " + lastName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CreateStudent.jsp").forward(req, resp);
    }
}



