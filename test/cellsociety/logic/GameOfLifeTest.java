package cellsociety.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {
    private GameOfLife gol;

    @BeforeEach
    void setUp(){
        gol = new GameOfLife(100, 100);
        gol.initializeWithCSVFile("data/game_of_life/blinkers.csv");
        gol.initializeWithSimFile("data/game_of_life/blinkers.sim");
    }

    @Test
    void updateTest(){
        gol.update();
        for(int i = 0; i < gol.getGrid().getWidth(); i++){
            for(int j = 0; j < gol.getGrid().getHeight(); j++){
                System.out.print(gol.getGrid().getCell(i, j) + ",");
            }
            System.out.println("");
        }

    }
}