import java.util.LinkedList;

public class Station{ 
 
    private String name; 
    private String line; 
    private int durationFromParent;
    LinkedList<Edge> edges = new LinkedList<>();
 
    //Overloaded constructor 
    public Station(String sname, String sline){ 
        this.name = sname; 
        this.line = sline; 
        this.durationFromParent = 0;
    } 
 
    public String get_name(){ 
        return this.name; 
    } 
 
    public String get_line(){ 
        return this.line; 
    } 

    public void set_duration(int duration){
        this.durationFromParent = duration;
    }

    public int get_duration(){
        return this.durationFromParent;
    }

    public void add_edge(Edge edge){
        edges.add(edge);
    }

    public LinkedList<Edge> get_edges(){
        return this.edges;
    }

    @Override public String toString()
    {
        String out = "Station: "+name+" on line: "+line+ "\n";

        for (Edge var : edges)
        {
            out += "\t*"+var.toString()+"\n";
        }

        return out;
    }
 
}