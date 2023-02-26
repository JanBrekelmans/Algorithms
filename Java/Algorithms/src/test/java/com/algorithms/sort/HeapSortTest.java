package com.algorithms.sort;

import com.algorithms.sort.HeapSort;
import com.algorithms.sort.SortTestSetup;
import com.algorithms.util.ListUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class HeapSortTest extends SortTestSetup {
    public HeapSortTest(int listSize) {
        super(listSize);
    }

    @Test
    public void testHeapSort() {
        HeapSort.heapSort(listToSort);
        assertTrue(ListUtil.isSorted(listToSort));
    }

    @Test
    public void testHeapSortWithCustomComparator() {
        Comparator<Integer> comparator = (n1, n2) -> n2-n1;
        HeapSort.heapSort(listToSort,comparator);
        assertTrue(ListUtil.isSorted(listToSort,comparator));
    }
}
