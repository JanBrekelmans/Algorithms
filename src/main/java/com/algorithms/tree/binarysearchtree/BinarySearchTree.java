package com.algorithms.tree.binarysearchtree;

import com.algorithms.tree.TreeBase;

import java.util.Comparator;

public class BinarySearchTree<T> extends TreeBase<T, BinarySearchTree<T>.Node> {
    protected int size;

    public BinarySearchTree(Comparator<T> comparator) {
        super(comparator);
        size = 0;
    }


    public void insert(T value) {
        Node newNode = new Node(value);
        Node parent = null;
        Node current = root;

        while(current != null) {
            parent = current;
            int comparisonValue = comparator.compare(value, current.value);
            if(comparisonValue < 0) {
                current = current.leftChild();
            } else if(comparisonValue > 0){
                current = current.rightChild();
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

    @Override
    public boolean remove(T value) {
        Node nodeToDelete = find(value);
        if(nodeToDelete == null) return false;

        if(nodeToDelete.leftChild() == null) {
            transplant(nodeToDelete.asNode(), nodeToDelete.rightChild());
        } else if (nodeToDelete.rightChild == null) {
            transplant(nodeToDelete, nodeToDelete.leftChild());
        } else {
            Node successor = minimum(nodeToDelete.rightChild());
            if(successor.parent != nodeToDelete) {
                transplant(successor, successor.rightChild());
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

    @Override
    public int size() {
        return size;
    }


    private void transplant(Node u, Node v) {
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

    protected class Node extends TreeBase<T, Node>.TreeNode {
        public Node(T value) {
            super(value);
        }
    }
}
