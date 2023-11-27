import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailsInetrface extends JFrame {
    
    private static final String NOM = "Ma Société";
    private static final String DESCRIPTION = "Nous sommes une entreprise spécialisée dans le développement de logiciels.";
    private static final String ADRESSE = "123 Rue Principale, Ville, Pays";
    private static final String TELEPHONE = "+216 559 143 55";
    private static final String EMAIL = "contact@masociete.com";
    
    private JLabel nomLabel;
    private JLabel descriptionLabel;
    private JLabel adresseLabel;
    private JLabel telephoneLabel;
    private JLabel emailLabel;
    private JLabel imageLabel;
    
    public DetailsInetrface() {
        super("Informations Société");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Création du panel pour les labels
        JPanel labelsPanel = new JPanel(new GridBagLayout());
        labelsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 10),
                BorderFactory.createLineBorder(Color.GRAY, 1)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        nomLabel = new JLabel(NOM);
        nomLabel.setFont(new Font("Arial", Font.BOLD, 24));
        labelsPanel.add(nomLabel, gbc);
        gbc.gridx++;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 5);
        descriptionLabel = new JLabel(DESCRIPTION);
        labelsPanel.add(descriptionLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);
        adresseLabel = new JLabel("<html><body><b>Adresse : </b>" + ADRESSE + "</body></html>");
        labelsPanel.add(adresseLabel, gbc);
        gbc.gridy++;
        telephoneLabel = new JLabel("<html><body><b>Téléphone : </b>" + TELEPHONE + "</body></html>");
        labelsPanel.add(telephoneLabel, gbc);
        gbc.gridy++;
        emailLabel = new JLabel("<html><body><b>Email : </b>" + EMAIL + "</body></html>");
        labelsPanel.add(emailLabel, gbc);
        
        // Chargement de l'image
        BufferedImage image = null;
        try {
            URL url = new URL("https://i.pinimg.com/564x/7c/50/74/7c50746e4c0833593b4a7a19a94163d3.jpg");
            image = ImageIO.read(url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        // Création du panel pour l'image
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createEmptyBorder(20, 10, 20, 20),
        BorderFactory.createLineBorder(Color.GRAY, 1)
         ));
        imageLabel = new JLabel();
        if (image != null) {
        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        }        
        imagePanel.add(imageLabel, BorderLayout.CENTER);
    // Création du bouton pour quitter l'application
    JButton accButton = new JButton("Accueil");
    accButton.addActionListener(e->{
         
                
        Main main = new Main(); 
        main.setVisible(true); 
        this.dispose();
    
        });
   
    
    // Création du panel principal pour tout mettre ensemble
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(labelsPanel, BorderLayout.WEST);
    mainPanel.add(imagePanel, BorderLayout.CENTER);
    mainPanel.add(accButton, BorderLayout.SOUTH);
    
    // Réglage de la taille de la fenêtre et affichage
    setPreferredSize(new Dimension(800, 400));
    setContentPane(mainPanel);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
}

public static void main(String[] args) {
    new DetailsInetrface();
}
}