package com.pl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class ProcessToCUrl {
    private File file;
    private final int LINE_BREAK = 10;

    public ProcessToCUrl(File file) {
        this.file = file;
    }

    public void ProcessToCUrl() {
        StringBuilder sb = getQuestionIds();
        //TODO:pl create file
    }

    private StringBuilder getQuestionIds() {
        StringBuilder sb = new StringBuilder();
        try {
            Document doc = Jsoup.connect("http://author.confirmit.com/extwix/singlepage.aspx?pid=p3084785446&l=9&hash=845ec5b9e4e90140a450be9f2ce06645").get();
            Elements divs = doc.select("div.questionarea");
            boolean isHidden = false;
            for (Element div : divs) {
                if (div.select("span").html().contains("Condition If </b>false")) {
                    isHidden = true;
                }
                if (isHidden == false && div.select("span").html().contains("Question ID:")) {
                    String qid = div.select("span:contains(Question ID:)")
                            .html()
                            .replace("[<b>Question ID: </b>","'")
                            .replace("]","',");
                    String mask = div.select("span:contains(Question mask:)")
                            .html()
                            .replace("[<b>Question mask: </b>","")
                            .replace("]","");
                    if (mask.indexOf("false") != 0 && qid.indexOf("'xxx") == -1) {
                        System.out.printf("%s\n", qid);
                    }
                }
                if (div.select("span").html().contains("Condition End")) {
                    isHidden = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

}
