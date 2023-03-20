package com.algorithms.tree;

public abstract class TreeNode<K, V> {
    public final K key;
    public V value;

    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
