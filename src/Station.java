/*
 * Station.java
 * simple station class to contain a station's name and line.
 */
public class Station
{
    private String name;
    private String line;

    //Overloaded constructor
    public Station(String sname, String sline)
    {
        this.name = sname;
        this.line = sline;
    }

    /**
     * Returns value of name
     * @return 
     */
    public String get_name()
    {
        return this.name;
    }

    /**
     * Returns value of line
     * @return 
     */
    public String get_line()
    {
        return this.line;
    }
}