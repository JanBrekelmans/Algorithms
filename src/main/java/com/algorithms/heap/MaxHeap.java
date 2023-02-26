package com.algorithms.heap;

import com.algorithms.util.ListUtil;

import java.util.*;

public class MaxHeap<T>{
    private final Comparator<T> comparator;
    List<T> heapList = new ArrayList<>();

    public MaxHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public MaxHeap(Collection<T> collection, Comparator<T> comparator) {
        this.comparator = comparator;
        heapList.addAll(collection);

        buildHeap();
    }

    public void add(T t) {
        heapList.add(t);
        increaseKey(t, heapList.size() - 1);
    }

    private void increaseKey(T t, int index) {
        while(index > 0) {
            int parent = parent(index);
            T e = heapList.get(parent);
            if(comparator.compare(t, e) < 0) {
                break;
            }
            heapList.set(index, e);
            index = parent;
        }
        heapList.set(index, t);
    }

    public T poll() {
        T r = peek();
        heapList.set(0, heapList.get(heapList.size() - 1));
        heapList.remove(heapList.size() - 1);
        maxHeapify(0);

        return r;
    }

    public T peek() {
        return heapList.get(0);
    }

    private void maxHeapify(int index) {
        int left = leftChild(index);
        int right = rightChild(index);

        int largest;

        if(left < size() && comparator.compare(heapList.get(index), heapList.get(left)) < 0) {
            largest = left;
        } else {
            largest = index;
        }

        if(right < size() && comparator.compare(heapList.get(largest), heapList.get(right)) < 0) {
            largest = right;
        }

        if(largest != index) {
            ListUtil.swap(heapList, index, largest);
            maxHeapify(largest);
        }
    }

    private void buildHeap() {
        for(int i = size() / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public int size() {
        return heapList.size();
    }

    public boolean isEmpty() {return size() == 0;}

    private int parent(int index) {
        return index >>> 1;
    }

    private int leftChild(int index) {
        return (index << 1)+1;
    }

    private int rightChild(int index) {
        return (index << 1) + 2;
    }
}
