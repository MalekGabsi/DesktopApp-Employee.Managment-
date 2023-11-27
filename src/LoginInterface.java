import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginInterface extends JFrame implements ActionListener {
    
    // Composants graphiques
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel errorLabel;
    
    public LoginInterface() {
        try {
            // Utilisation du thème Nimbus pour une apparence plus professionnelle
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception e) {
            e.printStackTrace();
        }
        // Initialisation de la fenêtre
        setTitle("Connexion");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Création des composants
        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Connexion");
        loginButton.addActionListener(this);
        resetButton = new JButton("Annuler");
        resetButton.addActionListener(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(usernameLabel, c);
        
        c.gridx = 1;
        c.gridy = 0;
        panel.add(usernameField, c);
        
        c.gridx = 0;
        c.gridy = 1;
        panel.add(passwordLabel, c);
        
        c.gridx = 1;
        c.gridy = 1;
        panel.add(passwordField, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(loginButton);
        buttonPanel.add(resetButton);
        panel.add(buttonPanel, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel, c);
        
        add(panel);
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Veuillez saisir votre nom d'utilisateur et votre mot de passe !");
            } else {
                try {
                    // Connexion à la base de données
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/employees", "root", "pass123");
                    
                    // Requête SQL pour récupérer les données du tableau login
                    String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    ResultSet rs = stmt.executeQuery();
                    
                    if (rs.next()) {
                        // Si les données existent, afficher la fenêtre d'accueil et fermer la fenêtre de connexion
                        Main main = new Main();
                        main.setVisible(true);
                        this.dispose();
                    } else {
                        // Si les données n'existent pas, afficher un message d'erreur
                        errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect !");
                    }
                    
                    // Fermeture des ressources
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    // Afficher un message d'erreur si la connexion à la base de données a échoué
                    errorLabel.setText("Erreur de connexion à la base de données !");
                    ex.printStackTrace();
                    }
                    }
                    } else if (e.getSource() == resetButton) {
                    // Réinitialiser les champs de saisie et le message d'erreur
                    usernameField.setText("");
                    passwordField.setText("");
                    errorLabel.setText("");
                    }
                    }

                    public static void main(String[] args) {
                        // Créer une instance de l'interface de connexion
                        new LoginInterface();
                    }
                    }
                    
