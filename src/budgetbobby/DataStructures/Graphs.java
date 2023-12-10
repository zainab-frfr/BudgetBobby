package budgetbobby.DataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Graphs{
    Vertex[] vertexList; // array of vertices
    int[][] adjMat; // adjacency matrix
    int count;


    public Graphs(int s){
        vertexList=new Vertex[s];
        adjMat = new int[s][s];
        count = 0;
    }

    public void addVertex(String L) {
        Vertex v = new Vertex(L);
        if(count < vertexList.length) {
            vertexList[count] = v;
            count++;
        }


    }
    public void addEdge(String L1, String L2, int weight) {
        int p1 = 0; int p2 = 0; int countx = 0;
        for(int i = 0; i < vertexList.length; i++){
            if(vertexList[i].getLabel().equals(L1)){
                p1 = i; countx++;
            }
            else if (vertexList[i].getLabel().equals(L2)){
                p2 = i;  countx++;
            }

            if(count==2) break;
        }

        if(countx==2) {
            adjMat[p1][p2] = weight;
            adjMat[p2][p1] = weight;
        }
    }
    public void display() {

        for(int i = 0; i < count; i++){
            System.out.print(vertexList[i] + " ");
        }

        System.out.println();
        for(int i = 0; i < adjMat.length; i ++){
            for(int j = 0; j < adjMat.length; j++){
                System.out.print( adjMat[i][j] + " ");
            }
            System.out.println();
        }


    }

    public int findVertex(String L) {
        for (int i = 0; i < vertexList.length; i++) {
            if (vertexList[i].getLabel().compareTo(L) == 0) {
                return i;
            }
        }
        return -1;
    }

    public String[] shortestPath(int src){
        String restaurant;

        switch (src) {
            case 0:
                restaurant = "Bahadurabad";
                break;
            case 1:
                restaurant = "Clifton";
                break;
            case 2:
                restaurant = "Sindhi Muslim";
                break;
            case 3:
                restaurant = "PECHS";
                break;
            case 4:
                restaurant = "Gulshan";
                break;
            default:
                restaurant = "";
                break;
        }
        //boolean visited[] = new boolean[adjMat.length];
//        int distance[] = new int[adjMat.length];

        for(int i = 0; i <adjMat.length; i++){
            vertexList[i].distance =  Integer.MAX_VALUE;
        }


        int idx = findVertex(restaurant);
        vertexList[idx].distance = 0;

        for(int i = 0; i < adjMat.length; i++){
            int minVertex = findMinVertex();
            vertexList[minVertex].visited = true;
            for(int j = 0; j < adjMat.length; j++){
                if(adjMat[minVertex][j]!=0 && !vertexList[j].visited && vertexList[minVertex].distance!=Integer.MAX_VALUE){
                    int newDist = vertexList[minVertex].distance + adjMat[minVertex][j];
                    if(newDist < vertexList[j].distance){
                        vertexList[j].distance = newDist;
                    }
                }
            }
        }


//
//        for(int i  = 0; i <adjMat.length; i++){
//            System.out.println(restaurant +  " is " + vertexList[i].distance + " km from " + vertexList[i].getLabel());
//        }



        List<DistanceAreaPair> distanceAreaPairs = new ArrayList<>();

        for (int i = 0; i < adjMat.length; i++) {
            distanceAreaPairs.add(new DistanceAreaPair(vertexList[i].distance, vertexList[i].getLabel()));
        }

        // Sort the list based on distances
        Collections.sort(distanceAreaPairs);


        String[] areaRestaurant = new String[5];
        // Print the sorted list
        for (int i = 0; i < distanceAreaPairs.size(); i++) {
            DistanceAreaPair pair = distanceAreaPairs.get(i);
            System.out.println(restaurant + " is " + pair.getDistance() + " km from " + pair.getArea());
            areaRestaurant[i] = pair.getArea();
        }

       // System.out.println(Arrays.toString(areaRestaurant));
        return areaRestaurant;
    }

    public  int  findMinVertex(){
        int minVertex = -1;
        for(int i = 0; i < vertexList.length; i++){
            if(!vertexList[i].visited && (minVertex==-1 || vertexList[i].distance < vertexList[minVertex].distance)){
                minVertex = i;
            }
        }

        return  minVertex;
    }


}