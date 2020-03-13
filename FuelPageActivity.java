package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class FuelPageActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_page);

        SeekBar energyBar = (SeekBar)findViewById(R.id.energyBar);
        final TextView energyBarValue = (TextView)findViewById(R.id.energyBarValue);

        Functions.addDataToFile("Fuel", energyBarValue, this.getFilesDir());

        energyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                energyBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

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
