
package com.example.thier.mygpa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Course_4 extends Fragment implements View.OnClickListener {

    private TextInputLayout courseName;
    private FloatingActionButton fabMain,fabStore,fabRemove,fabAdd,fabClear;

    private TextView textView, score, grade, weight, letterGrade, overallPercentage, text1,text2,text3,text4;
    private View view;
    private Button button;
    private  String name;
    private boolean changedName = false;

    private double finalScore = 0;
    private double finalWeight = 1;

    //removing and adding course tracker
    private int selector = 6;

    //first weight
    private EditText firstWeightName,firstWeightPercentage,firstScore,firstOutOf;
    private double score1 = 0.0;
    private double score1Percentage = 0.0;
    private double score1Grade = 0.0;
    private double score1OutOf = 1;


    //second weight
    private EditText SecondWeightName,SecondWeightPercentage,SecondScore,SecondOutOf;
    private double score2 = 0;
    private double score2Percentage = 0.0;
    private double score2Grade = 0.0;
    private double score2OutOf = 1;

    //third weight
    private EditText ThirdWeightName,ThirdWeightPercentage,ThirdScore,ThirdOutOf;
    private double score3 = 0;
    private double score3Percentage = 0.0;
    private double score3Grade = 0.0;
    private double score3OutOf = 1;

    //fourth weight
    private EditText FourthWeightName,FourthWeightPercentage,FourthScore,FourthOutOf;
    private double score4 = 0;
    private double score4Percentage = 0.0;
    private double score4Grade = 0.0;
    private double score4OutOf = 1;

    //fifth weight
    private EditText FifthWeightName,FifthWeightPercentage,FifthScore,FifthOutOf;
    private double score5 = 0;
    private double score5Percentage = 0.0;
    private double score5Grade = 0.0;
    private double score5OutOf = 1;

    //sixth weight
    private EditText SixthWeightName,SixthWeightPercentage,SixthScore,SixthOutOf;
    private double score6 = 0;
    private double score6Percentage = 0.0;
    private double score6Grade = 0.0;
    private double score6OutOf = 1;

    private TextView dash6, dash5, dash4, dash3, dash2, dash1;


    Float translationY = 100f;
    Boolean isMenuOpen = false;

    OvershootInterpolator interpolator = new OvershootInterpolator();


    //boolean to check if there is saved json
    private boolean isJsonSaved;

    //json that holds the the saved data
    JSONObject storedData;

    private static final String KEY_COURSE_NAME = "course_key";

    private Course4Listener listener;


    String getArgument;//String containing all saved json

    public Course_4()
    {
        this.isJsonSaved = false;
    }

    Map<String,String> jsonData;//data to save to file

    JSONObject jsonObject; //json for storing info

    //communication between fragment and activity
    public interface Course4Listener{
        void onInputCourse4Sent(JSONObject input);
        void renameDrawer4(String input);
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState)
    {
        view =  inflater.inflate(R.layout.fragment_course_4,container,false);

        jsonData = new HashMap<>();//instantiation of hashamp


        //check if something was sent from activity
        if(getArguments() != null)
        {
            this.isJsonSaved = true;
            getArgument = getArguments().getString("course4");
            try {
                storedData = new JSONObject(getArgument);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        courseName = view.findViewById(R.id.nameCourseD);
        fillTextInputLayout(courseName,"courseName");
        button =  view.findViewById(R.id.renameD);
        textView = view.findViewById(R.id.courseD);                              //show the letter grade
        fillTextView(textView,"CourseName");
        button.setOnClickListener(this);
        overallPercentage = view.findViewById(R.id.overallPercentageD);          //show overall percentage
        fillTextView(overallPercentage,"overallPercentage");



        text1 = view.findViewById(R.id.saveTD);
        text2 = view.findViewById(R.id.clearTD);
        text4 = view.findViewById(R.id.removeTD);
        text3 = view.findViewById(R.id.addTD);
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);



        //EditText 1st weight
        firstWeightName = view.findViewById(R.id.firstWeightNameD);
        fillCourseChar(firstWeightName,"firstWeightName");
        firstWeightPercentage = view.findViewById(R.id.firstWeightPercentageD);
        fillCourseInt(firstWeightPercentage,"firstWeightPercentage");
        firstScore = view.findViewById(R.id.firstScoreD);
        fillCourseInt(firstScore,"firstScore");
        firstOutOf = view.findViewById(R.id.firstOutOfD);
        fillCourseInt(firstOutOf,"firstOutOf");


        //EditText 2nd weight
        SecondWeightName = view.findViewById(R.id.fourthWeightNameD);
        fillCourseChar(SecondWeightName,"SecondWeightName");
        SecondWeightPercentage = view.findViewById(R.id.SecondWeightPercentageD);
        fillCourseInt(SecondWeightPercentage,"SecondWeightPercentage");
        SecondScore = view.findViewById(R.id.SecondScoreD);
        fillCourseInt(SecondScore,"SecondScore");
        SecondOutOf = view.findViewById(R.id.secondOutOfD);
        fillCourseInt(SecondOutOf,"SecondOutOf");

        //EditText 3rd weight
        ThirdWeightName = view.findViewById(R.id.thirdWeightNameD);
        fillCourseChar(ThirdWeightName,"ThirdWeightName");
        ThirdWeightPercentage = view.findViewById(R.id.thirdWeightD);
        fillCourseInt(ThirdWeightPercentage,"ThirdWeightPercentage");
        ThirdScore = view.findViewById(R.id.thirdScoreD);
        fillCourseInt(ThirdScore,"ThirdScore");
        ThirdOutOf = view.findViewById(R.id.thirdOutOfD);
        fillCourseInt(ThirdOutOf,"ThirdOutOf");

        //EditText 4th weight
        FourthWeightName = view.findViewById(R.id.secondWeightNameD);
        fillCourseChar(FourthWeightName,"FourthWeightName");
        FourthWeightPercentage = view.findViewById(R.id.fourthWeightD);
        fillCourseInt(FourthWeightPercentage,"FourthWeightPercentage");
        FourthScore = view.findViewById(R.id.fourthScoreD);
        fillCourseInt(FourthScore,"FourthScore");
        FourthOutOf = view.findViewById(R.id.fourthOutOfD);
        fillCourseInt(FourthOutOf,"FourthOutOf");

        //EditText 5th weight
        FifthWeightName = view.findViewById(R.id.fifthWeightNameD);
        fillCourseChar(FifthWeightName,"FifthWeightName");
        FifthWeightPercentage = view.findViewById(R.id.fifthWeightD);
        fillCourseInt(FifthWeightPercentage,"FifthWeightPercentage");
        FifthScore = view.findViewById(R.id.fifthScoreD);
        fillCourseInt(FifthScore,"FifthScore");
        FifthOutOf = view.findViewById(R.id.fifthOutOfD);
        fillCourseInt(FifthOutOf,"FifthOutOf");

        //EditText 6th weight
        SixthWeightName = view.findViewById(R.id.sixthWeightNameD);
        fillCourseChar(SixthWeightName,"SixthWeightName");
        SixthWeightPercentage = view.findViewById(R.id.sixthWeightPercentageD);
        fillCourseInt(SixthWeightPercentage,"SixthWeightPercentage");
        SixthScore = view.findViewById(R.id.sixthScoreD);
        fillCourseInt(SixthScore,"SixthScore");
        SixthOutOf = view.findViewById(R.id.sixthOutOfD);
        fillCourseInt(SixthOutOf,"SixthOutOf");

        //The dashes for removing and adding
        dash1 = view.findViewById(R.id.dash1D);
        dash2 = view.findViewById(R.id.dash2D);
        dash3 = view.findViewById(R.id.dash3D);
        dash4 = view.findViewById(R.id.dash4D);
        dash5 = view.findViewById(R.id.dash5D);
        dash6 = view.findViewById(R.id.dash6D);

        //TextView
        score = view.findViewById(R.id.scoreD);
        letterGrade = view.findViewById(R.id.letterGradeD);
        fillTextView(letterGrade,"letterGrade");
        grade = view.findViewById(R.id.gradeD);
        weight = view.findViewById(R.id.weightD);

        RemoveOrAdd("selector");
        //first grade
        firstScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                score1Grade = 0;
                String temp = editable.toString();
                if(!temp.isEmpty())
                {
                    score1Grade = Integer.parseInt(temp);
                    jsonData.put("firstScore",Double.toString(score1Grade));
                }
                score1 = score1Percentage * (score1Grade/score1OutOf);
                calculateGrade(view);
            }
        });
        firstWeightPercentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()) {
                    score1Percentage = Integer.parseInt(temp);
                    jsonData.put("firstWeightPercentage",Double.toString(score1Percentage));
                }
                score1 = score1Percentage * (score1Grade/score1OutOf);
                finalWeight = 0;
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
        firstOutOf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String temp = charSequence.toString();
                if(!temp.isEmpty()) {
                    score1OutOf = Integer.parseInt(temp);
                    jsonData.put("firstOutOf",Double.toString(score1OutOf));
                }
                score1 = score1Percentage * (score1Grade/score1OutOf);
                calculateGrade(view);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        firstWeightName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()) {
                    jsonData.put("firstWeightName",temp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //second grade
        SecondScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                score2Grade = 0;
                String temp = editable.toString();
                if(!temp.isEmpty()) {
                    score2Grade = Integer.parseInt(temp);
                    jsonData.put("SecondScore",Double.toString(score2Grade));
                }
                score2 = score2Percentage * (score2Grade/score2OutOf);
                calculateGrade(view);

            }
        });
        SecondWeightPercentage.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()){
                    score2Percentage = Integer.parseInt(temp);
                    jsonData.put("SecondWeightPercentage",Double.toString(score2Percentage));
                }

                score2 = score2Percentage * (score2Grade/score2OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        SecondOutOf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String temp = charSequence.toString();
                if(!temp.isEmpty()){
                    score2OutOf = Integer.parseInt(temp);
                    jsonData.put("SecondOutOf",Double.toString(score2OutOf));
                }
                score2 = score2Percentage * (score2Grade/score2OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        SecondWeightName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()) {
                    jsonData.put("SecondWeightName",temp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //third grade
        ThirdScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                score3Grade = 0;
                String temp = editable.toString();
                if(!temp.isEmpty())
                {
                    score3Grade = Integer.parseInt(temp);
                    jsonData.put("ThirdScore",Double.toString(score3Grade));
                }
                score3 = score3Percentage * (score3Grade/score3OutOf);
                calculateGrade(view);

            }
        });
        ThirdWeightPercentage.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()){
                    score3Percentage = Integer.parseInt(temp);
                    jsonData.put("ThirdWeightPercentage",Double.toString(score3Percentage));
                }
                score3 = score3Percentage * (score3Grade/score3OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ThirdOutOf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String temp = charSequence.toString();
                if(!temp.isEmpty())
                {
                    score3OutOf = Integer.parseInt(temp);
                    jsonData.put("ThirdOutOf",Double.toString(score3OutOf));
                }
                score3 = score3Percentage * (score3Grade/score3OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ThirdWeightName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()) {
                    jsonData.put("ThirdWeightName",temp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //4th grade
        FourthScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String temp = editable.toString();
                if(!temp.isEmpty()){
                    score4Grade = Integer.parseInt(temp);
                    jsonData.put("FourthScore",Double.toString(score4Grade));
                }
                score4 = score4Percentage * (score4Grade/score4OutOf);
                calculateGrade(view);

            }
        });
        FourthWeightPercentage.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()){
                    score4Percentage = Integer.parseInt(temp);
                    jsonData.put("FourthWeightPercentage",Double.toString(score4Percentage));
                }
                score4 = score4Percentage * (score4Grade/score4OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        FourthOutOf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String temp = charSequence.toString();
                if(!temp.isEmpty()){
                    score4OutOf = Integer.parseInt(temp);
                    jsonData.put("FourthOutOf",Double.toString(score4OutOf));

                }
                score4 = score4Percentage * (score4Grade/score4OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        FourthWeightName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()) {
                    jsonData.put("FourthWeightName",temp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //5th score
        FifthScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String temp = editable.toString();
                if(!temp.isEmpty()){
                    score5Grade = Integer.parseInt(temp);
                    jsonData.put("FifthScore",Double.toString(score5Grade));
                }
                score5 = score5Percentage * (score5Grade/score5OutOf);
                calculateGrade(view);

            }
        });
        FifthWeightPercentage.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()){
                    score5Percentage = Integer.parseInt(temp);
                    jsonData.put("FifthWeightPercentage",Double.toString(score5Percentage));

                }

                score5 = score5Percentage * (score5Grade/score5OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        FifthOutOf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String temp = charSequence.toString();
                if(!temp.isEmpty()){
                    score5OutOf = Integer.parseInt(temp);
                    jsonData.put("FifthOutOf",Double.toString(score5OutOf));
                }

                score5 = score5Percentage * (score5Grade/score5OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        FifthWeightName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()) {
                    jsonData.put("FifthWeightName",temp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        SixthWeightName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()) {
                    jsonData.put("SixthWeightName",temp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //5th score
        SixthScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String temp = editable.toString();
                if(!temp.isEmpty()){
                    score5Grade = Integer.parseInt(temp);
                    jsonData.put("SixthScore",Double.toString(score5Grade));
                }
                score6 = score6Percentage * (score6Grade/score6OutOf);
                calculateGrade(view);

            }
        });
        SixthWeightPercentage.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = charSequence.toString();
                if(!temp.isEmpty()){
                    score6Percentage = Integer.parseInt(temp);
                    jsonData.put("SixthWeightPercentage",Double.toString(score6Percentage));

                }

                score6 = score6Percentage * (score6Grade/score6OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        SixthOutOf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String temp = charSequence.toString();
                if(!temp.isEmpty()){
                    score6OutOf = Integer.parseInt(temp);
                    jsonData.put("SixthOutOf",Double.toString(score6OutOf));
                }

                score6 = score6Percentage * (score6Grade/score6OutOf);
                calculateGrade(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //save to json
        fabMain = (FloatingActionButton) view.findViewById(R.id.MainMenuD);
        fabAdd = (FloatingActionButton) view.findViewById(R.id.addD);
        fabRemove = (FloatingActionButton) view.findViewById(R.id.removeD);
        fabClear = (FloatingActionButton) view.findViewById(R.id.clearD);
        fabStore = (FloatingActionButton) view.findViewById(R.id.storeD);

        fabAdd.setAlpha(0f);
        fabRemove.setAlpha(0f);
        fabClear.setAlpha(0f);
        fabStore.setAlpha(0f);

        fabAdd.setTranslationY(translationY);
        fabRemove.setTranslationY(translationY);
        fabClear.setTranslationY(translationY);
        fabStore.setTranslationY(translationY);

        fabMain.setOnClickListener(this);
        fabAdd.setOnClickListener(this);
        fabRemove.setOnClickListener(this);
        fabStore.setOnClickListener(this);
        fabClear.setOnClickListener(this);



        //to handle rotation
        if(savedInstanceState != null)
        {
            String tempName = savedInstanceState.getString(KEY_COURSE_NAME,"");
            if(!tempName.isEmpty())hide(tempName);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Course4Listener)
        {
            listener =(Course4Listener) context;
        }else
        {
            throw new RuntimeException(context.toString()+" must implement Course4Listener");
        }
    }

    private void openMenu()
    {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();

        fabStore.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabAdd.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabClear.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabRemove.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        text1.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);
        text3.setVisibility(View.VISIBLE);
        text4.setVisibility(View.VISIBLE);
    }

    private void closeMenu()
    {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

        fabStore.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabAdd.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabClear.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabRemove.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
    }

    private void handleClear()
    {
        Toast.makeText(getActivity(),"clear",Toast.LENGTH_SHORT).show();
        firstWeightName.setText("");
        firstWeightPercentage.setText("");
        firstScore.setText("");
        firstOutOf.setText("");
        SecondWeightName.setText("");
        SecondWeightPercentage.setText("");
        SecondScore.setText("");
        SecondOutOf.setText("");
        ThirdWeightName.setText("");
        ThirdWeightPercentage.setText("");
        ThirdScore.setText("");
        ThirdOutOf.setText("");
        FourthWeightName.setText("");
        FourthWeightPercentage.setText("");
        FourthScore.setText("");
        FourthOutOf.setText("");
        FifthWeightName.setText("");
        FifthWeightPercentage.setText("");
        FifthScore.setText("");
        FifthOutOf.setText("");
        SixthOutOf.setText("");
        SixthWeightPercentage.setText("");
        SixthWeightName.setText("");
        SixthScore.setText("");
        listener.renameDrawer4("Course_4");
        textView.setText("");
        textView.setTextSize(50);
        button.setEnabled(true);
        courseName.setEnabled(true);
        button.setVisibility(View.VISIBLE);
        courseName.setVisibility(View.VISIBLE);
        changedName = false;
        //score1 = 0;
        //score2 = 0;
        //score3 = 0;
        //score4 = 0;
        //score5 = 0;
        //score6 = 0;
        if(storedData != null)
        {
            Iterator<String> keys = storedData.keys();
            while (keys.hasNext()) {
                try {
                    storedData.put(keys.next(), "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            listener.onInputCourse4Sent(storedData);
        }
    }

    private void handleStore()
    {
        //turn the saved values in a hashtable to json
        //if nothing was saved from previous use
        Toast.makeText(getActivity(),"saved",Toast.LENGTH_SHORT).show();
        if(storedData == null)
        {
            storedData = new JSONObject(jsonData);

        }
        else                                                            //add the saved key and value from the hashtable to the json
        {                                                                   //which holds previous data from saved work
            Iterator<Map.Entry<String, String>> iterate = jsonData.entrySet().iterator();
            while (iterate.hasNext())
            {
                Map.Entry<String, String> data = iterate.next();
                try {
                    storedData.put(data.getKey(),data.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        listener.onInputCourse4Sent(storedData); //send the json data

    }

    private void handleAdd()
    {
        selector++;
        if(selector >= 6)
        {
            selector = 6;
            Toast.makeText(getActivity(),"nothing to add",Toast.LENGTH_SHORT).show();
        }

        if(selector >= 6)
        {
            SixthScore.setEnabled(true);
            SixthScore.setVisibility(View.VISIBLE);
            SixthWeightName.setEnabled(true);
            SixthWeightName.setVisibility(View.VISIBLE);
            SixthWeightPercentage.setEnabled(true);
            SixthWeightPercentage.setVisibility(View.VISIBLE);
            dash6.setVisibility(View.VISIBLE);
            SixthOutOf.setEnabled(true);
            SixthOutOf.setVisibility(View.VISIBLE);
        }
        if(selector >= 5)
        {
            FifthScore.setEnabled(true);
            FifthScore.setVisibility(View.VISIBLE);
            FifthWeightName.setEnabled(true);
            FifthWeightName.setVisibility(View.VISIBLE);
            FifthWeightPercentage.setEnabled(true);
            FifthWeightPercentage.setVisibility(View.VISIBLE);
            dash5.setVisibility(View.VISIBLE);
            FifthOutOf.setEnabled(true);
            FifthOutOf.setVisibility(View.VISIBLE);
        }
        if(selector >= 4)
        {
            FourthScore.setEnabled(true);
            FourthScore.setVisibility(View.VISIBLE);
            FourthWeightName.setEnabled(true);
            FourthWeightName.setVisibility(View.VISIBLE);
            FourthWeightPercentage.setEnabled(true);
            FourthWeightPercentage.setVisibility(View.VISIBLE);
            dash4.setVisibility(View.VISIBLE);
            FourthOutOf.setEnabled(true);
            FourthOutOf.setVisibility(View.VISIBLE);
        }
        if(selector >= 3)
        {
            ThirdScore.setEnabled(true);
            ThirdScore.setVisibility(View.VISIBLE);
            ThirdWeightName.setEnabled(true);
            ThirdWeightName.setVisibility(View.VISIBLE);
            ThirdWeightPercentage.setEnabled(true);
            ThirdWeightPercentage.setVisibility(View.VISIBLE);
            dash3.setVisibility(View.VISIBLE);
            ThirdOutOf.setEnabled(true);
            ThirdOutOf.setVisibility(View.VISIBLE);
        }
        if(selector >= 2)
        {
            SecondScore.setEnabled(true);
            SecondScore.setVisibility(View.VISIBLE);
            SecondWeightName.setEnabled(true);
            SecondWeightName.setVisibility(View.VISIBLE);
            SecondWeightPercentage.setEnabled(true);
            SecondWeightPercentage.setVisibility(View.VISIBLE);
            dash2.setVisibility(View.VISIBLE);
            SecondOutOf.setEnabled(true);
            SecondOutOf.setVisibility(View.VISIBLE);
        }
        jsonData.put("selector",Integer.toString(selector));
    }

    private void handleRemove()
    {
        selector--;
        if(selector == 1) {
            selector = 1;
            Toast.makeText(getActivity(), "Nothing to remove", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getActivity(), "Removing Grading", Toast.LENGTH_SHORT).show();
        if(selector < 6)
        {
            SixthScore.setEnabled(false);
            SixthScore.setVisibility(View.INVISIBLE);
            SixthWeightName.setEnabled(false);
            SixthWeightName.setVisibility(View.INVISIBLE);
            SixthWeightPercentage.setEnabled(false);
            SixthWeightPercentage.setVisibility(View.INVISIBLE);
            dash6.setVisibility(View.INVISIBLE);
            SixthOutOf.setEnabled(false);
            SixthOutOf.setVisibility(View.INVISIBLE);
        }
        if(selector < 5)
        {
            FifthScore.setEnabled(false);
            FifthScore.setVisibility(View.INVISIBLE);
            FifthWeightName.setEnabled(false);
            FifthWeightName.setVisibility(View.INVISIBLE);
            FifthWeightPercentage.setEnabled(false);
            FifthWeightPercentage.setVisibility(View.INVISIBLE);
            dash5.setVisibility(View.INVISIBLE);
            FifthOutOf.setEnabled(false);
            FifthOutOf.setVisibility(View.INVISIBLE);
        }
        if(selector < 4)
        {
            FourthScore.setEnabled(false);
            FourthScore.setVisibility(View.INVISIBLE);
            FourthWeightName.setEnabled(false);
            FourthWeightName.setVisibility(View.INVISIBLE);
            FourthWeightPercentage.setEnabled(false);
            FourthWeightPercentage.setVisibility(View.INVISIBLE);
            dash4.setVisibility(View.INVISIBLE);
            FourthOutOf.setEnabled(false);
            FourthOutOf.setVisibility(View.INVISIBLE);
        }
        if(selector < 3)
        {
            ThirdScore.setEnabled(false);
            ThirdScore.setVisibility(View.INVISIBLE);
            ThirdWeightName.setEnabled(false);
            ThirdWeightName.setVisibility(View.INVISIBLE);
            ThirdWeightPercentage.setEnabled(false);
            ThirdWeightPercentage.setVisibility(View.INVISIBLE);
            dash3.setVisibility(View.INVISIBLE);
            ThirdOutOf.setEnabled(false);
            ThirdOutOf.setVisibility(View.INVISIBLE);
        }
        if(selector < 2)
        {
            SecondScore.setEnabled(false);
            SecondScore.setVisibility(View.INVISIBLE);
            SecondWeightName.setEnabled(false);
            SecondWeightName.setVisibility(View.INVISIBLE);
            SecondWeightPercentage.setEnabled(false);
            SecondWeightPercentage.setVisibility(View.INVISIBLE);
            dash2.setVisibility(View.INVISIBLE);
            SecondOutOf.setEnabled(false);
            SecondOutOf.setVisibility(View.INVISIBLE);
        }
        jsonData.put("selector",Integer.toString(selector));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void updateEditText(CharSequence newText)
    {
        if(newText.length() ==0)
        {
            letterGrade.setText("empty");
        }
        else {
            String temp = String.valueOf(newText);
            letterGrade.setText(newText);
        }

    }

    private void calculateGrade(View view)
    {
        finalScore = 0;
        finalWeight = 0;
        finalWeight = score2Percentage +score1Percentage + score3Percentage + score4Percentage + score5Percentage + score6Percentage;
        finalScore = ((score1 + score2 + score3 + score4 + score5 + score6)/finalWeight)*100;
        String temp = "F";
        if(finalScore > 89){
            letterGrade.setText("A");
            temp = "A";

        }
        else if(finalScore > 79 && finalScore < 90){
            letterGrade.setText("B");
            temp = "B";
        }
        else if(finalScore > 69 && finalScore < 80){
            letterGrade.setText("C");
            temp = "C";
        }
        else if(finalScore > 59 && finalScore < 70){
            letterGrade.setText("D");
            temp = "D";
        }
        else{
            letterGrade.setText("F");
        }
        //letterGrade.setTextColor(Color.rgb(129,198,57));
        jsonData.put("letterGrade",temp);

        if(finalScore < 10) //if score is less than 10
        {                       //show three letters

            String str = Double.toString(finalScore);           //show the overrall percentage
            if(str.length() <= 4)
                str =  str.concat("%");
            else
            {
                str = str.substring(0,3);
                str =   str.concat("%");
            }
            overallPercentage.setText(str);
            //overallPercentage.setTextColor(Color.rgb(129,198,57));
            jsonData.put("overallPercentage",str);
        }

        else
        {
            String str = Double.toString(finalScore);           //show the overrall percentage
            if(str.length() <= 5)
                str =  str.concat("%");
            else
            {
                str = str.substring(0,5);           //reevaluate this one
                str =   str.concat("%");
            }
            overallPercentage.setText(str);
            //overallPercentage.setTextColor(Color.rgb(129,198,57));
            jsonData.put("overallPercentage",str);
        }
    }

    private boolean validateName()
    {
        name = courseName.getEditText().getText().toString().trim();

        if(name.isEmpty())
        {
            courseName.setError("Field can't be empty");
            return false;
        }
        else if(name.length()>20)
        {
            courseName.setError("Name too long");
            return false;
        }
        else
        {
            courseName.setError(null);
            courseName.setErrorEnabled(false);
            return true;
        }
    }

    private void hide(String tempName)
    {
        textView.setText(tempName);
        textView.setTextSize(50);
        button.setEnabled(false);
        courseName.setEnabled(false);
        button.setVisibility(View.INVISIBLE);
        courseName.setVisibility(View.INVISIBLE);
        name = tempName;
    }

    //give the course the title
    private void rename()
    {
        if(!validateName())return;
        Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();
        textView.setText(name);
        textView.setTextSize(50);
        jsonData.put("CourseName",name);
        button.setEnabled(false);
        courseName.setEnabled(false);
        button.setVisibility(View.INVISIBLE);
        courseName.setVisibility(View.INVISIBLE);
        changedName = true;
        listener.renameDrawer4(name);
    }


    //the course name
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.renameD:
                rename();
                break;
            case R.id.storeD:
                handleStore();
                if(isMenuOpen)
                    closeMenu();
                else openMenu();
                break;
            case R.id.clearD:
                handleClear();
                if(isMenuOpen)
                    closeMenu();
                else openMenu();
                break;
            case R.id.removeD:
                handleRemove();
                if(isMenuOpen)
                    closeMenu();
                else openMenu();
                break;
            case R.id.addD:
                handleAdd();
                if(isMenuOpen)
                    closeMenu();
                else openMenu();
                break;
            case R.id.MainMenuD:
                if(isMenuOpen)
                    closeMenu();
                else openMenu();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_COURSE_NAME,name);
    }

    //fills the integer only Edittext
    @SuppressLint("SetTextI18n")
    private void fillCourseInt(EditText editText, String id)  {
        if(isJsonSaved && storedData.has(id)){
            String temp = null;
            try {
                temp = (String) storedData.get(id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(!temp.isEmpty())
            {
                Double temp1 = Double.parseDouble(temp);
                int i = temp1.intValue();
                editText.setText(Integer.toString(i));

                switch (id) //to make sure even the int variables have the same value
                {
                    case "firstScore":
                        score1Grade = i;
                        score1 = score1Percentage * (score1Grade/score1OutOf);
                        break;
                    case "firstWeightPercentage":
                        score1Percentage = i;
                        score1 = score1Percentage * (score1Grade/score1OutOf);
                        break;
                    case "firstOutOf":
                        score1OutOf = i;
                        score1 = score1Percentage * (score1Grade/score1OutOf);
                        break;
                    case "SecondWeightPercentage":
                        score2Percentage = i;
                        score2 = score2Percentage * (score2Grade/score2OutOf);
                        break;
                    case "SecondScore":
                        score2Grade = i;
                        score2 = score2Percentage * (score2Grade/score2OutOf);
                        break;
                    case "SecondOutOf":
                        score2OutOf = i;
                        score2 = score2Percentage * (score2Grade/score2OutOf);
                        break;
                    case "ThirdWeightPercentage":
                        score3Percentage = i;
                        score3 = score3Percentage * (score3Grade/score3OutOf);
                        break;
                    case "ThirdScore":
                        score3Grade = i;
                        score3 = score3Percentage * (score3Grade/score3OutOf);
                        break;
                    case "ThirdOutOf":
                        score3OutOf = i;
                        score3 = score3Percentage * (score3Grade/score3OutOf);
                        break;
                    case "FourthWeightPercentage":
                        score4Percentage = i;
                        score4 = score4Percentage * (score4Grade/score4OutOf);
                        break;
                    case "FourthScore":
                        score4Grade = i;
                        score4 = score4Percentage * (score4Grade/score4OutOf);
                        break;
                    case "FourthOutOf":
                        score4OutOf = i;
                        score4 = score4Percentage * (score4Grade/score4OutOf);
                        break;
                    case "FifthWeightPercentage":
                        score5Percentage = i;
                        score5 = score5Percentage * (score5Grade/score5OutOf);
                        break;
                    case "FifthScore":
                        score5Grade = i;
                        score5 = score5Percentage * (score5Grade/score5OutOf);
                        break;
                    case "FifthOutOf":
                        score5OutOf = i;
                        score5 = score5Percentage * (score5Grade/score5OutOf);
                        break;
                }
            }
        }

    }

    //fills the String only edittext
    private void fillCourseChar(EditText editText, String id) {
        if(isJsonSaved && storedData.has(id)){
            String temp = null;
            try {
                temp = (String) storedData.get(id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            editText.setText(temp);
        }
    }

    //fills all the textview
    private void fillTextView(TextView textView, String id) {
        if (isJsonSaved && storedData.has(id)) {
            String temp = null;
            try {
                temp = (String) storedData.get(id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(id == "CourseName" && !temp.isEmpty())
            {
                textView.setText(temp);
                textView.setTextSize(50);
                button.setEnabled(false);
                courseName.setEnabled(false);
                button.setVisibility(View.INVISIBLE);
                courseName.setVisibility(View.INVISIBLE);
                changedName = true;
                listener.renameDrawer4(temp);
            }
            textView.setText(temp);
        }
    }

    //fills all the textinput layout
    private void fillTextInputLayout(TextInputLayout textInputLayout, String id) {
        if (isJsonSaved && storedData.has(id)) {
            String temp = null;
            try {
                temp = (String) storedData.get(id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //te.setText(temp);
        }
    }

    private void RemoveOrAdd(String id)
    {
        String temp = null;
        if(isJsonSaved && storedData.has(id))
        {
            try {
                temp = (String) storedData.get(id);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        if(temp != null && !temp.isEmpty()) {
            Integer temp1 = Integer.parseInt(temp);
            selector = temp1.intValue();
            if(selector > 5) {
                selector = 7;
                handleAdd();
                Log.i("selector", "it is greater than 5");
            }
            else if(selector < 5) {
                selector++;
                handleRemove();
                Log.i("selector","it is less than 5");
            }
            else if(selector == 5)
            {
                //do nothing
                selector++;
                handleRemove();
            }
        }
        else
        {
            Log.i("selector","we are in else");
            selector = 7;
            handleRemove();
        }
    }

}