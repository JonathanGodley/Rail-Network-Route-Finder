/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp2230;

/**
 *
 * @author marz
 */
import java.util.Arrays;

public class MinHeap
{
    private int capacity;
    private int currentSize;
    private HeapNode[] minHeap;
    int[]      indexes;
    private int i;

    public MinHeap(int capacity)
    {
        this.capacity = capacity;
        minHeap = new HeapNode[capacity];
        indexes = new int[capacity];
        currentSize = -1;
        i = 0;
    }

    public void insert(HeapNode newNode)
    {

        currentSize++;
        
        minHeap[currentSize] = newNode;
        indexes[newNode.getIndex()] = currentSize;
        bubbleUp(currentSize);

    }

    public void bubbleUp(int pos) {
        int parentIdx = pos/2;
        int currentIdx = pos;
        while (currentIdx > 0 && minHeap[parentIdx].getDistance() > minHeap[currentIdx].getDistance()) {
            HeapNode currentNode = minHeap[currentIdx];
            HeapNode parentNode = minHeap[parentIdx];

            //swap the positions
            indexes[currentNode.getIndex()] = parentIdx;
            indexes[parentNode.getIndex()] = currentIdx;
            swap(currentIdx,parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx/2;
        }
    }

    public HeapNode extractMin() 
    {
        //made this heaps simpler. still seems to work, also this way never have to siftdown
        HeapNode min = minHeap[i];
        //HeapNode lastNode = minHeap[currentSize];
        //            update the indexes[] and move the last node to the top
       // indexes[lastNode.getIndex()] = 1;
        //minHeap[0] = lastNode;
        //minHeap[currentSize] = null;
        //sinkDown(i);
        //currentSize--;
        i++;
        return min;
    }

    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k+1;
        if (leftChildIdx < size() && minHeap[smallest].getDistance() > minHeap[leftChildIdx].getDistance()) {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < size() && minHeap[smallest].getDistance() > minHeap[rightChildIdx].getDistance()) {
            smallest = rightChildIdx;
        }
        if (smallest != k) {

            HeapNode smallestNode = minHeap[smallest];
            HeapNode kNode = minHeap[k];

            //swap the positions
            indexes[smallestNode.getIndex()] = k;
            indexes[kNode.getIndex()] = smallest;
            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    @Override
    public String toString()
    {
        return "MinHeap{" + "capacity=" + capacity + ", currentSize=" + currentSize + ", minHeap=" + Arrays.toString(
                minHeap) +
               ", indexes=" + Arrays.toString(indexes) + '}';
    }

    public void swap(int a, int b)
    {
        HeapNode tmp = minHeap[a];
        minHeap[a] = minHeap[b];
        minHeap[b] = tmp;
    }

    public boolean isEmpty()
    {
       return i == capacity;
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

    public HeapNode[] getMinHeap()
    {
        return minHeap;
    }

    public void setMinHeap(HeapNode[] minHeap)
    {
        this.minHeap = minHeap;
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
