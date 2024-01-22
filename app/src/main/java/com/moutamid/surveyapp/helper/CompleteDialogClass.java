package com.moutamid.surveyapp.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.moutamid.surveyapp.Activities.ProfileActivity;
import com.moutamid.surveyapp.HomeActivity;
import com.moutamid.surveyapp.R;

public class CompleteDialogClass extends Dialog {

    public Activity c;

    public CompleteDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.welcome_dailogue);
        Button welcome = findViewById(R.id.welcome);
        Button finnall = findViewById(R.id.finnall);

        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.startActivity(new Intent(c, ProfileActivity.class));
                c.finishAffinity();
            }
        });
        finnall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.startActivity(new Intent(c, HomeActivity.class));
                c.finishAffinity();
            }
        });


    }


}