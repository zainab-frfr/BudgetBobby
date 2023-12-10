package budgetbobby.DataStructures;

class DistanceAreaPair implements Comparable<DistanceAreaPair> {
    private int distance;
    private String area;

    public DistanceAreaPair(int distance, String area) {
        this.distance = distance;
        this.area = area;
    }

    public int getDistance() {
        return distance;
    }

    public String getArea() {
        return area;
    }

    @Override
    public int compareTo(DistanceAreaPair other) {
        return Integer.compare(this.distance, other.distance);
    }
}