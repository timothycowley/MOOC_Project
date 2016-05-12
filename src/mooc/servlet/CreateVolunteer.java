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

@WebServlet("/createvolunteer")
public class CreateVolunteer extends HttpServlet {
	protected VolunteerDao volunteerDao;
	
	@Override
	public void init() throws ServletException {
		volunteerDao = VolunteerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateVolunteer.jsp").forward(req, resp);
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
        	String association = req.getParameter("association");
        	
	        try {
	        	Volunteer volunteer = new Volunteer(firstName, lastName, email, (Integer.parseInt(association)));
	        	volunteer = volunteerDao.create(volunteer);
	        	messages.put("success", "Successfully created Volunteer " + firstName + " " + lastName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CreateVolunteer.jsp").forward(req, resp);
    }
}



