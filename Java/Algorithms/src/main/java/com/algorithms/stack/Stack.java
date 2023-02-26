package com.algorithms.stack;

import java.util.EmptyStackException;

public class Stack<T> {
    private final Object[] data;
    private int size;

    public Stack(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int size() {
        return size;
    }

    public void push(T val) {
        if(isFull()) {
            throw new StackOverflowError("Stack is full");
        }
        data[size++] = val;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        return (T) data[--size];
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        return (T) data[size-1];
    }
}
