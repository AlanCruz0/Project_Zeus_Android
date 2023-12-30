package com.example.projectzeus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectzeus.MainActivity;
import com.example.projectzeus.R;
import com.example.projectzeus.SharedPrefHelper;
import com.example.projectzeus.models.response.LogoutResponse;
import com.example.projectzeus.viewmodel.LoginViewModel;

public class Home extends AppCompatActivity {
    private TextView txtname;
    private SharedPrefHelper sharedPrefHelper;
    private ImageButton btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtname = findViewById(R.id.textname);
        sharedPrefHelper = new SharedPrefHelper(this);
        btnlogout = findViewById(R.id.imgback);

        String storedName = sharedPrefHelper.getUserName();
        Log.d("Home", "onCreate: " + storedName);

        txtname.setText(storedName);

        btnlogout.setOnClickListener(v -> {
            LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
            loginViewModel.logoutUser(this).observe(this, logoutResponse -> {
                if (logoutResponse != null) {
                    sharedPrefHelper.clearUserData();

                    Toast.makeText(Home.this, "Logout success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Home.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Home.this, "Logout error", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}