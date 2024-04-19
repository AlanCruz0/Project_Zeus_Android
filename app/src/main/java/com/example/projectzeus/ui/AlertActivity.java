package com.example.projectzeus.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectzeus.R;
import com.example.projectzeus.models.CocheItem;

public class AlertActivity extends AppCompatActivity {
    private TextView txtalert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        txtalert = findViewById(R.id.txtalert);
        CocheItem coche = (CocheItem) getIntent().getSerializableExtra("coche");
        String alias = coche.getAlias();
        txtalert.setText("El coche " + alias + " ha chocado");
    }
}