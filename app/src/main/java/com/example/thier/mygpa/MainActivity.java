package com.example.thier.mygpa;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//Course_1.Course1Listener,
public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener,Course_1.Course1Listener,
                                                                Course_2.Course2Listener, Course_3.Course3Listener, Course_4.Course4Listener,
                                                                Course_5.Course5Listener
{

    private MenuItem menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    String sendCourse1;
    HandleJson handleJson;
    JSONObject jsonObject;
    JSONArray userArray;

    private Course_1 course1;
    private Course_2 course2;
    private Course_3 course3;
    private Course_4 course4;
    private Course_5 course5;

    private int item = 0;

    String jsonData = null; //json data to send to handlejson



    String Filename = "file.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        sendCourse1 = "";
        course1 = new Course_1();
        course2 = new Course_2();
        course3 = new Course_3();
        course4 = new Course_4();
        course5 = new Course_5();

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Menu menu = navigationView.getMenu();
        menuItem1 = menu.findItem(R.id.NavCourse1);
        menuItem2 = menu.findItem(R.id.NavCourse2);
        menuItem3 = menu.findItem(R.id.NavCourse3);
        menuItem4 = menu.findItem(R.id.NavCourse4);
        menuItem5 = menu.findItem(R.id.NavCourse5);

        jsonData = ReadFromFile(Filename); //get saved data from files
        try {
            handleJson = new HandleJson(this,jsonData); //sending json data to HandleJson
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(savedInstanceState == null)
        {
            //Fragment course1Fragment = new Course_1();
            Bundle data = new Bundle();
            String temp = handleJson.getJson("course1");
            if(temp != "" && temp != null )
            {
                data.putString("course1",handleJson.getJson("course1"));
                course1.setArguments(data);
            }
            fragmentManager.beginTransaction().replace(R.id.fragment_container,course1).commit();
            navigationView.setCheckedItem(R.id.NavCourse1);
        }




        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

                switch(item)
                {
                    case R.id.NavCourse1:
                        Bundle data = new Bundle();
                        String temp = handleJson.getJson("course1");
                        if(temp != "" && temp != null )                         //check if course1 is saved, if null do nothing
                        {                                                       //if not null send the data to fragrament
                            data.putString("course1",handleJson.getJson("course1"));
                            course1.setArguments(data);
                        }
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_right_left,R.anim.exit_right_left,R.anim.enter_left_right,R.anim.exit_left_right).replace(R.id.fragment_container,course1).commit();
                        break;

                    case R.id.NavCourse2:
                        Bundle data1 = new Bundle();
                        String tempB = handleJson.getJson("course2");
                        if(tempB != "" && tempB != null)
                        {
                            data1.putString("course2",handleJson.getJson("course2"));  //check if course2 is saved, if null do nothing
                            course2.setArguments(data1);                                      //if not null send the data to fragrament
                        }
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_right_left,R.anim.exit_right_left,R.anim.enter_left_right,R.anim.exit_left_right).replace(R.id.fragment_container,course2).commit();
                        break;

                    case R.id.NavCourse3:
                        Bundle data2 = new Bundle();
                        String tempC = handleJson.getJson("course3");
                        if(tempC != "" && tempC != null)
                        {
                            data2.putString("course3",handleJson.getJson("course3"));  //check if course2 is saved, if null do nothing
                            course3.setArguments(data2);                                      //if not null send the data to fragrament
                        }
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_right_left,R.anim.exit_right_left,R.anim.enter_left_right,R.anim.exit_left_right).replace(R.id.fragment_container,course3).commit();
                        break;

                    case R.id.NavCourse4:
                        Bundle data3 = new Bundle();
                        String tempD = handleJson.getJson("course4");
                        if(tempD != "" && tempD != null)
                        {
                            data3.putString("course4",handleJson.getJson("course4"));  //check if course2 is saved, if null do nothing
                            course4.setArguments(data3);                                      //if not null send the data to fragrament
                        }
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_right_left,R.anim.exit_right_left,R.anim.enter_left_right,R.anim.exit_left_right).replace(R.id.fragment_container,course4).commit();

                        break;
                    case R.id.NavCourse5:
                        Bundle data4 = new Bundle();
                        String tempE = handleJson.getJson("course5");
                        if(tempE != "" && tempE != null)
                        {
                            data4.putString("course5",handleJson.getJson("course5"));  //check if course2 is saved, if null do nothing
                            course5.setArguments(data4);                                      //if not null send the data to fragrament
                        }
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_right_left,R.anim.exit_right_left,R.anim.enter_left_right,R.anim.exit_left_right).replace(R.id.fragment_container,course5).commit();
                        break;

                    default:break;

                }

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        OnStartNameDrawer();
    }

    private void OnStartNameDrawer()
    {
        //call getJson
        String temp1 = "";
        temp1 = handleJson.getJson("drawer1");
        if(!temp1.isEmpty() && temp1 != null )
        {
            JSONObject temp = null;
            try {
                temp = new JSONObject(temp1);
                temp1 = (String) temp.get("drawer1");
            } catch (JSONException e) {
                e.printStackTrace();
                temp1 = "Course_1";

            }
            menuItem1.setTitle(temp1);
        }


        String temp2 = "";
        temp2 = handleJson.getJson("drawer2");
        if(!temp2.isEmpty() && temp2 != null )
        {
            JSONObject temp = null;
            try {
                 temp = new JSONObject(temp2);
                 temp2 = (String) temp.get("drawer2");
            } catch (JSONException e) {
                e.printStackTrace();
                temp2 = "Course_2";

            }
            menuItem2.setTitle(temp2);
        }

        String temp3 = "";
        temp3 = handleJson.getJson("drawer3");
        if(!temp3.isEmpty() && temp3 != null )
        {
            JSONObject temp = null;
            try {
                temp = new JSONObject(temp3);
                temp3 = (String) temp.get("drawer3");
            } catch (JSONException e) {
                e.printStackTrace();
                temp3 = "Course_3";

            }
            menuItem3.setTitle(temp3);
        }


        String temp4 = "";
        temp4 = handleJson.getJson("drawer4");
        if(!temp4.isEmpty() && temp4 != null ) {
            JSONObject temp = null;
            try {
                temp = new JSONObject(temp4);
                temp4 = (String) temp.get("drawer4");
            } catch (JSONException e) {
                e.printStackTrace();
                temp4 = "Course_4";

            }
            menuItem4.setTitle(temp4);
        }

        String temp5 = "";
        temp5 = handleJson.getJson("drawer5");
        if(!temp5.isEmpty() && temp5 != null )
        {
            JSONObject temp = null;
            try {
                temp = new JSONObject(temp5);
                temp5 = (String) temp.get("drawer5");
            } catch (JSONException e) {
                e.printStackTrace();
                temp5 = "Course_5";

            }
            menuItem5.setTitle(temp5);
        }

    }


    @Override
    public void renameDrawer(String input)
    {
        Map<String,String> save = new HashMap<>();
        JSONObject drawer1 = null;
        menuItem1.setTitle(input);
        save.put("drawer1",input);
        drawer1 = new JSONObject(save);
        String temp = handleJson.saveJson(drawer1,"drawer1");
        StoreToFile(Filename,temp);
    }

    @Override
    public void renameDrawer2(String input)
    {
        Map<String,String> save = new HashMap<>();
        JSONObject drawer2 = null;
        menuItem2.setTitle(input);
        save.put("drawer2",input);
        drawer2 = new JSONObject(save);
        String temp = handleJson.saveJson(drawer2,"drawer2");
        StoreToFile(Filename,temp);
    }

    @Override
    public void renameDrawer3(String input)
    {
        Map<String,String> save = new HashMap<>();
        JSONObject drawer3 = null;
        menuItem3.setTitle(input);
        save.put("drawer3",input);
        drawer3 = new JSONObject(save);
        String temp = handleJson.saveJson(drawer3,"drawer3");
        StoreToFile(Filename,temp);
    }

    @Override
    public void renameDrawer4(String input)
    {
        Map<String,String> save = new HashMap<>();
        JSONObject drawer4 = null;
        menuItem4.setTitle(input);
        save.put("drawer4",input);
        drawer4 = new JSONObject(save);
        String temp = handleJson.saveJson(drawer4,"drawer4");
        StoreToFile(Filename,temp);
    }

    @Override
    public void renameDrawer5(String input)
    {
        Map<String,String> save = new HashMap<>();
        JSONObject drawer5 = null;
        menuItem5.setTitle(input);
        save.put("drawer5",input);
        drawer5 = new JSONObject(save);
        String temp = handleJson.saveJson(drawer5,"drawer5");
        StoreToFile(Filename,temp);
    }

    @Override   //receives the json from course1
    public void onInputCourse1Sent(JSONObject input)
    {
        //send json data to the handleJson class
        String temp = handleJson.saveJson(input,"course1");
        StoreToFile(Filename,temp); //send json to get saved
    }

    @Override   //receives the json from course2
    public void onInputCourse2Sent(JSONObject input)
    {
        //send json data to the handleJson class
        String temp = handleJson.saveJson(input,"course2");
        StoreToFile(Filename,temp); //send json to get saved
    }

    @Override   //receives the json from course3
    public void onInputCourse3Sent(JSONObject input)
    {
        //send json data to the handleJson class
        String temp = handleJson.saveJson(input,"course3");
        StoreToFile(Filename,temp); //send json to get saved
    }

    @Override
    public void onInputCourse4Sent(JSONObject input)
    {
        //send json data to the handleJson class
        String temp = handleJson.saveJson(input,"course4");
        StoreToFile(Filename,temp); //send json to get saved
    }

    @Override
    public void onInputCourse5Sent(JSONObject input)
    {
        //send json data to the handleJson class
        String temp = handleJson.saveJson(input,"course5");
        StoreToFile(Filename,temp); //send json to get saved
    }


    private String course1String()
    {
        return sendCourse1;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.NavCourse1:
                item = menuItem.getItemId();
                break;
            case R.id.NavCourse2:
                item = menuItem.getItemId();
                break;
            case R.id.NavCourse3:
                item = menuItem.getItemId();
               break;
            case R.id.NavCourse4:
                item = menuItem.getItemId();
                break;
            case R.id.NavCourse5:
                item = menuItem.getItemId();
                break;
            case R.id.settings:
                Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.reset:
                Toast.makeText(this,"cleared",Toast.LENGTH_SHORT).show();
                StoreToFile(Filename, handleJson.resetStored());
                ResetPressed();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ResetPressed()
    {
        menuItem1.setTitle("Course_1");
        menuItem2.setTitle("Course_2");
        menuItem3.setTitle("Course_3");
        menuItem4.setTitle("course_4");
        menuItem5.setTitle("Course_5");
    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    public String getJson()
    {
        String json = null;
            try {
                InputStream inputStream = getAssets().open("course1.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                json = new String(buffer,"UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        return json;
    }

    public String ReadFromFile(String file)
    {
        String text = "";
        try{
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        }catch (Exception e)
        {
            e.printStackTrace();
            Log.i("mainActivity","error reding from file");
        }
        return text;
    }


    public void StoreToFile(String file, String text)
    {
        try{
            FileOutputStream fileOutputStream = openFileOutput(file,Context.MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
            Log.i("mainActivity","finished writing to File");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
