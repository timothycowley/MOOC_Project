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


public class HistoryDao {
	protected ConnectionManager connectionManager;

	private static HistoryDao instance = null;
	protected HistoryDao() {
		connectionManager = new ConnectionManager();
	}
	public static HistoryDao getInstance() {
		if(instance == null) {
			instance = new HistoryDao();
		}
		return instance;
	}
	
	public History create(History active) throws SQLException {
		String insertHistory = "INSERT INTO History(StudentID,MOOCID,VolunteerID,StartDate,EndDate) " +
				"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHistory,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, active.getStudentID());
			insertStmt.setInt(2, active.getMOOCID());
			insertStmt.setInt(3, active.getVolunteerID());
			insertStmt.setDate(4, active.getStartDate());
			insertStmt.setDate(5, active.getEndDate());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int commentId = -1;
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			active.setHistoryID(commentId); // sets the auto-generated CreditCardId key
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

	public History updateEndDate(History active, Date EndDate) throws SQLException {
		String updateHistory = "UPDATE History SET EndDate=? WHERE HistoryID=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHistory);
			updateStmt.setDate(1, EndDate);
			updateStmt.setInt(2, active.getHistoryID());
			updateStmt.executeUpdate();
			active.setEndDate(EndDate);
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


	public History delete(History active) throws SQLException {
		String deleteHistory = "DELETE FROM History WHERE HistoryID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHistory);
			deleteStmt.setInt(1, active.getHistoryID());
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

	public List<History> getStudentHistory(int studentID) throws SQLException {
		List<History> hist_list = new ArrayList<History>();
		String selectHistory = "SELECT HistoryID,MOOCID,VolunteerID,StartDate,EndDate FROM " +
				"History WHERE StudentID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHistory);
			selectStmt.setInt(1, studentID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int HistoryID = results.getInt("HistoryID");
				int MOOCID = results.getInt("MOOCID");
				int VolunteerID = results.getInt("VolunteerID");
				Date StartDate = results.getDate("StartDate");
				Date EndDate = results.getDate("EndDate");
				History cc = new History(HistoryID,studentID,MOOCID,VolunteerID,StartDate,EndDate);
				hist_list.add(cc);
			}
			return hist_list;
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
	
	public List<History> getMOOCHistory(int moocID) throws SQLException {
		List<History> hist_list = new ArrayList<History>();
		String selectHistory = "SELECT * FROM " +
				"History WHERE MOOCID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHistory);
			selectStmt.setInt(1, moocID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int HistoryID = results.getInt("HistoryID");
				int studentID = results.getInt("StudentID");
				int MOOCID = results.getInt("MOOCID");
				int VolunteerID = results.getInt("VolunteerID");
				Date StartDate = results.getDate("StartDate");
				Date EndDate = results.getDate("EndDate");
				History cc = new History(HistoryID,studentID,MOOCID,VolunteerID,StartDate,EndDate);
				hist_list.add(cc);
			}
			return hist_list;
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
	
	public History getHistoryByID(int id) throws SQLException {
		String selectHistory = "SELECT * FROM History WHERE ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHistory);
			selectStmt.setInt(1,  id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int HistoryID = results.getInt("HistoryID");
				int StudentID = results.getInt("StudentID");
				int MOOCID = results.getInt("MOOCID");
				int VolunteerID = results.getInt("VolunteerID");
				Date StartDate = results.getDate("StartDate");
				Date EndDate = results.getDate("EndDate");
				History history = new History(HistoryID, StudentID, MOOCID, VolunteerID, StartDate, EndDate);
				return history;
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
	
