package com.algorithms.hashtable;

@FunctionalInterface
public interface HashFunction<T> {
    int hash(T val);
}
