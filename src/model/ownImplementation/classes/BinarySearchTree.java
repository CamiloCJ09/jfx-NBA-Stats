package model.ownImplementation.classes;

import model.ownImplementation.interfaces.IBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T,K extends Comparable<K>> implements IBinarySearchTree<T,K> {
    private Node<T,K> root;
    private String treeInfo;
    private int weight;
    private int height;

    public BinarySearchTree() {
        treeInfo = "";
        //treeInList = new ArrayList<>();
    }



    @Override
    public void addNode(T element, K key) {
      //  System.out.println("Está agregando a "+element.toString());
        Node<T,K> newNode = new Node<>(element,key);
        if(root == null){
          //  System.out.println("Entra root");
            root = newNode;
        }else{
            addNode(root,newNode);
        }

    }

    private void addNode(Node<T,K> root, Node<T,K> newNode){
        if(newNode.getKey().compareTo(root.getKey())<=0){
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
    public void delete(K element) {
        Node<T,K> toDelete = search(element);
        System.out.println("Raiz: "+root.getValue());
        System.out.println("El que se elimina: "+toDelete.getValue());
        delete(toDelete);

    }

    @Override
    public Node<T,K> search( K key) {
        return search(root,key);
    }

    @Override
    public boolean isEmpty() {
        return(root == null);
    }

    private void delete(Node<T,K> toDelete){
        if(toDelete!=null){
            if(toDelete.getLeft() == null && toDelete.getRight()== null){
                if(toDelete.getParent()!=null){
                    Node<T,K>aux = toDelete.getParent();
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
                    Node<T, K> aux = toDelete.getRight();
                    toDelete.setRight(null);
                    aux.setParent(toDelete.getParent());
                    toDelete.getParent().setRight(aux);

                }else if(toDelete.getRight() == null){
                    // System.out.println("Tiene hijo izquierdo");
                    Node<T, K> aux = toDelete.getLeft();
                    toDelete.setLeft(null);
                    aux.setParent(toDelete.getParent());
                    toDelete.getParent().setLeft(aux);
                }else{
                    //System.out.println("Tiene ambos");
                    Node<T,K> successor = successor(toDelete);
                    toDelete.setValue(successor.getValue());
                    delete(successor);
                }
            }
        }

    }





    public Node<T,K> search(Node<T,K> root, K key) {
        if (root == null) {
            return null;
        }else {
            if (root.getKey().compareTo(key) == 0) {
                // System.out.println("Es igual");
                return root;
            } else {
                // System.out.println("Compara "+root.getValue()+" con "+key);
                if (key.compareTo(root.getKey()) >= 0) {
                    //   System.out.println("Se va por la derecha");
                    return search(root.getRight(), key);
                } else {
                    // System.out.println("Se va por la Izquierda");
                    return search(root.getLeft(), key);
                }
            }

        }

    }


    @Override
    public Node<T,K> successor(Node<T,K> current) {
        if(current.getRight() != null){
            return min(current.getRight());
        }else if(current.getParent().getKey().compareTo(current.getKey()) > 0){
            return current.getParent();
        }else{
            return current.getParent().getParent();
        }
    }

    @Override
    public Node<T,K> min(Node<T,K> node) {
        if(node.getLeft()!=null){
            return min(node.getLeft());
        }else{
            return node;
        }

    }

    @Override
    public Node<T,K> max(Node<T,K> node){
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

    private void printInOrder(Node<T,K> node){
        if(node!=null){
            printInOrder(node.getLeft());
            treeInfo+=node.getValue().toString()+" ";
            printInOrder(node.getRight());
        }
    }

    public List<T> treeToList(){
        List<T> treeInList = new ArrayList<>();
        if(root!=null) {
            treeToList(root, treeInList);
        }
        return treeInList;
    }
    private void treeToList(Node<T,K> node,List<T> treeInList){
        if(node!=null){
            treeToList(node.getLeft(), treeInList);
            treeInList.add(node.getValue());
            treeToList(node.getRight(), treeInList);

        }
    }
    public int getWeight(){
        weight = 0;
        if(root!=null) {
            getWeight(root);
        }

        return weight;
    }

    private void getWeight(Node<T,K> node){
        if(node!=null){
            printInOrder(node.getLeft());
            weight++;
            printInOrder(node.getRight());
        }
    }

    public Node<T,K> getRoot() {
        return root;
    }

    public void setRoot(Node<T,K> root) {
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
