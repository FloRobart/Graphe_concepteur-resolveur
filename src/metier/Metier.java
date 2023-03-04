package metier;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.awt.Color;
import java.io.PrintWriter;
import java.util.HashMap;

import controleur.Controleur;


public class Metier
{
    private Controleur ctrl;

    private HashMap<String, Color> hmColorTheme;


    public Metier(Controleur ctrl)
    {
        this.ctrl = ctrl;

        /* Thèmes */
        this.hmColorTheme = new HashMap<String, Color>();
        this.chargerThemes(this.getThemeUsed());
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
    public void addNode()
	{
		
	}

    /**
     * Permet d'ajouter une arête entre deux noeuds du graphe
     * @param nA : Noeud d'origine
     * @param nB : Noeud de destination
     */
    public void addEdge(Noeud nA, Noeud nB)
	{
		
	}

    /**
     * Permet de trouver et d'afficher le plus court chemin entre deux noeuds
     * @param nA : Noeud d'origine
     * @param nB : Noeud de destination
     */
    public void findShortPath(Noeud nA, Noeud nB)
	{
		
	}

    /**
     * Permet de trouver et d'afficher les circuit absorbant du graphe s'il y en a
     */
    public void findAbsorbingCircuit()
	{
		
	}


    /*========*/
    /* Thèmes */
    /*========*/
    /**
     * Permert de récupérer toute les couleurs de thème charger en mémoire.
     * @return HashMap - liste des couleurs du thème.
     */
    public HashMap<String, Color> getTheme() { return this.hmColorTheme;}


    /**
	 * Récupère le thème utilisé dans le fichier xml de sauvegarde
	 * @return String : thème à utilisé
	 */
	public String getThemeUsed()
	{
		String themeUsed = "";
		SAXBuilder sxb = new SAXBuilder();

		try
		{
			themeUsed = sxb.build(Path.PATH_THEME_SAVE).getRootElement().getText();
		}
		catch (Exception e) { e.printStackTrace(); System.out.println("Erreur lors de la lecture du fichier XML du themes utilisé"); }

		return themeUsed;
	}


    /**
	 * Sauvegarde le thème selectionné par l'utilisateur dans le fichier xml de sauvegarde.
	 * Charge le thème selectionné dans la HashMap.
	 * Applique le thème selectionné (met à jour l'IHM).
	 * @param theme : thème à sauvegarder
	 */
	public void setThemeUsed(String theme)
	{
		if (!theme.equals(this.getThemeUsed()))
		{
			try
			{
				PrintWriter pw = new PrintWriter(Path.PATH_THEME_SAVE);
				pw.println("<theme>" + theme + "</theme>");
				pw.close();
			}
			catch (Exception e) { e.printStackTrace(); System.out.println("Erreur lors de l'écriture du fichier XML du themes utilisé"); }

			this.chargerThemes(theme);

			this.ctrl.appliquerTheme();
		}
	}


    /**
	 * Charge les couleurs du thème choisi par l'utilisateur dans la HashMap
	 * @param theme : thème à charger
	 * @return HashMap contenant les couleurs du thème
	 */
	public void chargerThemes(String theme)
	{
		SAXBuilder sxb = new SAXBuilder();

		try
		{
			Element racine = sxb.build(Path.PATH_THEME_X + theme + ".xml").getRootElement();

			/*----------------------------------------------*/
			/* Récupération des couleurs de chaque éléments */
			/*----------------------------------------------*/
			for (Element e : racine.getChildren())
			{
				Color color = new Color( Integer.parseInt(e.getAttributeValue("red")), Integer.parseInt(e.getAttributeValue("green")), Integer.parseInt(e.getAttributeValue("blue")), Integer.parseInt(e.getAttributeValue("alpha")));

				this.hmColorTheme.put(e.getName(), color);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Erreur lors de la lecture du fichier XML des informations du theme");
		}
	}
}
