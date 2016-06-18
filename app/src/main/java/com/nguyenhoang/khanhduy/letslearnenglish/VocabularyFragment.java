package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by KhanhDuy on 31/05/2016.
 */
public class VocabularyFragment extends Fragment {
    View result;
    ArrayList<Word> words = new ArrayList<Word>();

    TextView tvWord, tvPron, tvMean, tvMeanEx, tvExam, tvExamEx;
    LinearLayout ll;
    private int countWord;
    int currentWord = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        result = inflater.inflate(R.layout.fragment_vocabulary, container, false);

        loadWidgets();
        loadData();
        showDataRandom();

        return result;
    }

    private void showDataRandom() {
        Random rand = new Random();
        currentWord = rand.nextInt(countWord);

        Word w = words.get(currentWord);
        tvWord.setText(w.getWord());
        tvPron.setText(w.getPron());
        tvMean.setText(w.getMean());
        tvMeanEx.setText(w.getMeanEx());
        tvExam.setText(w.getExamp());
        tvExamEx.setText(w.getExampEx());
    }

    private void loadData() {
        InputStream stream = getResources().openRawResource(R.raw.word1);
        String data;

        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            data = bufferedReader.readLine();
            countWord = Integer.parseInt(data);     //number of questions

            for (int i = 0; i < countWord; i++){
                Word w = new Word();
                data = bufferedReader.readLine(); w.set("word", data);
                data = bufferedReader.readLine(); w.set("pron", data);
                data = bufferedReader.readLine(); w.set("mean", data);
                data = bufferedReader.readLine(); w.set("meanex", data);
                data = bufferedReader.readLine(); w.set("examp", data);
                data = bufferedReader.readLine(); w.set("exampex", data);
                words.add(w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataRandom();
            }
        });
    }

    private void loadWidgets() {
        tvWord = (TextView)result.findViewById(R.id.tv_word);
        tvPron = (TextView)result.findViewById(R.id.tv_pronounce);
        tvMean = (TextView)result.findViewById(R.id.tv_meaning);
        tvMeanEx = (TextView)result.findViewById(R.id.tv_meaning_explain);
        tvExam = (TextView)result.findViewById(R.id.tv_examp);
        tvExamEx = (TextView)result.findViewById(R.id.tv_examp_explain);
        ll = (LinearLayout)result.findViewById(R.id.ll);
    }
}
