package com.application.spokn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class NameChallenge extends AppCompatActivity {

    TextView tvKeys[];
    int i = 0; //Used to iterate over TextViews used in displayKeys Function
    DBConnect dbc = new DBConnect();
    WatsonSpeechTT newSpeech;
    ArrayList<String> keys;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_challenge);

        populateCategoryKeys(category);
        newSpeech=new WatsonSpeechTT();
        //newSpeech.SetFab(fab);
        newSpeech.SetActivity(this);
        newSpeech.Init();

        newSpeech.BeginSTT();


    }


    void populateCategoryKeys(String category){

        keys = dbc.getCategoryKey(category);
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();

        String callerClassName = stElements[1].getClassName().getClass().toString();
        String calleeClassName = stElements[0].getClassName().getClass().toString();

        System.out.println(callerClassName);
        System.out.println(calleeClassName);


    }
}
