package com.example.projectzeus.ui.detalle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

// Bluetooth
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import com.example.projectzeus.R;
import com.example.projectzeus.models.CocheItem;
import com.example.projectzeus.models.request.LedRequest;
import com.example.projectzeus.ui.Detalles_activity;
import com.example.projectzeus.viewmodel.RegistroViewModel;

public class Joystick extends AppCompatActivity {
    private Button btndetener, btnavanzar, btnretroceder;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);

        btndetener = findViewById(R.id.btnstop);
        btnavanzar = findViewById(R.id.btnup);
        btnretroceder = findViewById(R.id.btndown);

        btndetener.setEnabled(false);
        btnavanzar.setEnabled(false);
        btnretroceder.setEnabled(false);

        CocheItem coche = (CocheItem) getIntent().getSerializableExtra("coche");
        LedRequest ledRequest = new LedRequest("C");
        RegistroViewModel registroViewModel = new RegistroViewModel();
        registroViewModel.setControl(this, coche.getId(), ledRequest).observe(this, controlResponse -> {
            if (controlResponse.getStatus() == 200) {
                btndetener.setEnabled(true);
                btnavanzar.setEnabled(true);
                btnretroceder.setEnabled(true);
            } else {
                Toast.makeText(this, "Error al cambiar de modo", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "El dispositivo no admite Bluetooth", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Detalles_activity.class);
            startActivity(intent);
            finish();
        }

        enviarDatosBluetooth("C");

        btndetener.setOnClickListener(v -> {
            enviarDatosBluetooth("D");
        });

        btnavanzar.setOnClickListener(v -> {
            enviarDatosBluetooth("A");
        });

        btnretroceder.setOnClickListener(v -> {
            enviarDatosBluetooth("R");
        });
    }

    private void requestBluetoothPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, 1984);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1984) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de Bluetooth concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso de Bluetooth denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void closeBluetoothSocket() {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                Log.i("Bluetooth", "Error al cerrar el OutputStream");
            }
            outputStream = null;
        }

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                Log.i("Bluetooth", "Error al cerrar el socket");
            }
            socket = null;
        }
    }

    private void enviarDatosBluetooth(String data) {
        if (!bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Por favor, habilite Bluetooth", Toast.LENGTH_SHORT).show();
            return;
        }

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice("00:23:05:00:3B:6C");

        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
                if (socket == null || !socket.isConnected()) {
                    socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                    socket.connect();
                    outputStream = socket.getOutputStream();
                }
                outputStream.write(data.getBytes());
                Log.d("Bluetooth", "Datos enviados: " + data);
            } else {
                requestBluetoothPermission();
            }
        } catch (SecurityException se) {
            Toast.makeText(this, "Permiso de Bluetooth denegado", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            closeBluetoothSocket();
            Toast.makeText(this, "Error al enviar datos a través de Bluetooth", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        enviarDatosBluetooth("S");
        CocheItem coche = (CocheItem) getIntent().getSerializableExtra("coche");
        LedRequest ledRequest = new LedRequest("A");
        RegistroViewModel registroViewModel = new RegistroViewModel();
        registroViewModel.setControl(this, coche.getId(), ledRequest).observe(this, controlResponse -> {
            if (controlResponse.getStatus() == 200) {
                Log.d("Joystick", "Modo automático activado");
            } else {
                Toast.makeText(this, "Error al cambiar de modo", Toast.LENGTH_SHORT).show();
            }
        });
        super.onDestroy();
        closeBluetoothSocket();
    }
}