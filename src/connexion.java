

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexion {
	private static Connection connection;
	
	private connexion()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/employees", "root", "pass123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getInstance()
	{
		if(connection==null)
			new connexion();
		
		return connection;
		
	}
	

}
