package com.pl;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessReinvites {
    private File file;
    private String questionID;
    private String loopNumber;
    private String url;

    public ProcessReinvites(File file, String url, String questionID, String loopNumber) {
        this.file = file;
        this.questionID = questionID;
        this.loopNumber = loopNumber;
        this.url = url;
    }

    public void setReinvites() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\s+");
                String completeURL = url + "?__goto=" + questionID + "%2c"+loopNumber+"&r=" + data[0] + "&s=" + data[1];
                try {
                    Desktop.getDesktop().browse(new URI(completeURL));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(ProcessReinvites.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(ProcessReinvites.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
