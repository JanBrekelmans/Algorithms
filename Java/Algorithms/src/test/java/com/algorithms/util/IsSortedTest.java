package com.algorithms.util;


import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class IsSortedTest {
    @Test
    public void emptyListTest() {
        List<Integer> emptyList = List.of();
        assertTrue(ListUtil.isSorted(emptyList));
    }

    @Test
    public void singleElementListTest() {
        List<Integer> singleElementList = List.of(1);
        assertTrue(ListUtil.isSorted(singleElementList));
    }

    @Test
    public void multiElementSortedListTest() {
        List<Integer> multiElementList = List.of(1,2,3,4,5,6);
        assertTrue(ListUtil.isSorted(multiElementList));
    }

    @Test
    public void multiElementUnsortedListTest() {
        List<Integer> multiElementList = List.of(2,1,3,4,5,6);
        assertFalse(ListUtil.isSorted(multiElementList));
    }

    @Test
    public void multiElementSortedListCustomComparatorTest() {
        List<Integer> multiElementList = List.of(6,5,4,3,2,1);
        Comparator<Integer> customComparator = (n1,n2) -> n2-n1;
        assertTrue(ListUtil.isSorted(multiElementList, customComparator));
    }

    @Test
    public void multiElementUnsortedListCustomComparatorTest() {
        List<Integer> multiElementList = List.of(5,6,4,3,2,1);
        Comparator<Integer> customComparator = (n1,n2) -> n2-n1;
        assertFalse(ListUtil.isSorted(multiElementList, customComparator));
    }
}
