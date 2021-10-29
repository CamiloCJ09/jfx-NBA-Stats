package model.ownImplementation.classes;

public class Node<T, K extends Comparable<K>> {
    private T value;
    private K key;
    //Tree attributes
    private Node<T,K> parent;
    private Node<T,K> left;
    private Node<T,K> right;


    //List attributes
    private Node<T,K> next;
    private Node<T,K> behind;

    public Node(T value, K key) {
        this.value = value;
        this.key = key;
    }

    public Node<T,K> getParent() {
        return parent;
    }

    public void setParent(Node<T,K> parent) {
        this.parent = parent;
    }

    public Node<T,K> getLeft() {
        return left;
    }

    public void setLeft(Node<T,K> left) {
        this.left = left;
    }

    public Node<T,K> getRight() {
        return right;
    }

    public void setRight(Node<T,K> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T,K> getNext() {
        return next;
    }


    public void setNext(Node<T,K> next) {
        this.next = next;
    }

    public Node<T,K> getBehind() {
        return behind;
    }

    public void setBehind(Node<T,K> behind) {
        this.behind = behind;
    }

    public K getKey() {
        return key;
    }
}

