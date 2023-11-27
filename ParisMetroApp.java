import java.io.IOException;

public class ParisMetroApp {
    public static void main(String[] args) {
        try {
            Graph metroGraph = MetroReader.readMetro("metro.txt");
            ParisMetro parisMetro = new ParisMetro(metroGraph);

            if (args.length == 1) {
                int station = Integer.parseInt(args[0]);
                System.out.println("Test -----------------------------------");
                System.out.println("    Input:");
                System.out.println("    N1 = " + station);
                System.out.println("    Output:");
                parisMetro.findStationsInSameLine(station);
                System.out.println("End of Test -----------------------------");
            } else if (args.length == 2) {
                int source = Integer.parseInt(args[0]);
                int destination = Integer.parseInt(args[1]);
                System.out.println("Test -----------------------------------");
                System.out.println("    Input:");
                System.out.println("    N1 = " + source);
                System.out.println("    Output:");
                parisMetro.findShortestPath(source, destination);
                System.out.println("End of Test -----------------------------");
            } else if (args.length == 3) {
                int source = Integer.parseInt(args[0]);
                int destination = Integer.parseInt(args[1]);
                int brokenLineEndpoint = Integer.parseInt(args[2]);
                System.out.println("Test -----------------------------------");
                System.out.println("    Input:");
                System.out.println("    N1 = " + source);
                System.out.println("    Output:");
                parisMetro.findShortestPathWithBrokenLine(source, destination, brokenLineEndpoint);
                System.out.println("End of Test -----------------------------");
            } else {
                System.out.println("Test -----------------------------------");
                System.out.println("    Input:");
                System.out.println("    No specific input for bonus question");
                System.out.println("    Output:");
                parisMetro.findMinBrokenLines();
                System.out.println("End of Test -----------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
