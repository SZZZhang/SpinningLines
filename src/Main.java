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


    AnimationTimer timer;
    ArrayList<SpinningLine> spinningLines;
    Group spinningLinesGroup;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Line axisX = new Line(0, WINDOW_HEIGHT/2, WINDOW_WIDTH, WINDOW_HEIGHT/2);
        Line axisY = new Line(WINDOW_WIDTH/2, 0, WINDOW_WIDTH/2, WINDOW_HEIGHT);
        root.getChildren().addAll(axisX, axisY);

        spinningLinesGroup = new Group();
        spinningLines = new ArrayList<>();

        spinningLines.add(new SpinningLine(0, 100));
        spinningLines.add(new SpinningLine(0.5, 75));
        spinningLines.add(new SpinningLine(0.5, 50));

        root.getChildren().add(spinningLinesGroup);
        for (SpinningLine line : spinningLines) {
            spinningLinesGroup.getChildren().add(line.getLineGroup());
            line.startTimer();
        }

        spinningLines.get(0).setStartX(WINDOW_WIDTH/2);
        spinningLines.get(0).setStartY(WINDOW_HEIGHT/2);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                for (int i = 1; i < spinningLines.size(); i++) {
                    double prevEndX = spinningLines.get(i - 1).getEndX();
                    double prevEndY = spinningLines.get(i - 1).getEndY();

                    spinningLines.get(i).setStartX(prevEndX);
                    spinningLines.get(i).setStartY(prevEndY);
                }
            }
        };

        timer.start();


        primaryStage.setTitle("Spinning lines!");
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
