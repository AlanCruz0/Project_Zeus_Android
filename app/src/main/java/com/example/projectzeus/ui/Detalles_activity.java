package com.example.projectzeus.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import com.example.projectzeus.R;
import com.example.projectzeus.models.CocheItem;

public class Detalles_activity extends AppCompatActivity {
    private TextView txtname;
    private Switch switchled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        txtname = findViewById(R.id.txtname);
        switchled = findViewById(R.id.swled);
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
    }
}