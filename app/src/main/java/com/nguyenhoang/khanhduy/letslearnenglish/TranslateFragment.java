package com.nguyenhoang.khanhduy.letslearnenglish;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by KhanhDuy on 28/05/2016.
 */
public class TranslateFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View result = inflater.inflate(R.layout.fragment_translate, container, false);
        final Spinner spinner = (Spinner)result.findViewById(R.id.translate_option);
        String[] arr = {"Anh sang Việt", "Việt sang Anh"};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);

        Button btnTranslate = (Button)result.findViewById(R.id.btn_translate);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = ((EditText)result.findViewById(R.id.edit_text_to_translate)).getText().toString();
                int type = spinner.getSelectedItemPosition();
                String loai ="";
                if (type == 0)
                    loai ="AV";
                else
                    loai = "VA";

                class TranslateTask extends AsyncTask<String, Void, Void>{
                    String translatedText;
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
                        EditText edit = (EditText) result.findViewById(R.id.edit_text_translated);
                        edit.setText(translatedText);
                        edit.setMaxWidth(edit.getWidth());
                    }

                    @Override
                    protected Void doInBackground(String... params) {
                        String text = params[0];
                        try {
                            translatedText = translate(text , params[1]);
                        } catch (Exception e) {
                            //TODO: can't translate
                            e.printStackTrace();
                        }
                        return null;
                    }

                    private String translate(String text, String type) throws Exception {
                        String ClientId = getResources().getString(R.string.client_id);
                        Translate.setClientId(ClientId);
                        String ClientSecret = getResources().getString(R.string.client_secret);
                        Translate.setClientSecret(ClientSecret);

                        String res = "";
                        Language from, to;
                        from = Language.ENGLISH;
                        to = Language.VIETNAMESE;
                        if (type == "AV"){
                            from = Language.ENGLISH;
                            to = Language.VIETNAMESE;
                        } else {
                            from = Language.VIETNAMESE;
                            to = Language.ENGLISH;
                        }
                        res = Translate.execute(text, from, to);
                        return res;
                    }
                }
                new TranslateTask().execute(text, loai);
            }
        });

        return result;
    }
}
