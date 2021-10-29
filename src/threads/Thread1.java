package threads;

import model.ownImplementation.classes.AVLTree;
import model.ownImplementation.classes.BinarySearchTree;
import model.source.Player;

import java.util.List;

public class Thread1 extends Thread{
    private BinarySearchTree<Player> byPoints;
    private AVLTree<Player> others;
    private int statistic;
    private List<Player> players;
    public Thread1(BinarySearchTree<Player> byPoints, List<Player> players) {
        this.byPoints = byPoints;
        this.players = players;
    }
    public Thread1(AVLTree<Player> others,List<Player> players, int statistic){
        this.others = others;
        this.statistic = statistic;
        this.players = players;
    }
    public void run() {
        if(byPoints != null){
            for (Player a: players) {
                a.changePrefStat(1);
                byPoints.addNode(a);
            }
        }else{
            for (Player a: players) {
                a.changePrefStat(statistic);
                others.addNode(a);
            }
        }
    }

    public BinarySearchTree<Player> getByPoints() {
        return byPoints;
    }

    public void setByPoints(BinarySearchTree<Player> byPoints) {
        this.byPoints = byPoints;
    }

    public AVLTree<Player> getOthers() {
        return others;
    }

    public void setOthers(AVLTree<Player> others) {
        this.others = others;
    }

    public int getStatistic() {
        return statistic;
    }

    public void setStatistic(int statistic) {
        this.statistic = statistic;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
