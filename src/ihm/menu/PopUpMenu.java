package ihm.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controleur.Controleur;
import metier.Node;


public class PopUpMenu extends JPopupMenu implements ActionListener
{
    private Controleur ctrl;

    private JMenuItem addNode;
    private JMenuItem removeNode;
    private JMenu     removeEdge;

    private int      x;
    private int      y;

    private Node nodeSelectioned;

    public PopUpMenu(Controleur ctrl, Node nodeSelectioned)
    {
        this.ctrl = ctrl;

        /* Création des composants */
        this.addNode = null;
        this.removeNode = new JMenuItem("supprimer le noeud");
        this.removeEdge = new JMenu("supprimer le lien vers ");

        this.nodeSelectioned = nodeSelectioned;

        for (Node n : this.nodeSelectioned.getNeighbors())
        {
            JMenuItem item = new JMenuItem(n.getName());
            item.addActionListener(this);
            this.removeEdge.add(item);
        }

        this.removeEdge.setEnabled(this.removeEdge.getMenuComponents().length > 0);

        /* Ajouts des composants */
        this.add(this.removeNode);
        this.add(this.removeEdge);


        /* Activations des composants */
        this.removeNode.addActionListener(this);
    }

    public PopUpMenu(Controleur ctrl, int x, int y)
    {
        this.ctrl = ctrl;

        this.x = x;
        this.y = y;

        /* Création des composants */
        this.addNode = new JMenuItem("ajouter un noeud");
        this.removeNode = null;
        this.removeEdge = null;

        this.nodeSelectioned = null;

        /* Ajouts des composants */
        this.add(this.addNode);

        /* Activations des composants */
        this.addNode.addActionListener(this);
    }

    @Override
    public void show(Component c, int x, int y)
    {
        this.appliquerTheme();
        super.show(c, x, y);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        /* Changer de lecteur */
        if (source == this.removeNode)
        {
            this.ctrl.removeNode(this.nodeSelectioned);
            this.ctrl.removeNodeInJList(this.nodeSelectioned);
            this.ctrl.majIhm();
        }

        if (source == this.addNode)
        {
            this.ctrl.addNode(this.x, this.y);
            this.ctrl.majIhm();
        }

        if (this.removeEdge != null)
        {
            for (Component c : this.removeEdge.getMenuComponents())
            {
                JMenuItem item = (JMenuItem) c;
                if (source == item)
                {
                    this.nodeSelectioned.removeNeighbor(item.getText());
                    this.ctrl.majIhm();
                }
            }
        }
    }

    /**
     * Permet d'appliquer le theme
     */
    public void appliquerTheme()
    {
        Color backGeneralColor = this.ctrl.getTheme().get("background");
        Color foreGeneralColor = this.ctrl.getTheme().get("foreground");


        this.setBackground(backGeneralColor);
        this.setForeground(foreGeneralColor);

        if (this.addNode != null)
        {
            this.addNode.setBackground(backGeneralColor);
            this.addNode.setForeground(foreGeneralColor);
        }

        if (this.removeNode != null)
        {
            this.removeNode.setBackground(backGeneralColor);
            this.removeNode.setForeground(foreGeneralColor);
        }

        if (this.removeEdge != null)
        {
            this.removeEdge.setBackground(backGeneralColor);
            this.removeEdge.setForeground(foreGeneralColor);
        }

        if (this.removeEdge != null)
        {
            for (Component c : this.removeEdge.getMenuComponents())
            {
                JMenuItem item = (JMenuItem) c;
                item.setBackground(backGeneralColor);
                item.setForeground(foreGeneralColor);
            }
        }
    }
}