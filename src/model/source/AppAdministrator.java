package model.source;

import model.ownImplementation.classes.BinarySearchTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AppAdministrator {


    private ArrayList<Player> arrayList = new ArrayList<>();
    private BinarySearchTree<Player> byPoints = new BinarySearchTree<>();
    private BinarySearchTree<Player> byRebounds = new BinarySearchTree<>();
    private BinarySearchTree<Player> byAssits = new BinarySearchTree<>();
    private BinarySearchTree<Player> byRobberies = new BinarySearchTree<>();
    private BinarySearchTree<Player> byBlocks= new BinarySearchTree<>();

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

    public void addPlayer(String name, String lastName, int age, String team, double points, double rebounds, double assists, double robberies, double blocks){
        Player p = new Player(name, lastName, age, team, points, rebounds, assists, robberies,blocks);
        arrayList.add(p);
        p.changePrefStat(1);
        byPoints.addNode(p);
        p.changePrefStat(2);
        byRebounds.addNode(p);
        p.changePrefStat(3);
        byAssits.addNode(p);
        p.changePrefStat(4);
        byRobberies.addNode(p);
    }

    public ArrayList<Player> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Player> arrayList) {
        this.arrayList = arrayList;
    }

    public BinarySearchTree<Player> getByPoints() {
        return byPoints;
    }

    public void setByPoints(BinarySearchTree<Player> byPoints) {
        this.byPoints = byPoints;
    }

    public BinarySearchTree<Player> getByRebounds() {
        return byRebounds;
    }

    public void setByRebounds(BinarySearchTree<Player> byRebounds) {
        this.byRebounds = byRebounds;
    }

    public BinarySearchTree<Player> getByAssits() {
        return byAssits;
    }

    public void setByAssits(BinarySearchTree<Player> byAssits) {
        this.byAssits = byAssits;
    }

    public BinarySearchTree<Player> getByRobberies() {
        return byRobberies;
    }

    public void setByRobberies(BinarySearchTree<Player> byRobberies) {
        this.byRobberies = byRobberies;
    }

    public BinarySearchTree<Player> getByBlocks() {
        return byBlocks;
    }

    public void setByBlocks(BinarySearchTree<Player> byBlocks) {
        this.byBlocks = byBlocks;
    }
}
