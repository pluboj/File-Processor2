package com.pl;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("File Processor v2.0");

        //Title
        Text title = new Text("File Processor");
        title.setId("title");

        //Toggle group of radio buttons
        ToggleGroup processGroup = new ToggleGroup();

        RadioButton crFile = new RadioButton("Process CopyRecall File");
        crFile.setToggleGroup(processGroup);
        crFile.setId("cr-file");
        crFile.setSelected(true);

        RadioButton tocFile = new RadioButton("Process ToC File");
        tocFile.setToggleGroup(processGroup);
        tocFile.setId("toc-file");

        RadioButton tocUrl = new RadioButton("Process ToC URL");
        tocUrl.setToggleGroup(processGroup);
        tocUrl.setId("toc-url");

        //URL input
        TextField textUrl = new TextField();
        textUrl.setPromptText("Paste Url");
        textUrl.setPrefWidth(600);
        textUrl.setEditable(false);

        //Grid Pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        //Button
        Button openButton = new Button("Open Resource File");
        openButton.setId("chooser-button");

        //Add elements to gridPane
        gridPane.add(title,0,0);
        gridPane.add(crFile,0,1);
        gridPane.add(tocFile,0,2);
        gridPane.add(tocUrl,0,3);
        gridPane.add(textUrl,0,4);
        gridPane.add(openButton, 0,6,2,1);

        //Scene
        Scene scene = new Scene(gridPane, 600, 275);
        scene.getStylesheets().add("com/pl/main.css");
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
