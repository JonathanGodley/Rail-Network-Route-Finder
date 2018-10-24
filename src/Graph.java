/*
 * Graph.java
 * Graph class for use with for use with Dijkstra's algorithm using an indirect heap and adjacency list.
 */

import java.util.LinkedList;

public class Graph
{
    private Station[]          stations;
    private LinkedList<Edge>[] adjacencyList;

    //TODO: work out how to fix unchecked warning
    @SuppressWarnings("unchecked")
    public Graph(Station[] stations)
    {
        this.stations = stations;
        adjacencyList = new LinkedList[stations.length];

        for (int i = 0; i < stations.length; i++)
        {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // add a new edge to the graph
    public void addEdge(int source, int destination, int duration)
    {
        Edge edge = new Edge(source, destination, duration);
        adjacencyList[source].addFirst(edge);
    }

    // find the index of a station
    public int findIndex(String stationName)
    {

        for (int i = 0; i < stations.length; i++)
        {
            if (stationName.toLowerCase().equals(stations[i].get_name().toLowerCase()))
            {
                return i;
            }
        }

        //haven't found
        return -1;

    }

    // Print the path to the destination station
    private void printDijkstraTime(int destinationStation, HeapNode[] resultSet, int[] parents)
    {

        int    distance = resultSet[destinationStation].getDistance();
        int    changes  = 0;
        String input    = (returnPath(destinationStation, parents));


        String splitInput[] = input.split(",");

        int intArray[] = new int[splitInput.length];
        for (int i = 0; i < splitInput.length; i++)
        {
            intArray[i] = Integer.parseInt(splitInput[i]);
        }

        // check for unwanted line changes at start and destination station
        if (intArray.length != 1)
        {
            // check if first 2 aren't same station,
            // shouldn't be possible - already checked for @ beginning of algorithm
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
            if (stations[intArray[intArray.length - 1]].get_name()
                                                       .equals(stations[intArray[intArray.length - 2]].get_name()))
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

        System.out.print("From " + stations[intArray[0]].get_name() + ", take line " + currentLine + " to station ");

        for (int i = 1; i < intArray.length; i++)
        {
            if (intArray[i] != -1)
            {

                if (!currentLine.equals(stations[intArray[i]].get_line()))
                {
                    currentLine = stations[intArray[i]].get_line();
                    System.out.print(stations[intArray[i]].get_name() + ";");
                    if (i != intArray.length)
                    {
                        System.out.print("\nthen change to line " + currentLine + ", and continue to ");
                        changes++;
                    }
                    else if (i == intArray.length - 1)
                    {
                        System.out.print(stations[intArray[i]].get_name() + ";");
                    }
                }
                else if (i == intArray.length - 1)
                {
                    System.out.print(stations[intArray[i]].get_name() + ";");
                }
            }
        }

        if (intArray.length == 1)
        {
            System.out.print(stations[intArray[0]].get_name() + ";");
        }

        System.out.print("\nThe total trip will take approximately " + distance + " minutes and will have " + changes +
                         " changes.\n");

    }


    // Print the path to the destination station
    private void printDijkstraChanges(int destinationStation, HeapNode[] resultSet, int[] parents)
    {

        int    distance = resultSet[destinationStation].getDistance();
        int    changes  = 0;
        String input    = (returnPath(destinationStation, parents));


        String splitInput[] = input.split(",");

        int intArray[] = new int[splitInput.length];
        for (int i = 0; i < splitInput.length; i++)
        {
            intArray[i] = Integer.parseInt(splitInput[i]);
        }

        // check for unwanted line changes at start and destination station
        if (intArray.length != 1)
        {
            // check if first 2 aren't same station,
            // shouldn't be possible - already checked for @ beginning of algorithm
            if (stations[intArray[0]].get_name().equals(stations[intArray[1]].get_name()))
            {

                // remove duplicate from the array
                int tmpArray[] = new int[splitInput.length - 1];
                for (int i = 0; i < tmpArray.length; i++)
                {
                    tmpArray[i] = intArray[i + 1];
                }

                intArray = tmpArray;
                distance = distance - 10000;
            }

            // check if last 2 aren't same station
            if (stations[intArray[intArray.length - 1]].get_name()
                                                       .equals(stations[intArray[intArray.length - 2]].get_name()))
            {
                // remove duplicate from the array
                int tmpArray[] = new int[splitInput.length - 1];
                for (int i = 0; i < tmpArray.length; i++)
                {
                    tmpArray[i] = intArray[i];
                }

                intArray = tmpArray;
                distance = distance - 10000;
            }
        }
        String currentLine = stations[intArray[0]].get_line();

        System.out.print("From " + stations[intArray[0]].get_name() + ", take line " + currentLine + " to station ");

        for (int i = 1; i < intArray.length; i++)
        {
            if (intArray[i] != -1)
            {
                if (!currentLine.equals(stations[intArray[i]].get_line()))
                {
                    currentLine = stations[intArray[i]].get_line();
                    System.out.print(stations[intArray[i]].get_name() + ";");
                    if (i != intArray.length)
                    {
                        System.out.print("\nthen change to line " + currentLine + ", and continue to ");
                        changes++;
                    }
                    else if (i == intArray.length - 1)
                    {
                        System.out.print(stations[intArray[i]].get_name() + ";");
                    }
                }
                else if (i == intArray.length - 1)
                {
                    System.out.print(stations[intArray[i]].get_name() + ";");
                }
            }
        }

        if (intArray.length == 1)
        {
            System.out.print(stations[intArray[0]].get_name() + ";");
        }

        // remove excess weighting
        distance = distance - (changes * 10000);
        // add back in normal weighting
        distance = distance + (changes * 15);

        System.out.print("\nThe total trip will have " + changes + " changes and will take approximately " + distance +
                         " minutes.\n");

    }


    // recursive function to get the path to a vertex
    private String returnPath(int currentVertex, int[] parents)
    {
        if (currentVertex == -1)
        {
            return "";
        }
        return returnPath(parents[currentVertex], parents) + currentVertex + ",";
    }

    // This function will employ dijkstra's algorithm to find the route to the destination station that takes the shortest amount of time.
    // this version of the function prioritises speed, but as line changes are expensive, will generally choose the path with the least changes.
    public void getShortestTime(int sourceStation, int destinationStation)
    {
        // make algorithm more efficient by checking if we can start on the same line as the destination.
        if (!stations[sourceStation].get_line().equals(stations[destinationStation].get_line()))
        {
            for (Edge e : adjacencyList[sourceStation])
            {
                if (e.get_duration() == 15 &
                    stations[e.get_destination()].get_name().equals(stations[sourceStation].get_name()) &&
                    stations[e.get_destination()].get_line().equals(stations[destinationStation].get_line()))
                {
                    sourceStation = e.get_destination();
                }
            }
        }

        // shortest path tree
        boolean[] SPT = new boolean[stations.length];

        LinkedList<Integer> destinationStations = new LinkedList<>();

        // create heap nodes for vertices
        HeapNode[] heapNodes = new HeapNode[stations.length];
        for (int i = 0; i < stations.length; i++)
        {
            heapNodes[i] = new HeapNode();
            heapNodes[i].setStationIndex(i);
            heapNodes[i].setDistance(Integer.MAX_VALUE); // aka infinity
            if (stations[i].get_name().equals(stations[destinationStation].get_name()))
            {
                destinationStations.add(i);
            }
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
        for (int i = 0; i < stations.length; i++)
        {
            minHeap.insert(heapNodes[i]);
        }

        boolean found = false;

        while (!minHeap.isEmpty() && !found)
        {
            //extract the min
            //int nearestVertex = -1;
            HeapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedNode.getStationIndex();
            SPT[extractedVertex] = true;

            // check to see if we've found our destination
            if (destinationStations.contains(extractedVertex))
            {
                found = true;
                destinationStation = extractedVertex;
            }

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = adjacencyList[extractedVertex];
            for (int i = 0; i < list.size(); i++)
            {
                Edge edge        = list.get(i);
                int  destination = edge.get_destination();
                //only if  destination vertex is not present in SPT
                if (SPT[destination] == false)
                {
                    ///check if distance needs an update or not
                    //means check total weight from source to vertex_V is less than
                    //the current distance value, if yes then update the distance
                    int newKey     = heapNodes[extractedVertex].getDistance() + edge.get_duration();
                    int currentKey = heapNodes[destination].getDistance();
                    if (currentKey > newKey)
                    {
                        decreaseKey(minHeap, newKey, destination);
                        parents[destination] = extractedVertex; // not sure if correct
                        heapNodes[destination].setDistance(newKey);
                    }
                }
            }
        }
        printDijkstraTime(destinationStation, heapNodes, parents);
    }

    // This function will employ dijkstra's algorithm to find the route to the destination station that takes the least number of line changes.
    // Thanks to the nature of the algorithm, if multiple routes exist with the same number of line changes, the program will then choose the route
    // with the shortest travel time.
    public void getLeastChanges(int sourceStation, int destinationStation)
    {
        // Artificially inflate the weight of all line changes so they become an act of last resort
        for (int i = 0; i < adjacencyList.length; i++)
        {
            for (int x = 0; x < adjacencyList[i].size(); x++)
            {
                if (stations[adjacencyList[i].get(x).get_source()].get_name().equals(stations[adjacencyList[i].get(x)
                                                                                                              .get_destination()]
                                                                                             .get_name()))
                {
                    adjacencyList[i].get(x).set_duration(10000);
                }
            }
        }

        // make algorithm more efficient by checking if we can start on the same line as the destination.
        if (!stations[sourceStation].get_line().equals(stations[destinationStation].get_line()))
        {
            for (Edge e : adjacencyList[sourceStation])
            {
                if (e.get_duration() == 10000 &
                    stations[e.get_destination()].get_name().equals(stations[sourceStation].get_name()) &&
                    stations[e.get_destination()].get_line().equals(stations[destinationStation].get_line()))
                {
                    sourceStation = e.get_destination();
                }
            }
        }


        // shortest path tree
        boolean[] SPT = new boolean[stations.length];

        LinkedList<Integer> destinationStations = new LinkedList<>();

        // create heap nodes for vertices
        HeapNode[] heapNodes = new HeapNode[stations.length];
        for (int i = 0; i < stations.length; i++)
        {
            heapNodes[i] = new HeapNode();
            heapNodes[i].setStationIndex(i);
            heapNodes[i].setDistance(Integer.MAX_VALUE); // aka infinity
            if (stations[i].get_name().equals(stations[destinationStation].get_name()))
            {
                destinationStations.add(i);
            }
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
        for (int i = 0; i < stations.length; i++)
        {
            minHeap.insert(heapNodes[i]);
        }

        boolean found = false;

        while (!minHeap.isEmpty() && !found)
        {
            //extract the min
            //int nearestVertex = -1;
            HeapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedNode.getStationIndex();
            SPT[extractedVertex] = true;

            // check to see if we've found our destination
            if (destinationStations.contains(extractedVertex))
            {
                found = true;
                destinationStation = extractedVertex;

            }

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = adjacencyList[extractedVertex];
            for (int i = 0; i < list.size(); i++)
            {
                Edge edge        = list.get(i);
                int  destination = edge.get_destination();
                //only if  destination vertex is not present in SPT
                if (SPT[destination] == false)
                {
                    ///check if distance needs an update or not
                    //means check total weight from source to vertex_V is less than
                    //the current distance value, if yes then update the distance
                    int newKey     = heapNodes[extractedVertex].getDistance() + edge.get_duration();
                    int currentKey = heapNodes[destination].getDistance();
                    if (currentKey > newKey)
                    {
                        decreaseKey(minHeap, newKey, destination);
                        parents[destination] = extractedVertex;
                        heapNodes[destination].setDistance(newKey);
                    }
                }
            }
        }

        printDijkstraChanges(destinationStation, heapNodes, parents);

    }

    // organise the min heap
    public void decreaseKey(MinHeap minHeap, int newKey, int vertex)
    {

        //get the index which distance's needs a decrease;
        int index = minHeap.indexes[vertex];

        //get the node and update its value
        HeapNode node = minHeap.getMinHeap()[index];
        node.setDistance(newKey);
        minHeap.bubbleUp(index);
    }
}