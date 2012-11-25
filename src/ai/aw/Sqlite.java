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
		
		//ResultSet resultSet = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			
			con = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
			
			statement = con.createStatement();
						
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
//////  FIND IP //////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
	public String findIP(String textIP) {
		String res="", ip, ipsw, port, descr, mac , timelast;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		SqliteInit();
		
	    try {
	    		    	
	    	st = con.prepareStatement ("select * from ipdesc where ip = ?"); 
	    	st.setString (1, textIP); 
	    	rs = st.executeQuery();
		    while (rs.next()) {
		    	ip = rs.getString("ip");
		    	descr = rs.getString("descr");
		    	res += "IP: " + ip + "  descr: " + descr + " \n";
		    }
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	    finally {
			try {
				rs.close();
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
	    try {
	    	st = con.prepareStatement ("select * from arp where ip = ? ORDER BY timelast DESC"); 
	    	st.setString (1, textIP); 
	    	rs = st.executeQuery();
		    while (rs.next()) {
		    	ip = rs.getString("ip");
		    	mac = rs.getString("mac");
		    	timelast = rs.getString("timelast");
		    	res += "IP: " + ip + "  MAC: " + mac + "  last: " + timelast + " \n";
		    	
		    	PreparedStatement st2 = con.prepareStatement("select * from ports where mac = ? ORDER BY timelast DESC");
		    	st2.setString(1, mac);
		    	ResultSet rs2 = st2.executeQuery();
			    while (rs2.next()) {
			    	ipsw = rs2.getString("ipsw");
			    	port = rs2.getString("port");
			    	res += "      ipsw: " + ipsw + "  port: " + port + "  last: " + rs2.getString("timelast") + " \n";

			    	PreparedStatement st3 = con.prepareStatement("SELECT * FROM crossport WHERE ipsw = ? AND port = ?");
			    	st3.setString(1, ipsw);
			    	st3.setString(2, port);
			    	ResultSet rs3 = st3.executeQuery();
				    while (rs3.next()) {
				    	res += "         Розетка: " + rs3.getString("socket") + " \n";
				    }

			    	PreparedStatement st4 = con.prepareStatement("SELECT * FROM ports WHERE ipsw = ? AND port = ? ORDER BY timelast DESC");
			    	st4.setString(1, ipsw);
			    	st4.setString(2, port);
			    	ResultSet rs4 = st4.executeQuery();
				    while (rs4.next()) {
				    	res += "           MAC: " + rs4.getString("mac") + "  last: " + rs4.getString("timelast") + " \n";
				    }
			    }
		    	
		    }
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	    finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	    return res;
	}
	
//////FIND MAC //////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
	public String findMAC(String textIP) {
			String res="", ip, ipsw, port, descr, mac , timelast;
			PreparedStatement st=null;
			ResultSet rs=null;

			SqliteInit();

			
			
			
			
			
			
		return res;
	}
//////FIND DESCR //////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
	public String findDESCR(String textDESCR) {
		String res="", ip, ipsw, port, descr, mac , timelast;
		PreparedStatement st=null;
		ResultSet rs=null;

		SqliteInit();







		return res;
	}


}

