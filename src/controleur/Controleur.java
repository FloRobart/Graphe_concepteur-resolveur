package controleur;

import java.util.HashMap;

import java.awt.Color;

import ihm.FramePrincipale;
import metier.Metier;
import metier.Noeud;


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
     * Permet d'ajouter un noeud au graphe
     */
    public void addNode() { this.metier.addNode(); }

    /**
     * Permet d'ajouter une arête entre deux noeuds du graphe
     * @param nA : Noeud d'origine
     * @param nB : Noeud de destination
     */
    public void addEdge(Noeud nA, Noeud nB) { this.metier.addEdge(nA, nB); }

    /**
     * Permet de trouver et d'afficher le plus court chemin entre deux noeuds
     * @param nA : Noeud d'origine
     * @param nB : Noeud de destination
     */
    public void findShortPath(Noeud nA, Noeud nB) { this.metier.findShortPath(nA, nB); }

    /**
     * Permet de trouver et d'afficher les circuit absorbant du graphe s'il y en a
     */
    public void findAbsorbingCircuit() { this.metier.findAbsorbingCircuit(); }



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