package ihm;

import controleur.Controleur;
import ihm.menu.MenuBarre;

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
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Création des composants */
        this.menuBarre = new MenuBarre(this.ctrl);
        this.panelCreation = new PanelCreation(this.ctrl);
        this.panelRendu = new PanelRendu(this.ctrl);

        /* Ajout des composants */
        this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.panelCreation, this.panelRendu));
        this.setJMenuBar(this.menuBarre);


        /* Affichage */
        this.appliquerTheme();
        this.setVisible(true);
    }

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
