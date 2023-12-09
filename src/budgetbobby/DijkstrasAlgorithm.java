//package budgetbobby;
//
//import budgetbobby.DataStructures.GraphEdge;
//import budgetbobby.DataStructures.GraphNode;
//import org.w3c.dom.Node;
//
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.PriorityQueue;
//
//public class DijkstrasAlgorithm <T>{
//
//    public Map<T, Integer> dijkstra(T startLabel) {
//        Map<T, Integer> distances = new HashMap<>();
//        for (GraphNode<T> vertex : vertices) {
//            distances.put(vertex.getLabel(), Integer.MAX_VALUE);
//        }
//
//        PriorityQueue<Node<T>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
//        int startIndex = findVertex(startLabel);
//        distances.put(startLabel, 0);
//        priorityQueue.add(new Node<>(vertices[startIndex], 0));
//
//        while (!priorityQueue.isEmpty()) {
//            Node<T> currentNode = priorityQueue.poll();
//
//            for (GraphEdge<T> edge : currentNode.label.getEdges()) {
//                int newDistance = distances.get(currentNode.label.getLabel()) + (Integer) edge.getWeight();
//
//                if (newDistance < distances.get(edge.getDestination().getLabel())) {
//                    distances.put(edge.getDestination().getLabel(), newDistance);
//                    priorityQueue.add(new Node<>(edge.getDestination(), newDistance));
//                }
//            }
//        }
//
//        return distances;
//    }
//
//}
