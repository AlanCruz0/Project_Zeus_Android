package com.example.projectzeus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.projectzeus.models.response.CocheSensorsResponse;
import com.example.projectzeus.ui.detalle.Joystick;
import com.example.projectzeus.ui.detalle.Map;
import com.example.projectzeus.ui.detalle.Report;
import com.example.projectzeus.viewmodel.CocheViewModel;
import com.example.projectzeus.viewmodel.LoginViewModel;
import com.example.projectzeus.viewmodel.RegistroViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Detalles_activity extends AppCompatActivity {
    private TextView txtname;
    private Switch switchled;
    private Button btnmap, btncontrol, btnreporte;
    private boolean ledExists = false;
    private boolean mapExists = false;
    private boolean reportExists = false;
    private Handler mhandler;
    private Runnable mrunnable;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mhandler.removeCallbacks(mrunnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        txtname     = findViewById(R.id.txtname);
        switchled   = findViewById(R.id.swled);
        btnmap      = findViewById(R.id.btnmapa);
        btncontrol  = findViewById(R.id.btncontrol);
        btnreporte  = findViewById(R.id.btnreport);

        switchled.setEnabled(false);
        btnmap.setEnabled(false);
        btnreporte.setEnabled(false);

        CocheItem coche = (CocheItem) getIntent().getSerializableExtra("coche");
        Log.i("name", coche.getAlias());
        txtname.setText(coche.getAlias());

        CocheViewModel cocheViewModel = new ViewModelProvider(this).get(CocheViewModel.class);
        cocheViewModel.getSensors(Detalles_activity.this, coche.getId()).observe(Detalles_activity.this, cocheSensorsResponse -> {
            if (cocheSensorsResponse != null && cocheSensorsResponse.getData() != null) {
                List<String> skus = new ArrayList<>();
                cocheSensorsResponse.getData().forEach(sensor -> {
                    skus.add(sensor.getSku());
                });
                for (String sku : skus) {
                    if (sku.startsWith("LED")) {
                        ledExists = true;
                    } else if (sku.startsWith("DIS")) {
                        reportExists = true;
                    } else if (sku.startsWith("GPS")) {
                        mapExists = true;
                    }
                }
                switchled.setEnabled(ledExists);
                btnreporte.setEnabled(reportExists);
                btnmap.setEnabled(mapExists);

                RegistroViewModel registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);
                registroViewModel.getLed(Detalles_activity.this, coche.getId()).observe(Detalles_activity.this, ledstatus -> {
                    if (ledstatus != null && ledstatus.getData() != null) {
                        Log.d("led", "si ledexiste y si hay data");
                        if(ledstatus.getData().equals("1")) {
                            switchled.setChecked(true);
                        } else {
                            switchled.setChecked(false);
                        }
                    } else {
                        if (ledExists) {
                            Toast.makeText(Detalles_activity.this, "Error al cargar el estado del led", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(Detalles_activity.this, "Error al cargar los sensores", Toast.LENGTH_SHORT).show();
            }
        });

        mhandler = new Handler();
        mrunnable = new Runnable() {
            @Override
            public void run() {
                RegistroViewModel registroViewModel = new ViewModelProvider(Detalles_activity.this).get(RegistroViewModel.class);
                registroViewModel.getChoque(Detalles_activity.this, coche.getId()).observe(Detalles_activity.this, choque -> {
                    if (choque != null && choque.getData() != null && choque.getData().equals("1")) {
                        Intent intent = new Intent(Detalles_activity.this, AlertActivity.class);
                        intent.putExtra("coche", coche);
                        startActivity(intent);
                    }
                });
                mhandler.postDelayed(this, 30000);
            }
        };
        mhandler.postDelayed(mrunnable, 30000);


        btnmap.setOnClickListener(v -> {
            Intent intent = new Intent(Detalles_activity.this, Map.class);
            intent.putExtra("coche", coche);
            startActivity(intent);
        });

        btncontrol.setOnClickListener(v -> {
            Intent intent = new Intent(Detalles_activity.this, Joystick.class);
            intent.putExtra("coche", coche);
            startActivity(intent);
        });

        btnreporte.setOnClickListener(v -> {
            Intent intent = new Intent(Detalles_activity.this, Report.class);
            intent.putExtra("coche", coche);
            startActivity(intent);
        });

        switchled.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setSwtch(new LedRequest("1"), coche);
            } else {
                setSwtch(new LedRequest("0"), coche);
            }
        });
    }
    public void setSwtch(LedRequest led, CocheItem coche) {
        RegistroViewModel ledViewModel = new ViewModelProvider(Detalles_activity.this).get(RegistroViewModel.class);
        ledViewModel.setLed(Detalles_activity.this, coche.getId(), led).observe(Detalles_activity.this, response -> {
            if (response.getStatus() == 200) {
                switchled.setChecked(led.getValue() == "1" ? true : false);
            } else {
                Toast.makeText(Detalles_activity.this, "Error al cambiar el estado del led", Toast.LENGTH_SHORT).show();
            }
        });
    }
}