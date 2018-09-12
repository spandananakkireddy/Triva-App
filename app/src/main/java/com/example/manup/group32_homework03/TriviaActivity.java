package com.example.manup.group32_homework03;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity implements View.OnClickListener{


    Question question;
    TextView tvloadingimage;
    ProgressBar progressBar2;
    RadioButton answerChoice;
    TextView tvquestionno;
    TextView tvno;
    TextView  tvtime;
    ImageView imageView;
    TextView tvquestion;
    ScrollView radioGroupScroll;
    RadioGroup answerChoices;
    ArrayList<Question> questions;
    ArrayList<Question> que = null;
    long pausedTIme;
    CountDownTimer timerSet;
     int index = 0;
    int points =0;
    public static final String QUESTIONS="Questions";
    public static final String RESULT="result";
    Button btnnext,btnquit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        setTitle("Trivia App");
        questions=new ArrayList<Question>();
        tvloadingimage = (TextView) findViewById(R.id.tvloadingimage);
        tvquestion = (TextView) findViewById(R.id.tvquestion);
        tvno = (TextView) findViewById(R.id.tvno);
        //tvtimeleft = (TextView) findViewById(R.id.tvtimeleft);
        imageView = (ImageView) findViewById(R.id.imageView);
        //tvquestionno = (TextView) findViewById(R.id.tvquestionno);
        radioGroupScroll = (ScrollView) findViewById(R.id.radioGroupScroll);
        answerChoices = (RadioGroup) findViewById(R.id.answerChoices);
        answerChoices   .setOnClickListener(this);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        tvtime = (TextView)findViewById(R.id.tvtime);
        btnnext=(Button)findViewById(R.id.btnnext);
        btnnext.setOnClickListener(this);
        btnquit=(Button)findViewById(R.id.btnquit);
        btnquit.setOnClickListener(this);

        if (getIntent().getExtras().getSerializable(MainActivity.QUESTIONS_KEY) != null) {
            questions = (ArrayList<Question>) getIntent().getExtras().getSerializable(MainActivity.QUESTIONS_KEY);
            startQuiz(questions, index);
            Log.d("questions", questions + "");
            Log.d("index val", index + "");
            startTimer(120000);
        }
       /* else if(getIntent().getExtras().getSerializable(StatsActivity.QUESTION_LIST)!= null)
        {
            que= (ArrayList<Question>) getIntent().getExtras().getSerializable(StatsActivity.QUESTION_LIST);
            Log.d("q", que + "");
            startQuiz(que, index);
            startTimer(120000);
        }*/
    }
    public void killTimer(){
        if(timerSet!=null){
            timerSet.cancel();
        }
    }
    public void startTimer(long timeVal){
        timerSet=  new CountDownTimer(timeVal,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvtime.setText("Time Left:" +getResources().getText(R.string.tvTime)+" "+millisUntilFinished/1000+" sec");
            }
            @Override
            public void onFinish() {
                tvtime.setText("Time Up");
                Intent inr=new Intent(TriviaActivity.this,StatsActivity.class);
                inr.putExtra(QUESTIONS,questions);
                Log.d("marksaftertime","SA"+points);
                inr.putExtra(RESULT,points);
                Log.d("pointsss", points+"");
                startActivity(inr);
                finish();
            }
        };
        timerSet.start();
    }
    public void startQuiz(ArrayList<Question> questionList, int index) {
        tvno.setText("Q"+(index + 1));

        if (questionList.get(index).getImage().equals(""))
        {
            imageView.setVisibility(View.INVISIBLE);
            tvloadingimage.setVisibility(View.VISIBLE);
            tvloadingimage.setText("No image found");
        }
        else
        {
            progressBar2.setVisibility(View.VISIBLE);
            tvloadingimage.setVisibility(View.VISIBLE);
            tvloadingimage.setText(R.string.tvload);
            //imageView.setVisibility(View.VISIBLE);
            new GetImage(this).execute(questionList.get(index).getImage());
        }
        answer(questionList.get(index));
        tvquestion.setText(questionList.get(index).getData() + "");
    }
    public void answer(Question question) {
        answerChoices.clearCheck();
        answerChoices.removeAllViews();
        answerChoices.setOrientation(LinearLayout.VERTICAL);
        for (int m = 0; m < question.getChoices().length - 1; m++) {
            answerChoice = new RadioButton(this);
            answerChoice.setId(m + 1);
            answerChoice.setText(question.getChoices()[m]);
            answerChoices.addView(answerChoice);
        }
    }

    public int correctAnswer(int score){
        Log.d("sccc",score+"");
        //score=score+1;
        Log.d("sc",questions.get(score).getAnswer()+"");
        Log.d("sc1",answerChoices.getCheckedRadioButtonId()+"");
        if(questions.get(score).getAnswer()== answerChoices.getCheckedRadioButtonId()){

            points= points+1;
        }
        Log.d("potr" ,points+"");
        return points;
    }

    @Override
    public void onClick(View v) {
       if(v.getId() == R.id.btnnext)
       {
           points= correctAnswer(index);
           Log.d("points2", index+"");
           if(index == questions.size()-1)
           {
               Intent intent = new Intent(TriviaActivity.this,StatsActivity.class);
               intent.putExtra(QUESTIONS,questions);
               intent.putExtra(RESULT,points);
               startActivity(intent);
               killTimer();
               finish();
           }
           else if(index>=0 && index < questions.size()-1){
               index= index+1;
               startQuiz(questions,index);
               imageView.setImageResource(android.R.color.transparent);
           }
       }
        if(v.getId() == R.id.btnquit){
            Intent intent = new Intent(TriviaActivity.this,MainActivity.class);
            intent.putExtra(QUESTIONS,questions);
            intent.putExtra(RESULT,points);
            startActivity(intent);
            killTimer();
            finish();
        }

    }
}
