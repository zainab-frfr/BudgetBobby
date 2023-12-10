package budgetbobby.DataStructures;

class Vertex{
    String label;
    int distance;
    boolean visited;

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                '}';
    }

    public String getLabel() {
        return label;
    }

    public Vertex(String lab) {
        label = lab;
        distance = 0;
        visited = false;
    }
}