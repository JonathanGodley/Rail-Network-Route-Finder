/*
 * Edge.java
 * edge class that shows us the links between two stations as well as the travel duration.
 */

public class Edge
{
    private int source;
    private int destination;
    private int duration;

    public Edge(int source, int destination, int duration)
    {
        this.source = source;
        this.destination = destination;
        this.duration = duration;
    }

    public int get_source()
    {
        return this.source;
    }

    public int get_destination()
    {
        return this.destination;
    }

    public int get_duration()
    {
        return this.duration;
    }

    public void set_duration(int duration)
    {
        this.duration = duration;
    }
}
