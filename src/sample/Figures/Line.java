package sample.Figures;

import javafx.scene.canvas.Canvas;

public class Line extends MainFigure{

    protected int XXX;
    public void Draw(Canvas canvas) {
        canvas.getGraphicsContext2D().strokeLine(first.x,first.y,second.x, second.y);
    }

    public MainFigure NewObj(){
        return (new Line());
    }

}
