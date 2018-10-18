/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;

public class Graph {

    private Station[] stations;
    private LinkedList<Edge>[] adjacencylist;

    //Overloaded Constructor
    public Graph(Station[] stations)
    {
        this.stations = stations;
        adjacencylist = new LinkedList[stations.length];

        for(int i = 0; i < stations.length; i++)
        {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, int duration)
    {
        Edge edge = new Edge(source, destination, duration);
        adjacencylist[source].addFirst(edge);
    }

    public int findIndex(String stationName)
    {

        for(int i = 0; i < stations.length; i++)
        {
            if (stationName.equals(stations[i].get_name()))
            {
                return i;
            }
        }

       //haven't found
        return -1;

    }

    public void printDijkstra(HeapNode[] resultSet, int sourceVertex){
       System.out.println("Dijkstra Algorithm: (Adjacency List + Min Heap)");
        for (int i = 0; i < stations.length ; i++) {

                System.out.println("Source Station: " + stations[sourceVertex].get_name() + " to station " +
                                   stations[i].get_name() + " on line: "+stations[i].get_line()+" distance: " + resultSet[i].getDistance());

        }
    }

    // A utility function to print
    // the constructed distances
    // array and shortest paths
    private static void printSolution(int startVertex,
                                      HeapNode[] resultSet,
                                      int[] parents)
    {
        int nVertices = resultSet.length;
        System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++)
        {
            if (vertexIndex != startVertex)
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(resultSet[vertexIndex].getDistance() + "\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }

    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private static void printPath(int currentVertex,
                                  int[] parents)
    {

        // Base case : Source node has
        // been processed
        if (currentVertex == -1)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }

    public void getShortestTime(int sourceStation)
    {
        //TODO - This is currently just a find all short paths algorithm, and i'm pretty sure i've fucked it up since theres a few weird bugs happening.
        //TODO - the error is either in this file somewhere, or the MinHeap.java file. idk.

        // shortest path tree
        boolean[] SPT = new boolean[stations.length];

        // create heap nodes for vertices
        HeapNode[] heapNodes = new HeapNode[stations.length];
        for (int i = 0; i < stations.length; i++)
        {
            heapNodes[i] = new HeapNode();
            heapNodes[i].setStationIndex(i);
            heapNodes[i].setDistance(Integer.MAX_VALUE); // aka infinity
            //SPT[i] = false; - not sure if necessary - SPT[] should init as false
        }

        //decrease the distance for the first index
        heapNodes[sourceStation].setDistance(0);

        // Parent array to store shortest
        // path tree
        int[] parents = new int[stations.length];

        // The starting vertex does not
        // have a parent
        parents[sourceStation] = -1;

        //add all the vertices to the MinHeap
        MinHeap minHeap = new MinHeap(stations.length);
        for (int i = 0; i < stations.length ; i++) {
            minHeap.insert(heapNodes[i]);
        }

        while(!minHeap.isEmpty()){
            //extract the min
            //int nearestVertex = -1;
            HeapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedNode.getStationIndex();
            SPT[extractedVertex] = true;

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = adjacencylist[extractedVertex];
            for (int i = 0; i <list.size() ; i++) {
                Edge edge = list.get(i);
                int destination = edge.get_destination();
                //only if  destination vertex is not present in SPT
                if(SPT[destination]==false ) {
                    ///check if distance needs an update or not
                    //means check total weight from source to vertex_V is less than
                    //the current distance value, if yes then update the distance
                    int newKey =  heapNodes[extractedVertex].getDistance() + edge.get_duration();
                    int currentKey = heapNodes[destination].getDistance();
                    if(currentKey>newKey){
                        decreaseKey(minHeap, newKey, destination);
                        parents[destination] = extractedVertex; // not sure if correct
                        heapNodes[destination].setDistance(newKey);
                    }
                }
            }
        }

        printDijkstra(heapNodes, sourceStation);
        printSolution(sourceStation, heapNodes, parents);

    }

    public void decreaseKey(MinHeap minHeap, int newKey, int vertex)
    {

        //get the index which distance's needs a decrease;
        int index = minHeap.indexes[vertex];

        //get the node and update its value
        HeapNode node = minHeap.getMinHeap()[index];
        node.setDistance(newKey);
        minHeap.bubbleUp(index);
    }

    @Override public String toString()
    {
        // count number of connections.
        int length = 0;
        for(int i = 0; i < adjacencylist.length; i++)
        {
            length += adjacencylist[i].size();
        }

        return stations.length+" stations, with "+length+" connections";
    }
}