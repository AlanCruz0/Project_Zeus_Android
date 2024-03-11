package com.example.projectzeus.ui.detalle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projectzeus.R;
import com.example.projectzeus.adapters.CocheAdapter;
import com.example.projectzeus.models.CocheItem;
import com.example.projectzeus.ui.Home;
import com.example.projectzeus.viewmodel.CocheViewModel;
import com.example.projectzeus.viewmodel.RegistroViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private TextView txtdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        txtdata = findViewById(R.id.data);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_container);
        mapFragment.getMapAsync(this);


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        obtenerUbicacion();
        this.mMap.setOnMapClickListener(latLng -> {
            obtenerUbicacion();
        });
    }

    private void obtenerUbicacion() {
        CocheItem coche = (CocheItem) getIntent().getSerializableExtra("coche");
        Log.i("name", coche.getAlias());

        RegistroViewModel registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);
        registroViewModel.getUbicacion(Map.this, coche.getId()).observe(Map.this, ubicacion -> {
            if (ubicacion != null && ubicacion.getData() != null) {
                Double latitud = Double.parseDouble(ubicacion.getData().getLatitud());
                Double longitud = Double.parseDouble(ubicacion.getData().getLongitud());
                txtdata.setText("Latitud: " + latitud + " Longitud: " + longitud);
                LatLng ubicacionCoche = new LatLng(latitud, longitud);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(ubicacionCoche).title("Marcador en Ubicación en " + latitud + " " + longitud));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionCoche, 20.0f));
            } else {
                Toast.makeText(Map.this, "Error al cargar la ubicación", Toast.LENGTH_SHORT).show();
            }
        });
    }
}