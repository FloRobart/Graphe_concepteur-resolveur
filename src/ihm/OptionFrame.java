package ihm;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Controleur;


public class OptionFrame extends JFrame
{
    private Controleur ctrl;
    private JPanel panelOption;

    public OptionFrame(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle("Résultat");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* Création des composants */
        this.panelOption = new JPanel();

        /* Ajout des composants */
        this.add(this.panelOption);

        /* Affichage */
        this.appliquerTheme();
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
