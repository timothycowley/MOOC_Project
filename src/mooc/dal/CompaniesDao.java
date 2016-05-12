package mooc.dal;

import mooc.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompaniesDao {
	protected ConnectionManager connectionManager;

	private static CompaniesDao instance = null;
	protected CompaniesDao() {
		connectionManager = new ConnectionManager();
	}
	public static CompaniesDao getInstance() {
		if(instance == null) {
			instance = new CompaniesDao();
		}
		return instance;
	}
	
	public Companies create(Companies active) throws SQLException {
		String insertCompanies = "INSERT INTO Companies(Name,Location,Website) " +
				"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCompanies,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, active.getName());
			insertStmt.setString(2, active.getLocation());
			insertStmt.setString(3, active.getWebsite());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int commentId = -1;
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			active.setID(commentId); // sets the auto-generated CreditCardId key
			return active;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	
	public Companies updateLocation(Companies company, String newCity) throws SQLException {
		String updateCompany = "UPDATE Companies SET Location=? WHERE ID=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCompany);
			updateStmt.setString(1, newCity);
			updateStmt.setInt(2, company.getID());
			updateStmt.executeUpdate();
			company.setLocation(newCity);
			return company;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}


	public Companies delete(Companies active) throws SQLException {
		String deleteCompany = "DELETE FROM Companies WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCompany);
			deleteStmt.setInt(1, active.getID());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public List<Companies> getCompanyByCounty(String County) throws SQLException {
		List<Companies> comp_list = new ArrayList<Companies>();
		String selectUsers = "SELECT companies.ID,companies.Name,companies.Location,companies.Website FROM " +
				"Companies INNER JOIN City_County " +
				" ON Companies.location = City_County.city " +
				" WHERE County=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, County);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ID = results.getInt("ID");
				String name = results.getString("Name");
				String Location = results.getString("Location");
				String Website = results.getString("Website");
				Companies cc = new Companies(ID,name,Location,Website);
				comp_list.add(cc);
			}
			return comp_list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
	}
	
	public Companies getCompanyByID(int ID) throws SQLException {
		String selectUsers = "SELECT ID,Name,Location,Website FROM Companies"
				+ " WHERE ID=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setInt(1, ID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String name = results.getString("Name");
				String Location = results.getString("Location");
				String Website = results.getString("Website");
				Companies cc = new Companies(ID,name,Location,Website);
				return cc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Volunteer> getVolunteerByCompanyID(int companyID) throws SQLException {
		List<Volunteer> vol_list = new ArrayList<Volunteer>();
		String selectVolunteers = "SELECT Volunteer.ID, Volunteer.First_Name,Volunteer.Last_Name"
				+ " Volunteer.Email, Volunteer.Association FROM Companies INNRER JOIN Volunteer ON"
				+ " Volunteer.Association = Companies.ID;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVolunteers);
			selectStmt.setInt(1, companyID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ID = results.getInt("ID");
				String First_Name = results.getString("First_Name");
				String Last_Name = results.getString("Last_Name");
				String Email = results.getString("Email");
				int Association = results.getInt("Association");
				Volunteer volunteer = new Volunteer(ID,First_Name,Last_Name,Email,Association);
				vol_list.add(volunteer);
			}
			return vol_list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
	}
	
	
}
