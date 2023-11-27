import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

class Graph {
    private Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList=new HashMap<>();
    }

    // adds vertex to graph
    public void addVertex(int vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    // add directed edge inbetween two vertices w/  given weight
    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
    }

    // returns vertex neighbors 
    public List<Edge> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }

    // inner class that represent edge
    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {

            this.destination=destination;
            this.weight=weight;
        }
    }
}
