package metier;

import java.util.ArrayList;
import java.util.List;


public class Node
{
    private static final int DEFAULT_WIDTH  = 20;
    private static final int DEFAULT_HEIGHT = 20;

    private String name;
    private List<Node> lstNeighbors;
    private List<Integer> listCout;

    private int x;
    private int y;
    private int width;
    private int height;

    private boolean absorbant;


    public Node(String name)
    {
        this(name, 100, 100);
    }

    public Node(String name, int x, int y)
    {
        this.name = name;
        this.lstNeighbors = new ArrayList<Node>();
        this.listCout = new ArrayList<Integer>();

        this.x = x;
        this.y = y;
        this.width  = Node.DEFAULT_WIDTH;
        this.height = Node.DEFAULT_HEIGHT;

        this.absorbant = false;
    }

    public Node(String name, List<Node> lstNeighbors, List<Integer> listCout, int x, int y, int width, int height)
    {
        this.name = name;
        this.lstNeighbors = lstNeighbors == null ? new ArrayList<Node>   () : lstNeighbors;
        this.listCout   = listCout   == null ? new ArrayList<Integer>() : listCout;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.absorbant = false;
    }

    public Node(String name, int x, int y, int width, int height)
    {
        this.name = name;
        this.lstNeighbors = new ArrayList<Node>();
        this.listCout     = new ArrayList<Integer>();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.absorbant = false;
    }

    /**
     * Permet d'ajouter un voisin au node
     * @param neighborNode : Node voisin
     */
    public void addNeighbor(Node neighborNode, int cout)
    {
        if (neighborNode == null) throw new IllegalArgumentException("Le node ne peut pas être null");

        this.lstNeighbors.add(neighborNode);
        this.listCout.add(cout);
    }

    /**
     * Permet de supprimer un voisin du node
     * @param nodeToRemove : Node à supprimer
     */
    public void removeNeighbor(Node nodeToRemove)
    {
        if (nodeToRemove == null) throw new IllegalArgumentException("Le node ne peut pas être null");
        if (!this.isNeighbor(nodeToRemove)) throw new IllegalArgumentException("Le node n'est pas un voisin du node courant");

        for (int i = 0; i < this.lstNeighbors.size(); i++)
        {
            if (this.lstNeighbors.get(i) == nodeToRemove)
            {
                this.lstNeighbors.remove(i);
                this.listCout.remove(i);
                break;
            }
        }
    }

    /**
     * Permet de supprimer un voisin du node
     * @param nameNodeToRemove : nom du noeud à supprimer
     */
    public void removeNeighbor(String nameNodeToRemove)
    {
        for (Node n : this.lstNeighbors)
            if (n.getName().equals(nameNodeToRemove)) { this.removeNeighbor(n); break; }
    }

    /**
     * Permet de récupérer la liste des voisins du node
     * @return Liste des voisins
     */
    public List<Node> getNeighbors() { return this.lstNeighbors; }

    /**
     * Permet de définir tout les voisins du node.
     * Attention, cette méthode supprime tous les voisins existants.
     * Cette méthode est utile pour la méthode deserializable
     * @param lstNeighbors
     */
    public void setNeighbors(List<Node> lstNeighbors) { this.lstNeighbors = lstNeighbors; }

    /**
     * Permet de savoir si le node est un voisin du node courant
     * @param node : Node à tester
     * @return True si le node est un voisin, false sinon
     */
    public boolean isNeighbor(Node node)
    {
        if (node == null) throw new IllegalArgumentException("Le node ne peut pas être null");

        return this.lstNeighbors.contains(node);
    }

    /**
     * Permet de récupérer le nom du node
     * @return Nom du node
     */
    public String getName() { return this.name; }

    /**
     * Permet de récupérer le cout entre deux nodes
     * @param nodeDestination
     * @return Cout entre les deux nodes
     */
    public int getCost(Node nodeDestination)
    {
        if (nodeDestination == null) throw new IllegalArgumentException("Le node de destination ne peut pas être null");
        if (!this.isNeighbor(nodeDestination)) throw new IllegalArgumentException("Le node n'est pas un voisin du node courant");

        for(int i = 0; i < this.lstNeighbors.size(); i++)
            if(this.lstNeighbors.get(i) == nodeDestination)
                return this.listCout.get(i);
            
        throw new IllegalArgumentException("Le node n'est pas un voisin du node courant");
    }

    /**
     * Permet de récupérer la liste des couts entre le noeud courant et tous ses voisins
     * @return Liste des couts
     */
    public List<Integer> getCosts() { return this.listCout; }

    /**
     * Permet de récupérer la position x du node
     * @return Position x du node
     */
    public int getX() { return this.x; }

    /**
     * Permet de récupérer la position y du node
     * @return Position y du node
     */
    public int getY() { return this.y; }

    /**
     * Permet de récupérer la largeur du node
     * @return Largeur du node
     */
    public int getWidth() { return this.width; }

    /**
     * Permet de récupérer la hauteur du node
     * @return Hauteur du node
     */
    public int getHeight() { return this.height; }

    /**
     * Permet de modifier la position x du node
     * @param x : int nouvelle position x
     */
    public void setX(int x) { this.x = x; }

    /**
     * Permet de modifier la position y du node
     * @param y : int nouvelle position y
     */
    public void setY(int y) { this.y = y; }

    /**
     * Permet de modifier la position du noeud
     * @param x : nouvelle position x
     * @param y : nouvelle position y
     */
    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    /**
     * Permet de savoir si le node est absorbant
     * @return True si le node est absorbant, false sinon
     */
    public boolean isAbsorbant() { return this.absorbant; }

    /**
     * Permet de définir si le node est absorbant
     * @param absorbant : True si le node est absorbant, false sinon
     */
    public void setAbsorbant(boolean absorbant) { this.absorbant = absorbant; }


    /**
     * Permet de mettre le noeud sous forme textuel (sérialiser) pour l'enregistrer dans un fichier texte
     * @return String noeud sous forme textuel (sérialisée)
     */
    public String serializable()
    {
        String sRet = this.getName() + "(" + this.getX() + "," + this.getY() + "," + this.getWidth() + "," + this.getHeight() + ")";
        for (Node voisin : this.getNeighbors())
            sRet += "," + voisin.getName() + ":" + this.getCost(voisin);

        return sRet;
    }

    /**
     * Permet de récupérer le noeud à partir d'une chaine de caractère (désérialiser)
     * @param serializedNode : String noeud sous forme textuel (noeud sérialisée)
     * @return noeud créé à partir de la chaine de caractère passée en paramètre
     */
    public static Node deserializableNode(String serializedNode)
    {
        String[] ensInfo = (serializedNode.replace("(", ",")).split(",");
        return new Node(ensInfo[0], Integer.parseInt(ensInfo[1]), Integer.parseInt(ensInfo[2]), Integer.parseInt(ensInfo[3]), Integer.parseInt(ensInfo[4]));
    }


    @Override
    public String toString()
    {
        return this.name;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Node)) return false;

        return this.name.equals(((Node) obj).name);
    }
}
