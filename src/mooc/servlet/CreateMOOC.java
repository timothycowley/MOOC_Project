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

@WebServlet("/createmooc")
public class CreateMOOC extends HttpServlet {
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
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateMOOC.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate id.
        {
        	// Create the MOOC
        	String name = req.getParameter("name");
        	String description = req.getParameter("description");
        	String source = req.getParameter("source");
        	String directlink = req.getParameter("directlink");
        	String offered = req.getParameter("offered");
        	String difficulty = req.getParameter("difficulty");
        	String type = req.getParameter("type");
        	String length = req.getParameter("length");
        	
	        try {
	        	Main_MOOC mooc = new Main_MOOC(name, description, source, directlink, (Boolean.parseBoolean(offered)), (Integer.parseInt(difficulty)), type, (Integer.parseInt(length)));
	        	mooc = moocDao.create(mooc);
	        	messages.put("success", "Successfully created MOOC " + name);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CreateMOOC.jsp").forward(req, resp);
    }
}



