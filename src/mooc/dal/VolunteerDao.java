package mooc.dal;

import mooc.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class VolunteerDao {
	protected ConnectionManager connectionManager;
	
	private static VolunteerDao instance = null;
	protected VolunteerDao() {
		connectionManager = new ConnectionManager();
	}
	public static VolunteerDao getInstance() {
		if(instance == null) {
			instance = new VolunteerDao();
		}
		return instance;
	}
	
	
	protected int ID;
	protected String First_Name;
	protected String Last_Name;
	protected String Email;
	protected int Association;

	// public create(Volunteer volunteer)
	// adds a new Volunteer to the database
	public Volunteer create(Volunteer volunteer) throws SQLException {
		String insertVolunteer = "INSERT INTO Volunteer(First_Name, Last_Name, Email, Association) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		// below needed for autogenerated keys
		ResultSet resultKey = null;
		// end autogenerated key code 1of4
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertVolunteer,
					// below needed for autogenerated keys
					Statement.RETURN_GENERATED_KEYS);
                    // end autogenerated key code 2of4
			insertStmt.setString(1, volunteer.getFirst_Name());
			insertStmt.setString(2, volunteer.getLast_Name());
			insertStmt.setString(3, volunteer.getEmail());
			insertStmt.setInt(4, volunteer.getAssociation());

			insertStmt.executeUpdate();
			
			// bellow autogenerates key 
			resultKey = insertStmt.getGeneratedKeys();
			int volunteerId = -1;
			if(resultKey.next()) {
				volunteerId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			volunteer.setID(volunteerId);
			// end autogenerated key code 3of4
			return volunteer;
			
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
			// bellow used for autogenerated key
			if(resultKey != null) {
				resultKey.close();
			}
			// end autogenerated key code 4of4
		}
	}
	// public updateAssociation(Volunteer volunteer, Integer newAssociation)
	// GIVEN: A Volunteer and a CompanyID (an Association) updates the 
	// association of the given volunteer
	public Volunteer updateAssociation(Volunteer volunteer, Integer newAssociation) throws SQLException {
		String updateVolunteer = "UPDATE Volunteer SET Association=? WHERE ID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateVolunteer);
			updateStmt.setInt(1, newAssociation);
			updateStmt.setInt(2, volunteer.getID());
			updateStmt.executeUpdate();
			
			// Update the volunteer param before returning to the caller.
			volunteer.setAssociation(newAssociation);
			return volunteer;
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
	
	// public Volunteer delete(Volunteer volunteer) 
	// Given a volunteer, removes it from the database
	public Volunteer delete(Volunteer volunteer) throws SQLException {
		String deleteVolunteer = "DELETE FROM Volunteer WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteVolunteer);
			deleteStmt.setInt(1, volunteer.getID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Volunteer instance
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
	
	// public Volunteer getVolunteerFromID(int id)
	// given a vounteer ID, retrieves the matching Volunteer
	public Volunteer getVolunteerFromID(int id) throws SQLException {
		String selectVolunteer = "SELECT * FROM Volunteer WHERE ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectVolunteer);
			selectStmt.setInt(1, id);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				Integer resultID = results.getInt("ID");
				String firstName = results.getString("First_Name");
				String lastName = results.getString("Last_Name");
				String email = results.getString("Email");
				Integer association = results.getInt("Association");
				Volunteer volunteer = new Volunteer(resultID, firstName, lastName, email, association);
				return volunteer;
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
	
	
	
	// NEW 
		// Gets all volunteers for the specified schoolID
		public List<Volunteer> getVolunteerbySchool(int schoolID) throws SQLException {
			List<Volunteer> vol_list = new ArrayList<Volunteer>();
			String selectUsers = "SELECT * "  +
			"FROM Volunteer INNER JOIN volunteer_to_school " +
			"ON Volunteer.ID = volunteer_to_school.VolunteerID WHERE SchoolID=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectUsers);
				selectStmt.setInt(1, schoolID);
				results = selectStmt.executeQuery();
				while(results.next()) {
					int ID = results.getInt("ID");
					String First_Name = results.getString("First_Name");
					String Last_Name = results.getString("Last_Name");
					String Email = results.getString("Email");
					int Association = results.getInt("Association");
					Volunteer cc = new Volunteer(ID,First_Name,Last_Name,Email,Association);
					vol_list.add(cc);
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
		
		// NEW 
		// Gets all volunteers for the specified moocID
		public List<Volunteer> getVolunteerbyMooc(int moocID) throws SQLException {
			List<Volunteer> vol_list = new ArrayList<Volunteer>();
			String selectUsers = "SELECT * "  +
			"FROM Volunteer INNER JOIN volunteer_to_current_mooc " +
			"ON Volunteer.ID = volunteer_to_current_mooc.VolunteerID WHERE moocID=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectUsers);
				selectStmt.setInt(1, moocID);
				results = selectStmt.executeQuery();
				while(results.next()) {
					int ID = results.getInt("ID");
					String First_Name = results.getString("First_Name");
					String Last_Name = results.getString("Last_Name");
					String Email = results.getString("Email");
					int Association = results.getInt("Association");
					Volunteer cc = new Volunteer(ID,First_Name,Last_Name,Email,Association);
					vol_list.add(cc);
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
		
		// NEW 
		// Gets all volunteers for the specified companyID
		public List<Volunteer> getVolunteerbyCompany(int companyID) throws SQLException {
			List<Volunteer> vol_list = new ArrayList<Volunteer>();
			String selectUsers = "SELECT * "  +
			"FROM Volunteer WHERE Association=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectUsers);
				selectStmt.setInt(1, companyID);
				results = selectStmt.executeQuery();
				while(results.next()) {
					int ID = results.getInt("ID");
					String First_Name = results.getString("First_Name");
					String Last_Name = results.getString("Last_Name");
					String Email = results.getString("Email");
					int Association = results.getInt("Association");
					Volunteer cc = new Volunteer(ID,First_Name,Last_Name,Email,Association);
					vol_list.add(cc);
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