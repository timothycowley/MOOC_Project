package mooc.dal;

import mooc.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SchoolsDao {
	protected ConnectionManager connectionManager;

	private static SchoolsDao instance = null;
	protected SchoolsDao() {
		connectionManager = new ConnectionManager();
	}
	public static SchoolsDao getInstance() {
		if(instance == null) {
			instance = new SchoolsDao();
		}
		return instance;
	}
	
	public Schools create(Schools active) throws SQLException {
		String insertSchools = "INSERT INTO Schools(Name,Participation,Contacted,POC_Name," +
				"POC_Email,City) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSchools,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, active.getName());
			insertStmt.setBoolean(2, active.isParticipation());
			insertStmt.setBoolean(3, active.isContacted());
			insertStmt.setString(4, active.getPOC_Name());
			insertStmt.setString(5, active.getPOC_Email());
			insertStmt.setString(6, active.getCity());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int commentId = -1;
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			active.setID(commentId); 
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

	public Schools updateParticipation(Schools active, boolean Participation) throws SQLException {
		String updateSchools = "UPDATE Schools SET Participation=? WHERE ID=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSchools);
			updateStmt.setBoolean(1, Participation);
			updateStmt.setInt(2, active.getID());
			updateStmt.executeUpdate();
			active.setParticipation(Participation);
			return active;
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


	public Schools delete(Schools active) throws SQLException {
		String deleteSchools = "DELETE FROM Schools WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSchools);
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

	
	public Schools getSchoolByID(int id) throws SQLException {
		String selectSchool = "SELECT * FROM Schools WHERE ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSchool);
			selectStmt.setInt(1,  id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultID = results.getInt("ID");
				String name = results.getString("Name");
				Boolean participation = results.getBoolean("Participation");
				Boolean contacted = results.getBoolean("Contacted");
				String pocname = results.getString("POC_Name");
				String pocemail = results.getString("POC_Email");
				String city = results.getString("City");
				Schools school = new Schools(resultID, name, participation, contacted, pocname, pocemail, city);
				return school;
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
	
	
	public List<Schools> getParticipatingSchools() throws SQLException {
		List<Schools> mooc_list = new ArrayList<Schools>();
		String selectUsers = "SELECT Name,Participation,Contacted,POC_Name," +
				"POC_Email,City " +	" FROM Schools WHERE Participation=1;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ID = results.getInt("ID");
				String name = results.getString("Name");
				boolean Participation = results.getBoolean("Participation");
				boolean Contacted = results.getBoolean("Contacted");
				String POC_Name = results.getString("POC_Name");
				String POC_Email = results.getString("POC_Email");
				String City = results.getString("City");
				Schools cc = new Schools(ID,name,Participation,Contacted,POC_Name,POC_Email,City);
				mooc_list.add(cc);
			}
			return mooc_list;
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
	
	
	public List<Schools> getSchoolsForMOOCID(int moocID) throws SQLException {
		List<Schools> school_list = new ArrayList<Schools>();
		String selectSchool = "SELECT * " +
		"FROM Schools INNER JOIN School_to_MOOC "
		+ "ON Schools.ID = School_to_MOOC.SchoolID "
		+ "WHERE MOOCID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSchool);
			selectStmt.setInt(1, moocID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ID = results.getInt("ID");
				String name = results.getString("Name");
				boolean Participation = results.getBoolean("Participation");
				boolean Contacted = results.getBoolean("Contacted");
				String POC_Name = results.getString("POC_Name");
				String POC_Email = results.getString("POC_Email");
				String City = results.getString("City");
				Schools cc = new Schools(ID,name,Participation,Contacted,POC_Name,POC_Email,City);
				school_list.add(cc);
			}
			return school_list;
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
