package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by KhanhDuy on 30/05/2016.
 */
public class MainFragment extends Fragment {
    ImageButton btnVoca, btnDict, btnToeic, btnTranslate;
    View result;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        result = inflater.inflate(R.layout.fragment_main_page, container, false);
        loadWidgets();

        return result;
    }

    private void loadWidgets() {
        btnVoca = (ImageButton)result.findViewById(R.id.cat_voca);
        btnDict = (ImageButton)result.findViewById(R.id.cat_dict);
        btnToeic = (ImageButton)result.findViewById(R.id.cat_toeic);
        btnTranslate = (ImageButton)result.findViewById(R.id.cat_translate);

        final MainActivity mainAct = (MainActivity)getActivity();
        btnVoca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAct.selectItem(1);
            }
        });

        btnDict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAct.selectItem(2);
            }
        });

        btnToeic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAct.selectItem(3);
            }
        });
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAct.selectItem(4);
            }
        });
    }
}
