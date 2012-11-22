package ai.aw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlite {
	private static Connection con;
  
	void SqliteInit() {
		
		//Connection con = null;
		//ResultSet resultSet = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			
			con = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
			
			statement = con.createStatement();
			
/*			
            statement.executeUpdate("drop table if exists arp");      
            statement.executeUpdate("create table arp (id integer, ip string, mac string)");      
            statement.executeUpdate("insert into arp values(1, '10.254.53.23', '9966996699')");      
            statement.executeUpdate("insert into arp values(2, '10.254.50.72', '3454566433')");      
		    			
            ResultSet resultSet = statement.executeQuery("SELECT ip FROM arp");
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
				//con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public String showIP(String textIP) {
		String res="", ip, mac , timecreate, timelast;
	
		SqliteInit();
		
	    try {
	    		    	
	    	PreparedStatement st = con.prepareStatement ("select * from arp where ip = ?"); 
	    	st.setString (1, textIP); 
	    	
		    ResultSet rs = st.executeQuery();

		    while (rs.next()) {
		    	ip = rs.getString("ip");
		    	mac = rs.getString("mac");
		    	//timecreate = rs.getString("timecreate");
		    	//timelast = rs.getDouble("timelast");
		    	//timelast = rs.getString("timelast");
		    	res += "\n******************************";
		    	res += "\nIP: " + ip;
		    	res += "\nMAC: " + mac;
		    	//res += "\nTimeCreate: " + timecreate;
		    	//res += "\nTimeLast: " + timelast;
		    	res += "\n******************************";
		    }
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	    return res;
	}
	
}

