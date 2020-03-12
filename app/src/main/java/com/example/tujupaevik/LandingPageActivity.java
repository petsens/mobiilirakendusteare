package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        try {
            createStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openResultsPage(View v) {
        Intent intent = new Intent(this, ResultsPageActivity.class);
        startActivity(intent);
    }

    public void openMoodPage(View v) {
        Intent intent = new Intent(this, MoodPageActivity.class);
        startActivity(intent);
    }

    private void createStorage() throws IOException {
        String fileName = "tujuStorage.json";
        File file = new File(this.getFilesDir(), fileName);
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        if(!file.exists()) {
            file.createNewFile();
            fileWriter = new FileWriter(file.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("{}");
            bufferedWriter.close();
        }
    }
}
