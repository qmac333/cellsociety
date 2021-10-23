package cellsociety.display;

import cellsociety.controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.util.Duration;
import org.junit.jupiter.api.Test;
import javafx.stage.Stage;


import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisplayTest extends DisplayApplication {
    //The Cell Society program to run.
    private Controller cellSocietyProgram;

    //The JFX scene of the program.
    private Scene cellSocietyScene;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(String.format("%s %s", "Cell Society Test", "0.0.1"));
        initializeWindow(primaryStage);
        cellSocietyProgram = new Controller(primaryStage);
        initializeTimeline();
    }

    //Creates the JFX Timeline for our program, allowing the cellSocietyProgram to
    //update each tick.
    private void initializeTimeline() {
        Timeline myTimeline = new Timeline();
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.0/60), e -> cellSocietyProgram.update()));
        myTimeline.play();
    }

    //Initializes and displays the program window using the default window width and height.
    //Also gives the Controller access to the window's display group.
    private void initializeWindow(Stage myStage) {
        cellSocietyScene = new Scene(new Group(), 1280, 720);
        myStage.setScene(cellSocietyScene);
        myStage.show();
    }

    /**
     * Test loading file from Display.
     */
    @Test
    void SelectSimulationTest () {
        Button button = lookup("Load").query();
        clickOn(button);
    }

    /**
     * Test play and pause from Display.
     */
    @Test
    void PlayPauseTest () throws InterruptedException {
        Button button = lookup("Load").query();
        clickOn(button);

        TimeUnit.SECONDS.sleep(10);

        Button playButton = lookup("Play").query();
        clickOn(playButton);

        TimeUnit.SECONDS.sleep(5);

        Button pauseButton = lookup("Pause").query();
        clickOn(pauseButton);
    }

    /**
     * Test error message from Display.
     */
    @Test
    void ErrorTest () throws InterruptedException {
        //String BAD_FILE = "a bad file name"
        //File file = new File("./data/game_of_life/BAD_FILE");
        //cellSocietyProgram.loadFile(file);

        Button button = lookup("Load").query();
        clickOn(button);
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Test saving file
     */
    @Test
    void SaveTest (){
//        File file = new File("./data/game_of_life/blinkers.sim");
//
//        cellSocietyProgram.loadFile(file);
//        cellSocietyProgram.update();
//
//        Button button = lookup("Save").query();
//        clickOn(button);

    }
}


