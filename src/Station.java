import java.util.LinkedList;

public class Station{ 
 
    private String name; 
    private String line; 
    private int distance;

    //Overloaded constructor 
    public Station(String sname, String sline, int newDistance){
        this.name = sname; 
        this.line = sline; 
        this.distance = newDistance;
    }
 
    public String get_name(){ 
        return this.name; 
    } 
 
    public String get_line(){ 
        return this.line; 
    } 

    public void set_distance(int newDistance){
        this.distance = newDistance;
    }

    public int get_distance(){
        return this.distance;
    }

    @Override public String toString()
    {


        return "Station: "+name+" on line: "+line+ " current distance = "+distance;
    }

}