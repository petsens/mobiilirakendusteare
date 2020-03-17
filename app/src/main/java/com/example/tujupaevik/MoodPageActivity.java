package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.json.JSONException;
import java.io.IOException;

public class MoodPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_page);
    }

    public void onMoodSelect(View v) throws IOException, JSONException {
        String moodData = v.getTag().toString();
        Functions.addDataToFile("Mood", moodData, this.getFilesDir());
        Intent intent = new Intent(this, FeelingsPageActivity.class);
        startActivity(intent);
    }
}
