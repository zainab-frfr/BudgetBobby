package budgetbobby.DataStructures;

public class GraphNode {
    private String label;
    private LinkedList<GraphEdge> edges;

    public GraphNode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LinkedList<GraphEdge> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<GraphEdge> edges) {
        this.edges = edges;
    }
}
