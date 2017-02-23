package com.application.spokn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import static android.os.SystemClock.sleep;

public class Startup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        sleep(20000);
        startActivity(new Intent(Startup.this,LoginActivity.class));
    }
}
