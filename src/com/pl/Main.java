package com.pl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("processor.fxml"));
        primaryStage.setTitle("File Processor v2.0");
        primaryStage.setScene(new Scene(root, 600, 275));

        //Toggle group of radio buttons
        ToggleGroup processGroup = new ToggleGroup();
        RadioButton tocFile = new RadioButton("Process ToC file");
        tocFile.setToggleGroup(processGroup);
        tocFile.setId("toc-file");






        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
