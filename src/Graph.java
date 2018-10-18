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
    private void printSolution(int startVertex, int destinationStation,
                                      HeapNode[] resultSet,
                                      int[] parents)
    {

        int distance = resultSet[destinationStation].getDistance();
        int changes = 0;
        String input = (returnPath(destinationStation, parents));


        String splitInput[] = input.split(",");

        int intArray[] = new int[splitInput.length];
        for (int i = 0; i<splitInput.length;i++)
        {
            intArray[i] = Integer.parseInt(splitInput[i]);
        }

        // check for unwanted line changes at start and destination station
        if (intArray.length != 1)
        {
            // check if first 2 aren't same station,
            if (stations[intArray[0]].get_name().equals(stations[intArray[1]].get_name()))
            {

                // remove duplicate from the array
                int tmpArray[] = new int[splitInput.length - 1];
                for (int i = 0; i < tmpArray.length; i++)
                {
                    tmpArray[i] = intArray[i + 1];
                }

                intArray = tmpArray;
                distance = distance - 15;
            }

            // check if last 2 aren't same station
            if (stations[intArray[intArray.length-1]].get_name().equals(stations[intArray[intArray.length-2]].get_name()))
            {
                // remove duplicate from the array
                int tmpArray[] = new int[splitInput.length - 1];
                for (int i = 0; i < tmpArray.length; i++)
                {
                    tmpArray[i] = intArray[i];
                }

                intArray = tmpArray;
                distance = distance - 15;
            }
        }
        String currentLine = stations[intArray[0]].get_line();

        System.out.print("From "+stations[intArray[0]].get_name()+", take line "+currentLine+" to station ");

        for (int i = 1; i<intArray.length;i++)
        {
            if (intArray[i] != -1)
            {
                if (!currentLine.equals(stations[intArray[i]].get_line()))
                {
                    currentLine = stations[intArray[i]].get_line();
                    System.out.print(stations[intArray[i]].get_name()+";");
                    if (i != intArray.length)
                    {
                        System.out.print("\nthen change to line "+currentLine+", and continue to ");
                        changes++;
                    }
                    else if (i == intArray.length-1)
                    {
                        System.out.print(stations[intArray[i]].get_name()+";");
                    }
                }
                else if (i == intArray.length-1)
                {
                    System.out.print(stations[intArray[i]].get_name()+";");
                }
            }
        }

        if (intArray.length == 1)
        {
            System.out.print(stations[intArray[0]].get_name()+";");
        }

        System.out.print("\nThe total trip will take approximately "+distance+" minutes and will have "+changes+" changes.\n");

    }


    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private String returnPath(int currentVertex,
                                  int[] parents)
    {

        // Base case : Source node has
        // been processed
        if (currentVertex == -1)
        {
            return "";
        }
        return returnPath(parents[currentVertex], parents) + currentVertex + ",";

    }

    public void getShortestTime(int sourceStation, int destinationStation)
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

        //printDijkstra(heapNodes, sourceStation);
        printSolution(sourceStation, destinationStation, heapNodes, parents);

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