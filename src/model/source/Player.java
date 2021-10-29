package model.source;

public class Player implements Comparable<Player> {
    private String name;
    private String lastName;
    private int age;
    private String team;
    private double points;
    private double rebounds;
    private double assists;
    private double robberies;
    private double blocks;
    private int prefStat;

    public Player(String name, String lastName, int age, String team, double points, double rebounds, double assists, double robberies, double blocks) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.team = team;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.robberies = robberies;
        this.blocks = blocks;
        this.prefStat = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getRebounds() {
        return rebounds;
    }

    public void setRebounds(double rebounds) {
        this.rebounds = rebounds;
    }

    public double getAssists() {
        return assists;
    }

    public void setAssists(double assists) {
        this.assists = assists;
    }

    public double getRobberies() {
        return robberies;
    }

    public void setRobberies(double robberies) {
        this.robberies = robberies;
    }

    public double getBlocks() {
        return blocks;
    }

    public void setBlocks(double blocks) {
        this.blocks = blocks;
    }

    public void changePrefStat(int stat){
        prefStat = stat;
    }
    @Override
    public int compareTo(Player player) {
        switch(prefStat){
            case 1:
                return (int) (points-player.getPoints());
            case 2:
                return (int) (rebounds-player.getRebounds());
            case 3:
                return (int) (assists-player.getAssists());
            case 4:
                return  (int) (robberies-player.getRobberies());
            case 5:
                return  (int) (blocks-player.getBlocks());
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "assists=" + assists +
                '}';
    }
}
