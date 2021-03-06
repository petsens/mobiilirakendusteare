package com.example.tujupaevik;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MoodPageStatistics1<value> extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_page_statistics);
        displayData();
    }

    public void openHomePage(View v) {
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
    }

    public void openResultsPage(View v) {
        Intent intent = new Intent(this, ResultsPageActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayData() {
        String fileName = "tujuStorage.json";
        File file = new File(this.getFilesDir(), fileName);
        StringBuilder text = new StringBuilder();

        // --------- CHART
        BarChart chart = findViewById(R.id.statchart);
        int dayCount = 0;
        ArrayList Moods = new ArrayList();
        final List<String> Days = new ArrayList<>();


        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                try {
                    JSONObject fullData = new JSONObject(line);
                    Iterator<String> days = fullData.keys();

                    while (days.hasNext()) { // Data inserted into 1 day.
                        String day = days.next();
                        dayCount++;
                        Days.add(day);
                        JSONObject dayData = fullData.getJSONObject(day);
                        Iterator<String> dayDataKeys = dayData.keys();

                        float mood = 0;
                        float moodTempVal = 0;

                        int moodCount = 0;

                        while (dayDataKeys.hasNext()) { // Going over all object properties because we can't do someObject.myPropertyName
                            String dayProperty = dayDataKeys.next();
                            JSONArray arr = (JSONArray) dayData.get(dayProperty);

                            switch (dayProperty) {
                                case "mood": {
                                    for(int f = 0; f < arr.length(); f++) { // Cycling over all moods inserted into 1 day
                                        String moodStr = arr.get(f).toString();
                                        moodTempVal = 0;

                                        switch (moodStr) {
                                            case "angry":   { moodTempVal += 1; moodCount++; break; }
                                            case "scared":  { moodTempVal += 2; moodCount++; break; }
                                            case "sad":     { moodTempVal += 3; moodCount++; break; }
                                            case "anxious": { moodTempVal += 4; moodCount++; break; }
                                            case "calm":    { moodTempVal += 5; moodCount++; break; }
                                            case "rested":  { moodTempVal += 6; moodCount++; break; }
                                            case "happy":   { moodTempVal += 7; moodCount++; break; }
                                            case "excited": { moodTempVal += 8; moodCount++; break; }
                                        }
                                        mood += Float.valueOf(moodTempVal);
                                    }
                                    break;
                                }
                            }
                        }

                        mood = mood / moodCount;

                        float moodOffset = dayCount;

                        Moods.add(new BarEntry(moodOffset, mood));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String[] Daystemp = new String[dayCount + 2];
        final int size = Days.size();
        for (int i = 0; i < size; i++)
        {
            String day = Days.get(i);
            Daystemp[i + 1]=day;
            //do something with i
        }

        String[] YAxisLabels = new String[]{
                "", "Vihane", "Hirmunud", "Kurb", "Närviline", "Rahulik", "Puhanud", "Rõõmus", "Elevil"
        };

        TextView tv = findViewById(R.id.textView8);
        tv.setText(text.toString());

        BarDataSet moodBarDataSet = new BarDataSet(Moods, "Tuju");

        int[] moodColors = new int[1];

        moodColors[0] = 2936174;

        moodBarDataSet.setColors(new int[] { R.color.moodBar});

        //moodBarDataSet.setFormLineWidth(2);

        ArrayList<IBarDataSet> datasets = new ArrayList<>();
        datasets.add(moodBarDataSet);

        chart.animateY(500);

        Description desc = new Description();
        desc.setText(" ");
        chart.setDescription(desc);

        BarData fullStats = new BarData(datasets);
        chart.setData(fullStats);

        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setAxisMinimum(1);
        xAxis.setAxisMaximum(dayCount);
        xAxis.setLabelCount(dayCount, true);
        xAxis.setGranularity(1f); // restrict interval to 1 (minimum)
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new MyXAxisValueFormatter(Daystemp));

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setDrawLabels(true);
        yAxis.setAxisMinimum(1);
        yAxis.setAxisMaximum(8);
        yAxis.setLabelCount(9, true);
        yAxis.setGranularity(1f); // restrict interval to 1 (minimum)
        yAxis.setGranularityEnabled(true);
        yAxis.setValueFormatter(new MyYAxisValueFormatter(YAxisLabels));

        //xAxis.setValueFormatter(new DaysValueFormatter());

    }
}

