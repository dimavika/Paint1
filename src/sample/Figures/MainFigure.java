package sample.Figures;

import javafx.scene.canvas.Canvas;
import sample.Coordinate;
import java.io.Serializable;

public abstract class MainFigure implements Serializable {

    public Coordinate getFirst() {
        return first;
    }

    public void setFist(Coordinate first) {
        this.first = first;
    }

    public Coordinate getSecond() {
        return second;
    }

    public void setSecond(Coordinate second) {
        this.second = second;
    }

    public Coordinate first= new Coordinate();
    public Coordinate second =new Coordinate();

    abstract public void Draw(Canvas canvas);
    abstract public MainFigure NewObj();
}
