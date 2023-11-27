
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class gestionCong implements IgestionCong{
	
	private Connection cx=connexion.getInstance();
	
	@Override
	public List<employees> getAllcong() {
		List<employees>liste=new ArrayList<>();
		try {
			
			PreparedStatement ps=cx.prepareStatement("select * from employees.conge");
	        System.out.println(cx);
	        ResultSet rs=ps.executeQuery();
			
			
	        while(rs.next())
	        {
	        	employees e = new employees(rs.getInt(1), rs.getString(2), 
				rs.getString(3), 
				rs.getInt(4), rs.getString(5), rs.getString(6));
	            liste.add(e);
	        }
	        
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return liste;
	}

	@Override
	public void addCong(employees e) {
		try { System.out.println(cx);
			PreparedStatement ps=cx.prepareStatement("insert into employees.conge(nom,prenom,nbconge,dob,etat) values(?,?,?,?,?)");
			
	        ps.setString(1, e.getName());
	        ps.setString(2, e.getSurname());
	        ps.setInt(3, e.getNbconges());
	        ps.setString(4, e.getDob());
            ps.setString(5, e.getEtat());
	        ps.executeUpdate();
	        
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}


    
	}
	

	

	
	


