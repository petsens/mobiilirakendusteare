package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;
import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

import static com.example.tujupaevik.Functions.addDataToFile;

public class TempoPageActivity extends AppCompatActivity {

    private Button button;
    ArcSeekBar arcSeekBar;
    private String tempoStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempo_page);

        button = (Button) findViewById(R.id.tempoSubBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFuelPage();

                if (tempoStr == null){
                    tempoStr = "0";
                }

                SaveTodaysTempo(tempoStr);
            }
        });

        arcSeekBar = (ArcSeekBar)findViewById(R.id.deafultSeekBar);

        arcSeekBar.setOnProgressChangedListener(new ProgressListener() {
            @Override
            public void invoke(int moodTempo) {
                Log.d("Tempo",""+moodTempo);
                tempoStr = String.valueOf(moodTempo);
                button.setText(tempoStr);
            }
        });
    }

    public void SaveTodaysTempo(String data) {
        try {
            addDataToFile("Tempo", data, this.getFilesDir());
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

    public void openFuelPage() {
        Intent intent = new Intent(this, FuelPageActivity.class);
        startActivity(intent);
    }
}
