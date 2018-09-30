public class Edge{ 
 
    private Station source; 
    private Station destination; 
    private int duration;
 
    //Overloaded constructor 
    public Edge(Station esource, Station edestination, int eduration){ 
        this.source = esource;
        this.destination = edestination;
        this.duration = eduration;
    } 

    public Station get_source(){
        return this.source;
    }
    
    public Station get_destination(){
        return this.destination;
    }
    
    public int get_duration(){
        return this.duration;
    }

    @Override public String toString()
    {
        return source+" to "+destination+" in "+duration;
    }

 
}