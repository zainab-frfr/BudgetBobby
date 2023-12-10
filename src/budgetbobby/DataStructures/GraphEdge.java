package budgetbobby.DataStructures;

public class GraphEdge implements Comparable<GraphEdge> {
    private GraphNode destination;
    private int weight;

    public GraphEdge(GraphNode destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public GraphNode getDestination() {
        return destination;
    }

    public void setDestination(GraphNode destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(GraphEdge o) {
        return  this.weight-o.weight;
    }
}
