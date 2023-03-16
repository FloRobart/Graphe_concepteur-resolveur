package ihm;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;


public class OptionFrame extends JFrame
{
    private Controleur ctrl;
    private JPanel panelOption;
    private JLabel labelMessage;
    private String message;

    public OptionFrame(JFrame parent, String message, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.message = message;
        this.setTitle("Résultat");
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* Création des composants */
        this.panelOption = new JPanel();
        this.labelMessage = new JLabel(message);
        
        /* Ajout des composants */
        this.panelOption.add(this.labelMessage);
        this.add(this.panelOption);

        /* Affichage */
        this.appliquerTheme();
        this.pack();
        this.setVisible(true);
    }

    /**
     * Permet de récupérer le message
     * @return message
     */
    public String getMessage() { return this.message; }

    /**
     * Applique le thème à tous les composants du panel
     */
    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
        Color foreGeneralColor = this.ctrl.getTheme().get("foreground");

        this.panelOption.setBackground(backGeneralColor);
        this.panelOption.setForeground(foreGeneralColor);

        this.labelMessage.setBackground(backGeneralColor);
        this.labelMessage.setForeground(foreGeneralColor);
    }
}
