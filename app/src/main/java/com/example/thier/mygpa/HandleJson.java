package com.example.thier.mygpa;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HandleJson
{
    Map<String,JSONObject> AllData;
    MainActivity mainActivity;
    String jsonString;
    //JSONObject jsonObject;


    public HandleJson(MainActivity that_mainActivity,String json) throws JSONException {
        this.mainActivity = that_mainActivity;
        this.jsonString = json;
        AllData = new HashMap<>();
        addPreviousData();
    }


    //save everything the user had saved before
    private void addPreviousData() throws JSONException
    {
        JSONObject obj = null;
        try {
             obj = new JSONObject(this.jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if( obj != null)
        {
            Iterator<String> KeysIterate = obj.keys();
            while(KeysIterate.hasNext())
            {
                String key = KeysIterate.next();
                JSONObject value = (JSONObject) obj.get(key);
                AllData.put(key,value);
            }

        }
    }


    //save json
    public  String saveJson(JSONObject input, String courseId)
    {
        AllData.put(courseId,input);
        JSONObject jsonObjectStore = new JSONObject(AllData);
        this.jsonString = jsonObjectStore.toString();
        return this.jsonString;
    }

    public String resetStored()
    {
        this.jsonString = "";
        return this.jsonString;
    }


    public String getJson(String courseId)
    {
        String data = "";

            switch(courseId)
            {
                case "course1":
                    Log.i("HandleJson","getting json for Course 1");
                    data = processJson(courseId);
                    break;
                case "course2":
                    Log.i("HandleJson","getting json for course 2");
                    data = processJson(courseId);
                    break;
                case "course3":
                    Log.i("HandleJson","getting json for course 3");
                    data = processJson(courseId);
                    break;
                case "course4":
                    Log.i("HandleJson","getting json for course 4");
                    data = processJson(courseId);
                    break;
                case "course5":
                    Log.i("HandleJson","getting json for course 5");
                    data = processJson(courseId);
                    break;
                case "course6":
                    Log.i("HandleJson","getting json for course 6");
                    data = processJson(courseId);
                    break;
                case "drawer1":
                    data = processJson(courseId);
                    break;
                case "drawer2":
                    data = processJson(courseId);
                    break;
                case "drawer3":
                    data = processJson(courseId);
                    break;
                case "drawer4":
                    data = processJson(courseId);
                    break;
                case "drawer5":
                    data = processJson(courseId);
                    break;
            }

        return data;
    }

    //get data from
    private String processJson(String courseId)
    {
        String data= "";
        try {
            // get JSONObject from JSON file
           JSONObject jsonObjectGet = new JSONObject(this.jsonString);
            JSONObject course = jsonObjectGet.getJSONObject(courseId); //picks the json object with same name
            data = course.toString();

            }catch (JSONException e) {
                e.printStackTrace();
                //data = "no json data";
        }

        return data;
    }

}
