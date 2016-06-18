package com.nguyenhoang.khanhduy.letslearnenglish;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by KhanhDuy on 28/05/2016.
 */
public class AboutMeFragment extends android.app.Fragment {
    Activity context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.about_me, container, false);
        Button btn = (Button) result.findViewById(R.id.btn_call_me);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+841656492708";

                String uri = "tel:" + phone;
                final Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(uri));

                AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                b.setTitle("Chú ý!!");
                b.setMessage("Bạn sắp gọi vào số điện thoại của Khánh Duy. Anh ấy không thích bị gọi hoài đâu.  Bạn có muốn tiếp tục không?");
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(i);
                    }
                });
                b.create().show();
            }
        });
        return result;
    }
}
