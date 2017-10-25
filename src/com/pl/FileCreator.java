package com.pl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class FileCreator {

    protected final int LINE_BREAK = 8;

    public void createFile(StringBuilder sb, String fileName) {
        File path = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path+"/"+fileName), StandardCharsets.UTF_8))) {
            writer.write(sb.toString());
        }
        catch (IOException e) {
            Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
