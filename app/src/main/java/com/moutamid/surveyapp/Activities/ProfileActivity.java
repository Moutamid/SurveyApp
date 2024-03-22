package com.moutamid.surveyapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;
import com.moutamid.surveyapp.HomeActivity;
import com.moutamid.surveyapp.R;

public class ProfileActivity extends AppCompatActivity {
    EditText edt_name;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        submit = findViewById(R.id.submit);
        edt_name = findViewById(R.id.edt_name);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_name.getText().toString().isEmpty()) {
                    edt_name.setError("Bitte geben Sie Ihren Namen ein.");
                }
                else
                {
                    Stash.put("name", edt_name.getText().toString());
                    String mId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                    Stash.put("device_id", mId);
                    startActivity(new Intent(ProfileActivity.this, MainOptionActivity.class));
                    finish();
                }
            }
        });

    }
}