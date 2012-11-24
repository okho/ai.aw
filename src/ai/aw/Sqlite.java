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
//////  FIND IP //////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
	public String findIP(String textIP) {
		String res="", ip, descr, mac , timecreate, timelast;
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
		    	res += "IP: " + ip + "  descr: " + descr + " <br>";
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
		    	res += "IP: " + ip + "  MAC: " + mac + "  last: " + timelast + " <br>";
		    	
		    	
		    	
		    	
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
/*	    
	    <!--
///////////////////////////////////////////////////////////////////////////////
/////// IP search /////////////////////////////////////////////////////////////
if ("IP".equals(request.getParameter("CRITERIA"))) {

$query = "SELECT * FROM ipdesc WHERE ip = '{$ipparam}'";
mysql_query($query);
$result = mysql_query($query);
while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
printf("IP: %s  descr: %s <br>", $row[0], $row[2]);
}
mysql_free_result($result);


//$query = "SELECT * FROM arp WHERE ip IN (SELECT ip FROM arp WHERE mac IN (SELECT mac FROM arp WHERE ip='{$ipparam}'))";
$query = "SELECT * FROM arp WHERE ip = '{$ipparam}' ORDER BY timelast DESC";
mysql_query($query);
$result = mysql_query($query);
while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
  printf("IP: %s  MAC: %s  last: %s <br>", $row[1], $row[3], $row[5]); 
  $query2 = "SELECT * FROM ports WHERE mac = '{$row[3]}' ORDER BY timelast DESC";
  $result2 = mysql_query($query2);
  while ($row2 = mysql_fetch_array($result2, MYSQL_NUM)) {
    printf("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ipsw: %s  port: %s  last: %s <br>", $row2[4], $row2[3], $row2[6]);

    $query3 = "SELECT * FROM crossport WHERE ipsw = '{$row2[4]}' AND port = '{$row2[3]}'"; 
    $result3 = mysql_query($query3);
    while ($row3 = mysql_fetch_array($result3, MYSQL_NUM)) {
      printf("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Розетка: %s <br>", $row3[1]);

    }
    mysql_free_result($result3);

    $query3 = "SELECT * FROM ports WHERE ipsw = '{$row2[4]}' AND port = '{$row2[3]}' ORDER BY timelast DESC";
    $result3 = mysql_query($query3);
    if(mysql_num_rows($result3) > 1) {
      while ($row3 = mysql_fetch_array($result3, MYSQL_NUM)) {
        printf("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MAC: %s  last: %s <br>", $row3[2], $row3[6]);

      }
}
mysql_free_result($result3);

} 
mysql_free_result($result2); 
}
mysql_free_result($result);

}
///////////////////////////////////////////////////////////////////////////////

-->
*/
	}
	
}

