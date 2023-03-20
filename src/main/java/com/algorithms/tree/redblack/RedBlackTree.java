package com.algorithms.tree.redblack;

import com.algorithms.tree.TreeBase;

import java.util.Comparator;

/**
 * A red-black tree adhers to the following rules:
 * <ul>
 * <li>Each node is either red or black.</li>
 * <li>The root node is black.</li>
 * <li>All null leaves are black.</li>
 * <li>A red node may not have red children.</li>
 * <li>All paths from a node to the leaves contain the same number of black nodes.</li>
 * </ul>
 */
public class RedBlackTree<T> extends TreeBase<T, RedBlackTree<T>.Node>{


    public RedBlackTree(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void insert(T value) {
        Node node = root;
        Node parent = null;

        while(node != null) {
            parent = node;
            if(comparator.compare(value, node.value) < 0) {
                node = node.leftChild();
            } else if (comparator.compare(value, node.value) > 0) {
                node = node.rightChild();
            } else {
                return;
            }
        }

        Node newNode = new Node(value, Node.Color.RED);
        if(parent == null) {
            root = newNode;
        } else if(comparator.compare(value, parent.value) < 0) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        newNode.parent = parent;

        fixProperties(newNode);
    }

    private void fixProperties(Node node) {
        Node parent = node.parent();

        if(parent == null) {
            node.nodeColor = Node.Color.BLACK; // Set the color of the root to black
            return;
        }

        if(parent.nodeColor == Node.Color.BLACK) {
            return;
        }

        Node grandparent = parent.parent();

        if(grandparent == null) {
            parent.nodeColor = Node.Color.BLACK;
            return;
        }

        Node uncle = getUncle(parent);
        if(uncle != null && uncle.nodeColor == Node.Color.RED) {
            parent.nodeColor = Node.Color.BLACK;
            uncle.nodeColor = Node.Color.BLACK;
            grandparent.nodeColor = Node.Color.RED;
            fixProperties(grandparent);
        } else if (parent == grandparent.leftChild()) {
            if(node == parent.rightChild()) {
                leftRotate(parent);
                parent = node;
            }
            rightRotate(grandparent);
            parent.nodeColor = Node.Color.BLACK;
            grandparent.nodeColor = Node.Color.RED;
        }  else {
            if(node == parent.leftChild()) {
                rightRotate(parent);
                parent = node;
            }
            leftRotate(grandparent);
            parent.nodeColor = Node.Color.BLACK;
            grandparent.nodeColor = Node.Color.RED;
        }
    }

    private Node getUncle(Node parent) {
        Node grandparent = parent.parent();
        if(grandparent.leftChild() == parent) {
            return grandparent.rightChild();
        } else if (grandparent.rightChild() == parent) {
            return grandparent.leftChild();
        }
        throw  new IllegalStateException("Parent is not a child of its grandparent");
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    private void rightRotate(Node node) {
        Node parent = node.parent();
        Node leftChild = node.leftChild();
        node.leftChild = leftChild.rightChild;
        if(leftChild.rightChild != null) {
            leftChild.rightChild.parent = node;
        }

        leftChild.rightChild = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private void leftRotate(Node node) {
        Node parent = node.parent();
        Node rightChild = node.rightChild();
        node.rightChild = rightChild.leftChild;
        if(rightChild.leftChild != null) {
            rightChild.leftChild.parent = node;
        }

        rightChild.leftChild = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);

    }

    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if(parent == null) {
            root = newChild;
        } else if (parent.leftChild == oldChild) {
            parent.leftChild = newChild;
        } else if (parent.rightChild == oldChild) {
            parent.rightChild = newChild;
        }

        if(newChild != null) {
            newChild.parent = parent;
        }
    }

    @Override
    protected String getNodeColor(Node node) {
        return node.nodeColor == Node.Color.RED? "red" : "black";
    }

    protected class Node extends TreeBase<T, Node>.TreeNode {
        public Color nodeColor;

        public Node(T value, Color nodeColor) {
            super(value);
            this.nodeColor = nodeColor;
        }

        public enum Color {
            RED,
            BLACK
        }
    }
}
