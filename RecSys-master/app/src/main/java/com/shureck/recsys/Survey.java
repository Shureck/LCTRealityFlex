package com.shureck.recsys;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Survey extends AppCompatActivity implements View.OnClickListener {

    GridLayout gridLayout;
    ArrayList<View> surveyButtons;
    ArrayList<Boolean> selectedGenres;
    ArrayList<SurveyButtonContent> buttonContents;
    ArrayList<String> genresToSend;
    String str;
    Button button;
    int tappedButtons;

    private final OkHttpClient client = new OkHttpClient();

    class IOAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return sendData(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            str = response;
            Log.d("DDD",response);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey);



        button = findViewById(R.id.button);
        gridLayout = findViewById(R.id.grid);

        LayoutInflater inflater = LayoutInflater.from(gridLayout.getContext());

        selectedGenres = new ArrayList<>();
        buttonContents = new ArrayList<>();
        genresToSend = new ArrayList<>();

        buttonContents = SurveyHelper.fillSurveyContent();
        tappedButtons = 0;

        button.setOnClickListener(this);

        surveyButtons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            View newGenreButton = inflater.inflate(R.layout.element, null);

            ImageButton genreImage = newGenreButton.findViewById(R.id.imageButton);
            TextView genreName = newGenreButton.findViewById(R.id.imageText);

            genreImage.setImageResource(buttonContents.get(i).getSurveyButtonImage());
            genreName.setText(getString(buttonContents.get(i).getSurveyButtonText()));

            surveyButtons.add(newGenreButton.findViewById(R.id.imageButton));
            selectedGenres.add(false);

            newGenreButton.findViewById(R.id.imageButton).setOnClickListener(this);

            gridLayout.addView(newGenreButton);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        if (v == button) {

            String all = "";
            for (int i = 0; i < genresToSend.size(); i++) {
                all+=genresToSend.get(i)+"|";
            }

            new IOAsyncTask().execute(all);

            Intent intent = new Intent(Survey.this, MainActivity.class);
            startActivity(intent);

        } else {
            int index = surveyButtons.indexOf(v);
            boolean isSelected = selectedGenres.get(index);
            String genre = getString(buttonContents.get(index).surveyButtonText);

            if (!isSelected && tappedButtons != 5) {
                v.setAlpha(1.0f);
                selectedGenres.set(index, true);
                tappedButtons++;
                genresToSend.add(genre);

            } else if (isSelected) {
                v.setAlpha(0.5f);
                selectedGenres.set(index, false);
                tappedButtons--;
                genresToSend.remove(genre);
            }
        }

    }

    public String sendData(String str){
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("LatLong", str)
                    .build();

            Request request = new Request.Builder()
                    .url("http://25.101.65.98:8080/api")
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
