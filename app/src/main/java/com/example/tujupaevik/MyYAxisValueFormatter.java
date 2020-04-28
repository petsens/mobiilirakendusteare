package com.example.tujupaevik;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class MyYAxisValueFormatter extends ValueFormatter implements IAxisValueFormatter {

    private String[] mValues;
    private static final String TAG = "MyYAxisValueFormatter";

    public MyYAxisValueFormatter(String[] values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value) {
        // "value" represents the position of the label on the axis (x or y)
        return mValues[(int) value]+"";
    }
}
