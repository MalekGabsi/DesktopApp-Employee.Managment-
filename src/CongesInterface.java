import javax.swing.*;
import java.awt.*;

public class CongesInterface extends JFrame {
    private JLabel nomLabel, prenomLabel, nbCongesLabel, dateLabel, etatLabel, messageLabel;
    private JComboBox<String> etatComboBox;
    private JPanel panel;
    CongeModele tm=new CongeModele();
     JTable table1=new JTable(tm);
     JScrollPane jsp=new JScrollPane(table1);


     IgestionCong empl=new gestionCong();


    public CongesInterface() {
        super("Gestion des congés");

        // Création des labels pour chaque champ
        nomLabel = new JLabel("Nom :");
        prenomLabel = new JLabel("Prénom :");
        nbCongesLabel = new JLabel("Nombre de congés :");
        dateLabel = new JLabel("Date :");
        etatLabel = new JLabel("Etat :");

        // Création d'une combobox pour l'état
        etatComboBox = new JComboBox<String>(new String[] {"En attente", "Accepté", "Refusé"});
        etatComboBox.setBackground(new Color(231, 234, 236));
        etatComboBox.setForeground(new Color(51, 51, 51));
        etatComboBox.setPreferredSize(new Dimension(120, 30));
        etatComboBox.setBorder(BorderFactory.createLineBorder(new Color(198, 203, 206)));

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
        panel.add(prenomLabel, gbc);
        gbc.gridy++;
        panel.add(nbCongesLabel, gbc);
        gbc.gridy++;
        panel.add(dateLabel, gbc);
        gbc.gridy++;
        panel.add(etatLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        JTextField tf1= new JTextField(10);
        JTextField tf2= new JTextField(10);
        JTextField tf3= new JTextField(10);
        JTextField tf4= new JTextField(10);
        panel.add(tf1, gbc);
        gbc.gridy++;
        panel.add(tf2, gbc);
        gbc.gridy++;
        panel.add(tf3, gbc);
        gbc.gridy++;
        panel.add(tf4, gbc);
        gbc.gridy++;
        panel.add(etatComboBox, gbc);

        // Création du panel pour la partie droite de la fenêtre
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBackground(new Color(67, 67, 54));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageLabel = new JLabel("Liste complète des congés :");
        messageLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        messageLabel.setForeground(new Color(255, 255, 255));
        
        messagePanel.add(messageLabel, BorderLayout.NORTH);
        messagePanel.add(new JTextArea(), BorderLayout.CENTER);
        messagePanel.add(jsp);

        // Création du panel pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(247, 249, 250));
        JButton validerButton = new JButton("Valider");
        JButton annulerButton = new JButton("Annuler");
        JButton acceuilButton = new JButton("Acceuil");
        buttonPanel.add(validerButton);
        buttonPanel.add(annulerButton);
        buttonPanel.add(acceuilButton);
        

        // Ajout des panels à la fenêtre principale
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().add(messagePanel, BorderLayout
        .CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    
        // Configuration de la fenêtre principale
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);


        tm.charger(empl.getAllcong());

		validerButton.addActionListener(e->{
			
			String nom=tf1.getText();
			String prenom=tf2.getText();
            int nbconges = Integer.parseInt(tf3.getText());
			String dob=tf4.getText();
			String etat=(String)etatComboBox.getSelectedItem();
			
				
				employees et=new employees(nom, prenom, nbconges, dob,etat);
				empl.addCong(et);
				tm.charger(empl.getAllcong());
            
			
			
			
		});
        annulerButton.addActionListener(e->{
            tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			
			
            tf4.setText("");
            etatComboBox.setSelectedIndex(0);
            });
            acceuilButton.addActionListener(e->{
         
                
                Main main = new Main(); 
                this.dispose();
            main.setVisible(true); 
                });

    }
    
    public static void main(String[] args) {
        new CongesInterface();
    }
}    