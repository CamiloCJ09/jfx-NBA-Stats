package model.source;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player1;
    private Player player2;

    void setup1(){
        player1 = new Player("Andrew","Roberts",18,"Leakers",15,6,8,9,10);
        player2 = new Player("Jose","Martinez",18,"Leakers",5,6,8,9,10);
        player1.changePrefStat(1);
        player2.changePrefStat(1);
    }

    @Test
    void compareTo1(){
        setup1();
        assertEquals(10,player1.compareTo(player2));
    }


}