/*
 * MinHeap.java
 * Indirect Minimum Heap class, for use with Dijkstra's algorithm
 */

public class MinHeap
{
    int[] indexes;
    private int        capacity;
    private int        currentSize;
    private HeapNode[] minHeap;

    //Overloaded constructor
    public MinHeap(int capacity)
    {
        this.capacity = capacity;
        minHeap = new HeapNode[capacity + 1];
        indexes = new int[capacity];
        minHeap[0] = new HeapNode();
        minHeap[0].setDistance(Integer.MIN_VALUE);
        minHeap[0].setStationIndex(-1);
        currentSize = 0;
    }

    /**
     * Inserts a new node into the minHeap
     * @param newNode 
     */
    public void insert(HeapNode newNode)
    {
        currentSize++;
        int idx = currentSize;
        minHeap[idx] = newNode;
        indexes[newNode.getStationIndex()] = idx;
        bubbleUp(idx);
    }

    /**
     * Sorts the minHeap, bubbles up a new node to the correct position
     * @param pos 
     */
    public void bubbleUp(int pos)
    {
        int parentIdx  = pos / 2;
        int currentIdx = pos;
        while (currentIdx > 0 && minHeap[parentIdx].getDistance() > minHeap[currentIdx].getDistance())
        {
            HeapNode currentNode = minHeap[currentIdx];
            HeapNode parentNode  = minHeap[parentIdx];

            //swap the positions
            indexes[currentNode.getStationIndex()] = parentIdx;
            indexes[parentNode.getStationIndex()] = currentIdx;
            swap(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx / 2;
        }
    }

    /**
     * Returns and removes the lowest value node
     * @return 
     */
    public HeapNode extractMin()
    {
        HeapNode min      = minHeap[1];
        HeapNode lastNode = minHeap[currentSize];
        // update the indexes[] and move the last node to the top
        indexes[lastNode.getStationIndex()] = 1;
        minHeap[1] = lastNode;
        minHeap[currentSize] = null;
        sinkDown(1);
        currentSize--;
        return min;
    }

    /**
     * A recursive method to heapify a subtree with the root at given index
     * @param k 
     */
    public void sinkDown(int k)
    {
        int smallest      = k;
        int leftChildIdx  = 2 * k;
        int rightChildIdx = 2 * k + 1;
        if (leftChildIdx < size() && minHeap[smallest].getDistance() > minHeap[leftChildIdx].getDistance())
        {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < size() && minHeap[smallest].getDistance() > minHeap[rightChildIdx].getDistance())
        {
            smallest = rightChildIdx;
        }
        if (smallest != k)
        {
            HeapNode smallestNode = minHeap[smallest];
            HeapNode kNode        = minHeap[k];

            //swap the positions
            indexes[smallestNode.getStationIndex()] = k;
            indexes[kNode.getStationIndex()] = smallest;
            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    /**
     * Swaps two nodes
     * @param a
     * @param b 
     */
    public void swap(int a, int b)
    {
        HeapNode tmp = minHeap[a];
        minHeap[a] = minHeap[b];
        minHeap[b] = tmp;
    }
    
    /**
     * Returns boolean of if minHeap is empty
     * @return 
     */
    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    /**
     * Returns value of currentSize
     * @return 
     */
    public int size()
    {
        return currentSize;
    }

    /**
     * Returns the minHeap
     * @return 
     */
    public HeapNode[] getMinHeap()
    {
        return minHeap;
    }
}
