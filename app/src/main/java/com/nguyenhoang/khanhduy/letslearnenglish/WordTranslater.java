package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Activity;

/**
 * Created by KhanhDuy on 18/06/2016.
 */
public abstract class WordTranslater {
    protected Activity myContext;
    public WordTranslater(Activity context){
        myContext = context;
    }

    public abstract String translate(String word);
}
