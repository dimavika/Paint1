package sample.Figures;

import javafx.scene.canvas.Canvas;
import sample.MainFigure;

public class Circle extends MainFigure {

    public void Draw(Canvas canvas) {

        double sizeX = (first.x < second.x) ? (second.x - first.x) : (first.x - second.x);
        double sizeY = (first.y < second.y) ? (second.y - first.y) : (first.y - second.y);
        double sizeMin = (sizeX < sizeY) ? sizeX : sizeY;
        canvas.getGraphicsContext2D().strokeOval((first.x < second.x) ? first.x : second.x, (first.y < second.y) ? first.y : second.y, sizeMin, sizeMin);
    }

    public MainFigure NewObj(){
        return (new Circle());
    }

}
