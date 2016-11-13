package com.example.devin.mobiledevicesproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void login (View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivityForResult(intent, 1);
    }
}