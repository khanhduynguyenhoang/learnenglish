package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.TestItemAdapter;

/**
 * Created by KhanhDuy on 29/05/2016.
 */
public class TestChooserFragment extends Fragment{

    View result;
    ListView lvTests;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        result = inflater.inflate(R.layout.fragment_tests, container, false);

        //String arr[] = {"Test 001", "Test 002", "Test 003"};
        ArrayList<String> arrList = new ArrayList<String>();
        arrList.add("Test 001");
        arrList.add("Test 002");
        arrList.add("Test 003");

        TestItemAdapter adapter = new TestItemAdapter(getActivity(), R.layout.test_item, arrList);
        lvTests = (ListView)result.findViewById(R.id.lv_tests);
        lvTests.setAdapter(adapter);

        lvTests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), QuizActivity.class);
                i.putExtra("index", position);
                startActivity(i);
            }
        });

        return result;
    }
}
