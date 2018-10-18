/*
 * HeapNode.java
 * node class for use in an Indirect Minimum Heap
 */

public class HeapNode
{
    private int stationIndex;
    private int distance;

    public int getStationIndex()
    {
        return stationIndex;
    }

    public void setStationIndex(int stationIndex)
    {
        this.stationIndex = stationIndex;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

}