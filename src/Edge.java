public class Edge{ 
 
    private String source; 
    private String line;
    private String destination; 
    private int duration;
 
    //Overloaded constructor 
    public Edge(String esource, String eline, String edestination, int eduration){ 
        this.source = esource; 
        this.line = eline; 
        this.destination = edestination;
        this.duration = eduration;
    } 
 
    public String get_source(){
        return this.source;
    } 
 
    public String get_line(){ 
        return this.line; 
    } 

    public String get_destination(){
        return this.destination;
    }
    
    public int get_duration(){
        return this.duration;
    }

    @Override public String toString()
    {
        return source+" to "+destination+" in "+duration+ " on line "+line;
    }

 
}