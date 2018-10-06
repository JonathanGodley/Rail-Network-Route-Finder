import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;

public class DijkstraUsingMinHeap {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class HeapNode{ // Station.java
        int vertex;
        int distance;
    }
    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i < vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge);

            edge = new Edge(destination, source, weight);
            adjacencylist[destination].addFirst(edge); //for undirected graph
        }

        public void dijkstra_GetMinDistances(int sourceVertex){
            int INFINITY = Integer.MAX_VALUE;
            boolean[] SPT = new boolean[vertices];

            //          //create heapNode for all the vertices
            HeapNode [] heapNodes = new HeapNode[vertices];
            for (int i = 0; i <vertices ; i++) {
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].distance = INFINITY;
            }

            //decrease the distance for the first index
            heapNodes[sourceVertex].distance = 0;

            //add all the vertices to the MinHeap
            MinHeap minHeap = new MinHeap(vertices);
            for (int i = 0; i <vertices ; i++) {
                minHeap.insert(heapNodes[i]);
            }
            //while minHeap is not empty
            while(!minHeap.isEmpty()){
                //extract the min
                HeapNode extractedNode = minHeap.extractMin();

                //extracted vertex
                int extractedVertex = extractedNode.vertex;
                SPT[extractedVertex] = true;

                //iterate through all the adjacent vertices
                LinkedList<Edge> list = adjacencylist[extractedVertex];
                for (int i = 0; i <list.size() ; i++) {
                    Edge edge = list.get(i);
                    int destination = edge.destination;
                    //only if  destination vertex is not present in SPT
                    if(SPT[destination]==false ) {
                        ///check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance
                        int newKey =  heapNodes[extractedVertex].distance + edge.weight ;
                        int currentKey = heapNodes[destination].distance;
                        if(currentKey>newKey){
                            decreaseKey(minHeap, newKey, destination);
                            heapNodes[destination].distance = newKey;
                        }
                    }
                }
            }
            //print SPT
            printDijkstra(heapNodes, sourceVertex);
        }

        public void decreaseKey(MinHeap minHeap, int newKey, int vertex){

            //get the index which distance's needs a decrease;
            int index = minHeap.indexes[vertex];

            //get the node and update its value
            HeapNode node = minHeap.mH[index];
            node.distance = newKey;
            minHeap.bubbleUp(index);
        }

        public void printDijkstra(HeapNode[] resultSet, int sourceVertex){
            System.out.println("Dijkstra Algorithm: (Adjacency List + Min Heap)");
            for (int i = 0; i <vertices ; i++) {
                System.out.println("Source Vertex: " + sourceVertex + " to vertex " +   + i +
                                   " distance: " + resultSet[i].distance);
            }
        }
    }
    static class MinHeap
    {
        int        capacity;
        int        currentSize;
        HeapNode[] mH;
        int[]      indexes; //will be used to decrease the distance


        public MinHeap(int capacity)
        {
            this.capacity = capacity;
            mH = new HeapNode[capacity + 1];
            indexes = new int[capacity];
            mH[0] = new HeapNode();
            mH[0].distance = Integer.MIN_VALUE;
            mH[0].vertex = -1;
            currentSize = 0;
        }

        public void display()
        {
            for (int i = 0; i <= currentSize; i++)
            {
                System.out.println(" " + mH[i].vertex + "   distance   " + mH[i].distance);
            }
            System.out.println("________________________");
        }

        public void insert(HeapNode x)
        {
            currentSize++;
            int idx = currentSize;
            mH[idx] = x;
            indexes[x.vertex] = idx;
            bubbleUp(idx);
        }

        public void bubbleUp(int pos)
        {
            int parentIdx  = pos / 2;
            int currentIdx = pos;
            while (currentIdx > 0 && mH[parentIdx].distance > mH[currentIdx].distance)
            {
                HeapNode currentNode = mH[currentIdx];
                HeapNode parentNode  = mH[parentIdx];

                //swap the positions
                indexes[currentNode.vertex] = parentIdx;
                indexes[parentNode.vertex] = currentIdx;
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx / 2;
            }
        }

        public HeapNode extractMin()
        {
            HeapNode min      = mH[1];
            HeapNode lastNode = mH[currentSize];
            //            update the indexes[] and move the last node to the top
            indexes[lastNode.vertex] = 1;
            mH[1] = lastNode;
            mH[currentSize] = null;
            sinkDown(1);
            currentSize--;
            return min;
        }

        public void sinkDown(int k)
        {
            int smallest      = k;
            int leftChildIdx  = 2 * k;
            int rightChildIdx = 2 * k + 1;
            if (leftChildIdx < heapSize() && mH[smallest].distance > mH[leftChildIdx].distance)
            {
                smallest = leftChildIdx;
            }
            if (rightChildIdx < heapSize() && mH[smallest].distance > mH[rightChildIdx].distance)
            {
                smallest = rightChildIdx;
            }
            if (smallest != k)
            {

                HeapNode smallestNode = mH[smallest];
                HeapNode kNode        = mH[k];

                //swap the positions
                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        public void swap(int a, int b)
        {
            HeapNode temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }

        public boolean isEmpty()
        {
            return currentSize == 0;
        }

        public int heapSize()
        {
            return currentSize;
        }
    }


    public static void main(String[] args)
    {
        int   vertices = 258;
        Graph graph    = new Graph(vertices);

        try
        {

            File inputFile = new File("RailNetwork.xml");

            // create a document builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder        builder = factory.newDocumentBuilder();
            Document               doc     = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            //TODO - Make it check that the root element is Stations, otherwise Formatting Error
            //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Station");

            Station[] Stations = new Station[nList.getLength()];

            // create a list of the stations
            // create a list of the stations
            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);
                // TODO - add verification that the current element is called Station, otherwise formatting error
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;

                    //TODO - Add Formatting Verification, output the rest of the elements - including loops where necessary
                    // TODO - on that note, these two are REQUIRED, station edges aren't necessary as long as formatting is kept.

                    Stations[temp] = new Station(eElement.getElementsByTagName("Name").item(0).getTextContent(),
                                                 eElement.getElementsByTagName("Line").item(0).getTextContent(),
                                                 Integer.MAX_VALUE, temp);

                }
            }

            System.out.println("halfway");


            // second pass to get the edges //TODO make more efficient somehow?
            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);
                // TODO - add verification that the current element is called Station, otherwise formatting error
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;

                    //TODO - Add Formatting Verification, output the rest of the elements - including loops where necessary
                    // TODO - on that note, these two are REQUIRED, station edges aren't necessary as long as formatting is kept.

                    NodeList tnList = eElement.getElementsByTagName("StationEdge");

                    for (int x = 0; x < eElement.getElementsByTagName("StationEdge").getLength(); x++)
                    {

                        Node tNode = tnList.item(x);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element tElement = (Element) tNode;

                            String destination = tElement.getElementsByTagName("Name").item(0).getTextContent();
                            int destinationStation = -1;
                            String line = tElement.getElementsByTagName("Line").item(0).getTextContent();

                            for (int found = 0, i = 0; i < vertices && found < 1; i++)
                            {


                                if (destination.equals(Stations[i].get_name()) && line.equals(Stations[i].get_line()))
                                {
                                    destinationStation = i;
                                    found++;
                                }

                            }

                            graph.addEdge(temp, destinationStation, Integer.parseInt(
                                    tElement.getElementsByTagName("Duration").item(0).getTextContent()));
                        }
                    }
                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);

        }

        graph.dijkstra_GetMinDistances(4);
    }
}