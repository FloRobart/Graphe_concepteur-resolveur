package ihm;

import java.awt.Color;
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


public class PanelRendu extends JPanel implements ActionListener, MouseWheelListener, MouseListener, MouseMotionListener
{
    private Controleur ctrl;

    public PanelRendu(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }


    @Override
	public void mouseMoved  (MouseEvent me)        {}
    @Override
    public void mouseEntered(MouseEvent me)        {}
    @Override
    public void mouseExited (MouseEvent me)        {}
    @Override
    public void mouseDragged(MouseEvent me)        {}
    @Override
    public void mouseClicked(MouseEvent me)        {}
    @Override
    public void mousePressed(MouseEvent me)        {}
    @Override
    public void mouseReleased(MouseEvent me)       {}
    @Override
    public void mouseWheelMoved(MouseWheelEvent me){}
    @Override
    public void actionPerformed(ActionEvent me)    {}


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