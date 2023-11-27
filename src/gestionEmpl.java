
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class gestionEmpl implements IgestionEmpl{
	
	private Connection cx=connexion.getInstance();
	
	@Override
	public List<employees> getAllEmployees() {
		List<employees>liste=new ArrayList<>();
		try {
			
			PreparedStatement ps=cx.prepareStatement("select * from employees.employee");
	        System.out.println(cx);
	        ResultSet rs=ps.executeQuery();
			
			
	        while(rs.next())
	        {
	        	employees e = new employees(rs.getInt(1), rs.getString(2), 
				rs.getString(3), rs.getString(4), 
				rs.getString(5), rs.getString(6), rs.getString(7));
	            liste.add(e);
	        }
	        
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return liste;
	}

	@Override
	public void addEmploye(employees e) {
		try { System.out.println(cx);
			PreparedStatement ps=cx.prepareStatement("insert into employees.employee(name,surname,dob,sexe,phone,address) values(?,?,?,?,?,?)");
			
	        ps.setString(1, e.getName());
	        ps.setString(2, e.getSurname());
	        ps.setString(3, e.getDob());
	        ps.setString(4, e.getSex());
            ps.setString(5, e.getPhone());
            ps.setString(6, e.getAddress());
	        ps.executeUpdate();
	        
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void suppEmploye(employees e) {
		try {
			PreparedStatement ps=cx.prepareStatement("delete from employees.employee(name,surname,dob,sexe,phone,address) values(?,?,?,?,?,?)");
	        ps.setString(1, e.getName());
	        ps.setString(2, e.getSurname());
	        ps.setString(3, e.getDob());
	        ps.setString(4, e.getSex());
            ps.setString(5, e.getPhone());
            ps.setString(6, e.getAddress());
	        ps.executeUpdate();
	        
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	};
	}
	

	

	
	


