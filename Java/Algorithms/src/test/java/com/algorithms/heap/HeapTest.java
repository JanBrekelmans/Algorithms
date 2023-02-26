package com.algorithms.heap;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeapTest {
    @Test
    public void maxHeapIsEmptyTest() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(Integer::compare);
        assertTrue(maxHeap.isEmpty());
        maxHeap.add(1);
        assertFalse(maxHeap.isEmpty());
    }

    @Test
    public void maxHeapSizeTest() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(Integer::compare);
        assertEquals(0, maxHeap.size());
        maxHeap.add(1);
        assertEquals(1, maxHeap.size());
        maxHeap.add(3);
        assertEquals(2, maxHeap.size());
        maxHeap.add(2);
        assertEquals(3, maxHeap.size());
    }

    @Test
    public void singleItemTest() {
        Integer e = 1;
        MaxHeap<Integer> maxHeap = new MaxHeap<>(Integer::compare);
        maxHeap.add(1);
        assertEquals(e, maxHeap.peek());
        assertEquals(e, maxHeap.poll());
        assertTrue(maxHeap.isEmpty());
    }
}
