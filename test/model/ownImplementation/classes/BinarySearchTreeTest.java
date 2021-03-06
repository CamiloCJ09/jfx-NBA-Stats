package model.ownImplementation.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    private BinarySearchTree<Integer,Integer> tree;

    public void setup1() {

        tree = new BinarySearchTree<>();
    }

    public void setup2() {

        tree = new BinarySearchTree<>();
        tree.addNode(3,3);
        tree.addNode(5,5);


    }

    public void setup3() {

        tree = new BinarySearchTree<>();
        tree.addNode(3,3);
        tree.addNode(5,5);
        tree.addNode(61,61);
        tree.addNode(2,2);
        tree.addNode(7,7);
        tree.addNode(8,8);


    }
    @Test
    public void addNode1(){
        setup1();
        tree.addNode(3,3);
        tree.addNode(3,5);
        assertFalse(tree.isEmpty());


    }
    @Test
    public void delete(){
        setup2();
        tree.delete(5);
        System.out.println("Luego de borrar "+tree.printInOrder());
        assertEquals(1,tree.getWeight());
    }

    @Test
    public void search(){
        setup2();
        assertTrue(tree.search(5)!=null);
    }

    @Test
    public void successor1(){
        setup3();
        assertEquals(3, tree.successor(tree.search(2)).getValue().get(0));
    }
    @Test
    public void successor2(){
        setup3();
        assertEquals(7, tree.successor(tree.search(5)).getValue().get(0));
    }
    @Test
    public void successor3(){
        setup3();
        assertEquals(5, tree.successor(tree.search(3)).getValue().get(0));
    }
}