package budgetbobby.DataStructures;

public class Graphs<T extends Comparable<T>> {
    private GraphNode[] vertices;
    private T[][] adjMatrix;
    private int count;

    public Graphs(int size) {
        vertices = new GraphNode[size];
        adjMatrix = (T[][]) new Object[size][size];
        count = 0;
    }

    public void addVertex(String L) {
        if (count < vertices.length) {
            GraphNode ver = new GraphNode(L);
            vertices[count] = ver;
            count++;
        }
    }

    public int findVertex(T L) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].getLabel().compareTo(L) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(T a1, T a2, T weight) {

        int ver1 = findVertex(a1);
        int ver2 = findVertex(a2);

        if (ver1 != -1 || ver2 != -1) {
            GraphEdge ed1 = new GraphEdge(vertices[ver2], weight);
            GraphEdge ed2 = new GraphEdge(vertices[ver2], weight);
            adjMatrix[ver1][ver2] = weight;
            adjMatrix[ver2][ver1] = weight;
            vertices[ver1].getEdges().insert(ed1);
            vertices[ver2].getEdges().insert(ed2);

        } else {
            System.out.println("vertex doesn't exist");
        }

    }

    public void display() {
        System.out.println("Cites ");
        for (GraphNode vertex : vertices) {
            System.out.print(vertex.getLabel() + " ");
        }

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[0].length; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();

        }
    }
}
