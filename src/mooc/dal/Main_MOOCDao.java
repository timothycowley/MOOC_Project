package mooc.dal;

import mooc.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Main_MOOCDao {
	protected ConnectionManager connectionManager;

	private static Main_MOOCDao instance = null;
	protected Main_MOOCDao() {
		connectionManager = new ConnectionManager();
	}
	public static Main_MOOCDao getInstance() {
		if(instance == null) {
			instance = new Main_MOOCDao();
		}
		return instance;
	}
	
	public Main_MOOC create(Main_MOOC active) throws SQLException {
		String insertMain_MOOC = "INSERT INTO Main_MOOC(Name,Description,Source,Direct_Link," +
				"Offered,Difficulty,Type,Length) VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMain_MOOC,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, active.getName());
			insertStmt.setString(2, active.getDescription());
			insertStmt.setString(3, active.getSource());
			insertStmt.setString(4, active.getDirect_Link());
			insertStmt.setBoolean(5, active.isOffered());
			insertStmt.setInt(6, active.getDifficulty());
			insertStmt.setString(7, active.getType());
			insertStmt.setInt(8, active.getLength());
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
	
	public Main_MOOC getMainMOOCByID(int id) throws SQLException {
		String selectMOOC = "SELECT * FROM Main_MOOC WHERE ID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMOOC);
			selectStmt.setInt(1,  id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int ID = results.getInt("ID");
				String name = results.getString("Name");
				String Description = results.getString("Description");
				String Source = results.getString("Source");
				String Direct_Link = results.getString("Direct_Link");
				boolean Offered = results.getBoolean("Offered");
				int Difficulty = results.getInt("Difficulty");
				String type = results.getString("Type");
				int Length = results.getInt("Length");
				Main_MOOC mooc = new Main_MOOC(ID,name,Description,Source,Direct_Link,Offered,Difficulty,type,Length);
				return mooc;
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


	public Main_MOOC updateMain_MOOC(Main_MOOC active, String Description) throws SQLException {
		String updateMain_MOOC = "UPDATE Main_MOOC SET Description=? WHERE ID=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMain_MOOC);
			updateStmt.setString(1, Description);
			updateStmt.setInt(2, active.getID());
			updateStmt.executeUpdate();
			active.setDescription(Description);
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


	public Main_MOOC delete(Main_MOOC active) throws SQLException {
		String deleteMain_MOOC = "DELETE FROM Main_MOOC WHERE ID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteMain_MOOC);
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

	public List<Main_MOOC> getMOOCByType(String type) throws SQLException {
		List<Main_MOOC> mooc_list = new ArrayList<Main_MOOC>();
		String selectUsers = "SELECT ID,Name,Description,Source,Direct_Link," +
				"Offered,Difficulty,Length " +	"FROM Main_MOOC WHERE Type=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, type);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ID = results.getInt("ID");
				String name = results.getString("Name");
				String Description = results.getString("Description");
				String Source = results.getString("Source");
				String Direct_Link = results.getString("Direct_Link");
				boolean Offered = results.getBoolean("Offered");
				int Difficulty = results.getInt("Difficulty");
				int Length = results.getInt("Length");
				Main_MOOC cc = new Main_MOOC(ID,name,Description,Source,Direct_Link,Offered,Difficulty,type,Length);
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
