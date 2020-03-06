package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FuelPageActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_page);

        button = (Button) findViewById(R.id.fuelSubBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsPage();
            }
        });
    }

    public void openResultsPage() {
        Intent intent = new Intent(this, ResultsPageActivity.class);
        startActivity(intent);
    }
}
