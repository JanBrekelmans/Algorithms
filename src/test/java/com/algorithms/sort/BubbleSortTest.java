package com.algorithms.sort;

import com.algorithms.util.ListUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Comparator;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class BubbleSortTest extends SortTestSetup{
    public BubbleSortTest(int listSize) {
        super(listSize);
    }

    @Test
    public void testBubbleSort() {
        BubbleSort.bubbleSort(listToSort);
        assertTrue(ListUtil.isSorted(listToSort));
    }

    @Test
    public void testBubbleSortWithCustomComparator() {
        Comparator<Integer> comparator = (n1, n2) -> n2-n1;
        BubbleSort.bubbleSort(listToSort,comparator);
        assertTrue(ListUtil.isSorted(listToSort,comparator));
    }
}
