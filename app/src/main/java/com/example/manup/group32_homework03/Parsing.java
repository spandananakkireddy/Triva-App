package com.example.manup.group32_homework03;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by manup on 2/16/2018.
 */

public class Parsing extends AsyncTask<String,Void,ArrayList<Question>> {

    Question question;
    MainActivity activity;


    public Parsing(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        /*activity.imtrivia.setVisibility(View.VISIBLE);
        activity.tvloading.setText("Trivia Ready");
        activity.btnstart.setEnabled(true);
        activity.progressBar.invalidate();*/
        activity.onGetQuiz(questions);
        ProgressBar pb= (ProgressBar) activity.findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        activity.btnstart.setEnabled(true);
        activity.tvloading.setText("Trivia Ready");
        activity.imtrivia.setImageResource(R.drawable.trivia);
    }

    @Override
    protected ArrayList<Question> doInBackground(String... strings) {
        BufferedReader br;
        StringBuilder sb= null;
        ArrayList<Question> str= new ArrayList<Question>();
        ArrayList<String> str2 =  new ArrayList<String>();
       // ArrayList<String> wholestring =  new ArrayList<String>();
        String s ="";
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            if(con.getResponseCode()== HttpURLConnection.HTTP_OK)
            {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                sb= new StringBuilder();
                ArrayList<String> ans1 = new ArrayList<String>();
                while ((s=br.readLine()) != null)
                {
                    sb.append(s);
                    str2.add(s);
                    String[] temp = s.split(";");
                    int qno = (Integer.parseInt((temp[0])));
                    Log.d("temp", temp[0] +"");
                    String que = (temp[1].toString());
                    String imgurl = (temp[2]);
                    ans1.clear();
                    for(int k=3;k<=temp.length-1;k++) {
                        ans1.add(String.valueOf(temp[k]));

                    }
                    int ansin = (Integer.parseInt(temp[(temp.length)-1]));
                    Log.d("ansin", ansin+"");
                    ansin= ansin+1;
                    int n = Integer.parseInt(temp[(temp.length)-1]);
                    String[] ans = new String[n];
                    ans = ans1.toArray(ans);
                    question = new Question(qno,que,imgurl,ans,ansin);
                    str.add(question);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("str", str +"");
        return str;
    }
    static public  interface IData{
            public void onGetQuiz(ArrayList<Question> ql);
    }
}
