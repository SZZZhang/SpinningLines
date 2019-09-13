import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class SpinningLine {

    int LINE_STARTX = 400;
    int LINE_STARTY = 400;
    int LINE_LEN = 100;

    Double F = 0.01;
    Double DELTA_T = 0.01;

    private AnimationTimer timer;

    private double frequency;
    private double length;
    private Group lineGroup;
    private Line line;
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public double sin(double a, double f, double t) {
        return a * Math.sin(Math.toRadians(360 * f) * t);
    }

    public double cos(double a, double f, double t) {
        return a * Math.cos(Math.toRadians(360 * f) * t);
    }

    public SpinningLine (double frequency, double length) {
        this.frequency = frequency;
        this.length = length;

        lineGroup = new Group();
        line = new Line();

        lineGroup.getChildren().add(line);
        timer = new AnimationTimer() {
            double interval = 0;
            @Override
            public void handle(long now) {
                endX = cos(length, frequency, interval) + startX;
                endY = sin(length, frequency, interval) + startY;

                line.setStartX(startX);
                line.setStartY(startY);
                line.setEndX(endX);
                line.setEndY(endY);

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

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public Group getLineGroup() {
        return this.lineGroup;
    }
}
