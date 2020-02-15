package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main extends Application {
    Stage window;
    Scene scene;
    Button button;
    Button startbutton;
    String prevTime;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Time Study");
        startbutton = new Button("START");
        button = new Button("LAP");

        String st[] = {"Apple","Banana","Cat"};

        startbutton.setOnAction(event -> getStartTime());

        ComboBox c = new ComboBox(FXCollections.observableArrayList(st));
        c.setPromptText("Select");
        c.setEditable(true);

        button.setOnAction(e -> getChoice(c));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(startbutton, c, button);
        scene = new Scene(layout, 200,200);
        window.setScene(scene);
        window.show();
    }

    public void getChoice(ComboBox<String> c) {
        String currentTime = getCurrentTime();
        String choice = c.getValue();
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("timestudy.csv", true));

            writer.write(choice+","+prevTime+","+currentTime+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prevTime = currentTime;
    }

    public String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm:ss");  //it will give you the date in the formate that is given in the image
        String datetime = dateformat.format(calendar.getTime()); // it will give you the date
        return datetime;
    }

    public void getStartTime() {
        prevTime = getCurrentTime();
    }
}