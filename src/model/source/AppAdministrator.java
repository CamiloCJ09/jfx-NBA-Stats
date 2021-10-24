package model.source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AppAdministrator {


    private ArrayList<Player> arrayList = new ArrayList<Player>();


    public void importPlayers(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
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
        arrayList.add(new Player(name, lastName, age, team, points, rebounds, assists, robberies,blocks));
    }

}
