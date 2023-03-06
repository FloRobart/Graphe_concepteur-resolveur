package ihm;

import controleur.Controleur;
import ihm.menu.MenuBarre;
import metier.Node;

import javax.swing.JFrame;
import javax.swing.JSplitPane;


public class FramePrincipale extends JFrame
{
    private Controleur ctrl;
    private MenuBarre menuBarre;
    private PanelCreation panelCreation;
    private PanelRendu panelRendu;


    public FramePrincipale(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle("Frame principale");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Création des composants */
        this.menuBarre = new MenuBarre(this.ctrl);
        this.panelCreation = new PanelCreation(this.ctrl);
        this.panelRendu = new PanelRendu(this.ctrl);

        /* Ajout des composants */
        JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.panelCreation, this.panelRendu);
        jsp.setOneTouchExpandable(true);
		jsp.setContinuousLayout(true);
        this.add(jsp);
        this.setJMenuBar(this.menuBarre);


        /* Affichage */
        this.appliquerTheme();
        this.setVisible(true);
    }

    /**
     * Permet d'ajouter un noeud dans la JList de l'ihm
     * @param node : Node à ajouter
     */
    public void addNodeInJList(Node node) { this.panelCreation.addNodeInJList(node); }

    /**
     * Permet de mettre à jour l'ihm
     */
    public void majIhm() { this.panelRendu.majIhm(); }

    /**
     * Permet de supprimer un noeud de la JList de l'ihm
     * @param node : Node à supprimer
     */
    public void removeNodeInJList(Node node) { this.panelCreation.removeNodeInJList(node); }

    /**
     * Applique le thème à tous les composants de la fenêtre
     */
    public void appliquerTheme()
    {
        this.menuBarre.appliquerTheme();
        this.panelCreation.appliquerTheme();
        this.panelRendu.appliquerTheme();
    }
}
