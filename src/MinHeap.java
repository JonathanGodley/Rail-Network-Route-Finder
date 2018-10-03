import java.util.Arrays;

public class MinHeap
{
    private int capacity;
    private int currentSize;
    HeapNode[] mH;
    int[] indexes;

    public MinHeap(int capacity)
    {
        this.capacity = capacity;
        mH = new HeapNode[capacity+1];
        indexes = new int[capacity];
        mH[0] = new HeapNode();
        mH[0].setDistance(Integer.MIN_VALUE);
        mH[0].setStation(null);
        currentSize = 0;
    }

    @Override
    public String toString()
    {
        return "MinHeap{" + "capacity=" + capacity + ", currentSize=" + currentSize + ", mH=" + Arrays.toString(mH) +
               ", indexes=" + Arrays.toString(indexes) + '}';
    }

    public void swap(int a, int b)
    {
        HeapNode tmp = mH[a];
        mH[a] = mH[b];
        mH[b] = tmp;
    }

    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    public int size()
    {
        return currentSize;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public int getCurrentSize()
    {
        return currentSize;
    }

    public void setCurrentSize(int currentSize)
    {
        this.currentSize = currentSize;
    }

    public HeapNode[] getmH()
    {
        return mH;
    }

    public void setmH(HeapNode[] mH)
    {
        this.mH = mH;
    }

    public int[] getIndexes()
    {
        return indexes;
    }

    public void setIndexes(int[] indexes)
    {
        this.indexes = indexes;
    }
}
