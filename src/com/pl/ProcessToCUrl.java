package com.pl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProcessToCUrl extends FileCreator{
    private final int LINE_BREAK = 10;
    private String strUrl;

    public ProcessToCUrl(String strUrl) {
        this.strUrl = strUrl;
    }

    public void ProcessToCUrlVars() {
        StringBuilder sb = getQuestionIds(strUrl);
        createFile(sb,"TOC.txt");
    }

    private StringBuilder getQuestionIds(String url) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements divs = doc.select("div.text");
            boolean isHidden = false;
            for (Element div : divs) {
                if (div.select("span").html().contains("Condition If </b>false")) {
                    isHidden = true;
                }
                if (isHidden == false && div.select("span").html().contains("Question ID:")) {
                    String qid = div.select("span:contains(Question ID:)").first()
                            .html()
                            .replace("[<b>Question ID: </b>","'")
                            .replace("]","',");
                    String mask = div.select("span:contains(Question mask:)")
                            .html()
                            .replace("[<b>Question mask: </b>","")
                            .replace("]","");
                    if (mask.indexOf("false") != 0 && qid.indexOf("'xxx") == -1) {
                        counter++;
                        sb.append(qid);

                        if (counter % LINE_BREAK == 0) {
                            sb.append("\r\n");
                        }
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
