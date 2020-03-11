package com.example.tujupaevik;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Functions {
    public static void addDataToFile(String dataName, String dataFromInput, File directory) throws IOException, JSONException {
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

        JSONObject moodData = new JSONObject(response);
        boolean isMoodDataExisting = moodData.has(dataName);

        if(!isMoodDataExisting) {
            JSONArray newMoodData = new JSONArray();
            newMoodData.put(dataFromInput);
            moodData.put(dataName, newMoodData);
        } else {
            JSONArray newMoodData = (JSONArray) moodData.get(dataName);
            newMoodData.put(dataFromInput);
        }

        fileWriter = new FileWriter(file.getAbsoluteFile());
        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(moodData.toString());
        bufferedWriter.close();
    }

}
