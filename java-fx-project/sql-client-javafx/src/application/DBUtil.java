package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	// Connection to the database
	private Connection connection;

	// Statement to execute SQL commands
	private Statement statement;

	/** Connect to DB */
	public Connection connectToDB(String driver, String url, String username, String password) {
		try {
			Class.forName(driver.trim());
			return connection = DriverManager.getConnection(url.trim(), username.trim(), password.trim());
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/** Execute SQL commands */
	public String executeSQL(String sqlStr) {
		String resultStr = "";
		if (connection == null) {
			resultStr = "Please connect to a database first";
		} else {
			String sqlCommands = sqlStr.trim();
			String[] commands = sqlCommands.replace('\n', ' ').split(";");
			for (String aCommand : commands) {
				if (aCommand.trim().toUpperCase().startsWith("SELECT")) {
					resultStr = processSQLSelect(aCommand);
				} else {
					resultStr = processSQLNonSelect(aCommand);
				}
			}
		}
		return resultStr;
	}

	/** Execute SQL SELECT commands */
	public String processSQLSelect(String sqlCommand) {
		StringBuilder resultStr = new StringBuilder();

		try {
			// Get a new statement for the current connection
			statement = connection.createStatement();
			// Execute a SELECT SQL command
			ResultSet resultSet = statement.executeQuery(sqlCommand);
			// Find the number of columns in the result set
			int columnCount = resultSet.getMetaData().getColumnCount();

			// Display column names
			for (int i = 1; i <= columnCount; i++) {
				resultStr.append(resultSet.getMetaData().getColumnName(i) + "\t\t");
			}
			resultStr.append("\n");
			while (resultSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					// A non-String column is converted to a string
					resultStr.append(resultSet.getString(i) + "\t\t");
				}
				resultStr.append("\n");
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			resultStr.append(ex.getMessage());
		}
		System.out.println(resultStr);
		return resultStr.toString();

	}

	/** Execute SQL DDL, and modification commands */
	public String processSQLNonSelect(String sqlCommand) {
		String resultStr = "";
		try {
			// Get a new statement for the current connection
			statement = connection.createStatement();
			// Execute a non-SELECT SQL command
			statement.executeUpdate(sqlCommand);
			resultStr = "Sql command executed";
		} catch (SQLException ex) {
			resultStr = ex.getMessage();
		}
		return resultStr;
	}

}
