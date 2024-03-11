package com.example.projectzeus.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.projectzeus.R;
import com.example.projectzeus.models.CocheItem;
import com.example.projectzeus.ui.detalle.Map;

public class Detalles_activity extends AppCompatActivity {
    private TextView txtname;
    private Switch switchled;
    private Button btnmap;
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
        switchled.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Log.i("switch", "encendido");
            }else{
                Log.i("switch", "apagado");
            }
        });

        btnmap.setOnClickListener(v -> {
            Intent intent = new Intent(Detalles_activity.this, Map.class);
            intent.putExtra("coche", coche);
            startActivity(intent);
        });
    }
}