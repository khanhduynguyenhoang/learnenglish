package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Activity;
import android.content.Context;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by KhanhDuy on 18/06/2016.
 */
public class DummyTranslaterEngVie extends WordTranslater {


    public DummyTranslaterEngVie(Activity context) {
        super(context);
    }

    @Override
    public String translate(String word) {

        String ClientId = "DoAn72Hours";
        Translate.setClientId(ClientId);
        String ClientSecret = "MbUQJxHf9nwa2jRS+e+FTzdelVkeOTPs0c2jfbzRclQ=";
        Translate.setClientSecret(ClientSecret);

        Language from = Language.ENGLISH;
        Language to = Language.VIETNAMESE;
        String result = null;
        try {
            result = Translate.execute(word, from, to);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
