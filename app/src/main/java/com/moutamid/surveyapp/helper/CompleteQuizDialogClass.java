package com.moutamid.surveyapp.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.moutamid.surveyapp.Activities.AbschlussfragebogenActivity;
import com.moutamid.surveyapp.Activities.BewertungDerFahrtActivity;
import com.moutamid.surveyapp.Activities.MainOptionActivity;
import com.moutamid.surveyapp.Activities.ProfileActivity;
import com.moutamid.surveyapp.HomeActivity;
import com.moutamid.surveyapp.R;

public class CompleteQuizDialogClass extends Dialog {

    public Activity c;

    public CompleteQuizDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.welcome1_dailogue);
        Button welcome = findViewById(R.id.welcome);
        Button finnall = findViewById(R.id.finnall);

        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.startActivity(new Intent(c, BewertungDerFahrtActivity.class));
                c.finishAffinity();
            }
        });
        finnall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.startActivity(new Intent(c, MainOptionActivity.class));
                c.finishAffinity();
            }
        });


    }


}