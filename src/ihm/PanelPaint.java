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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controleur.Controleur;
import ihm.menu.PopUpMenu;
import metier.Node;


public class PanelPaint extends JPanel implements MouseListener, MouseMotionListener
{
    private Controleur ctrl;

    private Node nodeSelected;
    private PopUpMenu popUpMenu;

    private int[] taillePlateau;


    public PanelPaint(Controleur ctrl, int[] taillePlateau)
    {
        this.ctrl = ctrl;

        this.nodeSelected = null;
        this.popUpMenu = new PopUpMenu(this.ctrl);

        this.taillePlateau = taillePlateau;
		this.setBorder(BorderFactory.createLineBorder(this.ctrl.getTheme().get("titlesBackground"), 2));

		this.setSize(taillePlateau[0], taillePlateau[1]);
        this.setPreferredSize(new Dimension(taillePlateau[0], taillePlateau[1]));
        this.setBackground(this.ctrl.getTheme().get("titlesBackground"));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) 
    {
		super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

		// affichage de la couleur de fond
		g2.setColor(this.ctrl.getTheme().get("titlesBackground"));
		g2.fillRect(0, 0, taillePlateau[0], taillePlateau[1]);


        // affichage des liens
        for (Node node : this.ctrl.getNodes())
        {
            for (Node voisin : node.getNeighbors())
            {
                int xVoisin = voisin.getX() + voisin.getWidth() / 2;
                int yVoisin = voisin.getY() + voisin.getHeight() / 2;

                xVoisin = node.getX() > voisin.getX() ? xVoisin + voisin.getWidth() / 2 : xVoisin - voisin.getWidth() / 2;
                yVoisin = node.getY() > voisin.getY() ? yVoisin + voisin.getWidth() / 2 : yVoisin - voisin.getWidth() / 2;

                this.drawArrow(g2, node.getX() + node.getWidth() / 2, node.getY() + node.getHeight() / 2, xVoisin, yVoisin);
            }
        }

        // affichage des noeuds
        for (Node node : this.ctrl.getNodes())
            this.drawNode(g2, node.getX(), node.getY(), node.getHeight(), node.getHeight(), node.getName());
        
	}

    /**
     * Dessine une flèche entre deux points
     * @param g2 : Graphics2D sur lequel dessiner
     * @param x1 : coordonnée x du point de départ
     * @param y1 : coordonnée y du point de départ
     * @param x2 : coordonnée x du point d'arrivée
     * @param y2 : coordonnée y du point d'arrivée
     */
    private void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2)
    {
        g2.setColor(this.ctrl.getTheme().get("background"));
        if (x1 == x2 && y1 == y2)
        {
            // Draw a circle
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(x1 - 10, y1 - 10, 20, 20);
        }
        else 
        {
            // Draw the line
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(x1, y1, x2, y2);
            
            // Draw the arrow head
            double angle = Math.atan2(y2 - y1, x2 - x1);
            int arrowLength = 20;
            int x3 = x2 - (int) (arrowLength * Math.cos(angle - Math.PI/6));
            int y3 = y2 - (int) (arrowLength * Math.sin(angle - Math.PI/6));
            int x4 = x2 - (int) (arrowLength * Math.cos(angle + Math.PI/6));
            int y4 = y2 - (int) (arrowLength * Math.sin(angle + Math.PI/6));
            g2.drawLine(x2, y2, x3, y3);
            g2.drawLine(x2, y2, x4, y4);
        }
    }


    /**
     * Dessine un noeud
     * @param g2   : Graphics2D sur lequel dessiner
     * @param x    : coordonnée x du noeud
     * @param y    : coordonnée y du noeud
     * @param name : nom du noeud
     */
    private void drawNode(Graphics2D g2, int x, int y, int width, int height, String name)
    {
        g2.setColor(this.ctrl.getTheme().get("foreground"));
        g2.fillOval(x, y, width, height);

        // affichage du nom du noeud
        g2.setFont(new Font("Arial", Font.BOLD, width));
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(name, g2);
        g2.drawString(name, x - (int) rect.getWidth()/2 + width/2, y - (int) rect.getHeight()/2 + 5);
    }

    /**
     * Applique le thème à tous les composants du panel
     */
    public void appliquerTheme()
    {
        this.setBackground(this.ctrl.getTheme().get("titlesBackground"));
        if (this.popUpMenu != null)
            this.popUpMenu.appliquerTheme();
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
        if (this.nodeSelected != null)
        {
            if (me.getX() < 0)
                me.translatePoint(0, me.getY());
            else if (me.getY() < 0)
                me.translatePoint(me.getX(), 0);
            else if (me.getX() > this.getWidth())
                me.translatePoint(this.getWidth(), me.getY());
            else if (me.getY() > this.getHeight())
                me.translatePoint(me.getX(), this.getHeight());
            else
            {
                this.nodeSelected.setPosition(me.getX() - (nodeSelected.getWidth() / 2), me.getY() - (nodeSelected.getHeight() / 2));
                this.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        for (Node node : this.ctrl.getNodes())
        {
            if (me.getX() > node.getX() - node.getWidth () && me.getX() < node.getX() + node.getWidth() &&
                me.getY() > node.getY() - node.getHeight() && me.getY() < node.getY() + node.getHeight() )
            {
                this.nodeSelected = node;
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me)
    {
        this.nodeSelected = null;
    }

    @Override
    public void mouseClicked(MouseEvent me)
    {
        if (me.getButton() == MouseEvent.BUTTON3)
        {
            for (Node node : this.ctrl.getNodes())
            {
                if (me.getX() > node.getX() - node.getWidth () && me.getX() < node.getX() + node.getWidth() &&
                    me.getY() > node.getY() - node.getHeight() && me.getY() < node.getY() + node.getHeight() )
                {
                    this.popUpMenu.show(this, me.getX(), me.getY(), node);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
}