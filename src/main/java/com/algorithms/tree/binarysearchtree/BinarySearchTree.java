package com.algorithms.tree.binarysearchtree;

import java.util.Comparator;
import java.util.function.Consumer;

public class BinarySearchTree<T> {
    private int size;
    private final Comparator<T> comparator;
    private Node<T> root;

    public BinarySearchTree(Comparator<T> comparator) {
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

    public void insert(T value) {
        Node<T> newNode = new Node<>(value, null, null, null);
        Node<T> parent = null;
        Node<T> current = root;

        while(current != null) {
            parent = current;
            int comparisonValue = comparator.compare(value, current.value);
            if(comparisonValue < 0) {
                current = current.leftChild;
            } else if(comparisonValue > 0){
                current = current.rightChild;
            } else {
                current.value = value;
                return;
            }
        }
        newNode.parent = parent;
        if(parent == null) {
            root = newNode;
        } else if(comparator.compare(value, parent.value) < 0) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        size++;
    }

    public boolean delete(T value) {
        Node<T> nodeToDelete = find(value);
        if(nodeToDelete == null) return false;

        if(nodeToDelete.leftChild == null) {
            transplant(nodeToDelete, nodeToDelete.rightChild);
        } else if (nodeToDelete.rightChild == null) {
            transplant(nodeToDelete, nodeToDelete.leftChild);
        } else {
            Node<T> successor = minimum(nodeToDelete.rightChild);
            if(successor.parent != nodeToDelete) {
                transplant(successor, successor.rightChild);
                successor.rightChild = nodeToDelete.rightChild;
                successor.rightChild.parent = successor;
            }
            transplant(nodeToDelete, successor);
            successor.leftChild = nodeToDelete.leftChild;
            successor.leftChild.parent = successor;
        }
        size--;
        return true;
    }

    public int size() {return size;}

    private void transplant(Node<T> u, Node<T> v) {
        if(u.parent == null) {
            root = v;
        } else if (u == u.parent.leftChild) {
            u.parent.leftChild = v;
        } else {
            u.parent.rightChild = v;
        }

        if(v != null) {
            v.parent = u.parent;
        }
    }

    static class Node<T> {
        T value;
        Node<T> leftChild, rightChild, parent;

        Node(T value, Node<T> parent, Node<T> leftChild, Node<T> rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
}
