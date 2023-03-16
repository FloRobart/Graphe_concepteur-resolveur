package metier;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import controleur.Controleur;
import ihm.OptionFrame;


public class Metier
{
    private Controleur ctrl;

    private HashMap<String, Color> hmColorTheme;

	private List<Node> lstNode;


    public Metier(Controleur ctrl)
    {
        this.ctrl = ctrl;

		/* Gestion du graphe */
		this.lstNode = new ArrayList<Node>();

        /* Thèmes */
        this.hmColorTheme = new HashMap<String, Color>();
        this.chargerThemes(this.getThemeUsed());
    }


	/*===================*/
    /* Gestion du graphe */
    /*===================*/
	/*---------*/
	/* FIchier */
	/*---------*/
	/**
     * Permet de charger un graphe depuis un fichier
     * @param file : fichier .data contenant le graphe sous forme textuelle
     */
    public void chargerGraphe(File file)
	{
		if (!file.exists()) throw new IllegalArgumentException("Le fichier n'existe pas");

		this.lstNode = new ArrayList<Node>();
		try
		{
			/* Récupération des noeuds */
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine())
				this.lstNode.add(Node.deserializableNode(sc.nextLine().split("\\)")[0]));

			sc.close();

			/* Récupération des voisins */
			int cpt = 0;
			sc = new Scanner(file);
			while (sc.hasNextLine())
			{
				String[] ensNeighbors = sc.nextLine().split("\\)");
				if (ensNeighbors.length >= 2)
					for (String neighbor : ensNeighbors[1].substring(1).split(","))
						this.lstNode.get(cpt).addNeighbor(this.getNodeByName(neighbor.split(":")[0]), Integer.parseInt(neighbor.split(":")[1]));

				cpt++;
			}
			sc.close();

			/* Mise à jour de la JList des noeuds */
			this.ctrl.clearJList();
			for (Node n : this.lstNode)
				this.ctrl.addNodeInJList(n);
		}
		catch (Exception e) { e.printStackTrace(); System.out.println("Erreur lors de l'ouverture du fichier en lecture"); }
	}

    /**
     * Permet de sauvegarder le graphe dans un fichier
     * @param file : fichier .data contenant le graphe sous forme textuelle
     */
    public void sauvegarderGraphe(File file)
	{
		try
		{
			PrintWriter pw = new PrintWriter(file);

			for (Node node : this.lstNode)
			{
				pw.println(node.serializable());
			}

			pw.close();
		}
		catch (FileNotFoundException e) { e.printStackTrace(); System.out.println("Erreur lors de l'ouverture du fichier en écriture"); }
	}

    /*----------------*/
    /* Panel création */
    /*----------------*/
    /**
     * Permet d'ajouter un noeud au graphe
     */
    public void addNode()
	{
		this.lstNode.add(new Node(defineName("A")));
		this.ctrl.addNodeInJList(this.lstNode.get(this.lstNode.size()-1));
	}

	/**
     * Permet d'ajouter un noeud au graphe
     */
    public void addNode(int x, int y)
	{
		this.lstNode.add(new Node(defineName("A"), x, y));
		this.ctrl.addNodeInJList(this.lstNode.get(this.lstNode.size()-1));
	}

	/**
	 * Défini le nom du noeud en fonction des autres noeuds du graphe
	 * @param defaultName : Nom par défaut ("A")
	 * @return Nom du noeud
	 */
	private String defineName(String defaultName)
	{
		String name = defaultName;
		for(Node n : this.lstNode)
		{
			if(n.getName().equals(name))
			{
				for (int i = name.length()-1; i >= 0; i--)
				{
					if (name.charAt(i) == 'Z')
					{
						if (i == 0)
							name = name + "A";
						
						name = name.substring(0, i) + "A" + name.substring(i+1, name.length());
					}
					else
					{
						return this.defineName(name.substring(0, i) + (char)((int)(name.charAt(i)) + 1) + name.substring(i+1, name.length()));
					}
				}

				return this.defineName(name);
			}
		}

		return name;
	}

    /**
     * Permet d'ajouter une arête entre deux noeuds du graphe
     * @param nodeOrig : Node d'origine
     * @param nodeDest : Node de destination
	 * @param cout : Cout de l'arête
     */
    public void addEdge(Node nodeOrig, Node nodeDest, int cout)
	{
		if (nodeOrig == null) throw new NullPointerException("Node d'origine null");
		if (nodeDest == null) throw new NullPointerException("Node de destination null");
		//if (cout < 0) throw new IllegalArgumentException("Cout négatif");

		nodeOrig.addNeighbor(nodeDest, cout);
	}

    /**
     * Permet de trouver et d'afficher le plus court chemin entre deux noeuds
     * @param nA : Node d'origine
     * @param nB : Node de destination
     */
    public void findShortPath(Node nA, Node nB)
	{
		BellmanFord bf2 = new BellmanFord(this.lstNodesToMatrice(this.lstNode), this.lstNode.size());
		List<Integer> lstShortestPath = bf2.shortestPath(nA.getNameInInt(), nB.getNameInInt());
		
		if (lstShortestPath == null || lstShortestPath.size() == 0) { JOptionPane.showMessageDialog(this.ctrl.getFramePrincipale(), "Aucun chemin ne vas de " + nA.getName() + " à " + nB.getName() + ".", "Résultat du chemin le plus court", JOptionPane.INFORMATION_MESSAGE); return; }


		/* Remise à zero de l'attribut 'shortNeighborNode' dans tous les noeuds de la liste */
		for (Node n : this.lstNode)
			n.setShortNeighborNode(null);

		if (lstShortestPath.get(0) < 0)
		{
			new OptionFrame(this.ctrl.getFramePrincipale(), "Le graphe possède un circuit absorbant", this.ctrl).show();
			this.ctrl.majIhm();
			return;
		}

		for (int i = 1; i < lstShortestPath.size()-1; i++)
		{
			Node shortNeighborNode = this.getNodeByName(Node.convertIntToName(lstShortestPath.get(i))); /* Récupération du noeud qui à ce nom */
			shortNeighborNode.setShortNeighborNode(this.getNodeByName(Node.convertIntToName(lstShortestPath.get(i+1)))); /* Définition du noeud suivant dans la liste comme 'shortNeighborNode' */
		}

		this.ctrl.majIhm();
	}

	/**
	 * Permet de convertir une liste de noeuds en matrice d'adjacence
	 * @param nodes : Liste de noeuds
	 * @return Matrice d'adjacence
	 */
	private int[][] lstNodesToMatrice(List<Node> nodes)
	{
		int nbLigne = 0;
		for (Node node : nodes) nbLigne += node.getNeighbors().size();

		int[][] edges = new int[nbLigne][3]; // Initialiser le tableau de résultats avec une taille suffisante

		int row = 0;
		for (Node node : nodes)
		{
			int source = node.getNameInInt();
			for (int i = 0; i < node.getNeighbors().size(); i++)
			{
				Node neighbor = node.getNeighbors().get(i);
				int destination = neighbor.getNameInInt();
				int cost = node.getCosts().get(i);
				edges[row][0] = source;
				edges[row][1] = destination;
				edges[row][2] = cost;
				row++;
			}
		}

		return edges;
	}

	/**
     * Permet de trouver et d'afficher les circuit absorbant du graphe s'il y en a
     */
    public void findAbsorbingCircuit(Node nA, Node nB)
	{
		String sRet = "";

		BellmanFord bf = new BellmanFord(this.lstNodesToMatrice(this.lstNode), this.lstNode.size());
		System.out.println(""+bf.hasAbsorbingCycle());
		//new OptionFrame(this.ctrl.getFramePrincipale(), ""+bf.hasAbsorbingCycle(), this.ctrl).show();
	}


    /**
     * Permet de trouver et d'afficher les noeuds absorbant du graphe s'il y en a
     */
    public void findObseletNode()
	{
		for (Node node : this.lstNode)
		{
			if (node.getNeighbors().size() == 0)
				node.setObselet(true);
			else if (node.getNeighbors().size() == 1)
				if (node.getNeighbors().get(0) == node)
					node.setObselet(true);
				else
					node.setObselet(false);
			else
				node.setObselet(false);
		}

		this.ctrl.majIhm();
	}

	/**
     * Permet de supprimer un noeud de la JList de l'ihm
     * @param node : Node à supprimer
     */
    public void removeNode(Node node)
	{
		this.lstNode.remove(node);

		for (Node n : this.lstNode)
		{
			if (n.isNeighbor(node))
				n.removeNeighbor(node);
		}
	}


	/*-----------------*/
    /* metier générale */
    /*-----------------*/
    /**
     * Permet de récupérer le nombre de noeuds du graphe
	 * @return liste de tous les noeuds du graphe
     */
    public List<Node> getNodes() { return this.lstNode; }

	public Node getNodeByName(String name)
	{
		for (Node node : this.lstNode)
			if (node.getName().equals(name))
				return node;

		return null;
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
