package ai.aw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
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
	
	public String LoadDB() {
		
       // wget sqlite.db
        try {
            String urlString = "http://ubokho1.ibrae/sqlite.db";
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream(); 

        	File f=new File("sqlite.db.new");
        	OutputStream out=new FileOutputStream(f);
        	byte buf[]=new byte[1024];
        	int len;
        	while((len=inputStream.read(buf))>0)   out.write(buf,0,len);
        	out.flush();
        	out.close();
        	inputStream.close();
        	File fdb=new File("sqlite.db");
        	fdb.delete();
        	f.renameTo(fdb);
        	//sqlite = new Sqlite();
        	return "База данных успешно обновлена с http://ubokho1.ibrae/sqlite.db ";
        }
        catch (IOException e) {
        	//javax.swing.JOptionPane.showMessageDialog(null, "Не удалось прочитать  http://ubokho1.ibrae/sqlite.db ");
        	return "Error. Не удалось прочитать  http://ubokho1.ibrae/sqlite.db ";
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
	public String findMAC(String textMAC) {
			String res="", ip, ipsw, port, mac , socket, timecreate, timelast;
			PreparedStatement st=null;
			ResultSet rs=null;

			SqliteInit();

			String[] temp;
			temp = textMAC.split(":",6);
			if(temp.length == 6) textMAC = temp[0] + temp[1] + "."+ temp[2] + temp[3] + "."+ temp[4] + temp[5];
		    try {
		    	st = con.prepareStatement ("SELECT * FROM arp WHERE mac = ? ORDER BY timelast DESC"); 
		    	st.setString (1, textMAC); 
		    	rs = st.executeQuery();
			    while (rs.next()) {
			    	ip = rs.getString("ip");
			    	mac = rs.getString("mac");
			    	timecreate = rs.getString("timecreate");
			    	timelast = rs.getString("timelast");
			    	res += "IP: " + ip + "  MAC: " + mac + " create: " + timecreate + " last: " + timelast + " \n";
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
		    	st = con.prepareStatement ("SELECT * FROM ports WHERE mac = ? ORDER BY timelast DESC"); 
		    	st.setString (1, textMAC); 
		    	rs = st.executeQuery();
			    while (rs.next()) {
			    	ipsw = rs.getString("ipsw");
			    	port = rs.getString("port");
			    	timecreate = rs.getString("timecreate");
			    	timelast = rs.getString("timelast");


			    	PreparedStatement st2 = con.prepareStatement ("SELECT * FROM crossport WHERE ipsw = ? AND port = ?"); 
			    	st2.setString (1, ipsw); 
			    	st2.setString (2, port); 
			    	ResultSet rs2 = st2.executeQuery();
			    	socket = "";
			    	while (rs2.next()) 	socket = rs2.getString("socket");
			    	res += "Розетка: " + socket + " ipsw: " + ipsw + " port: " + port + " create: " + timecreate + " last: " + timelast + " \n";
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
//////FIND DESCR //////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
	public String findDESCR(String textDESCR) {
		String res="", ip, ipsw, port, socket, descr, mac , timelast;
		PreparedStatement st=null;
		ResultSet rs=null;

		SqliteInit();

	    try {
	    	
//	    	st = con.prepareStatement ("SELECT * FROM crossport WHERE socket LIKE '%?%' ORDER BY ipsw, ABS(SUBSTRING(port,5))");
// SUBSTRING()  sqlite unsupported	    	
	    	st = con.prepareStatement ("SELECT * FROM crossport WHERE socket LIKE ? ORDER BY ipsw, ABS(port)"); 
	    	st.setString (1, "%" + textDESCR + "%"); 
	    	rs = st.executeQuery();
		    while (rs.next()) {
		    	socket = rs.getString("socket");
		    	ipsw = rs.getString("ipsw");
		    	port = rs.getString("port");
		    	res += " Розетка: " + socket + "  ipsw: " + ipsw + "  port: " + port + " \n";
		    	
		    	PreparedStatement st2 = con.prepareStatement ("SELECT * FROM ports WHERE ipsw = ? AND port = ? ORDER BY timelast DESC"); 
		    	st2.setString (1, ipsw); 
		    	st2.setString (2, port); 
		    	ResultSet rs2 = st2.executeQuery();
			    while (rs2.next()) {
			    	mac = rs2.getString("mac");
			    	timelast = rs2.getString("timelast");
			    	res += "      MAC:" + mac + "  last: " + timelast + " \n";
			    	
			    	PreparedStatement st3 = con.prepareStatement ("SELECT arp.ip,arp.timelast,ipdesc.descr FROM arp LEFT JOIN ipdesc ON arp.ip=ipdesc.ip WHERE arp.mac = ? ORDER BY arp.timelast DESC"); 
			    	st3.setString (1, mac);  
			    	ResultSet rs3 = st3.executeQuery();
				    while (rs3.next()) {
				    	ip = rs3.getString("ip");
				    	timelast = rs3.getString("timelast");
				    	descr = rs3.getString("descr");
				    	res += "              IP: " + ip + "  last: " + timelast + "  descr: " + descr + " \n";
				    }
			    	
			    	
			    }
		    }
		    
	    	st = con.prepareStatement ("SELECT * FROM ipdesc WHERE descr LIKE ? ORDER BY ipbin"); 
	    	st.setString (1, "%" + textDESCR + "%"); 
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
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return res;
	}


}

