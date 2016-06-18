package com.nguyenhoang.khanhduy.letslearnenglish;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().hide();

        TextView tvScore = (TextView)findViewById(R.id.tv_score);
        TextView tvPercent = (TextView)findViewById(R.id.tv_percent);
        TextView result = (TextView)findViewById(R.id.tv_res);

        Bundle data = getIntent().getBundleExtra("data");
        int score = data.getInt("score");
        int total = data.getInt("total");

        tvScore.setText(Integer.toString(score));
        float percent = ((float)score) / total * 100;
        String res = String.format("%.2f", percent);
        tvPercent.setText("Tỉ lệ đúng: " + res + "%");

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.rl_everything);

        if (percent < 30){
            rl.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
            result.setText("Never give up. Test again maybe?");
        }
        else if (percent < 50){
            rl.setBackgroundColor(ContextCompat.getColor(this, R.color.light_yellow));
            result.setText("Try harder next time. You can do better!");
        }
        else if (percent < 70){
            rl.setBackgroundColor(ContextCompat.getColor(this, R.color.light_yellow));
            result.setText("Well done! You're not bad.");
        }
        else if (percent < 95){
            rl.setBackgroundColor(ContextCompat.getColor(this, R.color.light_green));
            result.setText("Excellent! You made it.");
        } else {
            rl.setBackgroundColor(ContextCompat.getColor(this, R.color.light_green));
            result.setText("Are you God?");
        }



        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
