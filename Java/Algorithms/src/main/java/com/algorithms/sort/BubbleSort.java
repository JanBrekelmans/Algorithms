package com.algorithms.sort;

import com.algorithms.util.ListUtil;

import java.util.Comparator;
import java.util.List;

public class BubbleSort {
    public static <T> void bubbleSort(List<T> list, Comparator<T> comparator) {
        for(int i = 0; i < list.size() - 1; i++) {
            for(int j = list.size() - 1; j >= i+1; j--) {
                if(comparator.compare(list.get(j), list.get(j-1)) < 0) {
                    ListUtil.swap(list, j, j-1);
                }
            }
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
        bubbleSort(list, T::compareTo);
    }
}
