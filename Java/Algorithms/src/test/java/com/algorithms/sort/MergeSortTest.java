package com.algorithms.sort;

import com.algorithms.util.ListUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Comparator;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MergeSortTest extends SortTestSetup{
    public MergeSortTest(int listSize) {
        super(listSize);
    }

    @Test
    public void testMergeSort() {
        MergeSort.mergeSort(listToSort);
        assertTrue(ListUtil.isSorted(listToSort));
    }

    @Test
    public void testMergeSortWithCustomComparator() {
        Comparator<Integer> comparator = (n1, n2) -> n2-n1;
        MergeSort.mergeSort(listToSort,comparator);
        assertTrue(ListUtil.isSorted(listToSort,comparator));
    }
}
