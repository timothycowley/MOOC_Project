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
 * Servlet implementation class DeleteCompany
 */
@WebServlet("/deletecompany")
public class DeleteCompany extends HttpServlet {
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Company");        
        req.getRequestDispatcher("/DeleteCompany.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String companyid = req.getParameter("companyid");
        if (companyid == null || companyid.trim().isEmpty()) {
            messages.put("title", "Invalid Comapny ID");
            messages.put("disableSubmit", "true");
        } else {
	        try {
	        	Companies comp= companyDao.getCompanyByID(Integer.parseInt(companyid));
	        	comp = companyDao.delete(comp);
	        	// Update the message.
		        if (comp == null) {
		            messages.put("title", "Successfully deleted " + companyid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + companyid);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeleteCompany.jsp").forward(req, resp);
	}

}
