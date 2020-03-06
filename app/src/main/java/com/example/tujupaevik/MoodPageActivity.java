package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoodPageActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_page);

        button = (Button) findViewById(R.id.moodSubBtn);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                openFeelingsPage();
           }
        });
    }

    public void openFeelingsPage() {
        Intent intent = new Intent(this, FeelingsPageActivity.class);
        startActivity(intent);
    }
}
