import java.util.LInkedList;

public class Graph {

    private int[] into;
    private int[] outof;
    private Station[] key;
    private LinkedList<Edge> edges;

    //Overloaded Constructor
    public void Graph(Station[] Stations, Station source){
        into = new int[Stations.length];
        outof = new int[Stations.length];
        key = new Station[Stations.length];

        for(int i = 0; i < Stations.length; i++){
            if(Stations[i] == source)
                add_Edges(Stations[i]); 
        }
    }

    //Recursive function to add edges to Graph
    public void add_Edges(Station station){
        edges = station.get_edges();
        while(peek() != Null){
            Edge newEdge = edges.remove();
            
        }
            
    }
}