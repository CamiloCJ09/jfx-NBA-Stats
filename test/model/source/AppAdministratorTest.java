package model.source;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AppAdministratorTest {

    private AppAdministrator app;

    void setup1(){
        app = new AppAdministrator();
    }

    @Test
    void addPlayer(){
        setup1();
        app.addPlayer("Jose","Martinez",18,"Leakers",5,6,8,9,10);
        assertEquals("Jose",app.getArrayList().get(0).getName());
    }

    @Test
    void importPlayers() throws IOException {
        setup1();
        app.importPlayers("src/data/Dataset.csv");
        assertEquals("Kayla",app.getArrayList().get(0).getName());
    }
}