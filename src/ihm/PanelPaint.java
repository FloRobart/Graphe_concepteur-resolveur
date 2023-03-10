package ihm;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controleur.Controleur;
import ihm.menu.PopUpMenu;
import metier.Node;


public class PanelPaint extends JPanel implements MouseListener, MouseMotionListener
{
    private Controleur ctrl;

    private Node nodeSelected;

    private int[] taillePlateau;


    public PanelPaint(Controleur ctrl, int[] taillePlateau)
    {
        this.ctrl = ctrl;

        this.nodeSelected = null;

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
                this.drawArrow(g2, node, voisin);
            }
        }

        // affichage des noeuds
        for (Node node : this.ctrl.getNodes())
            this.drawNode(g2, node);
	}

    /**
     * Dessine une flèche entre deux points
     * @param g2 : Graphics2D sur lequel dessiner
     * @param x1 : coordonnée x du point de départ
     * @param y1 : coordonnée y du point de départ
     * @param x2 : coordonnée x du point d'arrivée
     * @param y2 : coordonnée y du point d'arrivée
     */
    private void drawArrow(Graphics2D g2, Node node, Node voisin)
    {
        int x1, x2, y1, y2;

        g2.setStroke(new BasicStroke(2));

        if (node == voisin)
        {
            /*------------------------*/
            /* calcule arrow position */
            /*------------------------*/
            x1 = (node.getX() - (int)(node.getWidth ()/1.5)) + (int)(((int)(node.getWidth ()*1.5))/1.5);
            y1 = (node.getY() - (int)(node.getHeight()/1.5)) + (int)(((int)(node.getHeight()*1.5))/1.5);
            x2 = (int)(node.getWidth ()*1.5);
            y2 = (int)(node.getHeight()*1.5);


            /*----------------------*/
            /* draw the arrow count */
            /*----------------------*/
            g2.setFont(new Font("Arial", Font.BOLD, (int)(node.getWidth()*0.8)));
            g2.setColor(this.ctrl.getTheme().get("foreground"));
            g2.drawString(""+node.getCost(voisin), x1+x2, y1+y2+y2/3);
            

            /*----------------*/
            /* draw the arrow */
            /*----------------*/
            g2.setColor(this.ctrl.getTheme().get("background"));

            g2.drawOval(x1, y1, x2, y2);
        }
        else
        {
            /*------------------------------*/
            /* affichage du cout de l'arête */
            /*------------------------------*/
            int xOrig = (node  .getX());
            int yOrig = (node  .getY());
            int xDest = (voisin.getX());
            int yDest = (voisin.getY());

            int xSection = (xOrig + xDest) / 2;
            int ySection = (yOrig + yDest) / 2;

            g2.setColor(this.ctrl.getTheme().get("foreground"));
            g2.setFont(new Font("Arial", 0, node.getWidth()));
            g2.drawString(""+node.getCost(voisin), xSection, ySection);


            /*------------*/
            /* Arrow Line */
            /*------------*/
            x1 = node.getX() + (int) (node.getWidth() /2);
            y1 = node.getY() + (int) (node.getHeight()/2);

            x2 = voisin.getX() + (int) (voisin.getWidth() /2);
            y2 = voisin.getY() + (int) (voisin.getHeight()/2);

            g2.setColor(this.ctrl.getTheme().get("background"));

            g2.drawLine(x1, y1, x2, y2);

            /*------------*/
            /* Arrow Head */
            /*------------*/
            double angle = Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI;

            x1 = voisin.getX() + (int) ((voisin.getWidth ()/2) - ((voisin.getWidth ()/2)*Math.cos(Math.toRadians(angle))));
            y1 = voisin.getY() + (int) ((voisin.getHeight()/2) - ((voisin.getHeight()/2)*Math.sin(Math.toRadians(angle))));

            x2 = x1 - (int) (25*Math.cos(Math.toRadians(angle))) + (int) (15*Math.sin(Math.toRadians(angle)));
            y2 = y1 - (int) (25*Math.sin(Math.toRadians(angle))) - (int) (15*Math.cos(Math.toRadians(angle)));
            g2.drawLine(x1, y1, x2, y2);

            x2 = x1 - (int) (25*Math.cos(Math.toRadians(angle))) - (int) (15*Math.sin(Math.toRadians(angle)));
            y2 = y1 - (int) (25*Math.sin(Math.toRadians(angle))) + (int) (15*Math.cos(Math.toRadians(angle)));
            g2.drawLine(x1, y1, x2, y2);
        }
    }


    /**
     * Dessine un noeud
     * @param g2   : Graphics2D sur lequel dessiner
     * @param x    : coordonnée x du noeud
     * @param y    : coordonnée y du noeud
     * @param name : nom du noeud
     */
    private void drawNode(Graphics2D g2, Node node)
    {
        if (node.isAbsorbant())
            g2.setColor(this.ctrl.getTheme().get("disableColor"));
        else
            g2.setColor(this.ctrl.getTheme().get("foreground"));

        g2.fillOval(node.getX(), node.getY(), node.getWidth(), node.getHeight());

        // affichage du nom du noeud
        g2.setColor(this.ctrl.getTheme().get("foreground"));
        g2.setFont(new Font("Arial", Font.BOLD, node.getWidth()));
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(node.getName(), g2);
        g2.drawString(node.getName(), node.getX() - (int) rect.getWidth()/2 + node.getWidth()/2, node.getY() - (int) rect.getHeight()/2 + 5);
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
                    this.nodeSelected = node;
                    new PopUpMenu(this.ctrl, node).show(this, me.getX(), me.getY());
                    break;
                }
            }

            if (this.nodeSelected == null)
                new PopUpMenu(this.ctrl, me.getX(), me.getY()).show(this, me.getX(), me.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}

    /**
     * Applique le thème à tous les composants du panel
     */
    public void appliquerTheme()
    {
        this.setBackground(this.ctrl.getTheme().get("titlesBackground"));
    }
}