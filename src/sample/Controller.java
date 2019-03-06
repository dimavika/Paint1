package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import sample.Figures.*;

public class Controller {

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    private FigureList figureList = new FigureList();
    private MainFigure mainFigure = new Square();

    @FXML
    Canvas canvas;

    @FXML
    public void mouseClick(MouseEvent event) {
        x1 = event.getSceneX();
        y1 = event.getSceneY() - 116;
    }

    @FXML
    public void mouseRelease(MouseEvent event) {
        x2 = event.getSceneX();
        y2 = event.getSceneY() - 116;
        mainFigure.Draw(canvas, x1, y1, x2, y2);
    }

    @FXML
    public void tapOnLine(ActionEvent event) {
        mainFigure = new Line();
        figureList.getFiguresList().add(mainFigure);
    }

    @FXML
    public void tapOnCircle(ActionEvent event) {
        mainFigure = new Circle();
        figureList.getFiguresList().add(mainFigure);
    }

    @FXML
    public void tapOnSquare(ActionEvent event) {
        mainFigure = new Square();
        figureList.getFiguresList().add(mainFigure);
    }

    @FXML
    public void tapOnRect(ActionEvent event) {
        mainFigure = new Rectangle();
        figureList.getFiguresList().add(mainFigure);
    }

    @FXML
    public void tapOnEllipse(ActionEvent event) {

    }

    @FXML
    public void tapOnTriangle(ActionEvent event) {
        mainFigure = new Triangle();
        figureList.getFiguresList().add(mainFigure);
    }

    @FXML
    public void tapOnClear(ActionEvent event) {
    }
}
