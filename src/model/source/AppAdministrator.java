package model.source;

import model.ownImplementation.classes.AVLTree;
import model.ownImplementation.classes.BinarySearchTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AppAdministrator {


    private ArrayList<Player> arrayList = new ArrayList<>();
    private BinarySearchTree<Player,Double> byPoints = new BinarySearchTree<>();
    private AVLTree<Player,Double> byRebounds = new AVLTree<>();
    private AVLTree<Player,Double> byAssits = new AVLTree<>();
    private AVLTree<Player,Double> byRobberies = new AVLTree<>();
    private AVLTree<Player,Double> byBlocks= new AVLTree<>();

    public void importPlayers(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        line = br.readLine();
        while(line != null){
            String[] parts = line.split(",");
            String name = parts[0];
            String lastName = parts[1];
            int age = Integer.parseInt(parts[2]);
            String team = parts[3];
            double points = Double.parseDouble(parts[4]);
            double rebounds = Double.parseDouble(parts[5]);
            double assists = Double.parseDouble(parts[6]);
            double robberies = Double.parseDouble(parts[7]);
            double blocks = Double.parseDouble(parts[8]);

            addPlayer(name, lastName, age, team, points, rebounds, assists, robberies,blocks);
            line = br.readLine();
        }
    }

    public void addPlayer(String name, String lastName, int age, String team, double points,
                          double rebounds, double assists, double robberies, double blocks){
        Player p = new Player(name, lastName, age, team, points, rebounds, assists, robberies,blocks);
        arrayList.add(p);
    }

    public ArrayList<Integer> searchPlayer(String firstName, String lastName){
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i<arrayList.size(); i++){
            if(arrayList.get(i).getName() == firstName && arrayList.get(i).getLastName() == lastName){
                index.add(i);
            }
        }
        return index;
    }

    public ArrayList<Player> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Player> arrayList) {
        this.arrayList = arrayList;
    }

    public BinarySearchTree<Player,Double> getByPoints() {
        return byPoints;
    }

    public void setByPoints(BinarySearchTree<Player,Double> byPoints) {
        this.byPoints = byPoints;
    }

    public AVLTree<Player,Double> getByRebounds() {
        return byRebounds;
    }

    public void setByRebounds(AVLTree<Player,Double> byRebounds) {
        this.byRebounds = byRebounds;
    }

    public AVLTree<Player,Double> getByAssits() {
        return byAssits;
    }

    public void setByAssits(AVLTree<Player,Double> byAssits) {
        this.byAssits = byAssits;
    }

    public AVLTree<Player,Double> getByRobberies() {
        return byRobberies;
    }

    public void setByRobberies(AVLTree<Player,Double> byRobberies) {
        this.byRobberies = byRobberies;
    }

    public AVLTree<Player,Double> getByBlocks() {
        return byBlocks;
    }

    public void setByBlocks(AVLTree<Player,Double> byBlocks) {
        this.byBlocks = byBlocks;
    }
}
