public class Edge //extends Station
{

    waoinfwaionfawoidnawiond
    
    
    private int source;
    private int destination;
    private String line;
    private int duration;
 
    //Overloaded constructor 
    public Edge(int esource, int edest, String eline, int eduration){
        this.source = esource;
        this.destination = edest;
        this.line = eline;
        this.duration = eduration;
    } 

    public int get_source(){
        return this.source;
    }
    
    public int get_destination(){
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
