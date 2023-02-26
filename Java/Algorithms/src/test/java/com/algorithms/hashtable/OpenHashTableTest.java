package com.algorithms.hashtable;

import org.junit.Test;

import static org.junit.Assert.*;

public class OpenHashTableTest {
    @Test
    public void emptyHashTableTest() {
        OpenHashTable<Integer, String> hashTable = new OpenHashTable<>(i -> i, 10);
        assertEquals(0, hashTable.getSize());

        assertNull(hashTable.get(1));
    }

    @Test
    public void singleElementHashTableTest() {
        OpenHashTable<Integer, String> hashTable = new OpenHashTable<>(i -> i, 10);
        hashTable.put(1, "1");
        assertEquals(1, hashTable.getSize());
        assertEquals("1", hashTable.get(1));
    }

    @Test
    public void duplicateSameElementTest() {
        OpenHashTable<Integer, String> hashTable = new OpenHashTable<>(i -> i, 10);
        hashTable.put(1, "1");
        hashTable.put(1, "1");
        assertEquals(1, hashTable.getSize());
        assertEquals("1", hashTable.get(1));
    }

    @Test
    public void removeElementTest() {
        OpenHashTable<Integer, String> hashTable = new OpenHashTable<>(i -> i, 10);
        hashTable.put(1, "1");
        assertEquals("1", hashTable.remove(1));
        assertEquals(0, hashTable.getSize());
    }

    @Test
    public void removeElementRepeatedTest() {
        OpenHashTable<Integer, String> hashTable = new OpenHashTable<>(i -> i, 10);
        hashTable.put(1, "1");
        hashTable.remove(1);
        hashTable.put(1, "1");
        hashTable.remove(1);
        hashTable.put(1, "1");
        hashTable.remove(1);
        assertEquals(0, hashTable.getSize());
    }

    @Test
    public void addElementsIntoSameBucketTest() {
        OpenHashTable<Integer, String> hashTable = new OpenHashTable<>(i -> 0, 10);
        hashTable.put(1, "1");
        hashTable.put(2, "2");
        hashTable.put(3, "3");
        assertEquals(3, hashTable.getSize());

        hashTable.remove(2);
        assertEquals(2, hashTable.getSize());
        assertEquals("3", hashTable.get(3));
    }
}
