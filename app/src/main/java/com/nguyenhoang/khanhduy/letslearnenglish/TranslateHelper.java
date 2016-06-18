package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Activity;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by KhanhDuy on 29/05/2016.
 */
public class TranslateHelper {

    public static final int DUMMY_API = 0;
    public static final int CAMBRIDGE_API = 1;
    public static final int VIETDICT_API = 2;

    int curType = 0;
    Activity myContext;
    WordTranslater myTranslater;

    public TranslateHelper(Activity context) {
        this.myContext = context;
        myTranslater = new DummyTranslaterEngVie(myContext);
    }

    public void setMode(int type) {
        curType = type;
        if (type == CAMBRIDGE_API)
            myTranslater = new CambridgeTranslater(myContext);
        if (type == VIETDICT_API)
            myTranslater = new VietdictTranslater(myContext);
    }


    public String translate(String text) throws Exception {

        String res = myTranslater.translate(text);
        return res;
    }
}
