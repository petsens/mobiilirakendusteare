package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPageActivity extends AppCompatActivity {
    private Button resBtn;
    private Button moodBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        resBtn = (Button) findViewById(R.id.landingResBtn);
        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsPage();
            }
        });

        moodBtn = (Button) findViewById(R.id.landingMoodBtn);
        moodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMoodPage();
            }
        });
    }

    public void openResultsPage() {
        Intent intent = new Intent(this, ResultsPageActivity.class);
        startActivity(intent);
    }

    public void openMoodPage() {
        Intent intent = new Intent(this, MoodPageActivity.class);
        startActivity(intent);
    }
}
