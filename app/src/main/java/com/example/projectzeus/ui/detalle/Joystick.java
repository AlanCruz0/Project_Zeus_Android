package com.example.projectzeus.ui.detalle;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Bluetooth
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.example.projectzeus.R;

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

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!checkBluetoothPermission()) {
            requestBluetoothPermission();
        }

        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        btndetener.setOnClickListener(v -> {
            if (checkBluetoothPermission()) {
                enviarDatosBluetooth("D");
            }
        });

        btnavanzar.setOnClickListener(v -> {
            if (checkBluetoothPermission()) {
                enviarDatosBluetooth("A");
            }
        });

        btnretroceder.setOnClickListener(v -> {
            if (checkBluetoothPermission()) {
                enviarDatosBluetooth("R");
            }
        });
    }

    private boolean checkBluetoothPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED;
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

    private void enviarDatosBluetooth(String data) {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "El dispositivo no admite Bluetooth", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Por favor, habilite Bluetooth", Toast.LENGTH_SHORT).show();
            return;
        }

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice("Dirección MAC del dispositivo Bluetooth");

        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
                socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                socket.connect();
                outputStream = socket.getOutputStream();
                outputStream.write(data.getBytes());
                socket.close();
            } else {
                requestBluetoothPermission();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error al enviar datos a través de Bluetooth", Toast.LENGTH_SHORT).show();
            try {
                socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                socket.connect();
                outputStream = socket.getOutputStream();
                outputStream.write(data.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
                Toast.makeText(this, "Error al reconectar el socket Bluetooth", Toast.LENGTH_SHORT).show();
            }
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}