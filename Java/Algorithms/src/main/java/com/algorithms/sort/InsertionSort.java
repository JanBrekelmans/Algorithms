package com.algorithms.sort;

import com.algorithms.util.ListUtil;

import java.util.Comparator;
import java.util.List;

public class InsertionSort {
    public static <T> void insertionSort(List<T> list, Comparator<T> comparator) {
        for(int j = 1; j < list.size(); j++) {
            T key = list.get(j);

            int i = j-1;
            while(i >= 0 && comparator.compare(list.get(i), key) > 0) {
                ListUtil.swap(list, i, i+1);
                i--;
            }
            list.set(i+1, key);
        }
    }

    public static <T extends Comparable<T>> void insertionSort(List<T> list) {
        insertionSort(list, T::compareTo);
    }
}
