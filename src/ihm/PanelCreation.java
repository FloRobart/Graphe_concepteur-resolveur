package ihm;

import controleur.Controleur;

import java.awt.Color;

import javax.swing.JPanel;


public class PanelCreation extends JPanel
{
    private Controleur ctrl;

    public PanelCreation(Controleur ctrl)
    {
        this.ctrl = ctrl;
    }

    /**
     * Applique le thème à tous les composants du panel
     */
    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
		Color foreGeneralColor = this.ctrl.getTheme().get("foreground");

        
        this.setBackground(backGeneralColor);
        this.setForeground(foreGeneralColor);
    }
}
