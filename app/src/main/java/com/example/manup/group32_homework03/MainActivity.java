/* Group-32 Homework3
Priyanka Manusanipally- 801017222
Sai Spandana Nakireddy- 801023658*/

package com.example.manup.group32_homework03;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Parsing.IData{

    TextView tvloading;
    Button btnstart, btnexit;
    ImageView imtrivia;
    ProgressBar progressBar;
    ArrayList<Question> questions;
    static String QUESTIONS_KEY="questionlist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Trivia App");

        tvloading= (TextView) findViewById(R.id.tvloading);
        imtrivia= (ImageView) findViewById(R.id.imtrivia);
        btnstart= (Button) findViewById(R.id.btnstart);
        btnexit= (Button) findViewById(R.id.btnexit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        btnstart.setEnabled(false);

        if(isConnected()){
           // new GetData(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
            new Parsing(MainActivity.this).execute("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");

        }

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent in = new Intent(MainActivity.this, TriviaActivity.class);
             in.putExtra(QUESTIONS_KEY, (Serializable)questions);
             startActivity(in);
             finish();

            }
        });

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private Boolean isConnected() {
        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || networkInfo.getType() != ConnectivityManager.TYPE_WIFI && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE) {
            return false;
        }
        return true;
    }


    @Override
    public void onGetQuiz(ArrayList<Question> ql) {
        questions = ql;
    }
}
