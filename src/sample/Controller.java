package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import sample.Figures.*;

import java.io.*;

public class Controller {

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    private FigureList figureList = new FigureList();
    private MainFigure mainFigure = new Square();
    private String numb;

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
        mainFigure.NewObj();
        mainFigure.first.x=x1;
        mainFigure.first.y=y1;
        mainFigure.second.x=x2;
        mainFigure.second.y=y2;
        mainFigure.Draw(canvas);
        figureList.getFiguresList().add(mainFigure);
    }

    @FXML
    public void tapOnLine(ActionEvent event) {
       mainFigure = new Line();
    }

    @FXML
    public void tapOnCircle(ActionEvent event) {
        mainFigure = new Circle();
    }

    @FXML
    public void tapOnSquare(ActionEvent event) {
        mainFigure = new Square();
    }

    @FXML
    public void tapOnRect(ActionEvent event) {
        mainFigure = new Rectangle();
    }

    @FXML
    public void tapOnEllipse(ActionEvent event) {
        mainFigure = new Ellipse();
    }

    @FXML
    public void tapOnTriangle(ActionEvent event) {
        mainFigure = new Triangle();
    }

    @FXML
    public void tapOnClear(ActionEvent event) {
        canvas.getGraphicsContext2D().clearRect(0,0,1000,690);
        figureList.getFiguresList().clear();
    }

    @FXML
    public void Save(ActionEvent event) throws IOException {
        FileOutputStream fos = new FileOutputStream("serialization");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(int i = 0; i < figureList.getFiguresList().size();i++) {
            oos.writeObject(figureList.getFiguresList().get(i));
        }
        oos.flush();
        oos.close();

        numb = Integer.toString(figureList.getFiguresList().size());
        FileWriter writer = new FileWriter("numb.txt",false);
        writer.write(numb);
        writer.close();
    }

    @FXML
    public void Upload(ActionEvent event) throws IOException, ClassNotFoundException {
        BufferedReader in = new BufferedReader(new FileReader("numb.txt"));
        numb=in.readLine();
        int numb1 = Integer.parseInt(numb);
        FileInputStream fis = new FileInputStream("serialization");
        ObjectInputStream oin = new ObjectInputStream(fis);
        for (int i = 0; i< numb1; i++) {
            MainFigure upobj = (MainFigure) oin.readObject();
            figureList.getFiguresList().add(upobj);
            upobj.Draw(canvas);
        }
        oin.close();
    }
}
