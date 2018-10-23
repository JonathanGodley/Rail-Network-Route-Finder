/*
 * Edge.java
 * edge class that shows us the links between two stations as well as the travel duration.
 */

public class Edge
{
    private int source;
    private int destination;
    private int duration;

    //Overloaded constructor
    public Edge(int source, int destination, int duration)
    {
        this.source = source;
        this.destination = destination;
        this.duration = duration;
    }

    /**
     * Returns the value of source
     * @return 
     */
    public int get_source()
    {
        return this.source;
    }

    /**
     * Returns the value of destination
     * @return 
     */
    public int get_destination()
    {
        return this.destination;
    }

    /**
     * Returns the value of duration
     * @return 
     */
    public int get_duration()
    {
        return this.duration;
    }

    /**
     * Sets the value of duration
     * @param duration 
     */
    public void set_duration(int duration)
    {
        this.duration = duration;
    }
}
