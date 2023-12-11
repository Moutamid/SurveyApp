package com.moutamid.surveyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.moutamid.surveyapp.Activities.AbschlussfragebogenActivity;
import com.moutamid.surveyapp.Activities.BewertungDerFahrtActivity;
import com.moutamid.surveyapp.Activities.VorabfragebogenActivity;
import com.moutamid.surveyapp.helper.Config;

public class HomeActivity extends AppCompatActivity {
    Button vorabfragebogen, abschlussfragebogen, bewertung_der_fahrt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Config.checkApp(HomeActivity.this);
        bewertung_der_fahrt = findViewById(R.id.bewertung_der_fahrt);
        abschlussfragebogen = findViewById(R.id.abschlussfragebogen);
        vorabfragebogen = findViewById(R.id.vorabfragebogen);
        bewertung_der_fahrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeActivity.this, BewertungDerFahrtActivity.class);
                startActivity(intent);
            }
        });
        vorabfragebogen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeActivity.this, VorabfragebogenActivity.class);
                startActivity(intent);
            }
        });
        abschlussfragebogen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AbschlussfragebogenActivity.class);
                startActivity(intent);
            }
        });
    }
}