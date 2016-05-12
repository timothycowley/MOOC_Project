package mooc.dal;

import mooc.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class School_to_MOOCDao {
	protected ConnectionManager connectionManager;

	private static School_to_MOOCDao instance = null;
	protected School_to_MOOCDao() {
		connectionManager = new ConnectionManager();
	}
	public static School_to_MOOCDao getInstance() {
		if(instance == null) {
			instance = new School_to_MOOCDao();
		}
		return instance;
	}
	public School_to_MOOC create(School_to_MOOC school_to_mooc) throws SQLException {
		String insertSchool_to_MOOC = "INSERT INTO School_to_MOOC(MOOCID,SchoolID)" + 
				" VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSchool_to_MOOC);
			insertStmt.setInt(1, school_to_mooc.getMOOCID());
			insertStmt.setInt(2, school_to_mooc.getSchoolID());
			insertStmt.executeUpdate();	
		
			return school_to_mooc;
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
		}
	}

	public School_to_MOOC delete(School_to_MOOC school_to_mooc) throws SQLException {
		String deleteSchool_to_MOOC = "DELETE FROM School_to_MOOC WHERE SchoolID=? AND MOOCID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSchool_to_MOOC);
			deleteStmt.setInt(1, school_to_mooc.getSchoolID());
			deleteStmt.setInt(2, school_to_mooc.getMOOCID());
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

	public List<School_to_MOOC> getMOOCsofSchool(int SchoolID) throws SQLException {
		List<School_to_MOOC> mooc_list = new ArrayList<School_to_MOOC>();
		String selectUsers = "SELECT * " +
		"FROM School_to_MOOC WHERE SchoolID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setInt(1, SchoolID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int MOOCID = results.getInt("MOOCID");
				School_to_MOOC cc = new School_to_MOOC(SchoolID,MOOCID);
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
	
	public List<Main_MOOC> getMOOCsBySchool(int SchoolID) throws SQLException {
		List<Main_MOOC> mooc_list = new ArrayList<Main_MOOC>();
		String selectMOOC = "SELECT * " +
		"FROM Main_MOOC INNER JOIN School_to_MOOC "
		+ "ON Main_MOOC.ID = School_to_MOOC.MOOCID "
		+ "WHERE SchoolID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMOOC);
			selectStmt.setInt(1, SchoolID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int id = results.getInt("ID");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String source = results.getString("Source");
				String directLink = results.getString("Direct_Link");
				Boolean offered = results.getBoolean("Offered");
				int difficulty = results.getInt("Difficulty");
				String type = results.getString("Type");
				int length = results.getInt("Length");
				Main_MOOC cc = new Main_MOOC(id, name, description, source, directLink, offered, difficulty, type, length);
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
	
}


	
