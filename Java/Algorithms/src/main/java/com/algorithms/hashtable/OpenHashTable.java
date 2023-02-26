package com.algorithms.hashtable;


public class OpenHashTable<K, V> {
    private final HashFunction<K> hashFunction;
    private final Node<K, V>[] buckets;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public OpenHashTable(HashFunction<K> hashFunction, int capacity) {
        this.hashFunction = hashFunction;
        buckets = new Node[capacity];
    }

    public void put(K key, V value) {
        int hash = hashFunction.hash(key);
        Node<K,V> entry = new Node<>(hash, key, value, null);

        int bucketId = hash % buckets.length;
        Node<K, V> bucket = buckets[bucketId];

        if(bucket == null) {
            buckets[bucketId] = entry;
            size++;
        } else {
            while (bucket.next != null) {
                if (bucket.key.equals(key)) {
                    bucket.value = value;
                    return;
                }
                bucket = bucket.next;
            }

            if (bucket.key.equals(key)) {
                bucket.value = value;
            } else {
                bucket.next = entry;
                size++;
            }
        }
    }

    public V get(K key) {
        Node<K,V> bucket = buckets[hashFunction.hash(key) % buckets.length];
        while(bucket != null) {
            if(bucket.key.equals(key)) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

    public V remove(K key) {
        int bucketId = hashFunction.hash(key) % buckets.length;
        Node<K,V> node = buckets[bucketId];

        if(node.key.equals(key)) {
            buckets[bucketId] = node.next;
            size--;
            return node.value;
        }

        while(node.next != null) {
            var nextNode = node.next;
            if(nextNode.key.equals(key)) {
                node.next = nextNode.next;
                size--;
                return node.value;
            }
            node = nextNode;
        }

        return null;
    }

    public int getSize() {
        return size;
    }

    private static class Node<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
