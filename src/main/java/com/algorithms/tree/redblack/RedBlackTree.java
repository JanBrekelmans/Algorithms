package com.algorithms.tree.redblack;

import java.util.Comparator;
import java.util.function.Consumer;

public class RedBlackTree<T> {
    private int size;
    private final Comparator<T> comparator;
    private Node<T> root;

    public RedBlackTree(Comparator<T> comparator) {
        this.comparator = comparator;
        size = 0;
    }

    public void inorderWalk(Consumer<T> cons) {
        Node<T> current = root;
    }

    private void inorderWalk(Consumer<T> cons, Node<T> current) {
        if(current != null) {
            inorderWalk(cons, current.leftChild);
            cons.accept(current.value);
            inorderWalk(cons, current.rightChild);
        }
    }

    public boolean contains(T value) {
        return find(value) != null;
    }

    private Node<T> find(T value) {
        Node<T> current = root;

        while(current != null) {
            int cmp = comparator.compare(value, current.value);
            if(cmp < 0) {
                current = current.leftChild;
            } else if (cmp > 0) {
                current = current.rightChild;
            } else {
                return current;
            }
        }

        return null;
    }

    public T minimum() {
        Node<T> m = minimum(root);
        return m != null? m.value: null;
    }

    private Node<T> minimum(Node<T> current) {
        while(current != null && current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }

    public T maximum() {
        Node<T> m = maximum(root);
        return m != null? m.value: null;
    }

    private Node<T> maximum(Node<T> current) {
        while(current != null && current.rightChild != null) {
            current = current.rightChild;
        }
        return current;
    }

    private boolean contains(T value, Node<T> current) {
        if(current == null) {
            return false;
        } else return current.value.equals(value);
    }

    private void leftRotate(Node<T> x) {
        Node<T> y = x.rightChild;
        x.rightChild = y.leftChild;
        if(y.leftChild != null) {
            y.leftChild.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null) {
            root = y;
        } else if (x == x.parent.leftChild) {
            x.parent.leftChild = y;
        } else {
            x.parent.rightChild = y;
        }
        y.leftChild = x;
        x.parent = y;
    }

    private void rightRotate(Node<T> x) {
        Node<T> y = x.leftChild;
        x.leftChild = y.rightChild;
        if(y.rightChild != null) {
            y.rightChild.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null) {
            root = y;
        } else if (x == x.parent.rightChild) {
            x.parent.rightChild = y;
        } else {
            x.parent.leftChild = y;
        }
        y.rightChild = x;
        x.parent = y;
    }

    public void insert(T value) {
        Node<T> newNode = new Node<>(value, null, null, null, Node.NodeColor.RED);
        Node<T> parent = null;
        Node<T> current = root;
        while(current != null) {
            parent = current;
            if(comparator.compare(value, current.value) < 0) {
                current = current.leftChild;
            } else if (comparator.compare(value, current.value) > 0) {
                current = current.rightChild;
            } else {
                current.value = value;
                return;
            }
        }
        newNode.parent = parent;
        if(parent == null) {
            root = newNode;
        } else if (comparator.compare(value, parent.value) < 0) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        insertFix(newNode);
    }

    private void insertFix(Node<T> newNode) {

    }

    static class Node<T> {
        T value;
        Node<T> leftChild, rightChild, parent;
        NodeColor nodeColor;

        Node(T value, Node<T> parent, Node<T> leftChild, Node<T> rightChild, NodeColor nodeColor) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.nodeColor = nodeColor;
        }

        enum NodeColor {
            RED,
            BLACK
        }
    }
}
