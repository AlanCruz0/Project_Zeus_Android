package com.example.projectzeus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectzeus.MainActivity;
import com.example.projectzeus.R;
import com.example.projectzeus.SharedPrefHelper;
import com.example.projectzeus.adapters.CocheAdapter;
import com.example.projectzeus.models.CocheItem;
import com.example.projectzeus.models.response.LogoutResponse;
import com.example.projectzeus.viewmodel.CocheViewModel;
import com.example.projectzeus.viewmodel.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private TextView txtname;
    private SharedPrefHelper sharedPrefHelper;
    private ImageButton btnlogout;
    private RecyclerView rvcoches;
    private CocheAdapter cocheAdapter;
    private LoginViewModel loginViewModel;
    private CocheViewModel cocheViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtname = findViewById(R.id.textname);
        sharedPrefHelper = new SharedPrefHelper(this);
        btnlogout = findViewById(R.id.imgback);
        rvcoches = findViewById(R.id.rvcoches);

        String storedName = sharedPrefHelper.getUserName();
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

        CocheViewModel cocheViewModel = new ViewModelProvider(this).get(CocheViewModel.class);
        cocheViewModel.getCoches(Home.this).observe(this, coches -> {
            if (coches != null && coches.getData() != null) {
                List<CocheItem> cocheItems = coches.getData();
                cocheAdapter = new CocheAdapter(cocheItems);
                rvcoches.setAdapter(cocheAdapter);
                rvcoches.setLayoutManager(new LinearLayoutManager(this));
                rvcoches.hasFixedSize();
            } else {
                Toast.makeText(this, "Error al cargar los coches", Toast.LENGTH_SHORT).show();
            }
        });

    }
}