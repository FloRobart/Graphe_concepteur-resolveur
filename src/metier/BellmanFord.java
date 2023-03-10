package metier;

import java.util.*;

public class BellmanFord {
    private List<Node> nodes;
    private List<Integer> distances;
    private List<Node> predecessors;

    public BellmanFord(List<Node> nodes) {
        this.nodes = nodes;
        this.distances = new ArrayList<>(Collections.nCopies(nodes.size(), Integer.MAX_VALUE));
        this.predecessors = new ArrayList<>(Collections.nCopies(nodes.size(), null));
    }

    public void execute(Node source)
    {
        int sourceIndex = nodes.indexOf(source);
        distances.set(sourceIndex, 0);

        for (int i = 1; i < nodes.size(); i++)
        {
            for (Node node : nodes)
            {
                int nodeIndex = nodes.indexOf(node);
                List<Node> neighbors = node.getNeighbors();
                List<Integer> neighborCosts = node.getCosts();

                for (int j = 0; j < neighbors.size(); j++)
                {
                    Node neighbor = neighbors.get(j);
                    int neighborIndex = nodes.indexOf(neighbor);
                    int cost = neighborCosts.get(j);

                    if (distances.get(nodeIndex) != Integer.MAX_VALUE && distances.get(neighborIndex) > distances.get(nodeIndex) + cost)
                    {
                        distances.set(neighborIndex, distances.get(nodeIndex) + cost);
                        predecessors.set(neighborIndex, node);
                    }
                }
            }
        }

        for (Node node : nodes)
        {
            int nodeIndex = nodes.indexOf(node);
            List<Node> neighbors = node.getNeighbors();
            List<Integer> neighborCosts = node.getCosts();

            for (int i = 0; i < neighbors.size(); i++)
            {
                Node neighbor = neighbors.get(i);
                int neighborIndex = nodes.indexOf(neighbor);
                int cost = neighborCosts.get(i);

                if (distances.get(nodeIndex) != Integer.MAX_VALUE && distances.get(neighborIndex) > distances.get(nodeIndex) + cost)
                {
                    throw new RuntimeException("Negative cycle detected");
                }
            }
        }
    }

    public List<Integer> getDistances()
    {
        for (Integer integer : distances)
        {
            System.out.println(integer + ", ");
        }
        return distances;
    }

    public List<Node> getPredecessors()
    {
        for (Node node : predecessors)
        {
            System.out.println(node.getName() + ", ");
        }
        return predecessors;
    }

    public void getDistance(Node destination)
    {
        int destinationIndex = nodes.indexOf(destination);
        System.out.println("Shortest path to node " + destination.getName() + " is " + distances.get(destinationIndex) + ":");
        

        for (int i = 0; i < nodes.size(); i++)
        {
            System.out.println("i = " + i);
            System.out.println("size = " + nodes.size());


            Node currentNode = nodes.get(i);
            List<Node> path = new ArrayList<>();
            path.add(currentNode);

            int cpt = i;
            while (predecessors.get(cpt) != null)
            {
                currentNode = predecessors.get(cpt);
                cpt = nodes.indexOf(currentNode);
                path.add(currentNode);
            }

            Collections.reverse(path);

            if (distances.get(cpt) == Integer.MAX_VALUE)
                System.out.println("No path to node " + nodes.get(destinationIndex).getName());
            else
            {
                System.out.print("Path to node " + nodes.get(destinationIndex).getName() + " via ");
                for (Node node : path)
                    System.out.print(node.getName() + " ");

                System.out.println("has a cost of " + distances.get(destinationIndex));
            }
        }
    }
}

