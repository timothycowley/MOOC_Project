package mooc.dal;

import mooc.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsDao {
	protected ConnectionManager connectionManager;

	private static StudentsDao instance = null;
	protected StudentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static StudentsDao getInstance() {
		if(instance == null) {
			instance = new StudentsDao();
		}
		return instance;
	}
	
	public Students create(Students student) throws SQLException {
		String insertStudents = "INSERT INTO Students(First_Name,Last_Name,Email,SchoolID,Class) " +
				"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertStudents,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, student.getFirst_Name());
			insertStmt.setString(2, student.getLast_Name());
			insertStmt.setString(3, student.getEmail());
			insertStmt.setInt(4, student.getSchoolID());
			insertStmt.setString(5, student.getYear());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int id = -1;
			if(resultKey.next()) {
				id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			student.setID(id); 
			return student;
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

	public Students updateSchoolID(Students active, int SchoolID) throws SQLException {
		String updateStudents = "UPDATE Students SET SchoolID=? WHERE ID=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateStudents);
			updateStmt.setInt(1, SchoolID);
			updateStmt.setInt(2, active.getID());
			updateStmt.executeUpdate();
			active.setSchoolID(SchoolID);
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


	public Students delete(Students active) throws SQLException {
		String deleteStudents = "DELETE FROM Students WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteStudents);
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
	
	public Students getStudentByID(int id) throws SQLException {
		String selectStudent = "SELECT * FROM Students WHERE ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStudent);
			selectStmt.setInt(1,  id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int ID = results.getInt("ID");
				String First_Name = results.getString("First_Name");
				String Last_Name = results.getString("Last_Name");
				String Email = results.getString("Email");
				int schoolID = results.getInt("SchoolID");
				String Year = results.getString("Class");
				Students student = new Students(ID, First_Name, Last_Name, Email, schoolID, Year);
				return student;
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

	public List<Students> getStudentbySchool(int schoolID) throws SQLException {
		List<Students> stud_list = new ArrayList<Students>();
		String selectUsers = "SELECT ID,First_Name,Last_Name,Email,Class "  +
		"FROM Students WHERE SchoolID=?;";
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
				String Year = results.getString("Class");
				Students cc = new Students(ID,First_Name,Last_Name,Email,schoolID,Year);
				stud_list.add(cc);
			}
			return stud_list;
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
