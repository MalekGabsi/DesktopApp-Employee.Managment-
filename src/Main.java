import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main extends JFrame {

    public Main(){
        try {
            // Utilisation du thème Nimbus pour une apparence plus professionnelle
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Gestion des employés");

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton employesButton = new JButton("Employés");
        JButton detailsButton = new JButton("Détails");
        JButton salaireButton = new JButton("Salaire");
        JButton congesButton = new JButton("Congés");
        buttonsPanel.add(employesButton);
        buttonsPanel.add(detailsButton);
        buttonsPanel.add(salaireButton);
        buttonsPanel.add(congesButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        
       

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel descriptionLabel = new JLabel("Bienvenue dans la gestion des employés !");
        bottomPanel.add(descriptionLabel);

        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        employesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormulaireEmploye formulaire = new FormulaireEmploye();
                formulaire.setVisible(true);
                frame.dispose();
            }
        });
        congesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CongesInterface();
                congesButton.setVisible(true);
                frame.dispose();
            }
        });

        salaireButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new salaireInerface();
                congesButton.setVisible(true);
                frame.dispose();
            }
        });

        detailsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DetailsInetrface();
                detailsButton.setVisible(true);
                frame.dispose();
            }
        });

        
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

}
