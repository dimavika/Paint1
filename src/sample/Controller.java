package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Figures.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    private List<MainFigure> figureList = new ArrayList<>();
    private MainFigure mainFigure = new Line();

    @FXML
    Canvas canvas;

    @FXML
    public void mouseClick(MouseEvent event) {
        x1 = event.getSceneX();
        y1 = event.getSceneY() - 116;
        mainFigure = mainFigure.NewObj();
    }

    @FXML
    public void mouseDragged(MouseEvent event) {
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        x2 = event.getSceneX();
        y2 = event.getSceneY() - 116;
        mainFigure.first.x=x1;
        mainFigure.first.y=y1;
        mainFigure.second.x= x2;
        mainFigure.second.y= y2;
        PaintAll(figureList);
        mainFigure.Draw(canvas);
    }

    @FXML
    public void mouseRelease(MouseEvent event) {
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        x2 = event.getSceneX();
        y2 = event.getSceneY() - 116;
        mainFigure.first.x=x1;
        mainFigure.first.y=y1;
        mainFigure.second.x= x2;
        mainFigure.second.y= y2;
        figureList.add(mainFigure);
        PaintAll(figureList);
    }

    @FXML
    public void tapOnLine() {
       mainFigure = new Line();
    }

    @FXML
    public void tapOnCircle() {
        mainFigure = new Circle();
    }

    @FXML
    public void tapOnSquare() {
        mainFigure = new Square();
    }

    @FXML
    public void tapOnRect() {
        mainFigure = new Rectangle();
    }

    @FXML
    public void tapOnEllipse() {
        mainFigure = new Ellipse();
    }

    @FXML
    public void tapOnTriangle() {
        mainFigure = new Triangle();
    }

    @FXML
    public void tapOnClear() {
        canvas.getGraphicsContext2D().clearRect(0,0,1000,690);
        figureList.clear();
    }

    private void PaintAll(List<MainFigure> list){
        for (MainFigure obj: list){
            obj.Draw(canvas);
        }
    }

    @FXML
    public void Save() throws IOException {
        FileOutputStream fos = new FileOutputStream("serialization");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(figureList);
        oos.flush();
        oos.close();
    }

    @FXML
    public void Upload() throws IOException {
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        figureList.clear();
        FileInputStream fis = new FileInputStream("serialization");
        try {
            ObjectInputStream oin = new ObjectInputStream(fis);
            figureList = (List<MainFigure>) oin.readObject();
            PaintAll(figureList);
            oin.close();
        } catch (Exception ex){
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("error.fxml"));
            stage.setTitle("Error");
            stage.setScene(new Scene(root, 250, 250));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }
}
