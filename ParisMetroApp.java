import java.io.IOException;

public class ParisMetroApp {
    public static void main(String[] args) {
        try {
            Graph metroGraph = MetroReader.readMetro("metro.txt");
            ParisMetro parisMetro = new ParisMetro(metroGraph);

            if (args.length == 1) {
                // Question 2-i: Identify all stations belonging to the same line of a given station
                int station = Integer.parseInt(args[0]);
                parisMetro.findStationsInSameLine(station);
            } else if (args.length == 2) {
                // Question 2-ii: Find the shortest path between two stations
                int source = Integer.parseInt(args[0]);
                int destination = Integer.parseInt(args[1]);
                parisMetro.findShortestPath(source, destination);
            } else if (args.length == 3) {
                // Question 2-iii: Find the shortest path with a broken line
                int source = Integer.parseInt(args[0]);
                int destination = Integer.parseInt(args[1]);
                int brokenLineEndpoint = Integer.parseInt(args[2]);
                parisMetro.findShortestPathWithBrokenLine(source, destination, brokenLineEndpoint);
            } else {
                // Bonus Question iv: Determine the minimum number of broken lines
                parisMetro.findMinBrokenLines();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
