package budgetbobby.DataStructures;

public class GraphNode <T extends Comparable<T>> {
    private T label;
    private LinkedList<GraphEdge> edges;

    public GraphNode(T label) {
        this.label = label;
    }

    public T getLabel() {
        return label;
    }

    public void setLabel(T label) {
        this.label = label;
    }

    public LinkedList<GraphEdge> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<GraphEdge> edges) {
        this.edges = edges;
    }
}
