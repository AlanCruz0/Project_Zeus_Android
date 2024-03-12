package com.example.projectzeus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectzeus.MainActivity;
import com.example.projectzeus.R;
import com.example.projectzeus.SharedPrefHelper;
import com.example.projectzeus.models.CocheItem;
import com.example.projectzeus.models.request.LedRequest;
import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.ui.detalle.Map;
import com.example.projectzeus.viewmodel.LoginViewModel;
import com.example.projectzeus.viewmodel.RegistroViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Detalles_activity extends AppCompatActivity {
    private TextView txtname;
    private Switch switchled;
    private Button btnmap;
    private Boolean ledbool = false;
    private Boolean ledneg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        txtname = findViewById(R.id.txtname);
        switchled = findViewById(R.id.swled);
        btnmap = findViewById(R.id.btnmapa);
        CocheItem coche = (CocheItem) getIntent().getSerializableExtra("coche");
        Log.i("name", coche.getAlias());
        txtname.setText(coche.getAlias());

        RegistroViewModel registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);
        registroViewModel.getLed(Detalles_activity.this, coche.getId()).observe(Detalles_activity.this, ledstatus -> {
            if (ledstatus != null && ledstatus.getData() != null) {
                if(ledstatus.getData().equals("1")) {
                    switchled.setChecked(true);
                    ledbool = true;
                } else {
                    switchled.setChecked(false);
                    ledbool = false;
                }
            } else {
                Toast.makeText(Detalles_activity.this, "Error al cargar el estado del led", Toast.LENGTH_SHORT).show();
            }
        });

        btnmap.setOnClickListener(v -> {
            Intent intent = new Intent(Detalles_activity.this, Map.class);
            intent.putExtra("coche", coche);
            startActivity(intent);
        });

        switchled.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                LedRequest led = new LedRequest("1");
                RegistroViewModel ledViewModel = new ViewModelProvider(Detalles_activity.this).get(RegistroViewModel.class);
                ledViewModel.setLed(Detalles_activity.this, coche.getId(), led).observe(Detalles_activity.this, response -> {
                    if (response.getStatus() == 200) {
                        switchled.setChecked(true);
                    } else {
                        Toast.makeText(Detalles_activity.this, "Error al cambiar el estado del led", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                LedRequest led = new LedRequest("0");
                RegistroViewModel ledViewModel = new ViewModelProvider(Detalles_activity.this).get(RegistroViewModel.class);
                ledViewModel.setLed(Detalles_activity.this, coche.getId(), led).observe(Detalles_activity.this, response -> {
                    if (response.getStatus() == 200) {
                        switchled.setChecked(false);
                    } else {
                        Toast.makeText(Detalles_activity.this, "Error al cambiar el estado del led", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}