package model.ownImplementation.classes;


import model.ownImplementation.interfaces.IBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<T, K extends Comparable<K>>implements IBinarySearchTree<T,K> {
    private Node<T,K> root;
    private String treeInfo;
    private int weight;
    private int height;
    private int rollingFactor;
    public static final int HIGH_DIFFERENCE = 1;


    public AVLTree() {
        treeInfo = "";
        rollingFactor = 0;
    }


    public boolean isBalanced(){
        return isBalanced(root);
    }

    public boolean isBalanced(Node<T,K> root){
        if(root.getRight() != null && root.getLeft() !=null){
           // System.out.println("No es hoja");
            rollingFactor = getHeight(root.getRight()) - getHeight(root.getLeft());
            return getHeight(root.getRight()) - getHeight(root.getLeft()) <= HIGH_DIFFERENCE && getHeight(root.getRight()) - getHeight(root.getLeft()) > -2;
        }else if(root.getRight()!=null){
           // System.out.println("Tampoco es hoja");
            rollingFactor = getHeight(root.getRight());
            return getHeight(root.getRight()) <=HIGH_DIFFERENCE && getHeight(root.getRight())> -2;
        } else if(root.getLeft()!=null){
           // System.out.println("Sigue sin ser hoja");
            rollingFactor = -getHeight(root.getLeft());
            return -getHeight(root.getLeft()) <=HIGH_DIFFERENCE && -getHeight(root.getLeft())> -2;
        }else{
           // System.out.println("Es hoja");
            return true;
        }



    }

    public void balance(Node<T,K> root){
        if(!isBalanced(root)){
            if(rollingFactor>1){
               // System.out.println("Cargado a la derecha");
                if (getRollingFactor(root.getRight()) < 0) {
                   // System.out.println("Se rota doble izquierda");
                    rigthRotate(root.getRight());
                }else{
                 //   System.out.println("se rota simple derecha");
                    leftRotate(root);
                }
            }else{
              //  System.out.println("Cargado a la Izquierda");
                if (getRollingFactor(root.getLeft()) > 0) {
                    System.out.println("Se rota doble derecha");
                    leftRotate(root.getLeft());
                }else{
                    //System.out.println("se rota simple derecha");
                    rigthRotate(root);
                }

            }

            balance(root);
        }
    }

    /*
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

     */


    @Override
    public void addNode(T element, K key) {
        Node<T,K> newNode = new Node<>(element,key);
        if(root == null){
            root = newNode;
        }else{
            addNode(root,newNode);
        }

    }

    private void addNode(Node<T,K> root, Node<T,K> newNode){
        if(newNode.getKey().compareTo(root.getKey())<0){
            if(root.getLeft()==null){
                root.setLeft(newNode);
                newNode.setParent(root);
            }else{
                addNode(root.getLeft(),newNode);
            }
        }else if(newNode.getKey().compareTo(root.getKey())==0){
            root.getValue().add(newNode.getValue().get(0));
        }else{
            if(root.getRight()==null){
                root.setRight(newNode);
                newNode.setParent(root);
            }else{
                addNode(root.getRight(),newNode);
            }
        }


        if(!isBalanced(root)){
            //System.out.println("Va a balancear");
            balance(root);
        }else {
            //System.out.println("Balance?: "+isBalanced());
        }
    }

    @Override
    public void delete(K element) {
        Node<T,K> toDelete = search(root,element);
        // System.out.println("Raiz: "+root.getValue());
        // System.out.println("El que se elimina: "+toDelete.getValue());
        delete(toDelete);

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
                    //System.out.println("padre: "+aux.getValue());
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


                    if(!isBalanced(aux)){
                        balance(aux);
                    }



                }
            }else{
                if(toDelete.getLeft() == null){
                    //System.out.println("Tiene hijo derecho");
                    Node<T,K> aux = toDelete.getRight();
                    toDelete.setRight(null);
                    aux.setParent(toDelete.getParent());
                    toDelete.getParent().setRight(aux);


                    if(!isBalanced(aux)){
                        balance(aux);
                    }



                }else if(toDelete.getRight() == null){
                    // System.out.println("Tiene hijo izquierdo");
                    Node<T,K> aux = toDelete.getLeft();
                    toDelete.setLeft(null);

                    aux.setParent(toDelete.getParent());

                    toDelete.getParent().setLeft(aux);
                    if(!isBalanced(aux)){
                        balance(aux);
                    }



                }else{
                    //System.out.println("Tiene ambos");
                    Node<T,K> successor = successor(toDelete);
                    toDelete.setValue(successor.getValue());
                    delete(successor);

                    if(!isBalanced(toDelete)){
                        balance(toDelete);
                    }


                }
            }



        }





    }

    @Override
    public Node<T,K> search(K key){
        return search(root,key);
    }

    public Node<T,K> search(Node<T,K> root,K key) {
        if(root!=null){
            if(root.getKey().compareTo(key)==0){
                // System.out.println("Es igual");
                return root;
            }else{
                // System.out.println("Compara "+root.getValue()+" con "+key);
                if(key.compareTo(root.getKey())>=0){
                    //   System.out.println("Se va por la derecha");
                    return search(root.getRight(), key);
                }else{
                    // System.out.println("Se va por la Izquierda");
                    return search(root.getLeft(),key);
                }
            }
        }else {
            return  null;
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

    public Node<T,K> min(){
        return min(root);
    }
    @Override
    public Node<T,K> min(Node<T,K> node) {
        if(node.getLeft()!=null){
            return min(node.getLeft());
        }else{
            return node;
        }

    }

    public Node<T,K> max(){
        return max(root);
    }

    @Override
    public Node<T,K> max(Node<T,K> node){
        if(node.getRight()!=null){
            return max(node.getRight());
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
            treeInfo+=node.getKey()+" ";
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

    public int getHeight(){
        return getHeight(root);
    }

    private int getHeight(Node<T,K> node) {
        if(node == null){
            return 0;
        }else{
            return 1+ Math.max(getHeight(node.getLeft()),getHeight(node.getRight()));
        }

    }

    public int getRollingFactor() {
        return rollingFactor;
    }

    private int getRollingFactor(Node<T,K> node){
        return getHeight(node.getRight())-getHeight(node.getLeft());
    }

    public void rigthRotate(Node<T,K> node){
        //System.out.println("Se rota: "+node.getValue()+" a la derecha");
        Node<T,K> aux = node.getLeft();
        node.setLeft(aux.getRight());
        if(aux.getRight()!=null){
            aux.getRight().setParent(node);
        }
        aux.setParent(node.getParent());

        if(node.getParent()==null){
            root = aux;
        }else{
            if(node.getKey().compareTo(node.getParent().getKey())<0){
                node.getParent().setLeft(aux);
            }else{
                node.getParent().setRight(aux);
            }
        }

        aux.setRight(node);
        node.setParent(aux);


    }

    public void leftRotate(Node<T,K> node){
        //System.out.println("Se rota: "+node.getValue()+" a la izquierda");
        Node<T,K> aux = node.getRight();
        node.setRight(aux.getLeft());
        if(aux.getLeft()!=null){
            aux.getLeft().setParent(node);
        }
        aux.setParent(node.getParent());
        if(node.getParent()==null){
            root = aux;
        }else{
            if(node.getKey().compareTo(node.getParent().getKey())<0){
                node.getParent().setLeft(aux);
            }else{
                node.getParent().setRight(aux);
            }
        }
        aux.setLeft(node);
        node.setParent(aux);

    }


}
