package com.example.manup.group32_homework03;

import android.os.AsyncTask;
import android.util.Log;

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

public class GetData extends AsyncTask<String,Void,ArrayList<String>>{
    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        return null;
    }

/*    MainActivity activity;
    ArrayList<String> str1 = new ArrayList<String>();
    int end =0;
    int num1,num2;

    public GetData(MainActivity activity) {
        this.activity = activity;
    }
    @Override
    protected ArrayList<String> doInBackground(String... strings) {

        try {
            URL url= new URL(strings[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode= con.getResponseCode();
            if(statusCode== HttpURLConnection.HTTP_OK)
            {
                BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb= new StringBuilder();
                String line = reader.readLine();
                end = 0;
                while(line != null)
                {
                    sb.append(line);
                    line=reader.readLine();
                }
                Log.d("demo", line +"");
                for (int k = 0; k < sb.length(); k++) {
                    if (sb.charAt(k) == ';') {
                        String s1 = sb.substring(end, k);
                        end=k+1;
                        str1.add(s1);
                    }

                }
             //   return arrayList;
                Log.d("str1", str1 +"");
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  str1;

    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {

        activity.imtrivia.setImageResource(R.drawable.trivia);
        activity.tvloading.setText("Trivia Ready");
        activity.btnstart.setEnabled(true);
        activity.progressBar.invalidate();

    }*/
}

