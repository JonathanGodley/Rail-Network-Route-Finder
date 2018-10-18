/*
 * Station.java
 * simple station class to contain a station's name and line.
 */
public class Station
{
    private String name;
    private String line;

    public Station(String sname, String sline)
    {
        this.name = sname;
        this.line = sline;
    }

    public String get_name()
    {
        return this.name;
    }

    public String get_line()
    {
        return this.line;
    }
}