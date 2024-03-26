package com.moutamid.surveyapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.moutamid.surveyapp.R;

public class MainOptionActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    String[] permissions13 = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_option);
    }

    public void vora(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (above13Check()) {
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES);
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_VIDEO);
                ActivityCompat.requestPermissions(MainOptionActivity.this, permissions13, 2);
            } else {
                startActivity(new Intent(this, VorabfragebogenActivity.class));
            }
        } else {
            if (below13Check()) {
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                ActivityCompat.requestPermissions(MainOptionActivity.this, permissions, 2);
            } else {
                startActivity(new Intent(this, VorabfragebogenActivity.class));
            }
        }
    }    public void absc(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (above13Check()) {
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES);
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_VIDEO);
                ActivityCompat.requestPermissions(MainOptionActivity.this, permissions13, 2);
            } else {
                startActivity(new Intent(this, AbschlussfragebogenActivity.class));
            }
        } else {
            if (below13Check()) {
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                ActivityCompat.requestPermissions(MainOptionActivity.this, permissions, 2);
            } else {
                startActivity(new Intent(this, AbschlussfragebogenActivity.class));
            }
        }
    }
    public void bewer(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (above13Check()) {
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES);
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_VIDEO);
                ActivityCompat.requestPermissions(MainOptionActivity.this, permissions13, 2);
            } else {
                startActivity(new Intent(this, BewertungDerFahrtActivity.class));
            }
        } else {
            if (below13Check()) {
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                ActivityCompat.requestPermissions(MainOptionActivity.this, permissions, 2);
            } else {
                startActivity(new Intent(this, BewertungDerFahrtActivity.class));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private boolean above13Check() {
        return ContextCompat.checkSelfPermission(MainOptionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainOptionActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    private boolean below13Check() {
        return ContextCompat.checkSelfPermission(MainOptionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainOptionActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }
}