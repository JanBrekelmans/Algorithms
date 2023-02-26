package com.algorithms.sort;

import com.algorithms.util.ListUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Comparator;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuickSortTest extends SortTestSetup{
    public QuickSortTest(int listSize) {
        super(listSize);
    }

    @Test
    public void testQuickSort() {
        QuickSort.quickSort(listToSort);
        assertTrue(ListUtil.isSorted(listToSort));
    }

    @Test
    public void testQuickSortWithCustomComparator() {
        Comparator<Integer> comparator = (n1, n2) -> n2-n1;
        QuickSort.quickSort(listToSort,comparator);
        assertTrue(ListUtil.isSorted(listToSort,comparator));
    }
}
