package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FeelingsPageActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_page);

        button = (Button) findViewById(R.id.feelingsSubBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTempoPage();
            }
        });
    }

    public void openTempoPage() {
        Intent intent = new Intent(this, TempoPageActivity.class);
        startActivity(intent);
    }
}
