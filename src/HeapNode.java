/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author marz
 */
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
            System.out.println("error " + distance);
        }
    }

    public int getIndex()
    {
        return station.getIndex();
    }

}