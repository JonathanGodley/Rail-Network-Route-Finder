import java.util.LinkedList;

public class Station{ 
 
    private String name; 
    private String line; 
    private int duration;
    LinkedList<Edge> edges;
 
    //Overloaded constructor 
    public Station(String sname, String sline){ 
        this.name = sname; 
        this.line = sline; 
        this.duration = 0;
        this.edges = new LinkedList<>();
    } 
 
    public String get_name(){ 
        return this.name; 
    } 
 
    public String get_line(){ 
        return this.line; 
    } 

    public void set_druation(int new_duration){
        this.duration = new_duration;
    }

    public int get_duration(){
        return this.duration;
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