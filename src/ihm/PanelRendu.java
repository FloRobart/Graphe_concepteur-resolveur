package ihm;

import java.awt.Color;

import javax.swing.JPanel;

import java.awt.Toolkit;

import controleur.Controleur;


public class PanelRendu extends JPanel
{
    private Controleur ctrl;
    private PanelPaint panelPaint;


    public PanelRendu(Controleur ctrl)
    {
        this.ctrl = ctrl;

        /* Création des composants */
        this.panelPaint = new PanelPaint(this.ctrl, new int[]{Toolkit.getDefaultToolkit().getScreenSize().width - 300, Toolkit.getDefaultToolkit().getScreenSize().height - 100});


        /* Ajout des composants */
        this.add(this.panelPaint);
    }

    /**
     * Permet de mettre à jour l'ihm
     */
    public void majIhm() { this.panelPaint.repaint(); }


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