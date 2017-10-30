package com.pl;

import java.io.File;

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

    }
}
