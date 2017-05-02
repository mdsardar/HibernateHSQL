package org.javaVillage.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author www.javaworkspace.com
 * 
 */
public class ConnectHSQLDB {
	public static void main(String[] args) {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:file:D:/hsqldb", "ams", "");			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM studentdetails WHERE STUDENTID='10B'");
			while (resultSet.next()) {
				System.out.println("STUDENT NAME:" + resultSet.getString("STUDENTNAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
