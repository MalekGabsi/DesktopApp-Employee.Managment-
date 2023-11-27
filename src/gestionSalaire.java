import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class gestionSalaire implements IgestionSalaire {
    private Connection cx=connexion.getInstance();

    @Override
	public List<employees> getAllsalaire() {
		List<employees>liste=new ArrayList<>();
		try {
			
			PreparedStatement ps=cx.prepareStatement("select * from employees.salaire");
	        System.out.println(cx);
	        ResultSet rs=ps.executeQuery();
			
			
	        while(rs.next())
	        {
	        	employees e = new employees(rs.getInt(1), rs.getString(2), 
				rs.getDouble(3), rs.getString(4));
	            liste.add(e);
	        }
	        
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return liste;
	}

    @Override
	public void addSalaire(employees e) {
		try { System.out.println(cx);
			PreparedStatement ps=cx.prepareStatement("insert into employees.salaire(nom,salaire,date_) values(?,?,?)");
			
	        ps.setString(1, e.getName());
	        
	        ps.setDouble(2, e.getSalaire());
	        ps.setString(3, e.getDob());
            
	        ps.executeUpdate();
	        
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	
}
