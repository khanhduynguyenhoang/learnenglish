package com.nguyenhoang.khanhduy.letslearnenglish;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Data.Question;

public class QuizActivity extends AppCompatActivity {

    ArrayList<Integer> dataStorage = new ArrayList<Integer>();
    ArrayList<Question> questions = new ArrayList<Question>();
    int currentQuestion = 0, countQuestion;
    int point = 0;
    MediaPlayer mpCorrect = null;
    private MediaPlayer mpWrong = null;

    //views
    private TextView tvPoint, tvProgress, tvQuestion;
    ArrayList<Button> btns = new ArrayList<Button>();


    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

        int index = getIntent().getIntExtra("index", -1);
        loadWidgets();
        populateData();
        loadQuestion(index);

        //game start here
        showCurrentQuestion();
    }

    private void loadWidgets() {
        tvPoint = (TextView)findViewById(R.id.tv_point);
        tvProgress = (TextView)findViewById(R.id.tv_progress);
        tvQuestion = (TextView)findViewById(R.id.tv_question);
        Button a = (Button)findViewById(R.id.btn_option1); btns.add(a);
        Button b = (Button)findViewById(R.id.btn_option2); btns.add(b);
        Button c = (Button)findViewById(R.id.btn_option3); btns.add(c);
        Button d = (Button)findViewById(R.id.btn_option4); btns.add(d);

        for (int i = 0; i < 4; i++){
            final int finalI = i;
            btns.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chooseOption(finalI + 1);
                }
            });
        }
    }

    private void chooseOption(int option) {     //1234
        Question q = questions.get(currentQuestion);
        btns.get(q.getCorrectAns() - 1).setBackgroundResource(R.color.correctAnswer);
        if (q.isCorrect(option)){
            //correct
            //Toast.makeText(QuizActivity.this, "Đúng rồi", Toast.LENGTH_SHORT).show();
            mpCorrect.start();
            point += 10;
        } else {
            //incorrect
            //Toast.makeText(QuizActivity.this, "Sai rồi", Toast.LENGTH_SHORT).show();
            mpWrong.start();
            btns.get(option - 1).setBackgroundResource(R.color.wrongAnswer);
        }

        class delayTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                for (int i = 0; i < 4; i++)
                    btns.get(i).setClickable(false);
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                for (int i = 0; i < 4; i++){
                    btns.get(i).setBackgroundResource(R.color.optionColor);
                    btns.get(i).setClickable(true);
                }


                if (currentQuestion + 1 < countQuestion){
                    currentQuestion++;
                    showCurrentQuestion();
                }
                else
                    endGame();
            }
        }
        new delayTask().execute();

    }

    private void showCurrentQuestion() {
        Question q = questions.get(currentQuestion);
        tvQuestion.setText(q.getQuestion());

        for (int i = 0; i < 4; i++)
            btns.get(i).setText(q.getAns(i));

        tvPoint.setText("" + point + " điểm");
        tvProgress.setText("Câu " + (currentQuestion + 1) + "/" + countQuestion);
    }

    private void endGame() {
        //TODO: end game
        Bundle b = new Bundle();
        b.putInt("total", countQuestion * 10);
        b.putInt("score", point);
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("data", b);
        startActivity(i);
        //Toast.makeText(QuizActivity.this, "Bạn được " + point + " điểm.", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void populateData() {
        dataStorage.add(R.raw.test1);
        dataStorage.add(R.raw.test2);
        dataStorage.add(R.raw.test3);

        mpCorrect = MediaPlayer.create(this, R.raw.correct);
        mpWrong = MediaPlayer.create(this, R.raw.wrong);
    }

    private void loadQuestion(int index) {
        InputStream stream = getResources().openRawResource(dataStorage.get(index));
        String data;

        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            data = bufferedReader.readLine();
            countQuestion = Integer.parseInt(data);     //number of questions

            for (int i = 0; i < countQuestion; i++){
                String ques;
                ArrayList<String> ans = new ArrayList<String>();
                ques = bufferedReader.readLine();

                for (int j = 0; j < 4; j++){
                    data = bufferedReader.readLine();
                    ans.add(data);
                }

                data = bufferedReader.readLine();
                Question q = new Question(ques, ans, data);
                questions.add(q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long seed = System.nanoTime();
        Collections.shuffle(questions, new Random(seed));
    }

}
