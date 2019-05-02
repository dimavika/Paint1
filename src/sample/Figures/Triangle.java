package sample.Figures;

import javafx.scene.canvas.Canvas;
import sample.MainFigure;

public class Triangle extends MainFigure {

    public void Draw(Canvas canvas) {
        canvas.getGraphicsContext2D().strokeLine(second.x,second.y,(first.x+(second.x-first.x)/2),first.y);
        canvas.getGraphicsContext2D().strokeLine((first.x+(second.x-first.x)/2),first.y,first.x,second.y);
        canvas.getGraphicsContext2D().strokeLine(second.x,second.y,first.x,second.y);
    }

    public MainFigure NewObj(){
        return (new Triangle());
    }
}