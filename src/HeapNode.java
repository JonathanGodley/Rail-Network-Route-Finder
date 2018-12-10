/*
 * HeapNode.java
 * node class for use in an Indirect Minimum Heap
 */

public class HeapNode
{
    private int stationIndex;
    private int distance;

    /**
     * Returns value of stationIndex
     * @return 
     */
    public int getStationIndex()
    {
        return stationIndex;
    }

    /**
     * Sets value of stationIndex
     * @param stationIndex 
     */
    public void setStationIndex(int stationIndex)
    {
        this.stationIndex = stationIndex;
    }

    /**
     * Returns value of distance
     * @return 
     */
    public int getDistance()
    {
        return distance;
    }

    /**
     * Sets value of distance
     * @param distance 
     */
    public void setDistance(int distance)
    {
        this.distance = distance;
    }
}