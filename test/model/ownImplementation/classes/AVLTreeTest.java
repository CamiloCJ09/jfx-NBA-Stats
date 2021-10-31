package model.ownImplementation.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    AVLTree<Integer, Integer> tree;

    void setUp1(){
        tree = new AVLTree<>();
        tree.addNode(1,5);
        tree.addNode(1,3);
        tree.addNode(1,8);
        tree.addNode(2, 6);
        tree.addNode(1,1);
    }

    void setUp2(){
        tree = new AVLTree<>();
        tree.addNode(1,5);
        tree.addNode(1,3);
        tree.addNode(1,8);
        tree.addNode(1, 6);
        tree.addNode(1,1);
        tree.addNode(1, 9);
        tree.addNode(1, 10);
    }

    @Test
    void testIsBalanced() {
        setUp1();
        assertTrue(tree.isBalanced());
    }

    @Test
    void testIsBalanced2() {
        setUp2();
        assertTrue(tree.isBalanced());
    }

    @Test
    void addNode() {
        setUp1();
        tree.addNode(2,11);
        assertEquals(2,tree.search(11).getValue().get(0));
    }

    @Test
    void delete() {
        setUp2();
        tree.delete(10);
        assertEquals(9,tree.max().getKey());
    }

    @Test
    void testSearch() {
        setUp1();
        assertEquals(2,tree.search(6).getValue().get(0));
    }

    @Test
    void testSuccessor() {
        setUp1();
        assertEquals(8,tree.successor(tree.search(6)).getKey());
    }

    @Test
    void testMin() {
        setUp1();
        assertEquals(8,tree.max().getKey());
    }

    @Test
    void testMax() {
        setUp1();
        assertEquals(1,tree.min().getKey());
    }


    @Test
    void getHeight() {
        setUp1();
        assertEquals(3,tree.getHeight());
    }

    @Test
    void getRollingFactor() {
        setUp1();
        assertEquals(0,tree.getRollingFactor());
    }
}