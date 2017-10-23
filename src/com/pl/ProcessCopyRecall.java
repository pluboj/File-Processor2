package com.pl;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* Input: text file with variables copied from shell.spss
* Output: new text file with variables formatted for pasting
* into CopyRecallData for chart studies
* */

public class ProcessCopyRecall extends FileCreator{

    private File file;
    //TODO:pl nice to have user input for this
    private final int LINE_BREAK = 10;

    public ProcessCopyRecall(File file) {
        this.file = file;
    }

    public void generateCopyRecallVars() {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String text;
            int counter = 0;
            while ((text = bufferedReader.readLine()) != null) {
                counter++;
                sb.append("'"+text+"',");
                if (counter % LINE_BREAK == 0) {
                    sb.append("\r\n");
                }
            }
            createFile(sb,"COPYRECALL.txt");
        } catch (FileNotFoundException e) {
            Logger.getLogger(ProcessCopyRecall.class.getName()).log(Level.SEVERE, null, e);
        }catch (IOException e) {
            Logger.getLogger(ProcessCopyRecall.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
