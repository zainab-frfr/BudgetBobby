package budgetbobby.DataStructures;

import java.util.PriorityQueue;

public class Graphs<T extends Comparable<T>> {
    private GraphNode[] vertices;
//    private T[][] adjMatrix;
    private int count;

    public Graphs(int size) {
        vertices = new GraphNode[size];
//        adjMatrix = (T[][]) new Object[size][size];
        count = 0;
    }

    public void addVertex(String L) {
        if (count < vertices.length) {
            GraphNode ver = new GraphNode(L);
            vertices[count] = ver;
            count++;
        }
    }

    public GraphNode findVertex(String L) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].getLabel().compareTo(L) == 0) {
                return vertices[i];
            }
        }
        return null;
    }

    public void addEdge(String a1, String a2, int weight) {

        GraphNode ver1 = findVertex(a1);
        GraphNode ver2 = findVertex(a2);

        if (ver1 != null &&  ver2 != null) {
            GraphEdge ed1 = new GraphEdge(ver2, weight);
            GraphEdge ed2 = new GraphEdge(ver1, weight);
//            adjMatrix[ver1][ver2] = weight;
//            adjMatrix[ver2][ver1] = weight;
            ver1.getEdges().insert(ed1);
            ver2.getEdges().insert(ed2);

        } else {
            System.out.println("vertex doesn't exist");
        }

    }

    public void display() {
        System.out.println("Cites ");
        for (GraphNode vertex : vertices) {
            System.out.print(vertex.getLabel() + " ");
        }

//        for (int i = 0; i < adjMatrix.length; i++) {
//            for (int j = 0; j < adjMatrix[0].length; j++) {
//                System.out.print(adjMatrix[i][j] + " ");
//            }
//            System.out.println();
//
//        }
    }

    public void dikstras_Algorithm(String src){
        PriorityQueue<GraphNode> pq = new PriorityQueue<>();
        int dist[] = new int[vertices.length];
        for(int i = 0; i <  vertices.length; i++){
            if(!vertices[i].getLabel().equals(src)){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean[] visited = new boolean[vertices.length];


    }
}
