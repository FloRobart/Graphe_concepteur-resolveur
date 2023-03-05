package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controleur.Controleur;


public class PanelRendu extends JPanel
{
    private Controleur ctrl;
    private PanelPaint panelPaint;

    public PanelRendu(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setSize(500, 500);
        this.setPreferredSize(new Dimension(500, 500));

        /* Création des composants */
        this.panelPaint = new PanelPaint(this.ctrl, new int[]{500, 500});


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