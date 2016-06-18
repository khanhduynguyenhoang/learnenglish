package Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nguyenhoang.khanhduy.letslearnenglish.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by KhanhDuy on 30/05/2016.
 */
public class TestItemAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    ArrayList<String> arr;

    public TestItemAdapter(Activity context, int resource, ArrayList<String> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.arr = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(resource, null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.text1);
        tv.setText(arr.get(position));
        return convertView;
    }
}
