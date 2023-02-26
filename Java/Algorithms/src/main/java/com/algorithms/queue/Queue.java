package com.algorithms.queue;

public class Queue<T>{
    private final Object[] data;
    private int head, tail, size;

    public Queue(int capacity) {
        data = new Object[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public void enqueue(T x) {
        if(isFull()) {
            throw new IndexOutOfBoundsException("Queue is full");
        }
        data[head++] = x;
        size++;

        if(head == data.length) {
            head = 0;
        }
    }

    @SuppressWarnings("unchecked")
    public T deque() {
        if(isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }

        T r = (T) data[tail++];
        size--;
        if(tail == data.length) {
            tail = 0;
        }

        return r;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if(isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        return (T) data[tail];
    }
}
