package com.example.manup.group32_homework03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Spandana Nakkireddy on 2/17/2018.
 */

public class GetImage extends AsyncTask<String,Void,Bitmap> {
    TriviaActivity triviaActivity;

    public GetImage(TriviaActivity triviaActivity) {
        this.triviaActivity = triviaActivity;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            if(strings [0]!=""){
                URL imageUrl=new URL(strings[0]);
                HttpURLConnection con= (HttpURLConnection) imageUrl.openConnection();
                con.setRequestMethod("GET");
                Bitmap image= BitmapFactory.decodeStream(con.getInputStream());
                return image;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        triviaActivity.tvloadingimage.setVisibility(View.INVISIBLE);
        triviaActivity.progressBar2.setVisibility(View.INVISIBLE);
        triviaActivity.imageView.setImageBitmap(bitmap);
        triviaActivity.imageView.setVisibility(View.VISIBLE);
    }
}
