package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Activity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by KhanhDuy on 18/06/2016.
 */
public class CambridgeTranslater extends WordTranslater {
    public CambridgeTranslater(Activity context) {
        super(context);
    }

    @Override
    public String translate(String word) {
        Document site;
        String url = "http://dictionary.cambridge.org/dictionary/english/" + word;

        try {
            site = Jsoup.connect(url).get();
            String title = site.title();
            Elements entry = site.getElementsByClass("entry");

            String result = "";
            result = getInfo(entry.get(0));

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "null";
    }

    private String getInfo(Element entry) {
        String res = "";
        Elements headers = entry.getElementsByClass("pos-header");
        Elements bodies = entry.getElementsByClass("pos-body");


        for (int i = 0; i < Math.min(headers.size(), bodies.size()); i++){
            res += " <font color=#000088><b>" + headers.get(i).getElementsByClass("hw").get(0).text()+ "</b></font>" + "<br>";
            res +=  "(" + headers.get(i).getElementsByClass("posgram").get(0).text() + " ";

            Elements ipa = headers.get(i).getElementsByClass("ipa");
            if (ipa.size() > 1)
                res += "UK   [" + ipa.get(0).text() + "]   US   [" + ipa.get(1).text() + "])<br>";
            else
                res += "  [" + ipa.get(0).text() + "] )<br>";

            Elements es = bodies.get(i).getElementsByClass("def-block");
            for (int j = 0; j < es.size(); j++){
                String temp =  getDefText(es.get(j));
                res += temp;
            }

            res += "---------------------------<br><br>";
        }
        return res;
    }

    private String getDefText(Element element) {

        Elements ehead = element.getElementsByClass("def-head");
        Elements exams = element.getElementsByClass("examp");
        String result = "";
        result += "<font color=#a8397a> <b>" + ehead.text() + "</b> </font>" + "<br>";
        if (exams.size() > 0)
            result += "Examples:  <br>";
        for (int i = 0; i < exams.size(); i++){
            result += "&emsp;"  + exams.get(i).text() + "<br>";
        }
        return result;
    }
}
