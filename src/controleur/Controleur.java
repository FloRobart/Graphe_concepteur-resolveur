package controleur;

import java.util.HashMap;
import java.util.List;
import java.awt.Color;

import ihm.FramePrincipale;
import metier.Metier;
import metier.Node;


public class Controleur
{
    private Metier metier;
    private FramePrincipale ihm;


    public Controleur()
    {
        this.metier = new Metier(this);
        this.ihm = new FramePrincipale(this);
    }


    /*===================*/
    /* Gestion du graphe */
    /*===================*/
    /*----------------*/
    /* Panel création */
    /*----------------*/
    /**
     * Permet d'ajouter un noeud au graphe.
     * Le nom du noeud est défini automatiquement.
     * Le noeud est ajouté à la liste des noeuds du panel de création.
     */
    public void addNode() { this.metier.addNode(); }

    /**
     * Permet d'ajouter un noeud au graphe.
     * Le nom du noeud est défini automatiquement.
     * Le noeud est ajouté à la liste des noeuds du panel de création.
     * @param x : Position x du noeud
     * @param y : Position y du noeud
     */
    public void addNode(int x, int y) { this.metier.addNode(x, y); }

    /**
     * Permet d'ajouter une arête entre deux noeuds du graphe
     * @param nodeOrig : Node d'origine
     * @param nodeDest : Node de destination
     * @param cout : Cout de l'arête
     */
    public void addEdge(Node nodeOrig, Node nodeDest, int cout) { this.metier.addEdge(nodeOrig, nodeDest, cout); }

    /**
     * Permet de trouver et d'afficher le plus court chemin entre deux noeuds
     * @param nA : Node d'origine
     * @param nB : Node de destination
     */
    public void findShortPath(Node nA, Node nB) { this.metier.findShortPath(nA, nB); }

    /**
     * Permet de trouver et d'afficher les circuit absorbant du graphe s'il y en a
     */
    public void findAbsorbingCircuit() { this.metier.findAbsorbingCircuit(); }

    /**
     * Permet d'ajouter un noeud dans la JList de l'ihm
     * @param node : Node à ajouter
     */
    public void addNodeInJList(Node node) { this.ihm.addNodeInJList(node); }

    /**
     * Permet de supprimer un noeud de la JList de l'ihm
     * @param node : Node à supprimer
     */
    public void removeNodeInJList(Node node) { this.ihm.removeNodeInJList(node); }

    /**
     * Permet de supprimer un noeud
     * @param node : Node à supprimer
     */
    public void removeNode(Node node) { this.metier.removeNode(node); }


    /*-------------*/
    /* Panel rendu */
    /*-------------*/
    /**
     * Permet de mettre à jour l'ihm
     */
    public void majIhm() { this.ihm.majIhm(); }


    /*-----------------*/
    /* metier générale */
    /*-----------------*/
    /**
     * Permet d'obtenir la frame principale
     * @return Frame principale
     */
    public FramePrincipale getFramePrincipale() { return this.ihm; }

    /**
     * Permet de récupérer le nombre de noeuds du graphe
	 * @return liste de tous les noeuds du graphe
     */
    public List<Node> getNodes() { return this.metier.getNodes(); }



    /*========*/
    /* Thèmes */
    /*========*/
    /**
     * Permet d'appliquer le thème à l'ihm
     */
    public void appliquerTheme() { this.ihm.appliquerTheme(); }

    /**
     * Permet de à l'ihm de récupérer la hashmap contenant les couleurs du thème
     * @return HashMap contenant les couleurs du thème
     */
    public HashMap<String, Color> getTheme() { return this.metier.getTheme(); }

	/**
	 * Permet de récupérer le nom partièle du thème utilisé (nom complet : theme_X.xml)
	 * @return Nom du thème utilisé (nom renvoyé par cette méthode : X)
	 */
	public String getThemeUsed() { return this.metier.getThemeUsed(); }

    /**
     * Change le thème à utilisé dans le fichier de sauvegarde.
     * Charge en mémoire le nouveau thème.
     * Met à jour l'ihm.
     * @param theme : Nom du thème à utiliser
     */
    public void changerTheme(String theme) { this.metier.setThemeUsed(theme); }




    public static void main(String[] args)
    {
        new Controleur();
    }
}