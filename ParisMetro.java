import java.util.*;

class ParisMetro {
    private Graph metroGraph;
    private static final int WALKING_TIME = 90;

    public ParisMetro(Graph metroGraph) {
        this.metroGraph = metroGraph;
    }

    // Identify all stations belonging to the same line of a given station
    public void findStationsInSameLine(int station) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("Stations in the same line as " + station + ": ");
        dfsFindLineStations(station, visited);
        System.out.println();
    }

    // Helper method for DFS to find stations in the same line
    private void dfsFindLineStations(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (Graph.Edge neighbor : metroGraph.getNeighbors(current)) {
            if (neighbor.weight >= 0 && !visited.contains(neighbor.destination)) {
                dfsFindLineStations(neighbor.destination, visited);
            }
        }
    }

    // Find the shortest path between two stations and print the path and total travel time
    public void findShortestPath(int source, int destination) {
        Map<Integer, Integer> distance = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        distance.put(source, 0);
        minHeap.add(source);

        while (!minHeap.isEmpty()) {
            int current = minHeap.poll();

            for (Graph.Edge neighbor : metroGraph.getNeighbors(current)) {
                int newDistance = distance.get(current) + neighbor.weight;
                if (!distance.containsKey(neighbor.destination) || newDistance < distance.get(neighbor.destination)) {
                    distance.put(neighbor.destination, newDistance);
                    parent.put(neighbor.destination, current);
                    minHeap.add(neighbor.destination);
                }
            }
        }

        printPath(source, destination, parent);
        System.out.println("Total travel time: " + distance.get(destination) + " seconds");
    }

    // Find the shortest path between two stations when a line is not functioning
    public void findShortestPathWithBrokenLine(int source, int destination, int brokenLineEndpoint) {
        Graph.Edge brokenLineEdge = new Graph.Edge(brokenLineEndpoint, WALKING_TIME);
        List<Graph.Edge> removedEdges = metroGraph.getNeighbors(brokenLineEndpoint);
        metroGraph.getNeighbors(brokenLineEndpoint).clear();
        findShortestPath(source, destination);
        metroGraph.getNeighbors(brokenLineEndpoint).addAll(removedEdges);
    }

    // Bonus Question iv: Determine the minimum number of broken lines
    public void findMinBrokenLines() {
        Graph lineGraph = new Graph();

        for (int vertex : metroGraph.getNeighbors().keySet()) {
            lineGraph.addVertex(vertex);
        }

        for (List<Graph.Edge> edges : metroGraph.getNeighbors().values()) {
            for (Graph.Edge edge : edges) {
                if (edge.weight == WALKING_TIME) {
                    lineGraph.addEdge(edge.destination, edge.destination, 0);
                }
            }
        }

        Set<Integer> visitedLines = new HashSet<>();
        List<Integer> connectedComponents = new ArrayList<>();

        for (int line : lineGraph.getNeighbors().keySet()) {
            if (!visitedLines.contains(line)) {
                List<Integer> currentComponent = new ArrayList<>();
                dfs(lineGraph, line, visitedLines, currentComponent);
                connectedComponents.add(line);
            }
        }

        System.out.println("Connected components in the line graph:");
        for (int i = 0; i < connectedComponents.size(); i++) {
            System.out.println("Component " + (i + 1) + ": " + connectedComponents.get(i));
        }

        int minBrokenLines = connectedComponents.size() - 1;
        System.out.println("Minimum number of broken lines required: " + minBrokenLines);
    }

    // Depth-First Search (DFS) to find connected components in the line graph
    private void dfs(Graph lineGraph, int current, Set<Integer> visited, List<Integer> component) {
        visited.add(current);
        component.add(current);

        for (Graph.Edge neighbor : lineGraph.getNeighbors(current)) {
            if (!visited.contains(neighbor.destination)) {
                dfs(lineGraph, neighbor.destination, visited, component);
            }
        }
    }

    // Prints the path from source to destination using the parent map
    private void printPath(int source, int destination, Map<Integer, Integer> parent) {
        System.out.print("Shortest path from " + source + " to " + destination + ": ");
        Stack<Integer> path = new Stack<>();
        int current = destination;
        while (current != source) {
            path.push(current);
            current = parent.get(current);
        }
        path.push(source);

        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
        System.out.println();
    }
}
