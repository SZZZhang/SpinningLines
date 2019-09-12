import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class SpinningLine {

    int LINE_STARTX = 400;
    int LINE_STARTY = 400;
    int LINE_LEN = 100;

    Double F = 0.01;
    Double DELTA_T = 0.01;

    AnimationTimer timer;

    private double frequency;
    private double length;
    private Group lineGroup;

    public double sin(double a, double f, double t) {
        return a * Math.sin(Math.toRadians(360 * f) * t);
    }

    public double cos(double a, double f, double t) {
        return a * Math.cos(Math.toRadians(360 * f) * t);
    }

    public SpinningLine (double frequency, double length, int startX, int endX) {
        this.frequency = frequency;
        this.length = length;

        lineGroup = new Group();

        timer = new AnimationTimer() {
            double interval = 0;
            @Override
            public void handle(long now) {
                lineGroup.getChildren().clear();
                Line line = new Line(LINE_STARTX, LINE_STARTY, cos(LINE_LEN, F, interval) + 300,
                        sin(LINE_LEN, F, interval) + 300);
                lineGroup.getChildren().add(line);
                interval += DELTA_T;
            }
        };
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}
