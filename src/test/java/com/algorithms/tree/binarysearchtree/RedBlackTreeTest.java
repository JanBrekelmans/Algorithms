package com.algorithms.tree.binarysearchtree;

import com.algorithms.tree.redblack.RedBlackTree;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class RedBlackTreeTest {

    @Test
    public void test() throws IOException {
        RedBlackTree<Integer> bst = new RedBlackTree<>(Integer::compare);
        List<Integer> elementToInsert = List.of(15,6,18,3,7,17,20,2,4,13,9,44,34,23,12,64,23);
        elementToInsert.forEach(bst::insert);
        bst.createTreeImage(Path.of(new File("").getAbsolutePath(), "img.png").toString());
    }
}
