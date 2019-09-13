import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

    int WINDOW_WIDTH = 800;
    int WINDOW_HEIGHT = 800;


    AnimationTimer timer;
    ArrayList<SpinningLine> spinningLines;
    Group spinningLinesGroup;
    Group dotsGroup;
    Group pathGroup;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Line axisX = new Line(0, WINDOW_HEIGHT/2, WINDOW_WIDTH, WINDOW_HEIGHT/2);
        Line axisY = new Line(WINDOW_WIDTH/2, 0, WINDOW_WIDTH/2, WINDOW_HEIGHT);
        root.getChildren().addAll(axisX, axisY);

        spinningLinesGroup = new Group();
        spinningLines = new ArrayList<>();
        dotsGroup = new Group();
        pathGroup = new Group();

        spinningLines.add(new SpinningLine(0.025, 100));
        spinningLines.add(new SpinningLine(0.05, 75));
        spinningLines.add(new SpinningLine(0.5, 50));

        //sets stuff
        spinningLines.get(0).setStartX(WINDOW_WIDTH/2);
        spinningLines.get(0).setStartY(WINDOW_HEIGHT/2);
        spinningLines.get(0).setEndX(WINDOW_WIDTH/2 + spinningLines.get(0).getLength());
        spinningLines.get(0).setEndY(WINDOW_HEIGHT/2);

        double prevLengths = 0;
        for (int i = 1; i < spinningLines.size(); i++) {
            prevLengths += spinningLines.get(i - 1).getLength();
            spinningLines.get(i).setStartX(prevLengths + WINDOW_WIDTH/2);
            spinningLines.get(i).setStartY(WINDOW_HEIGHT/2);
            spinningLines.get(i).setEndX(prevLengths + spinningLines.get(i).getLength() + WINDOW_WIDTH/2);
            spinningLines.get(i).setEndY(WINDOW_HEIGHT/2);
        }

        root.getChildren().addAll(spinningLinesGroup, dotsGroup, pathGroup);
        for (SpinningLine line : spinningLines) {
            spinningLinesGroup.getChildren().add(line.getLineGroup());
            line.startTimer();

            //finds combined length of all lines
        }



        Path path = new Path();
        MoveTo moveTo = new MoveTo(WINDOW_WIDTH/2 + prevLengths, WINDOW_HEIGHT/2);

        path.getElements().add(moveTo);
        pathGroup.getChildren().add(path);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                dotsGroup.getChildren().clear();

                for (int i = 1; i < spinningLines.size(); i++) {
                    double prevEndX = spinningLines.get(i - 1).getEndX();
                    double prevEndY = spinningLines.get(i - 1).getEndY();
                    Circle dot = new Circle(prevEndX, prevEndY, 5);

                    dotsGroup.getChildren().add(dot);
                    spinningLines.get(i).setStartX(prevEndX);
                    spinningLines.get(i).setStartY(prevEndY);
                }

                LineTo lineTo = new LineTo(spinningLines.get(spinningLines.size()-1).getEndX(),
                        spinningLines.get(spinningLines.size()-1).getEndY());
                path.getElements().add(lineTo);
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
