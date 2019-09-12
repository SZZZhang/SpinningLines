import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

    int WINDOW_WIDTH = 800;
    int WINDOW_HEIGHT = 800;



    ArrayList<Line> spinningLines;
    Group linesGroup;
    AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Line axisX = new Line(0, WINDOW_HEIGHT/2, WINDOW_WIDTH, WINDOW_HEIGHT/2);
        Line axisY = new Line(WINDOW_WIDTH/2, 0, WINDOW_WIDTH/2, WINDOW_HEIGHT);
        root.getChildren().addAll(axisX, axisY);

        linesGroup = new Group();
        spinningLines = new ArrayList<>();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        }


        for (SpinningLine line : spinningLines) {
            spinningLinesGroup.getChildren().add(line.getLineGroup());
            line.startTimer();
        }







        primaryStage.setTitle("Spinning lines!");
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
