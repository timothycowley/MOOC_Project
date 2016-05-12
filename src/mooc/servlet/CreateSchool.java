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

@WebServlet("/createschool")
public class CreateSchool extends HttpServlet {
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
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateSchool.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate id.
        {
        	// Create the School
        	String name = req.getParameter("name");
        	String participation = req.getParameter("participation");
        	String contacted = req.getParameter("contacted");
        	String pocname = req.getParameter("pocname");
        	String pocemail = req.getParameter("pocemail");
        	String location = req.getParameter("location");
        	
	        try {
	        	Schools school = new Schools(name, (Boolean.parseBoolean(participation)), (Boolean.parseBoolean(contacted)), pocname, pocemail, location);
	        	school = schoolsDao.create(school);
	        	messages.put("success", "Successfully created School " + name);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CreateSchool.jsp").forward(req, resp);
    }
}



