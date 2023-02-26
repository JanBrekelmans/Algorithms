package com.algorithms.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ListUtil {
    public static <T> void swap(List<T> list, int i, int j) {
        if(i == j) return;

        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static <T> boolean isSorted(List<T> list, Comparator<T> comparator) {
        if(list.size() <= 1) return true;
        Iterator<T> iterator = list.iterator();

        T current = iterator.next();
        while(iterator.hasNext()) {
            T next = iterator.next();
            if(comparator.compare(current, next) > 0) {
                return false;
            }
            current = next;
        }
        return true;
    }

    public static <T extends Comparable<T>> boolean isSorted(List<T> list) {
        return isSorted(list, T::compareTo);
    }
}
