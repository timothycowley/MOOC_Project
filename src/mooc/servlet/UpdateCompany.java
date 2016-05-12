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

/**
 * Servlet implementation class UpdateCompany
 */
@WebServlet("/updatecompany")
public class UpdateCompany extends HttpServlet {
	protected CompaniesDao companyDao;

	@Override
	public void init() throws ServletException {
		companyDao = CompaniesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String companyid = req.getParameter("companyid");
        if (companyid == null || companyid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Company");
        } else {
        	try {
        		Companies comp = companyDao.getCompanyByID(Integer.parseInt(companyid));
        		if(comp == null) {
        			messages.put("success", "Company does not exist.");
        		}
        		req.setAttribute("comapny", comp);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateCompany.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String companyid = req.getParameter("companyid");
        if (companyid == null || companyid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Company");
        } else {
        	try {
        		Companies comp = companyDao.getCompanyByID(Integer.parseInt(companyid));
        		if(comp == null) {
        			messages.put("success", "Company does not exist. No update to perform.");
        		} else {
        			String newcity = req.getParameter("newcity");
        			if (newcity == null || newcity.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid City.");
        	        } else {
        	        	comp = companyDao.updateLocation(comp, newcity);
        	        	messages.put("success", "Successfully updated " + companyid);
        	        }
        		}
        		req.setAttribute("company", comp);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateCompany.jsp").forward(req, resp);
	}

}
