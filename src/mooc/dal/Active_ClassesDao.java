package mooc.dal;

import mooc.model.*;

import java.sql.Connection;
//import java.util.Date;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Active_ClassesDao {
	protected ConnectionManager connectionManager;

	private static Active_ClassesDao instance = null;
	protected Active_ClassesDao() {
		connectionManager = new ConnectionManager();
	}
	public static Active_ClassesDao getInstance() {
		if(instance == null) {
			instance = new Active_ClassesDao();
		}
		return instance;
	}

	public Active_Classes create(Active_Classes active) throws SQLException {
		String insertActive_Classes = "INSERT INTO Active_Classes(StudentID, MOOCID, StartDate, EndDate) " +
				"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			
			insertStmt = connection.prepareStatement(insertActive_Classes,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, active.getStudentID());
			insertStmt.setInt(2, active.getMOOCID());
			insertStmt.setDate(3, active.getStartDate());
			insertStmt.setDate(4, active.getEndDate());
			
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int commentId = -1;
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			active.setActiveID(commentId); // sets the auto-generated CreditCardId key
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

	public Active_Classes updateEndDate(Active_Classes active, Date newEndDate) throws SQLException {
		String updateEndDate = "UPDATE Active_Classes SET EndDate=? WHERE ActiveID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateEndDate);
			updateStmt.setDate(1, newEndDate);
			updateStmt.setInt(2, active.getActiveID());
			updateStmt.executeUpdate();
			active.setEndDate(newEndDate);
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


	public Active_Classes delete(Active_Classes active) throws SQLException {
		String deleteActive_Classes = "DELETE FROM Active_Classes WHERE ActiveID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteActive_Classes);
			deleteStmt.setInt(1, active.getActiveID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the CreditCards instance.
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

	public List<Active_Classes> getClassListbyMOOCID(int MOOCID) throws SQLException {
		List<Active_Classes> act_list = new ArrayList<Active_Classes>();
		String selectActive_Classes = "SELECT ActiveID,StudentID,StartDate,EndDate" +
				" FROM Active_Classes WHERE MOOCID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectActive_Classes);
			selectStmt.setInt(1, MOOCID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ActiveID = results.getInt("ActiveID");
				int StudentID = results.getInt("StudentID");
				Date StartDate = results.getDate("StartDate");
				Date EndDate = results.getDate("EndDate");
				Active_Classes person = new Active_Classes(ActiveID, StudentID, MOOCID, StartDate, EndDate);
				act_list.add(person);
			}
			return act_list;
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
	public List<Active_Classes> getActiveClassesForStudent(int studentID) throws SQLException {
		List<Active_Classes> act_list = new ArrayList<Active_Classes>();
		String selectActive_Classes = "SELECT *" +
				" FROM Active_Classes "
				+ "WHERE StudentID=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectActive_Classes);
			selectStmt.setInt(1, studentID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ActiveID = results.getInt("ActiveID");
				int StudentID = results.getInt("StudentID");
				int MOOCID = results.getInt("MOOCID");
				Date StartDate = results.getDate("StartDate");
				Date EndDate = results.getDate("EndDate");
				Active_Classes person = new Active_Classes(ActiveID, StudentID, MOOCID, StartDate, EndDate);
				act_list.add(person);
			}
			return act_list;
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
	
	public List<Active_Classes> getActiveClassesForSchool(int schoolID) throws SQLException {
		List<Active_Classes> act_list = new ArrayList<Active_Classes>();
		String selectActive_Classes = "SELECT *" +
				" FROM Students INNER JOIN Active_Classes "
				+ "ON Students.ID = Active_Classes.StudentID "
				+ "WHERE Students.SchoolID=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectActive_Classes);
			selectStmt.setInt(1, schoolID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ActiveID = results.getInt("ActiveID");
				int StudentID = results.getInt("StudentID");
				int MOOCID = results.getInt("MOOCID");
				Date StartDate = results.getDate("StartDate");
				Date EndDate = results.getDate("EndDate");
				Active_Classes person = new Active_Classes(ActiveID, StudentID, MOOCID, StartDate, EndDate);
				act_list.add(person);
			}
			return act_list;
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
	
	
	public Active_Classes getActiveClassByID(int id) throws SQLException {
		String selectStudent = "SELECT * FROM Active_Classes WHERE ActiveID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStudent);
			selectStmt.setInt(1,  id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int ActiveID = results.getInt("ActiveID");
				int StudentID = results.getInt("StudentID");
				int MOOCID = results.getInt("MOOCID");
				Date StartDate = results.getDate("StartDate");
				Date EndDate = results.getDate("EndDate");
				Active_Classes active = new Active_Classes(ActiveID, StudentID, MOOCID, StartDate, EndDate);
				return active;
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
	
}

