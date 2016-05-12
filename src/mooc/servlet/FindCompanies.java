package mooc.servlet;
import mooc.dal.*;
import mooc.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FindCompany
 */
@WebServlet("/findcompanies")
public class FindCompanies extends HttpServlet {
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

		List<Companies> companies = new ArrayList<Companies>();

		String companyID = req.getParameter("companyid");

		try {
			if (companyID != null && !companyID.trim().isEmpty()){
				companies = java.util.Arrays.asList(companyDao.getCompanyByID(Integer.parseInt(companyID)));
				messages.put("success", "Displaying results for " + companyID);
			} else {
				messages.put("success", "Please enter a valid Company ID.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.setAttribute("companies", companies);
		req.getRequestDispatcher("/FindCompanies.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Companies> companies = new ArrayList<Companies>();


		String companyID = req.getParameter("companyid");

		try {
			if (companyID != null && !companyID.trim().isEmpty()){

				companies = java.util.Arrays.asList(companyDao.getCompanyByID(Integer.parseInt(companyID)));
				messages.put("success", "Displaying results for " + companyID);
			} else {
				messages.put("success", "Please enter a valid Company ID.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.setAttribute("companies", companies);
		req.getRequestDispatcher("/FindCompanies.jsp").forward(req, resp);
	}

}
