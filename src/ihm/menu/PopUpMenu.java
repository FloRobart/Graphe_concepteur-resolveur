package ihm.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import controleur.Controleur;
import metier.Node;


public class PopUpMenu extends JPopupMenu implements ActionListener
{
    private Controleur ctrl;

    private JMenuItem deleteNode;

    private Node nodeSelectioned;

    public PopUpMenu(Controleur ctrl)
    {
        this.ctrl = ctrl;

        /* Création des composants */
        this.deleteNode = new JMenuItem("supprimer le noeud");

        this.nodeSelectioned = null;


        /* Ajouts des composants */
        this.add(this.deleteNode);

        /* Activations des composants */
        this.deleteNode.addActionListener(this);
    }

    @Override
    public void show(Component c, int x, int y)
    {
        super.show(c, x, y);
    }

    /**
     * Permet d'afficher le menu contextuel
     * @param node : le noeud selectionné
     * @param c : le composant sur lequel on affiche le menu
     * @param x : la position x
     * @param y : la position y
     */
    public void show(Component c, int x, int y, Node nodeSelectioned)
    {
        this.nodeSelectioned = nodeSelectioned;
        this.appliquerTheme();
        this.show(c, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        /* Changer de lecteur */
        if (source == this.deleteNode)
        {
            this.ctrl.deleteNode(this.nodeSelectioned);
            this.ctrl.deleteNodeInJList(this.nodeSelectioned);
            this.ctrl.majIhm();
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

        this.deleteNode.setBackground(backGeneralColor);
        this.deleteNode.setForeground(foreGeneralColor);
    }
}