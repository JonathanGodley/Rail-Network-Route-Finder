/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author marz
 */

public class MinHeap
{
    int[] indexes;
    private int        capacity;
    private int        currentSize;
    private HeapNode[] minHeap;

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

    public void insert(HeapNode newNode)
    {

        currentSize++;
        int idx = currentSize;
        minHeap[idx] = newNode;
        indexes[newNode.getStationIndex()] = idx;
        bubbleUp(idx);

    }

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

    public HeapNode extractMin()
    {
        HeapNode min      = minHeap[1];
        HeapNode lastNode = minHeap[currentSize];
        //            update the indexes[] and move the last node to the top
        indexes[lastNode.getStationIndex()] = 1;
        minHeap[1] = lastNode;
        minHeap[currentSize] = null;
        sinkDown(1);
        currentSize--;
        return min;
    }

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

    public void swap(int a, int b)
    {
        HeapNode tmp = minHeap[a];
        minHeap[a] = minHeap[b];
        minHeap[b] = tmp;
    }

    public boolean isEmpty()
    {
        return currentSize == 0;
    }

    public int size()
    {
        return currentSize;
    }

    public HeapNode[] getMinHeap()
    {
        return minHeap;
    }
}
