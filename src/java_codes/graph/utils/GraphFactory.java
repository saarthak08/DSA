package java_codes.graph.utils;

import java.util.Scanner;

public class GraphFactory {

    private static final Scanner sc = new Scanner(System.in);

    private static int getNumberOfVertices() {
        int n;
        while (true) {
            System.out.print("Enter the number of vertices: ");
            n = sc.nextInt();
            if (n > 0) {
                return n;
            }
            System.out.println("Number of vertices must be positive. Please try again.");
        }
    }

    private static int getNumberOfEdges() {
        int e;
        while (true) {
            System.out.print("Enter the number of edges: ");
            e = sc.nextInt();
            if (e >= 0) {
                return e;
            } 
            System.out.println("Number of edges cannot be negative. Please try again.");
        }
    }

    private static int getVertex(String vertexType, int n) {
        int v;
        while (true) {
            System.out.print("Enter the " + vertexType + " vertex (0 to " + (n - 1) + "): ");
            v = sc.nextInt();
            if (v >= 0 && v < n) {
                return v;
            }
            System.out.println("Invalid vertex. Please enter a value between 0 and " + (n - 1) + ".");
        }
    }

    private static GraphEdge getEdge(int n) {
        int u, v;
        System.out.println("Enter the two vertices for the edge (0 to " + (n - 1) + ").");
        u = getVertex("first", n);
        v = getVertex("second", n);
        return new GraphEdge(u, v, 0);
    }

    private static GraphEdge getWeightedEdge(int n) {
        int u, v, w;
        System.out.println("Enter the source, destination, and weight for the edge.");
        u = getVertex("source", n);
        v = getVertex("destination", n);
        System.out.print("Enter the weight: ");
        w = sc.nextInt();
        return new GraphEdge(u, v, w);
    }

    public static GraphAdjacencyList createAdjacencyListGraphFromUserInput(boolean isDirected, boolean isWeighted) {
        int n = getNumberOfVertices();
        GraphAdjacencyList graph = new GraphAdjacencyList(n, isDirected, isWeighted);

        int e = getNumberOfEdges();
        System.out.println("Enter " + e + " edges:");

        for (int i = 0; i < e; i++) {
            System.out.println("Edge " + (i + 1) + ":");
            if (isWeighted) {
                GraphEdge edge = getWeightedEdge(n); 
                graph.addEdge(edge.getSrc(), edge.getDest(), edge.getWeight());
            } else {
                GraphEdge edge = getEdge(n);
                graph.addEdge(edge.getSrc(), edge.getDest());
            }
        }
        return graph;
    }
}
