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
    private JFrame parent;

    public OptionFrame(JFrame parent, String message, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle("Résultat");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* Création des composants */
        this.panelOption = new JPanel();
        this.panelOption.add(new JLabel(message));

        /* Ajout des composants */
        this.add(this.panelOption);

        /* Affichage */
        this.appliquerTheme();
        this.setVisible(false);
    }

    public void show()
    {
        //this.setLocation(this.parent.getWidth()/2, this.parent.getHeight()/2);
        this.setLocationRelativeTo(this.parent);
        this.setVisible(true);
    }

    /**
     * Applique le thème à tous les composants du panel
     */
    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
        Color foreGeneralColor = this.ctrl.getTheme().get("foreground");

        this.panelOption.setBackground(backGeneralColor);
        this.panelOption.setForeground(foreGeneralColor);
    }
}
