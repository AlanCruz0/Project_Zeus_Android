package com.example.projectzeus.ui.detalle;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectzeus.R;
import com.example.projectzeus.adapters.ReporteAdapter;
import com.example.projectzeus.models.CocheItem;
import com.example.projectzeus.models.ReporteItem;
import com.example.projectzeus.viewmodel.RegistroViewModel;

import java.util.List;

public class Report extends AppCompatActivity {

    private RecyclerView rvreport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        rvreport = findViewById(R.id.recycler);


        CocheItem coche = (CocheItem) getIntent().getSerializableExtra("coche");

        RegistroViewModel registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);
        registroViewModel.getReporte(Report.this, coche.getId()).observe(this, reportes -> {
            if (reportes != null && reportes.getData() != null) {
                List<ReporteItem> reporteItems = reportes.getData();
                ReporteAdapter reporteAdapter = new ReporteAdapter(reporteItems);
                rvreport.setAdapter(reporteAdapter);
                rvreport.setLayoutManager(new LinearLayoutManager(this));
                rvreport.hasFixedSize();
            } else {
                Toast.makeText(this, "Error al cargar los coches", Toast.LENGTH_SHORT).show();
            }
        });

    }
}