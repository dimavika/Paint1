package sample.Figures;

import javafx.scene.canvas.Canvas;
import sample.MainFigure;

public class Ellipse extends MainFigure {


    public void Draw(Canvas canvas) {
        canvas.getGraphicsContext2D().strokeOval((first.x<second.x)?first.x:second.x, (first.y<second.y)?first.y:second.y, (first.x<second.x)?(second.x-first.x):(first.x-second.x), (first.y<second.y)?(second.y-first.y):(first.y-second.y));
    }

    public MainFigure NewObj(){
        return (new Ellipse());
    }

}