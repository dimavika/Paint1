package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.io.Serializable;

public abstract class MainFigure implements Serializable {


    public Coordinate first= new Coordinate();
    public Coordinate second =new Coordinate();
    private String color;

    abstract public void Draw(Canvas canvas);
    abstract public MainFigure NewObj();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
