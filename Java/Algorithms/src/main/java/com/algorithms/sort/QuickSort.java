package com.algorithms.sort;

import com.algorithms.heap.MaxHeap;
import com.algorithms.util.ListUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QuickSort {
    public static <T> void quickSort(List<T> list, Comparator<T> comparator) {
        quickSort(list, 0, list.size()-1, comparator);
    }

    public static <T extends Comparable<T>> void quickSort(List<T> list) {
        quickSort(list, T::compareTo);
    }

    private static <T> int partition(List<T> list, int l, int h, Comparator<T> comparator) {
        T pivot = list.get(h);
        int r = l - 1;

        for(int i = l; i < h; i++) {
            if(comparator.compare(list.get(i), pivot) < 0) {
                r++;
                ListUtil.swap(list, r, i);
            }
        }
        ListUtil.swap(list, r+1, h);

        return r+1;
    }

    private static <T> void quickSort(List<T> list, int l, int h, Comparator<T> comparator) {
        if(l < h) {
            int m = partition(list, l, h, comparator);
            quickSort(list, l, m-1, comparator);
            quickSort(list, m+1, h, comparator);
        }
    }
}
