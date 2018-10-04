// HeapNode.java for use with minHeap
public class HeapNode
{
    private Station station;
    private int distance;

    public Station getStation()
    {
        return station;
    }

    public void setStation(Station station)
    {
        this.station = station;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;

        if (distance < 0)
        {
            System.out.println("error");
        }
    }

    public int getIndex()
    {
        return station.getIndex();
    }

}
