import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.*;

public class FormulaireEmploye extends JFrame {
    // Définition des éléments graphiques
    private JLabel nameLabel = new JLabel("Nom :");
    private JTextField nameField = new JTextField(20);
    private JLabel surnameLabel = new JLabel("Prénom :");
    private JTextField surnameField = new JTextField(20);
    private JLabel dobLabel = new JLabel("Date de naissance :");
    private JTextField dobField = new JTextField(10);
    private JLabel sexLabel = new JLabel("Sexe :");
    private JRadioButton maleRadioButton = new JRadioButton("Masculin");
    private JRadioButton femaleRadioButton = new JRadioButton("Féminin");
    
    
    private JLabel phoneLabel = new JLabel("Numéro de téléphone :");
    private JTextField phoneField = new JTextField(10);
    private JLabel addressLabel = new JLabel("Adresse :");
    private JTextField addressArea = new JTextField();
    
    private JButton submitButton = new JButton("Ajouter");
	private JButton cancelButton = new JButton("Annuler");
    private JButton acceuilButton = new JButton("Acceuil");
    private JButton suppButton = new JButton("Supprimer");
     ButtonGroup b = new ButtonGroup();
    
    

	 table tm=new table();
     JTable table1=new JTable(tm);
     JScrollPane jsp=new JScrollPane(table1);
     
	IgestionEmpl empl=new gestionEmpl();

    public FormulaireEmploye() {
        
        setTitle("Formulaire d'employé");
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        //classe interne anonyme
		cancelButton.addActionListener(e -> cancelForm());
        
        

        
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel pnom = new JPanel();
	    JPanel pprenom = new JPanel();
	    JPanel pdob = new JPanel();
	    JPanel psexe = new JPanel();

        JPanel pnumero = new JPanel();
	    JPanel pad = new JPanel(new GridLayout(1,2));
	
        JPanel p100 = new JPanel(new GridLayout(6, 1));
        JPanel p200 = new JPanel();
        JPanel p300 = new JPanel(new GridLayout(1, 1));
        JButton accButton = new JButton("Acceuil");
       
        pnom.add(nameLabel);
        pnom.add(nameField);
        pprenom.add(surnameLabel);
        pprenom.add(surnameField);
        pdob.add(dobLabel);
        pdob.add(dobField);
        psexe.add(sexLabel);
        psexe.add(femaleRadioButton);
		psexe.add(maleRadioButton);
        pnumero.add(phoneLabel);
        pnumero.add(phoneField);
        pad.add(addressLabel);
        pad.add(addressArea);
        

		
		b.add(femaleRadioButton);
		b.add(maleRadioButton);
        p100.add(pnom);
        p100.add(pprenom);
        p100.add(pdob);
        p100.add(psexe);
        p100.add(pnumero);
        p100.add(pad);

        p200.add(submitButton);
        p200.add(suppButton);
        p200.add(cancelButton);
        p200.add(accButton);
        
        p1.add(p100, BorderLayout.WEST);
		p1.add(p200, BorderLayout.SOUTH);

		this.add(p1, BorderLayout.NORTH);

        p300.add(jsp);
        p2.add(p300, BorderLayout.CENTER);
		this.add(p2, BorderLayout.CENTER);
        
        
        
       
		tm.charger(empl.getAllEmployees());

		submitButton.addActionListener(e->{
			
			String nom=nameField.getText();
			String prenom=surnameField.getText();
			String dob=dobField.getText();
			String sexe="";
			String num= phoneField.getText();
			String add= addressArea.getText();
			if(femaleRadioButton.isSelected())
				sexe="Femele";	
			else
				sexe="Male";
			if(nom.equals("")||prenom.equals("")||dob.equals("")||num.equals("")||add.equals(""))
				JOptionPane.showMessageDialog(this, "erreur de saisie");
			else
			{
				
				employees et=new employees(nom, prenom, dob, sexe,num,add);
				empl.addEmploye(et);
				tm.charger(empl.getAllEmployees());
			}
			
			
			
		});
        accButton.addActionListener(e->{
         
                
            Main main = new Main(); 
            main.setVisible(true); 
            this.dispose();
        
            });
       

        suppButton.addActionListener(e->{
        Scanner id = new Scanner(System.in); 
        int idToDelete=id.nextInt();
        // Connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String password = "pass123";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Récupération de l'ID de la ligne à supprimer
            String sqlGetId = "SELECT id FROM employee WHERE id = ?";
            PreparedStatement stmtGetId = conn.prepareStatement(sqlGetId);
            stmtGetId.setInt(1, idToDelete);
            ResultSet rs = stmtGetId.executeQuery();
            int idDeleted = 0;
            if (rs.next()) {
                idDeleted = rs.getInt("id");
            } else {
                System.out.println("Aucune ligne n'a été supprimée.");
                return;
            }
            
            // Suppression de la ligne
            String sqlDelete = "DELETE FROM employee WHERE id = ?";
            PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete);
            stmtDelete.setInt(1, idToDelete);
            int rowsDeleted = stmtDelete.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La ligne avec l'ID " + idToDelete + " a été supprimée.");
                tm.charger(empl.getAllEmployees());
            } else {
                System.out.println("Aucune ligne n'a été supprimée.");
                return;
            }
            
            // Décrémentation des ID des lignes suivantes
            String sqlDecrement = "UPDATE employee SET id = id - 1 WHERE id > ?";
            PreparedStatement stmtDecrement = conn.prepareStatement(sqlDecrement);
            stmtDecrement.setInt(1, idDeleted);
            stmtDecrement.executeUpdate();
            System.out.println("Les ID des lignes suivantes ont été décrémentés.");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de la ligne : " + ex.getMessage());
        }
    
		});
        


        setLocationRelativeTo(null);
        
        setVisible(true);

    }

    
		


        private void cancelForm() {
			
			nameField.setText("");
			surnameField.setText("");
			dobField.setText("");
			b.clearSelection();
			
            phoneField.setText("");
            addressArea.setText("");
			}
            
            

            

		public static void main(String[] args){
			new FormulaireEmploye();
		}
	}
