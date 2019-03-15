package sample.Figures;

import javafx.scene.canvas.Canvas;

public class Ellipse extends MainFigure {


    @Override
    public void Draw(Canvas canvas, double x1, double y1, double x2, double y2) {
        canvas.getGraphicsContext2D().strokeOval((x1<x2)?x1:x2, (y1<y2)?y1:y2, (x1<x2)?(x2-x1):(x1-x2), (y1<y2)?(y2-y1):(y1-y2));
    }
}
