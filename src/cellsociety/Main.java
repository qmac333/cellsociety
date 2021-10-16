package cellsociety;


import cellsociety.display.Display;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.stage.Stage;
import java.awt.Dimension;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A class to organize and initialize our JFX Application.
 *
 * @author William Convertino
 * @since 0.0.1
 */

public class Main extends Application {

    /**The current version of our CellSociety program.**/
    public static final String CURRENT_VERSION = "0.0.1";

    /**The language our program should initialize with is {@value #INIT_LANGUAGE}**/
    public static final String INIT_LANGUAGE = "English";

    /**The name of our program's window is {@value #WINDOW_NAME}**/
    public static final String WINDOW_NAME = "Cell Society";

    /**The default dimension of our program's window is {@value #WINDOW_DEFAULT_WIDTH} by {@value WINDOW_DEFAULT_HEIGHT}**/
    public static final double WINDOW_DEFAULT_WIDTH = 1280;
    public static final double WINDOW_DEFAULT_HEIGHT = 720;

    /**The number of frames per second the program should run at is {@value #FPS} **/
    public static final double FPS = 60;

    //The Cell Society program to run.
    private Controller cellSocietyProgram;

    /**
     * Starts our program as a JFX Application, giving the window a title
     * that contains our program name and the current version. Also initializes
     * our CellSociety program with a default language of {@value #INIT_LANGUAGE},
     * running at {@value #FPS} frames per second.
     *
     * @param primaryStage the JFX stage of our application.
     * @throws Exception if there is some error during start-up.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(String.format("%s %s",WINDOW_NAME,CURRENT_VERSION));
        cellSocietyProgram = new Controller();
        initializeTimeline();
        initializeWindow(primaryStage);
    }

    //Creates the JFX Timeline for our program, allowing the cellSocietyProgram to
    //update each tick.
    private void initializeTimeline() {
        Timeline myTimeline = new Timeline();
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.0/FPS), e -> cellSocietyProgram.update()));
        myTimeline.play();
    }

    //Initializes and displays the program window using the default window width and height.
    //Also gives the Controller access to the window's display group.
    private void initializeWindow(Stage myStage) {
        Scene myScene = new Scene(new Group(), WINDOW_DEFAULT_WIDTH, WINDOW_DEFAULT_HEIGHT);
        myStage.setScene(myScene);
        myStage.show();
    }

}
