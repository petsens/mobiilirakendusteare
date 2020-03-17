package com.example.tujupaevik;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Functions {
    public static void addDataToFile(String dataName, String dataFromInput, File directory) throws Exception {
        dataName = dataName.toLowerCase();

        String fileName = "tujuStorage.json";
        File file = new File(directory, fileName);
        FileWriter fileWriter;
        FileReader fileReader;
        BufferedWriter bufferedWriter;
        StringBuffer output = new StringBuffer();
        fileReader = new FileReader(file.getAbsolutePath());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            output.append(line).append("\n");
        }

        String response = output.toString();
        bufferedReader.close();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        JSONObject moodData = new JSONObject(response);
        boolean isMoodDataExisting = moodData.has(formattedDate);
        JSONObject newMoodData;

        if(!isMoodDataExisting) {
            JSONArray moodArr = new JSONArray();
            JSONArray reasonArr = new JSONArray();
            JSONArray tempoArr = new JSONArray();
            JSONArray fuelArr = new JSONArray();

            newMoodData = new JSONObject();
            newMoodData.put("mood", moodArr);
            newMoodData.put("reason", reasonArr);
            newMoodData.put("tempo", tempoArr);
            newMoodData.put("fuel", fuelArr);

        } else {
            newMoodData = (JSONObject) moodData.get(formattedDate);
        }

        switch (dataName) {
            case "fuel": case "tempo": case "reason": case "mood": { break; }
            default: { throw new Exception("Options for dataName are: Mood, Reason, Tempo or Fuel. Change your 'addDataToFile' usage accordingly."); }
        }

        JSONArray arr = (JSONArray)newMoodData.get(dataName);
        arr.put(dataFromInput);

        moodData.put(formattedDate, newMoodData);

        fileWriter = new FileWriter(file.getAbsoluteFile());
        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(moodData.toString());
        bufferedWriter.close();
    }

}
