package com.example.projectzeus;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.projectzeus.ui.AlertActivity;

public class AlertService extends Service {

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                // Muestra la alerta
                showAlert();
                // Programa la ejecución del Runnable cada 30 segundos
                mHandler.postDelayed(this, 30000);
            }
        };
        // Inicia el Runnable
        mHandler.postDelayed(mRunnable, 30000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Detiene el Runnable cuando se destruye el servicio
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showAlert() {
        Intent dialogIntent = new Intent(this, AlertActivity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dialogIntent);
    }
}
