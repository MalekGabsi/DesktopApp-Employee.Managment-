
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class salaireInerface extends JFrame {
private JLabel nomLabel, salaireLabel, dateLabel, messageLabel;
private JPanel panel;
private JComboBox<String> tf1; 

private ArrayList<String> noms;

salaireModele tm=new salaireModele();
JTable table1=new JTable(tm);
JScrollPane jsp=new JScrollPane(table1);
IgestionSalaire empl=new gestionSalaire();


public salaireInerface() {
    super("Gestion des salaires");

    // Création des labels pour chaque champ
    nomLabel = new JLabel("Employé :");
    salaireLabel = new JLabel("Salaire :");
    dateLabel = new JLabel("Date :");

    // Création du panel pour la partie gauche de la fenêtre
    panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(247, 249, 250));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;
    panel.add(nomLabel, gbc);
    gbc.gridy++;
    panel.add(salaireLabel, gbc);
    gbc.gridy++;
    panel.add(dateLabel, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 1;
    JTextField tf2= new JTextField(10);
    tf1= new JComboBox();
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "pass123");

        // Création de la requête SELECT pour extraire tous les noms de la colonne "nom" de la table "employes"
        String query = "SELECT name,surname FROM employee";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        // Ajout des noms à la JComboBox
        noms = new ArrayList<String>();
        while (rs.next()) {
            String nom = rs.getString("name") + " " +  rs.getString("surname");
            tf1.addItem(nom);
        }
        
        // Fermeture de la connexion
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    JTextField tf3= new JTextField(10);
    panel.add(tf1, gbc);
    gbc.gridy++;
    panel.add(tf2, gbc);
    gbc.gridy++;
    panel.add(tf3, gbc);

    // Création du panel pour la partie droite de la fenêtre
    JPanel messagePanel = new JPanel(new BorderLayout());
    messagePanel.setBackground(new Color(67, 67, 54));
    messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    messageLabel = new JLabel("Liste complète des salaires :");
    messageLabel.setFont(new Font("Roboto", Font.BOLD, 16));
    messageLabel.setForeground(new Color(255, 255, 255));
    
    messagePanel.add(messageLabel, BorderLayout.NORTH);
    messagePanel.add(new JTextArea(), BorderLayout.CENTER);
    messagePanel.add(jsp);

    // Création du panel pour les boutons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonPanel.setBackground(new Color(247, 249, 250));
    JButton payerButton = new JButton("Payer");
    JButton annulerButton = new JButton("Annuler");
    JButton acceuilButton = new JButton("Acceuil");
    buttonPanel.add(payerButton);
    buttonPanel.add(annulerButton);
    buttonPanel.add(acceuilButton);
    

    // Ajout des panels à la fenêtre principale
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panel, BorderLayout.WEST);
    getContentPane().add(messagePanel, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    // Configuration de la fenêtre principale
    setSize(650, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(true);
    setVisible(true);


    tm.charger(empl.getAllsalaire());

    payerButton.addActionListener(e->{
        
        String nom=(String)tf1.getSelectedItem();
        int salaire = Integer.parseInt(tf2.getText());
        String dob=tf3.getText();
        
            
            employees s=new employees(nom, salaire, dob);
            empl.addSalaire(s);
            tm.charger(empl.getAllsalaire());
        });
        annulerButton.addActionListener(e ->
        {
            
			tf2.setText("");
			tf3.setText("");
        } );
        acceuilButton.addActionListener(e->{
         
                
            Main main = new Main(); 
            this.dispose();
        main.setVisible(true); 
            });
    }
    
    public static void main(String[] args) {
        new salaireInerface();
        }
    
    }