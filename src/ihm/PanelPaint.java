package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controleur.Controleur;
import metier.Node;


public class PanelPaint extends JPanel
{
    private Controleur ctrl;

    private int[] taillePlateau;	
	private double zoomFactor = 1;

    public PanelPaint(Controleur ctrl, int[] taillePlateau)
    {
        this.ctrl = ctrl;

        this.taillePlateau = taillePlateau;
		this.setBorder(BorderFactory.createLineBorder(this.ctrl.getTheme().get("titlesBackground"), 2));

		this.setSize(taillePlateau[0], taillePlateau[1]);
    }

    @Override
    public void paint(Graphics g) 
    {
		super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

		AffineTransform at = new AffineTransform();
		at.scale(zoomFactor, zoomFactor);
        g2.transform(at);

		// affichage de la couleur de fond
		g2.setColor(this.ctrl.getTheme().get("titlesBackground"));
		g2.fillRect(0, 0, taillePlateau[0], taillePlateau[1]);

        // affichage des noeuds
        List<Node> lstNodes = this.ctrl.getNodes();
        for (int i = 0; i < lstNodes.size(); i++)
        {
            g2.setColor(this.ctrl.getTheme().get("foreground"));
            g2.fillOval(lstNodes.get(i).getX(), lstNodes.get(i).getY(), 10, 10);

            // affichage du nom du noeud
            g2.setFont(new Font("Arial", Font.BOLD, 10));
            FontMetrics fm = g2.getFontMetrics();
            Rectangle2D rect = fm.getStringBounds(lstNodes.get(i).getName(), g2);
            g2.drawString(lstNodes.get(i).getName(), lstNodes.get(i).getX() - (int) rect.getWidth()/2 + 5, lstNodes.get(i).getY() - (int) rect.getHeight()/2 + 5);
        }
	}

	/**
     * Met à jour le zoom de la fenêtre
     * @param zoomFactor le facteur de zoom
     */
	public void majZoom(double zoomFactor)
	{
		this.zoomFactor = zoomFactor;
		this.setSize( (int) (this.taillePlateau[0] * zoomFactor), 
		              (int) (this.taillePlateau[1] * zoomFactor) );

		this.repaint();
	}

    /**
     * Met à jour l'affichage de la fenêtre
     */
	public void majIhm()
	{
		this.repaint();
	}

    public void appliquerTheme()
    {
        this.setBackground(this.ctrl.getTheme().get("titlesBackground"));
    }
}
