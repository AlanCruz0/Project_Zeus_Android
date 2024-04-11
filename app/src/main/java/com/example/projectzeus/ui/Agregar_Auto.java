package com.example.projectzeus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectzeus.R;
import com.example.projectzeus.SharedPrefHelper;
import com.example.projectzeus.models.request.AgregarRequest;
import com.example.projectzeus.viewmodel.CocheViewModel;


public class Agregar_Auto extends AppCompatActivity {
    private EditText etalias, etdescripcion, etcodigo;
    private SharedPrefHelper sharedPrefHelper;
    private String alias, descripcion, codigo;
    private CocheViewModel cocheViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_auto);
        sharedPrefHelper = new SharedPrefHelper(this);

        etalias = findViewById(R.id.etnombre);
        etdescripcion = findViewById(R.id.etdescripcion);
        etcodigo = findViewById(R.id.etcodigo);

        Button btnagregar = findViewById(R.id.btnagregar);

        btnagregar.setOnClickListener(v -> {
            alias = etalias.getText().toString();
            descripcion = etdescripcion.getText().toString();
            codigo = etcodigo.getText().toString();

            if (alias.isEmpty() || descripcion.isEmpty() || codigo.isEmpty()) {
                Toast.makeText(Agregar_Auto.this, "Llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (codigo.length() != 6) {
                Toast.makeText(Agregar_Auto.this, "El código debe tener 6 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }

            Long storedId = sharedPrefHelper.getUserId();

            AgregarRequest coche = new AgregarRequest(alias, descripcion, codigo, storedId);

            cocheViewModel = new ViewModelProvider(Agregar_Auto.this).get(CocheViewModel.class);

            cocheViewModel.addCoches(this, coche).observe(Agregar_Auto.this, response -> {
                if (response != null) {
                    if (response.getStatus().equals("201")) {
                        Toast.makeText(Agregar_Auto.this, "Auto agregado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Agregar_Auto.this, Home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Agregar_Auto.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Agregar_Auto.this, "Respuesta Nula", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}