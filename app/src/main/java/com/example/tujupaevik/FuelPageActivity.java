package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

import static com.example.tujupaevik.Functions.addDataToFile;

public class FuelPageActivity extends AppCompatActivity {
    private String energyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_page);

        SeekBar energyBar = findViewById(R.id.energyBar);
        final TextView energyBarValue = findViewById(R.id.energyBarValue);

        energyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                energyData = String.valueOf(progress);
                energyBarValue.setText(energyData);
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
    }

    public void openResultsPage(View v) throws IOException, JSONException {

        if (energyData == null){
            energyData = "0";
        }

        SaveTodaysFuel(energyData);
        Intent intent = new Intent(this, ResultsPageActivity.class);
        startActivity(intent);
    }

    public void SaveTodaysFuel(String dataToSave) {
        try {
            Functions.addDataToFile("Fuel", dataToSave, this.getFilesDir());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
