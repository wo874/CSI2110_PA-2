import java.io.IOException;

public class ParisMetroApp {
    public static void main(String[] args) {
        try {
            Graph metroGraph = MetroReader.readMetro("metro.txt");
            ParisMetro parisMetro = new ParisMetro(metroGraph);

            if (args.length == 1) {
                int station = Integer.parseInt(args[0]);
                parisMetro.findStationsInSameLine(station);
            } else if (args.length == 2) {
                int source = Integer.parseInt(args[0]);
                int destination = Integer.parseInt(args[1]);
                parisMetro.findShortestPath(source, destination);
            } else if (args.length == 3) {
                int source = Integer.parseInt(args[0]);
                int destination = Integer.parseInt(args[1]);
                int brokenLineEndpoint = Integer.parseInt(args[2]);
                parisMetro.findShortestPathWithBrokenLine(source, destination, brokenLineEndpoint);
            } else {
                parisMetro.findMinBrokenLines();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
