package com.algorithms.linkedlist;

public class LinkedList<T> {
    private Node<T> head, tail;

    public void add(T item) {
        Node<T> newNode = new Node<>(item, null, null);

        if(head == null) {
            head = newNode;
            tail = newNode;
        } else {
            Node<T> oldTail = tail;
            oldTail.next = newNode;
            newNode.previous = oldTail;
            tail = newNode;
        }
    }

    public T poll() {
        if(tail == null) {
            return null;
        }
        Node<T> oldTail = tail;
        Node<T> newTail = tail.previous;
        newTail.next = null;
        tail = newTail;

        return oldTail.item;
    }

    public void remove(T item) {
        Node<T> nodeToRemove = find(item);
        Node<T> previousNode = nodeToRemove.previous;
        Node<T> nextNode = nodeToRemove.next;

        if(nodeToRemove == head) {
            head = nodeToRemove.next;
            head.previous = null;
        }
        if (nodeToRemove == tail) {
            tail = nodeToRemove.previous;
            tail.next = null;
        }

        if(previousNode != null) {
            previousNode.next = nextNode;
        }
        if(nextNode != null) {
            nextNode.previous = previousNode;
        }
    }

    /**
     * Find the node with the corresponding item.
     */
    private Node<T> find(T item) {
        Node<T> current = head;
        while(current != null && !current.item.equals(item)) {
            current = current.next;
        }
        return current;
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> previous;

        Node(T item, Node<T> next, Node<T> previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }
}
