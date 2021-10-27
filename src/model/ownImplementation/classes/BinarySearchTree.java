package model.ownImplementation.classes;

import model.ownImplementation.interfaces.IBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends  Comparable<T>> implements IBinarySearchTree<T> {
    private Node<T> root;
    private String treeInfo;
    private int weight;
    private int height;
    private List<T> treeInList;
    public BinarySearchTree() {
        treeInfo = "";
        treeInList = new ArrayList<>();
    }

    @Override
    public void addNode(T element) {
        Node<T> newNode = new Node<>(element);
        if(root == null){
            root = newNode;
        }else{
            addNode(root,newNode);
        }

    }

    private void addNode(Node<T> root, Node<T> newNode){
        if(newNode.getValue().compareTo(root.getValue())<=0){
            if(root.getLeft()==null){
                root.setLeft(newNode);
                newNode.setParent(root);
            }else{
                addNode(root.getLeft(),newNode);
            }
        }else{
            if(root.getRight()==null){
                root.setRight(newNode);
                newNode.setParent(root);
            }else{
                addNode(root.getRight(),newNode);
            }
        }
    }

    @Override
    public void delete(T element) {
        Node<T> toDelete = search(root,element);
        System.out.println("Raiz: "+root.getValue());
        System.out.println("El que se elimina: "+toDelete.getValue());
        delete(toDelete);

    }

    @Override
    public boolean isEmpty() {
        return(root == null);
    }

    private void delete(Node<T> toDelete){
        if(toDelete!=null){
            if(toDelete.getLeft() == null && toDelete.getRight()== null){
                if(toDelete.getParent()!=null){
                    Node<T>aux = toDelete.getParent();
                    System.out.println("padre: "+aux.getValue());
                    if(aux.getLeft() != null ){
                        if(aux.getLeft().equals(toDelete)){
                            toDelete.getParent().setLeft(null);
                        }else{
                            toDelete.getParent().setRight(null);
                        }
                    }else if(aux.getRight()!=null){
                        toDelete.getParent().setRight(null);
                    }

                    //  System.out.println("No tiene hijos entonces entra acá");
                    toDelete.setParent(null);
                    //System.out.println(toDelete.getParent());

                }
            }else{
                if(toDelete.getLeft() == null){
                    //   System.out.println("Tiene hijo derecho");
                    Node<T> aux = toDelete.getRight();
                    toDelete.setRight(null);
                    aux.setParent(toDelete.getParent());
                    toDelete.getParent().setRight(aux);

                }else if(toDelete.getRight() == null){
                    // System.out.println("Tiene hijo izquierdo");
                    Node<T> aux = toDelete.getLeft();
                    toDelete.setLeft(null);
                    aux.setParent(toDelete.getParent());
                    toDelete.getParent().setLeft(aux);
                }else{
                    //System.out.println("Tiene ambos");
                    Node<T> successor = successor(toDelete);
                    toDelete.setValue(successor.getValue());
                    delete(successor);
                }
            }
        }

    }

    public Node<T> search(T key){
        return search(root,key);
    }
    @Override
    public Node<T> search(Node<T> root,T key) {
        if(root.getValue().compareTo(key)==0){
            // System.out.println("Es igual");
            return root;
        }else{
            // System.out.println("Compara "+root.getValue()+" con "+key);
            if(key.compareTo(root.getValue())>=0){
                //   System.out.println("Se va por la derecha");
                return search(root.getRight(), key);
            }else{
                // System.out.println("Se va por la Izquierda");
                return search(root.getLeft(),key);
            }
        }

    }


    @Override
    public Node<T> successor(Node<T> current) {
        if(current.getRight() != null){
            return min(current.getRight());
        }else if(current.getParent().getValue().compareTo(current.getValue()) > 0){
            return current.getParent();
        }else{
            return current.getParent().getParent();
        }
    }

    @Override
    public Node<T> min(Node<T> node) {
        if(node.getLeft()!=null){
            return min(node.getLeft());
        }else{
            return node;
        }

    }

    @Override
    public Node<T> max(Node<T> node){
        if(node.getRight()!=null){
            return min(node.getRight());
        }else{
            return node;
        }
    }

    public String printInOrder(){
        treeInfo = "";
        if(root!=null) {
            printInOrder(root);
        }

        return treeInfo;
    }

    private void printInOrder(Node<T> node){
        if(node!=null){
            printInOrder(node.getLeft());
            treeInfo+=node.getValue()+" ";
            printInOrder(node.getRight());
        }
    }

    public List<T> treeToList(){
        if(root!=null) {
            treeToList(root);
        }
        return treeInList;
    }
    private void treeToList(Node<T> node){
        if(node!=null){
            printInOrder(node.getLeft());
            treeInList.add(node.getValue());
            printInOrder(node.getRight());
        }
    }
    public int getWeight(){
        weight = 0;
        if(root!=null) {
            getWeight(root);
        }

        return weight;
    }

    private void getWeight(Node<T> node){
        if(node!=null){
            printInOrder(node.getLeft());
            weight++;
            printInOrder(node.getRight());
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public String getTreeInfo() {
        return treeInfo;
    }

    public void setTreeInfo(String treeInfo) {
        this.treeInfo = treeInfo;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
