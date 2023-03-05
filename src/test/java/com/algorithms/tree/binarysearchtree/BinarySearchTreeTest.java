package com.algorithms.tree.binarysearchtree;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    @Test
    public void noElementTest() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(Integer::compare);
        assertEquals(0, bst.size());
    }

    @Test
    public void singleElementInsertionTest() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(Integer::compare);
        bst.insert(1);
        assertEquals(1, bst.size());
        assertTrue(bst.contains(1));
        assertTrue(bst.delete(1));
    }

    @Test
    public void duplicateElementInsertionTest() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(Integer::compare);
        bst.insert(1);
        assertEquals(1, bst.size());

        bst.insert(1);
        assertEquals(1, bst.size());

        assertTrue(bst.contains(1));
        assertTrue(bst.delete(1));
    }

    @Test
    public void multiElementInsertionTest() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(Integer::compare);
        bst.insert(1);
        assertEquals(1, bst.size());
        assertTrue(bst.contains(1));

        bst.insert(2);
        assertEquals(2, bst.size());
        assertTrue(bst.contains(2));

        bst.insert(3);
        assertEquals(3, bst.size());
        assertTrue(bst.contains(3));

        assertTrue(bst.delete(1));
        assertTrue(bst.delete(2));
        assertTrue(bst.delete(3));
    }

    @Test
    public void multiElementInsertionAndDeletionTest() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(Integer::compare);
        List<Integer> elementToInsert = List.of(15,6,18,3,7,17,20,2,4,13,9);
        elementToInsert.forEach(bst::insert);

        elementToInsert.forEach(e -> {
            System.out.println(e);
            assertTrue(bst.contains(e));
            bst.delete(e);
            assertFalse(bst.contains(e));
        });

    }
}
