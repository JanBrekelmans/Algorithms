package com.algorithms.sort;

import com.algorithms.util.ListUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Comparator;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class InsertionSortTest extends SortTestSetup{

    public InsertionSortTest(int listSize) {
        super(listSize);
    }

    @Test
    public void testInsertionSort() {
        InsertionSort.insertionSort(listToSort);
        assertTrue(ListUtil.isSorted(listToSort));
    }

    @Test
    public void testInsertionSortWithCustomComparator() {
        Comparator<Integer> comparator = (n1,n2) -> n2-n1;
        InsertionSort.insertionSort(listToSort,comparator);
        assertTrue(ListUtil.isSorted(listToSort,comparator));
    }
}
