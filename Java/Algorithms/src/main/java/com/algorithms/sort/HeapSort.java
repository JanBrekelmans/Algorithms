package com.algorithms.sort;

import com.algorithms.heap.MaxHeap;
import com.algorithms.util.ListUtil;

import java.util.Comparator;
import java.util.List;

public class HeapSort {
    public static <T> void heapSort(List<T> list, Comparator<T> comparator) {
        MaxHeap<T> heap = new MaxHeap<>(list, comparator);

        for(int i = list.size() -1; i>= 0; i--) {
            list.set(i, heap.poll());
        }
    }

    public static <T extends Comparable<T>> void heapSort(List<T> list) {
        heapSort(list, T::compareTo);
    }
}
