package com.pl;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* Input: text file with variables copied from singlePageSurveyDefinition
* Output: new text file with variables formatted for pasting into ToC
* */
public class ProcessToC extends FileCreator {
    private File file;

    public ProcessToC(File file) {
        this.file = file;
    }

    public void generateToCVars() {
        StringBuilder sb = getQuestionIds();
        createFile(sb,"TOC.txt");
    }

    private StringBuilder getQuestionIds() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                if (line.indexOf("[Question ID:") != -1) {
                    counter++;
                    line = line.replace("[Question ID: ","'");
                    line = line.replace("]","',");

                    // skip IDs that start with 'xxx'
                    if (line.indexOf("xxx") != 1) {
                        sb.append(line);
                    }

                    if (counter % LINE_BREAK == 0) {
                        sb.append("\r\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(ProcessToC.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(ProcessToC.class.getName()).log(Level.SEVERE, null, e);
        }

        return sb;
    }
}
