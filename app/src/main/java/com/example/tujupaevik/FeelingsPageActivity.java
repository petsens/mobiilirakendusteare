package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import static com.example.tujupaevik.Functions.addDataToFile;

public class FeelingsPageActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_page);
    }

    public void setMoodState(View v) {
        String moodReason = v.getTag().toString();
        SaveTodaysMoodReason(moodReason);
        openTempoPage();
    }

    public void SaveTodaysMoodReason(String reason) {
        try {
            addDataToFile("Reason", reason, this.getFilesDir());
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_NUMPAD_ENTER:
                EditText mEdit   = findViewById(R.id.editText);
                SaveTodaysMoodReason(mEdit.getText().toString());
                openTempoPage();
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    public void openTempoPage() {
        Intent intent = new Intent(this, TempoPageActivity.class);
        startActivity(intent);
    }
}
