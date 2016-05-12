package mooc.dal;

import mooc.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class City_CountyDao {
	protected ConnectionManager connectionManager;

	private static City_CountyDao instance = null;
	protected City_CountyDao() {
		connectionManager = new ConnectionManager();
	}
	public static City_CountyDao getInstance() {
		if(instance == null) {
			instance = new City_CountyDao();
		}
		return instance;
	}
	
	public City_County create(City_County active) throws SQLException {
		String insertCity_County = "INSERT INTO City_County(City,County) " +
				"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCity_County,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, active.getCity());
			insertStmt.setString(2, active.getCounty());
			insertStmt.executeUpdate();
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

	public City_County updateCounties(City_County active, String newCounty) throws SQLException {
		String updateCounties = "UPDATE City_County SET County=? WHERE City=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCounties);
			updateStmt.setString(1, newCounty);
			updateStmt.setString(2, active.getCity());
			updateStmt.executeUpdate();
			active.setCounty(newCounty);
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


	public City_County delete(City_County active) throws SQLException {
		String deleteCity_County = "DELETE FROM City_County WHERE City=? AND " +
				"County=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCity_County);
			deleteStmt.setString(1, active.getCity());
			deleteStmt.setString(2, active.getCounty());
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

	public City_County getCityByCounty(String County) throws SQLException {
		String selectCity_County = "SELECT City" +
				" FROM City_County WHERE County=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCity_County);
			selectStmt.setString(1, County);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String City = results.getString("City");
				City_County cc = new City_County(City, County);
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
}
