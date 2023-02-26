package com.algorithms.sort;

import org.junit.Before;
import org.junit.runners.Parameterized;

import java.util.*;

abstract class SortTestSetup {
    private final int MIN_VALUE = 0;
    private final int MAX_VALUE = Integer.MAX_VALUE;
    private final int listSize;
    protected List<Integer> listToSort;

    protected SortTestSetup(int listSize) {
        this.listSize = listSize;
    }

    @Parameterized.Parameters
    public static Collection listSizes() {
        return Arrays.asList(new Object[][] {
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {8},
                {13},
                {21},
                {34},
                {55},
                {89},
                {31},
                {32},
                {33},
                {1024},
                {2048},
                {4096}
        });
    }

    @Before
    public void initialize() {
        Random random = new Random(0);
        listToSort = new ArrayList<>(random.ints(listSize, MIN_VALUE, MAX_VALUE).boxed().toList());
    }
}
