package com.example.tujupaevik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class ResultsPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        displayData();
    }

    public void openHomePage(View v) {
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
    }

    private void displayData() {
        String fileName = "tujuStorage.json";
        File file = new File(this.getFilesDir(), fileName);
        StringBuilder text = new StringBuilder();

        // --------- CHART
        BarChart chart = findViewById(R.id.barchart);
        int dayCount = 0;
        ArrayList Moods = new ArrayList();
        ArrayList Tempos = new ArrayList();
        ArrayList Fuels = new ArrayList();
        final ArrayList<String> Days = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                try {
                    JSONObject fullData = new JSONObject(line);
                    Iterator<String> days = fullData.keys();

                    while (days.hasNext()) {
                        String day = days.next();
                        Days.add(day);
                        dayCount++;
                        JSONObject dayData = fullData.getJSONObject(day);
                        Iterator<String> dayDataKeys = dayData.keys();

                        float mood = 0;
                        float moodTempVal = 0;
                        float tempo = 0;
                        float fuel = 0;

                        int moodCount = 0;
                        int tempoCount = 0;
                        int fuelCount = 0;

                        while (dayDataKeys.hasNext()) {
                            String dayProperty = dayDataKeys.next();
                            JSONArray arr = (JSONArray) dayData.get(dayProperty);

                            switch (dayProperty) {
                                case "mood": {
                                    for(int f = 0; f < arr.length(); f++) {
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
                                case "tempo": {
                                    for(int f = 0; f < arr.length(); f++) {
                                        float dataVal = Float.valueOf(arr.get(f).toString());
                                        tempo += dataVal;
                                        tempoCount++;
                                    }
                                    break;
                                }
                                case "fuel": {
                                    for(int f = 0; f < arr.length(); f++) {
                                        float fuelVal = Float.valueOf(arr.get(f).toString());
                                        fuel += fuelVal;
                                        fuelCount++;
                                    }
                                    break;
                                }
                            }
                        }

                        mood = mood / moodCount;
                        tempo = (tempo / tempoCount) / 100 * 8;
                        fuel = (fuel / fuelCount) / 100 * 8;

                        float moodOffset = dayCount + 5f;
                        float tempoOffset = moodOffset + 5f;
                        float fuelOffset = tempoOffset + 5f;

                        Moods.add(new BarEntry(moodOffset, mood));
                        Tempos.add(new BarEntry(tempoOffset, tempo));
                        Fuels.add(new BarEntry(fuelOffset, fuel));
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
        TextView tv = findViewById(R.id.textView7);
        tv.setText(text.toString());

        BarDataSet moodBarDataSet = new BarDataSet(Moods, "Tuju");
        BarDataSet tempoBarDataSet = new BarDataSet(Tempos, "Tempo");
        BarDataSet energyBarDataSet = new BarDataSet(Fuels, "Energia");

        int[] moodColors = new int[1];
        int[] tempoColors = new int[1];
        int[] fuelColors = new int[1];

        moodColors[0] = 2936174;
        tempoColors[0] = 1290224;
        fuelColors[0] = 15744886;

        moodBarDataSet.setColors(new int[] { R.color.moodBar});
        tempoBarDataSet.setColors(new int[] { R.color.tempoBar});
        energyBarDataSet.setColors(new int[] { R.color.fuelBar});

        tempoBarDataSet.setFormLineWidth(2);
        moodBarDataSet.setFormLineWidth(3);

        ArrayList<IBarDataSet> datasets = new ArrayList<>();
        datasets.add(moodBarDataSet);
        datasets.add(tempoBarDataSet);
        datasets.add(energyBarDataSet);

        chart.animateY(500);

        Description desc = new Description();
        desc.setText(" ");
        chart.setDescription(desc);

        BarData fullStats = new BarData(datasets);
        chart.setData(fullStats);

        XAxis xAxis = chart.getXAxis();

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return Days.get((int) value)+" asd";
            }
        });

        //xAxis.setValueFormatter(new DaysValueFormatter());

    }
}