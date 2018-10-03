public class Edge //extends Station
{
 
    private Station source; 
    private Station destination; 
    private String line;
    private int duration;
 
    //Overloaded constructor 
    public Edge(Station esource, Station edestination, String eline, int eduration){ 
        this.source = esource;
        this.destination = edestination;
        this.line = eline;
        this.duration = eduration;
    } 

    public Station get_source(){
        return this.source;
    }
    
    public Station get_destination(){
        return this.destination;
    }

    public String get_line(){
        return this.line;
    }
    
    public int get_duration(){
        return this.duration;
    }

    @Override public String toString()
    {
        return source+" to "+destination+" in "+duration+" = on "+line;
    }

 
}