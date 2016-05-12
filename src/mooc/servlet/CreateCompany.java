package mooc.servlet;
import mooc.dal.*;
import mooc.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mooc.dal.CompaniesDao;
import mooc.model.Students;

/**
 * Servlet implementation class CreateCompany
 */
@WebServlet("/createcompany")
public class CreateCompany extends HttpServlet {
	protected CompaniesDao companyDao;

	@Override
	public void init() throws ServletException {
		companyDao = CompaniesDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateCompany.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate id.
        {
        	// Create the Student.
        	String Name = req.getParameter("Name");
        	String Location = req.getParameter("Location");
        	String Website = req.getParameter("Website");
        	
	        try {
	        	Companies comp = new Companies(Name,Location,Website);
	        	comp = companyDao.create(comp);
	        	messages.put("success", "Successfully created Company " + Name);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CreateCompany.jsp").forward(req, resp);
	}

}
