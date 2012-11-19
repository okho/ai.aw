package ai.aw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sqlite {
  
	Sqlite() {
		
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
			
			statement = connection.createStatement();
			
			/*
            statement.executeUpdate("drop table if exists arp");      
            statement.executeUpdate("create table arp (id integer, ip string)");      
            statement.executeUpdate("insert into arp values(1, '10.254.53.23')");      
            statement.executeUpdate("insert into arp values(2, '10.254.50.72')");      
		    			
			resultSet = statement.executeQuery("SELECT ip FROM arp");
			while (resultSet.next()) {
				System.out.println("IP:" + resultSet.getString("ip"));
			}
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			/*	resultSet.close();*/
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		
	}
	
	
}

