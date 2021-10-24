package model.ownImplementation.classes;

public class Node<T> {
    private T value;
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;
    private Node<T> behind;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getBehind() {
        return behind;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setBehind(Node<T> behind) {
        this.behind = behind;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
