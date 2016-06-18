package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by KhanhDuy on 29/05/2016.
 */
public class DictionaryFragment extends Fragment {
    View result;
    Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         result = inflater.inflate(R.layout.fragment_dictionary, container, false);

        spinner = (Spinner)result.findViewById(R.id.translate_option);
        setUpDictionaryTypes(spinner);
        setUpTranslateButton();
        return result;
    }

    private void setUpTranslateButton() {
        Button btn = (Button)result.findViewById(R.id.btn_look_up);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = ((EditText)result.findViewById(R.id.edit_word)).getText().toString();
                int type = spinner.getSelectedItemPosition();
                String loai = "";
                if (type == 0)
                    loai = "AA";
                if (type == 1)
                    loai = "AV";
                if (type == 2)
                    loai = "VA";


                class LookUpTask extends AsyncTask<String, Void, Void> {
                    String definition;
                    ProgressDialog dialog;
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        dialog = new ProgressDialog(getActivity());
                        dialog.setTitle("Đang dịch");
                        dialog.setMessage("Đợi tí xíu nha...");
                        dialog.show();
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        dialog.hide();
                        EditText edit = (EditText) result.findViewById(R.id.edit_definition);
                        edit.setText(Html.fromHtml(definition));
                        edit.setMaxWidth(edit.getWidth());
                    }

                    @Override
                    protected Void doInBackground(String... params) {
                        String text = params[0];
                        try {
                            definition = translate(text , params[1]);
                        } catch (Exception e) {
                            //TODO: can't translate
                            e.printStackTrace();
                        }
                        return null;
                    }

                    private String translate(String text, String type) throws Exception {
                        TranslateHelper helper= new TranslateHelper(getActivity());
                        if (type == "AA")
                            helper.setMode(TranslateHelper.CAMBRIDGE_API);
                        if (type == "AV")
                            helper.setMode(TranslateHelper.VIETDICT_API);
                        if (type == "VA")
                            helper.setMode(TranslateHelper.DUMMY_API);
                        String res = helper.translate(text);
                        return res;
                    }
                }
                new LookUpTask().execute(text, loai);
            }
        });
    }

    private void setUpDictionaryTypes(Spinner spinner) {
        String[] arr = {"Từ điển Tiếng Anh", "Từ điển Anh - Việt", "Từ điển Việt - Anh"};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
    }
}
