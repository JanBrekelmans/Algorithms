package com.algorithms.stack;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class StackTest {
    private final static int STACK_SIZE = 2;
    Stack<Integer> stack;

    @Before
    public void initialize() {
        stack = new Stack<>(STACK_SIZE);
    }

    @Test
    public void emptyStackTest() {
        assertEquals(stack.size(), 0);
        assertTrue(stack.isEmpty());
        assertFalse(stack.isFull());
    }

    @Test(expected = EmptyStackException.class)
    public void emptyPeekTest() {
        stack.peek();
    }

    @Test(expected = EmptyStackException.class)
    public void emptyPopTest() {
        stack.pop();
    }

    @Test
    public void stackPushTest() {
        stack.push(1);
        assertEquals(stack.size(), 1);
        assertFalse(stack.isEmpty());
        assertFalse(stack.isFull());

        stack.push(2);
        assertEquals(stack.size(), 2);
        assertFalse(stack.isEmpty());
        assertTrue(stack.isFull());
    }

    @Test
    public void stackPushAndPopTest() {
        stack.push(1);
        assertEquals((int) stack.peek(), 1);
        assertEquals((int) stack.pop(), 1);
        assertTrue(stack.isEmpty());
    }

    @Test(expected = StackOverflowError.class)
    public void stackOverFlowTest() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }
}
