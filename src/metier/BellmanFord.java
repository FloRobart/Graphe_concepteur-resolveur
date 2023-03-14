package metier;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class BellmanFord
{
    private int V, E;
    private int[][] edges;
    private int[] distance;

    public BellmanFord(int[][] edges, int V)
    {
        this.edges = edges;
        this.V = V;
        this.E = edges.length;
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

        // Initialisation des distances et des parents
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distance[source] = 0;

        // Algorithme de Bellman-Ford
        for (int i = 0; i < V - 1; i++)
        {
            for (int j = 0; j < E; j++)
            {
                int u = edges[j][0];
                int v = edges[j][1];
                int weight = edges[j][2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
                {
                    distance[v] = distance[u] + weight;
                    parent[v] = u;
                }
            }
        }

        // Affichage des chemins les plus courts

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

    public boolean bellmanFordOptimized(int[][] edges)
    {
        boolean isOptimized = true;
        for (int i = 0; i < V - 1 && isOptimized; i++)
        {
            isOptimized = false;
            for (int j = 0; j < E; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int weight = edges[j][2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
                {
                    distance[v] = distance[u] + weight;
                    isOptimized = true;
                }
            }
        }
        return !isOptimized;
    }

    public boolean hasAbsorbingCycle(int[][] edges)
    {
        distance = new int[V];
        boolean isOptimized = true;
        for (int i = 0; i < V - 1 && isOptimized; i++)
        {
            isOptimized = false;
            for (int j = 0; j < E; j++)
            {
                int u = edges[j][0];
                int v = edges[j][1];
                int weight = edges[j][2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
                {
                    distance[v] = distance[u] + weight;
                    isOptimized = true;
                }
            }
        }

        for (int j = 0; j < E; j++)
        {
            int u = edges[j][0];
            int v = edges[j][1];
            int weight = edges[j][2];
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
            {
                return true; // un circuit absorbant a été trouvé
            }
        }
        return false; // pas de circuit absorbant
    }

    public static void main(String[] args)
    {
        // Définition du graphe
        int[][] graph = {
            {0, 1, 4},
            {0, 2, 3},
            {1, 2, -2},
            {1, 3, 5},
            {2, 3, 4},
            {2, 4, 1},
            {3, 4, 2},
            {3, 5, 6},
            {4, 5, 3},
            {3, 3, -1}
        };
        int nbNoeud = 6;

        // Création de l'objet BellmanFord
        BellmanFord bellmanFord2 = new BellmanFord(graph, nbNoeud);

        // Recherche des chemins les plus courts à partir du sommet 0
        //bellmanFord.shortestPath(0);
        bellmanFord2.hasAbsorbingCycle(graph);
    }
}