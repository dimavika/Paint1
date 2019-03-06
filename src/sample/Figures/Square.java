package sample.Figures;

import javafx.scene.canvas.Canvas;

public class Square extends MainFigure {

    @Override
    public void Draw(Canvas canvas, double x1, double y1, double x2, double y2) {
        double sizeX = (x1 < x2) ? (x2 - x1) : (x1 - x2);
        double sizeY = (y1 < y2) ? (y2 - y1) : (y1 - y2);
        double sizeMin = (sizeX < sizeY) ? sizeX : sizeY;
        canvas.getGraphicsContext2D().strokeRect((x1 < x2) ? x1 : x2, (y1 < y2) ? y1 : y2, sizeMin, sizeMin);
    }

}
