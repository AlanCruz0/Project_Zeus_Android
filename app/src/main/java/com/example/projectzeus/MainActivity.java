package com.example.projectzeus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.ui.Home;
import com.example.projectzeus.ui.register;
import com.example.projectzeus.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {
    private TextView txtregister;
    private EditText etemail, etpassword;
    private LoginViewModel loginViewModel;
    private Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtregister = findViewById(R.id.txtregister);
        etemail = findViewById(R.id.edtemail);
        etpassword = findViewById(R.id.edtpassword);
        btnlogin = findViewById(R.id.btnlogin);

        SpannableString spannableString = new SpannableString("Si aun no tienes cuenta, Registrate");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        };
        spannableString.setSpan(clickableSpan, 25, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtregister.setText(spannableString);
        txtregister.setMovementMethod(LinkMovementMethod.getInstance());

        SharedPrefHelper preferences = new SharedPrefHelper(this);
        Log.d("MainActivity", "Stored userId: " + preferences.getUserId());
        LoginViewModel loginViewModelVal = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModelVal.validateUser(this).observe(this, validateResponse -> {
            if (validateResponse != null) {
                if (validateResponse.getStatus() == 200) {
                    //Toast.makeText(this, "Token valido", Toast.LENGTH_SHORT).show();
                    Log.i("MainActivity", "Token valido");
                    // Verificar permiso de llamada

                    solicitarPermiso();
                } else {
                    Log.i("MainActivity", "Token invalido");
                    //Toast.makeText(this, "Token invalido", Toast.LENGTH_SHORT).show();
                    preferences.clearUserData();
                }
            } else {
                Log.i("MainActivity", "Respuesta nula");
                //Toast.makeText(this, "Respuesta nula", Toast.LENGTH_SHORT).show();
            }
        });

        btnlogin.setOnClickListener(v -> {
            String email = etemail.getText().toString().trim();
            String password = etpassword.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                UserRequest user = new UserRequest(email, password);
                loginViewModel = new ViewModelProvider(MainActivity.this).get(LoginViewModel.class);
                loginViewModel.loginUser(user).observe(MainActivity.this, token -> {
                    if (token != null) {
                        Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(MainActivity.this);
                        sharedPreferencesHelper.saveToken(token.getAccessToken());
                        sharedPreferencesHelper.saveUserId(token.getUserId());
                        sharedPreferencesHelper.saveUserName(token.getUserName());
                        sharedPreferencesHelper.saveUserEmail(token.getUserEmail());

                        solicitarPermiso();
                    } else {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                    btnlogin.setEnabled(true);
                });
                btnlogin.setEnabled(false);
            }
        });
    }

    public void solicitarPermiso(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1987);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permiso necesario")
                    .setMessage("Necesitamos el permiso para realizar llamadas telefónicas, ¿estás de acuerdo?")
                    .setPositiveButton("Sí", (dialog, which) -> ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1))
                    .setNegativeButton("No", (dialog, which) -> Toast.makeText(MainActivity.this, "La función de llamada no estará disponible sin el permiso.", Toast.LENGTH_SHORT).show())
                    .show();
        } else {
            home();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1987){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                home();
            }
        }
    }

    public void home(){
        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
        finish();
    }

}