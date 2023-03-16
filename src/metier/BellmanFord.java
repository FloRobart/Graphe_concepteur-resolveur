package metier;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class BellmanFord
{
    private int V, E;
    private int[][] matrice;
    private int[] distance;

    public BellmanFord(int[][] matriceAdjascence, int V)
    {
        this.matrice = matriceAdjascence;
        this.V = V;
        this.E = matrice.length;
    }

    /**
     * Affiche le chemin le plus court entre source et dest
     * @param source Noeud de départ
     * @param dest Noeud d'arrivée
     * @return Liste contenant le chemin le plus court entre source et dest ainsi que la distance
     *        Si le chemin n'existe pas, retourne null
     * lstRet.get(0) = distance
     * lstRet.get(1) = source
     * lstRet.get(lstRet.size() - 1) = dest
     */
    public List<Integer> shortestPath(int source, int dest)
    {
        List<Integer> lstRet = null;
        int[] distance = new int[V];
        int[] parent = new int[V];

        /* Initialisation des distances et des parents */
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distance[source] = 0;

        /* Algorithme de Bellman-Ford */
        for (int i = 0; i < V - 1; i++)
        {
            for (int j = 0; j < E; j++)
            {
                int u = matrice[j][0];
                int v = matrice[j][1];
                int weight = matrice[j][2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
                {
                    distance[v] = distance[u] + weight;
                    parent[v] = u;
                }
            }
        }

        /* Remplissage de la liste à return */
        if (distance[dest] != Integer.MAX_VALUE)
        {
            lstRet = new ArrayList<>();
            int current = dest;
            lstRet.add(current);
            while (current != source)
            {
                current = parent[current];
                lstRet.add(current);
            }

            lstRet.add(distance[dest]);
            Collections.reverse(lstRet);
        }

        return lstRet;
    }

    /**
     * ???
     * @param matrice Matrice d'adjascence du graphe
     * @return ???
     */
    public boolean bellmanFordOptimized()
    {
        boolean isOptimized = true;
        for (int i = 0; i < V - 1 && isOptimized; i++)
        {
            isOptimized = false;
            for (int j = 0; j < E; j++) {
                int u = matrice[j][0];
                int v = matrice[j][1];
                int weight = matrice[j][2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
                {
                    distance[v] = distance[u] + weight;
                    isOptimized = true;
                }
            }
        }
        return !isOptimized;
    }

    /**
     * Permet de savoir si le graphe contient un cycle absorbant
     * @param matrice Matrice d'adjascence du graphe
     * @return true si le graphe contient un cycle absorbant, false sinon
     */
    public boolean hasAbsorbingCycle()
    {
        distance = new int[V];
        boolean isOptimized = true;
        for (int i = 0; i < V - 1 && isOptimized; i++)
        {
            isOptimized = false;
            for (int j = 0; j < E; j++)
            {
                int u = matrice[j][0];
                int v = matrice[j][1];
                int weight = matrice[j][2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
                {
                    distance[v] = distance[u] + weight;
                    isOptimized = true;
                }
            }
        }

        for (int j = 0; j < E; j++)
        {
            int u = matrice[j][0];
            int v = matrice[j][1];
            int weight = matrice[j][2];
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
            {
                return true; // un circuit absorbant a été trouvé
            }
        }
        return false; // pas de circuit absorbant
    }
}