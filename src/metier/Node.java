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

    private boolean obselet;
    private Node shortNeighborNode;


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

        this.obselet = false;
        this.shortNeighborNode = null;
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

        this.obselet = false;
        this.shortNeighborNode = null;
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

        this.obselet = false;
        this.shortNeighborNode = null;
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
     * Permet de convertir le nom du node en int
     * @return Nom du node converti en int
     */
    public int getNameInInt()
    {
        int result = 0;
        for (int i = 0; i < name.length(); i++)
            result = result * 26 + (int)(name.charAt(i) - 'A' + 1); // Ajouter la valeur numérique à la somme

        return result - 1; // Soustraire 1 car les noms auto-incrémentés commencent à 1, pas à 0
    }

    /**
     * Permet de récupéré un noeud avec son numéro (qui est en fait son nom convertie en int avec la méthode getNameInInt())
     * @param name : numéro du noeud
     * @return Noeud
     */
    public Node getNodeWithIntName(int name)
    {
        Node nodeRet = null;




        return nodeRet;
    }

    /**
     * Permet d'avoir le nom d'un noeud à partir de son numéro
     * @param num : numéro du noeud
     * @return Nom du noeud
     */
    public static String convertIntToName(int num)
    {
        StringBuilder sb = new StringBuilder();
        num++; // Ajouter 1 car les noms auto-incrémentés commencent à 1, pas à 0
        while (num > 0)
        {
            int rem = num % 26;
            if (rem == 0)
            { // si le reste est 0, alors la lettre doit être 'Z'
                sb.append('Z');
                num = (num / 26) - 1;
            }
            else
            { // sinon, ajouter la lettre correspondante
                sb.append((char) (rem - 1 + 'A'));
                num /= 26;
            }
        }

        return sb.reverse().toString(); // retourner le nom inversé
    }

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
     * Permet de récupérer le noeud par le quel il faut passer pour aller le plus vite au noeud de destination (qui n'est pas connu par le node courant)
     */
    public Node getShortNeighborNode() { return this.shortNeighborNode; }

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
     * Permet de savoir si le node est obselet
     * @return True si le node est obselet, false sinon
     */
    public boolean isObselet() { return this.obselet; }

    /**
     * Permet de définir si le node est obselet
     * @param obselet : True si le node est obselet, false sinon
     */
    public void setObselet(boolean obselet) { this.obselet = obselet; }

    /**
     * Permet de modifier le noeud par le quel il faut passer pour aller le plus vite au noeud de destination (qui n'est pas connu par le node courant)
     * @param shortNeighborNode : Noeud par le quel il faut passer pour aller le plus vite au noeud de destination (qui n'est pas connu par le node courant)
     */
    public void setShortNeighborNode(Node shortNeighborNode) { this.shortNeighborNode = shortNeighborNode; }


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
