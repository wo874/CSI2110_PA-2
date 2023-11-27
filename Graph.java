import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph {
    private Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Adds a vertex to the graph
    public void addVertex(int vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    // Adds a directed edge between two vertices with a given weight
    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
    }

    // Returns neighbors of a vertex
    public List<Edge> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }

    // Inner class to represent an edge
    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}
