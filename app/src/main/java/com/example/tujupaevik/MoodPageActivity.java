package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import org.json.JSONException;
import java.io.IOException;

import static com.example.tujupaevik.Functions.*;

public class MoodPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_page);
    }

    public void onMoodSelect(View v) throws IOException, JSONException {
        String moodData = "";
        switch (v.getId()) {
            case R.id.moodSelect:
                //angry
                moodData = "angry";
                break;
            case R.id.moodSelect2:
                //anxious
                moodData = "anxious";
                break;
            case R.id.moodSelect3:
                //excited
                moodData = "excited";
                break;
            case R.id.moodSelect4:
                //happy
                moodData = "happy";
                break;
            case R.id.moodSelect5:
                //sad
                moodData = "sad";
                break;
            case R.id.moodSelect6:
                //calm
                moodData = "calm";
                break;
            case R.id.moodSelect7:
                //rested
                moodData = "rested";
                break;
            case R.id.moodSelect8:
                //scared
                moodData = "scared";
                break;
        }

        addDataToFile("Mood", moodData, this.getFilesDir());
        Intent intent = new Intent(this, FeelingsPageActivity.class);
        startActivity(intent);
    }
}
