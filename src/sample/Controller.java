package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    //private String modulePath = "out/production/Paint/sample/Figures/";
    private String modulePath = "./Figures/";

    private List<MainFigure> figureList = new ArrayList<>();
    private MainFigure mainFigure;
    private List<Class<?>> classList = new ArrayList<>();
    private List<Button> btnList = new ArrayList<>();


    @FXML
    Canvas canvas;

    @FXML
    Pane Pane1;

    @FXML
    ColorPicker colorPicker;

    public Controller() {
    }

    @FXML
    public void initialize(){
        colorPicker.setValue(Color.BLACK);
        ClassLoaderr loader = new ClassLoaderr(modulePath,"sample.Figures", ClassLoader.getSystemClassLoader());

        String absolutePath = new File(modulePath).getAbsolutePath();
        File dir = new File(absolutePath);
        String[] modules = dir.list();

        if (modules != null) {
            for (String module : modules) {
                try {
                    Pattern pattern = Pattern.compile(".class");
                    Matcher matcher = pattern.matcher(module);
                    if (matcher.find(0)) {
                        String moduleName = module.split(".class")[0];
                        Class clazz = loader.loadClass(moduleName);
                        classList.add(clazz);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        int five = 0;
        for (int i = 0; i < classList.size(); i++) {
            String btnName = classList.get(i).getName().substring(15);
            btnList.add(new Button(btnName));
            btnList.get(i).setPrefHeight(35);
            btnList.get(i).setPrefWidth(90);
            btnList.get(i).setLayoutY(10);
            if (i == 0) {
                btnList.get(i).setLayoutX(165);
            } else if (i<5){
                btnList.get(i).setLayoutX(100 * i + 165);
            } else {
                btnList.get(i).setLayoutX(five * 100 + 165);
                btnList.get(i).setLayoutY(54);
                five++;
            }
            final int e = i;
            btnList.get(i).setOnAction(event -> {
                try {
                    mainFigure = (MainFigure) classList.get(e).newInstance();
                } catch (InstantiationException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            });
            Pane1.getChildren().add(btnList.get(i));
        }
    }

    @FXML
    public void mouseClick(MouseEvent event) {
        x1 = event.getSceneX();
        y1 = event.getSceneY() - 110;
        if (mainFigure != null)
        mainFigure = mainFigure.NewObj();
    }

    @FXML
    public void mouseDragged(MouseEvent event) {
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        x2 = event.getSceneX();
        y2 = event.getSceneY() - 110;
        if (mainFigure != null) {
            mainFigure.first.x = x1;
            mainFigure.first.y = y1;
            mainFigure.second.x = x2;
            mainFigure.second.y = y2;
            canvas.getGraphicsContext2D().setStroke(colorPicker.getValue());
            mainFigure.Draw(canvas);
        }
        PaintAll(figureList);
    }

    @FXML
    public void mouseRelease(MouseEvent event) {
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        x2 = event.getSceneX();
        y2 = event.getSceneY() - 110;
        if (mainFigure != null) {
            mainFigure.first.x = x1;
            mainFigure.first.y = y1;
            mainFigure.second.x = x2;
            mainFigure.second.y = y2;
            mainFigure.setColor(colorPicker.getValue().toString());
            figureList.add(mainFigure);
        }
        PaintAll(figureList);
    }

    @FXML
    public void tapOnClear() {
        canvas.getGraphicsContext2D().clearRect(0,0,1000,690);
        figureList.clear();
    }

    private void PaintAll(List<MainFigure> list){
        for (MainFigure obj: list){
            canvas.getGraphicsContext2D().setStroke(Color.valueOf(obj.getColor()));
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
