package threads;

import model.ownImplementation.classes.AVLTree;
import model.ownImplementation.classes.BinarySearchTree;
import model.source.Player;

import java.util.List;

public class Thread1 extends Thread{
    private BinarySearchTree<Player,Double> byPoints;
    private AVLTree<Player,Double> others;
    private int statistic;
    private List<Player> players;
    public Thread1(BinarySearchTree<Player,Double> byPoints, List<Player> players) {
        this.byPoints = byPoints;
        this.players = players;
    }
    public Thread1(AVLTree<Player,Double> others,List<Player> players, int statistic){
        this.others = others;
        this.statistic = statistic;
        this.players = players;
    }
    public void run() {
        if(byPoints != null){
            for (Player a: players) {
                a.changePrefStat(1);
                byPoints.addNode(a, a.getPoints());
            }
        }else{
            for (Player a: players) {
               // System.out.println("Aca");
                a.changePrefStat(statistic);
                switch (statistic){
                    case 2:   System.out.println("Acaaaaaa");
                        others.addNode(a,a.getRebounds());
                        break;
                    case 3:
                        others.addNode(a,a.getAssists());
                        break;
                    case 4:others.addNode(a,a.getRobberies());
                        break;
                    case 5: others.addNode(a, a.getBlocks());
                        break;
                }


            }


        }
    }

    public BinarySearchTree<Player,Double> getByPoints() {
        return byPoints;
    }

    public void setByPoints(BinarySearchTree<Player,Double> byPoints) {
        this.byPoints = byPoints;
    }

    public AVLTree<Player,Double> getOthers() {
        return others;
    }

    public void setOthers(AVLTree<Player,Double> others) {
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
