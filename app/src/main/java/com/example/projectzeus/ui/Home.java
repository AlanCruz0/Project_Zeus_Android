package com.example.projectzeus.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.projectzeus.R;
import com.example.projectzeus.SharedPrefHelper;

public class Home extends AppCompatActivity {
    private TextView txtname;
    private SharedPrefHelper sharedPrefHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtname = findViewById(R.id.textname);
        sharedPrefHelper = new SharedPrefHelper(this);

        String storedName = sharedPrefHelper.getUserName();
        Log.d("TAG", "onCreate: " + storedName);

        txtname.setText(storedName);
    }
}