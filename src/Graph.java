import java.util.LinkedList;

public class Graph {

    private int[] into;
    private int[] outof;
    private Station[] key;
    private Station[] stations;
    private LinkedList<Edge> edges;

    //Overloaded Constructor
    public Graph(Station[] Stations, String source){
        into = new int[Stations.length];
        outof = new int[Stations.length];
        key = new Station[Stations.length];
        stations = Stations;
        Station station = find_source(source);
        add_Edges(station);
    }

    public Station find_source(String source){
        for (Station Station : stations) {
            if (Station.get_name().equals(source)) {
                return Station; 
            }
        }
        return null;
    }
    
    //Recursive function to add edges to Graph
    public void add_Edges(Station station){
        edges = station.get_edges();
        //TODO add station to graph arrays
        while(edges.peek() != null){
            Edge newEdge = edges.remove();
            
            //This is kind of working but it goes down the line then backtracks so prints same station twice
            //Need to not add edge is it is same as parent station
            for (Station Station : stations){
                if (Station.get_name().equals(newEdge.get_destination()) && Station.get_line().equals(newEdge.get_line())){
                    Station.set_duration(newEdge.get_duration());
                    add_Edges(Station);
                    System.out.println(station.toString());
                }
            }  
        }
            
    }
}
