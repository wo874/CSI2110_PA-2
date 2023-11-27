import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class MetroReader {
    // Reads the metro information from the given file and constructs the graph
    public static Graph readMetro(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Graph metroGraph = new Graph();

        // Reading the number of vertices and edges
        int numVertices = Integer.parseInt(reader.readLine().trim());
        int numEdges = Integer.parseInt(reader.readLine().trim());

        // Reading and adding vertices to the graph
        for (int i = 0; i < numVertices; i++) {
            String[] vertexInfo = reader.readLine().trim().split(" ");
            int vertexNumber = Integer.parseInt(vertexInfo[0]);
            metroGraph.addVertex(vertexNumber);
        }

        // Reading and adding edges to the graph
        for (int i = 0; i < numEdges; i++) {
            String[] edgeInfo = reader.readLine().trim().split(" ");
            int source = Integer.parseInt(edgeInfo[0]);
            int destination = Integer.parseInt(edgeInfo[1]);
            int weight = Integer.parseInt(edgeInfo[2]);
            metroGraph.addEdge(source, destination, weight);
        }

        reader.close();
        return metroGraph;
    }
}
