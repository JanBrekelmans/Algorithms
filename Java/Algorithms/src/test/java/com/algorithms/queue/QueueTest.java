package com.algorithms.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void emptyQueueTest() {
        Queue<Integer> queue = new Queue<>(5);
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
    }

    @Test
    public void oneElementQueueTest() {
        Queue<Integer> queue = new Queue<>(5);
        Integer e = 1;
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(e, queue.peek());
        assertEquals(e, queue.deque());
    }
}
