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

    public void addEdge(String source, String line, String destination, int duration)
    {
        // need to find matching station,
        int sourceStation = 0;
        int destinationStation = 0;
        int found = 0;


        // loops through stations array, find index of source and destination stations
        for(int i = 0; i < stations.length && found < 2; i++)
        {


            if (source.equals(stations[i].get_name()) && line.equals(stations[i].get_line()))
            {
                sourceStation = i;
                found++;
            }
            else if (destination.equals(stations[i].get_name()) && line.equals(stations[i].get_line()))
            {
                destinationStation = i;
                found++;
            }
            else if (destination.equals(stations[i].get_name()) && source.equals(stations[i].get_name()))
            {
                destinationStation = i;
                sourceStation = i;
                found++;
            }

        }

        // if found both stations
        if (found == 2)
        {
            // link edge to stations
            Edge edge = new Edge(stations[sourceStation], stations[destinationStation], line, duration);
            adjacencylist[sourceStation].addFirst(edge);

            // not needed since input file already has an edge entry for each direction
            //edge = new Edge(stations[destinationStation], stations[sourceStation], line, duration);
            //adjacencylist[destinationStation].addFirst(edge);
        }
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

        // haven't found
        return -1;

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