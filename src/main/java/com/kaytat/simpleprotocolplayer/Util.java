package com.kaytat.simpleprotocolplayer;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Util {

    public static ArrayList<String> getListFromPrefs(SharedPreferences prefs, String keyJson,
                                       String keySingle) {
        // Retrieve the values from the shared preferences
        String jsonString = prefs.getString(keyJson, null);
        ArrayList<String> arrayList = new ArrayList<>();

        if (jsonString == null || jsonString.length() == 0) {
            // Try to fill with the original key used
            String single = prefs.getString(keySingle, null);
            if (single != null && single.length() != 0) {
                arrayList.add(single);
            }
        } else {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);

                // Note that the array is hard-coded as the element labelled
                // as 'list'
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                for (int i = 0; i < jsonArray.length(); i++) {
                    String s = (String) jsonArray.get(i);
                    if (s != null && s.length() != 0) {
                        arrayList.add(s);
                    }
                }
            } catch (JSONException jsonException) {
                Log.i(TAG, jsonException.toString());
            }
        }

        return arrayList;
    }
}
