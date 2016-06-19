package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Fragment;

import java.util.ArrayList;

/**
 * Created by KhanhDuy on 19/06/2016.
 */
public class FragmentFactory {
    String[] fragmentNames = {"Trang chủ", "Học từ vựng", "Từ điển",
            "Trắc nghiệm TOEIC", "Dịch văn bản", "Giới thiệu"};

    ArrayList<Fragment> sampleFragment = new ArrayList<Fragment>();

    public FragmentFactory(){
        sampleFragment.add(new MainFragment());
        sampleFragment.add(new VocabularyFragment());
        sampleFragment.add(new DictionaryFragment());
        sampleFragment.add(new TestChooserFragment());
        sampleFragment.add(new TranslateFragment());
        sampleFragment.add(new AboutMeFragment());
    }

    public Fragment getFragment(int id){
        return sampleFragment.get(id);
    }

    public String getFragmentName(int id){
        return fragmentNames[id];
    }
}
