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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {
    private ProcessCopyRecall recall;
    private ProcessToC tocVars;
    private ProcessToCUrl tocUrlLink;
    private ProcessReinvites re;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("File Processor v2.0");

        //File Chooser
        File desktop = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(desktop);

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

        RadioButton reinvite = new RadioButton("Process Reinvites");
        reinvite.setToggleGroup(processGroup);
        reinvite.setId("reinvite");

        //Text input
        TextField textUrl = new TextField();
        textUrl.setPromptText("Paste Url");
        textUrl.setPrefWidth(600);

        TextField reinviteUrl = new TextField();
        reinviteUrl.setPromptText("Paste Survey Url");
        reinviteUrl.setPrefWidth(600);

        TextField qid = new TextField();
        qid.setPromptText("QID");
        qid.setMaxWidth(200);

        TextField loop = new TextField();
        loop.setPromptText("mainLoop (e.g. 0, 1, 2)");
        loop.setMaxWidth(200);

        //Grid Pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        //Button
        Button openButton = new Button("Open/Process Resource File");
        openButton.setId("chooser-button");

        //Add elements to gridPane
        gridPane.add(title,0,0);
        gridPane.add(crFile,0,1);
        gridPane.add(tocFile,0,2);
        gridPane.add(tocUrl,0,3);
        gridPane.add(textUrl,0,4);
        gridPane.add(reinvite,0,5);
        gridPane.add(reinviteUrl,0,6);
        gridPane.add(qid,0,7);
        gridPane.add(loop,0,8);
        gridPane.add(openButton, 0,9,2,1);

        //Scene
        Scene scene = new Scene(gridPane, 600, 375);
        scene.getStylesheets().add("com/pl/main.css");
        primaryStage.setScene(scene);

        //Action
        openButton.setOnAction(
                event -> {
                    RadioButton chk = (RadioButton)processGroup.getSelectedToggle();
                    String selectedRadio = chk.getId();

                    if (selectedRadio.equalsIgnoreCase("toc-url")) {
                        String stringUrl = textUrl.getText();
                        if (stringUrl != null && !stringUrl.isEmpty()) {
                            tocUrlLink = new ProcessToCUrl(stringUrl);
                            tocUrlLink.ProcessToCUrlVars();
                        }
                    } else {
                        File file = fileChooser.showOpenDialog(primaryStage);
                        if (file != null) {
                            if (selectedRadio.equalsIgnoreCase("cr-file")) {
                                recall = new ProcessCopyRecall(file);
                                recall.generateCopyRecallVars();
                            } else if (selectedRadio.equalsIgnoreCase("toc-file")) {
                                tocVars = new ProcessToC(file);
                                tocVars.generateToCVars();
                            } else if (selectedRadio.equalsIgnoreCase("reinvite")) {
                                String reUrl = reinviteUrl.getText();
                                String questionID = qid.getText();
                                String loopNumber = loop.getText();
                                if (reUrl != null && !reUrl.isEmpty()
                                        && questionID != null && !questionID.isEmpty()
                                        && loopNumber != null && !loopNumber.isEmpty()) {
                                    re = new ProcessReinvites(file, reUrl, questionID, loopNumber);
                                }
                            }
                        }
                    }
                }
        );


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
