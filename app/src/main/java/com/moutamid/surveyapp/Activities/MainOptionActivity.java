package com.moutamid.surveyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moutamid.surveyapp.R;

public class MainOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_option);
    }

    public void absc(View view) {
        startActivity(new Intent(this, AbschlussfragebogenActivity.class));
    }
}