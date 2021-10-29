package model.ownImplementation.interfaces;
import model.ownImplementation.classes.Node;

public interface IBinarySearchTree<T , K extends Comparable<K>> {
    void addNode(T element, K key);
    void delete(K element);
    Node<T,K> search(K key);
    Node<T,K> successor(Node<T,K> current);
    Node<T,K> min(Node<T,K> node);
    boolean isEmpty();
    Node<T,K> max(Node<T,K> node);


}
