package com.algorithms.sort;

import com.algorithms.util.ListUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
    public static <T> void mergeSort(List<T> list, Comparator<T> comparator) {
        mergeSort(list, comparator, 0, list.size()-1);
    }

    public static <T extends Comparable<T>> void mergeSort(List<T> list) {
        mergeSort(list, T::compareTo);
    }

    private static <T> void mergeSort(List<T> list, Comparator<T> comparator, int leftIndex, int rightIndex) {
        if(leftIndex < rightIndex) {
            int centerIndex = leftIndex + (rightIndex - leftIndex)/2;
            mergeSort(list, comparator, leftIndex, centerIndex);
            mergeSort(list, comparator, centerIndex+1, rightIndex);
            merge(list, comparator, leftIndex, centerIndex, rightIndex);
        }
    }

    private static <T> void merge(List<T> listToMerge, Comparator<T> comparator, int l, int m, int r) {
        int sizeLeft = m - l + 1;
        int sizeRight = r - m;
        List<T> leftList= new ArrayList<>(sizeLeft);
        List<T> rightList = new ArrayList<>(sizeRight);

        for(int i = 0; i < sizeLeft; i++) {
            leftList.add(listToMerge.get(l + i));
        }
        for(int i = 0; i < sizeRight; i++) {
            rightList.add(listToMerge.get(m +1 + i));
        }

        int leftIndex = 0, rightIndex = 0;
        int index = l;
        while(leftIndex < sizeLeft && rightIndex < sizeRight) {
            if(comparator.compare(leftList.get(leftIndex), rightList.get(rightIndex)) < 0) {
                listToMerge.set(index, leftList.get(leftIndex));
                leftIndex++;
            } else {
                listToMerge.set(index, rightList.get(rightIndex));
                rightIndex++;
            }
            index++;
        }

        while(leftIndex < sizeLeft) {
            listToMerge.set(index, leftList.get(leftIndex));
            leftIndex++;
            index++;
        }

        while(rightIndex < sizeRight) {
            listToMerge.set(index, rightList.get(rightIndex));
            rightIndex++;
            index++;
        }
    }
}
