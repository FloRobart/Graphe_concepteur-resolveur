package metier;

import java.util.ArrayList;
import java.util.List;


public class Node
{
    private String name;
    private List<Node> listVoisin;
    private List<Integer> listCout;

    private int x;
    private int y;

    public Node(String name)
    {
        this.name = name;
        this.listVoisin = new ArrayList<Node>();
    }

    public Node(String name, List<Node> listVoisin, List<Integer> listCout, int x, int y)
    {
        this.name = name;
        this.listVoisin = listVoisin == null ? new ArrayList<Node>   () : listVoisin;
        this.listCout   = listCout   == null ? new ArrayList<Integer>() : listCout;
        this.x = x;
        this.y = y;
    }

    /**
     * Permet d'ajouter un voisin au node
     * @param nodeVoisin : Node voisin
     */
    public void addVoisin(Node nodeVoisin, int cout)
    {
        if (nodeVoisin == null) throw new IllegalArgumentException("Le node ne peut pas être null");

        this.listVoisin.add(nodeVoisin);
        this.listCout.add(cout);
    }

    /**
     * Permet de supprimer un voisin du node
     * @param nodeToRemove : Node à supprimer
     */
    public void removeVoisin(Node nodeToRemove)
    {
        if (nodeToRemove == null) throw new IllegalArgumentException("Le node ne peut pas être null");
        if (!nodeToRemove.isVoisin(this)) throw new IllegalArgumentException("Le node n'est pas un voisin du node courant");

        for (int i = 0; i < this.listVoisin.size(); i++)
        {
            if (this.listVoisin.get(i) == nodeToRemove)
            {
                this.listVoisin.remove(i);
                this.listCout.remove(i);
                break;
            }
        }
    }

    /**
     * Permet de récupérer la liste des voisins du node
     * @return Liste des voisins
     */
    public List<Node> getListVoisin() { return this.listVoisin; }

    /**
     * Permet de savoir si le node est un voisin du node courant
     * @param node : Node à tester
     * @return True si le node est un voisin, false sinon
     */
    public boolean isVoisin(Node node)
    {
        if (node == null) throw new IllegalArgumentException("Le node ne peut pas être null");
        if (node == this) throw new IllegalArgumentException("Le node ne peut pas être le node courant");

        for (Node n : this.listVoisin)
            if (n == node)
                return true;

        return false;
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
    public int getCout(Node nodeDestination)
    {
        if (nodeDestination == null) throw new IllegalArgumentException("Le node de destination ne peut pas être null");
        if (nodeDestination == this) throw new IllegalArgumentException("Le node de destination ne peut pas être le node courant");
        if (!nodeDestination.isVoisin(this)) throw new IllegalArgumentException("Le node n'est pas un voisin du node courant");

        for(int i = 0; i < this.listVoisin.size(); i++)
            if(this.listVoisin.get(i) == nodeDestination)
                return this.listCout.get(i);
            
        throw new IllegalArgumentException("Le node n'est pas un voisin du node courant");
    }

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
     * Permet de mettre le noeud sous forme textuel (sérialiser) pour l'enregistrer dans un fichier texte
     * @return String noeud sous forme textuel (sérialisée)
     */
    public String serializable()
    {
        String sRet = "";

        // TODO : Sérialiser le noeud

        return sRet;
    }

    /**
     * Permet de récupérer le noeud à partir d'une chaine de caractère (désérialiser)
     * @param serializedNode : String noeud sous forme textuel (noeud sérialisée)
     * @return noeud créé à partir de la chaine de caractère passée en paramètre
     */
    public static Node deserializable(String serializedNode)
    {
        Node node = null;

        // TODO : Désérialiser le noeud

        return node;
    }

    @Override
    public String toString()
    {
        String str = this.name + " : [";
        for (Node n : this.listVoisin)
            str += n.getName() + ", ";

        return str.substring(0, str.length() - 2) + "]";
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
