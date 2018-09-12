package com.example.manup.group32_homework03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressBar progressBar3;
    TextView tvpercentage;
    ArrayList<Question> quest;
    int points = 0;
    Button buttonquit,buttontryagain;
    //public final static String QUESTION_LIST="Questions";
    double percent=0.0;
    double percentMarks;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        setTitle("Stats Activity");
        progressBar3 = (ProgressBar)findViewById(R.id.progressBar3);
        tvpercentage = (TextView)findViewById(R.id.tvpercentage);
        buttonquit = (Button) findViewById(R.id.buttonquit);
        buttontryagain = (Button) findViewById(R.id.buttontryagain);
        textView4 = (TextView) findViewById(R.id.textView4);
        buttonquit.setOnClickListener(this);
        buttontryagain.setOnClickListener(this);

        if (getIntent().getExtras().getSerializable(TriviaActivity.QUESTIONS) != null) {
            quest = (ArrayList<Question>) getIntent().getExtras().getSerializable(TriviaActivity.QUESTIONS);
        }
        points= getIntent().getExtras().getInt(TriviaActivity.RESULT);
        Log.d("po" ,points+"");
        percentMarks=percentage(points,quest);
        tvpercentage.setText(percentMarks+"%");
        progressBar3.setProgress((int)percentMarks);
        if(percentMarks == 100)
        {
            textView4.setText("Congratulations");
            buttontryagain.setVisibility(View.INVISIBLE);
        }
    }
    public double percentage(int points,ArrayList<Question> questionList)
    {

        Log.d("questionlist",questionList.size()+"");
        percent=(double)points/(double)questionList.size();
        Log.d("score",percent+"");
     percent=percent*100;
     return percent;
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonquit)
        {
            Intent intent = new Intent(StatsActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(v.getId() == R.id.buttontryagain)
        {
            Intent intent = new Intent(StatsActivity.this,TriviaActivity.class);
            intent.putExtra(MainActivity.QUESTIONS_KEY,quest);
            startActivity(intent);
            finish();
        }
    }
}
