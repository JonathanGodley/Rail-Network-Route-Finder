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

public void getShortestTime(int sourceStation, int dest)
    {
        // shortest path tree
        boolean[] SPT = new boolean[stations.length];

        // create heap nodes for vertices

        HeapNode[] heapNodes = new HeapNode[stations.length];
        for (int i = 0; i < stations.length; i++)
        {
            heapNodes[i] = new HeapNode();
            heapNodes[i].setStationIndex(i);
            heapNodes[i].setDistance(Integer.MAX_VALUE); // aka infinity
        }

        //decrease the distance for the first index
        heapNodes[sourceStation].setDistance(0);
        
        //parent array to store the shortest path tree
        int[] parents = new int[stations.length];
        parents[0] = -1;

        //add all the vertices to the MinHeap
        MinHeap minHeap = new MinHeap(stations.length);
        for (int i = 0; i < stations.length ; i++) {
            minHeap.insert(heapNodes[i]);
        }
        int j = 1;
        while(!minHeap.isEmpty()){
            //extract the min
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
                    int newKey =  heapNodes[extractedVertex].getDistance() + edge.get_duration() ;
                    int currentKey = heapNodes[destination].getDistance();
                    if(currentKey>newKey){
                        parents[j] = extractedVertex;
                        
                        decreaseKey(minHeap, newKey, destination);
                        heapNodes[destination].setDistance(newKey);
                    }
                   
                }
            } j++;
            
        }
        //Finds all stations corresponding to the destination station
        //Returns the one that has the shortest distance
        String destName = stations[dest].get_name();
        int dist = Integer.MAX_VALUE;
        for (int i = 0; i < stations.length ; i++){
            if(stations[i].get_name().equals(destName)){
                if(heapNodes[i].getDistance() <= dist){
                    dist = heapNodes[i].getDistance();
                    dest = i;
                }               
            }            
        }
        //printDijkstra(heapNodes, sourceStation);
        String line = stations[sourceStation].get_line();
        System.out.print("From " +stations[sourceStation].get_name()+ ", take line " +stations[sourceStation].get_line()+ " to station ");
        printPath(dest, parents, line);
        System.out.print(stations[dest].get_name()+ ". The total trip will take approximately " +dist+ " minutes and will have m changes.");
    }

// Function to print shortest path 
    // from source to currentVertex 
    // using parents array 
    private void printPath(int currentVertex, 
                                  int[] parents, String line) 
    {       
        // Base case : Source node has 
        // been processed 
        if (currentVertex == -1) 
        { 
            return; 
        } 
        if(!line.equals(stations[currentVertex].get_line())){
            System.out.print(stations[currentVertex].get_name()+ ";\n then change to line " +stations[currentVertex].get_line()+ ", and continue to "); 
            line = stations[currentVertex].get_line();
        }
        printPath(parents[currentVertex], parents, line); 
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