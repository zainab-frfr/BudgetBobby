package budgetbobby.DataStructures;

public class GraphEdge <T> {
    private GraphNode destination;
    private T weight;

    public GraphEdge(GraphNode destination, T weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public GraphNode getDestination() {
        return destination;
    }

    public void setDestination(GraphNode destination) {
        this.destination = destination;
    }

    public T getWeight() {
        return weight;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }
}
