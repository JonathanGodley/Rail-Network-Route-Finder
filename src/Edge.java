public class Edge{ 
 
    private Station source; 
    private String destination; 
    private String line;
    private int duration;
 
    //Overloaded constructor 
    public Edge(Station esource, String eline, String edest, int eduration){ 
        this.source = esource;
        this.destination = edest;
        this.line = eline;
        this.duration = eduration;
    } 

    public Station get_source(){
        return this.source;
    }
    
    public String get_destination(){
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
        StringBuffer sBuffer = new StringBuffer(source+" to "+destination+" in "+duration+" on "+line);
        return sBuffer.toString();
    }

 
}
