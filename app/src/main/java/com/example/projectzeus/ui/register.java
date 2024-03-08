package com.example.projectzeus.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectzeus.MainActivity;
import com.example.projectzeus.R;
import com.example.projectzeus.SharedPrefHelper;
import com.example.projectzeus.models.request.RegisterRequest;
import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.viewmodel.LoginViewModel;

public class register extends AppCompatActivity {
    private TextView txtlogin;
    private EditText etemail, etpassword, etname;
    private LoginViewModel loginViewModel;
    private Button btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtlogin = findViewById(R.id.txtlogin);
        etemail = findViewById(R.id.edtemail);
        etpassword = findViewById(R.id.edtpassword);
        etname = findViewById(R.id.edtname);
        btnregister = findViewById(R.id.btnlogin);

        SpannableString spannableString = new SpannableString("Si ya tienes cuenta, Inicia Sesion");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, MainActivity.class);
                startActivity(intent);
            }
        };
        spannableString.setSpan(clickableSpan, 21, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtlogin.setText(spannableString);
        txtlogin.setMovementMethod(LinkMovementMethod.getInstance());

        btnregister.setOnClickListener(v -> {
            String email = etemail.getText().toString().trim();
            String password = etpassword.getText().toString().trim();
            String name = etname.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                Toast.makeText(register.this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                RegisterRequest user = new RegisterRequest(name, email, password);
                loginViewModel = new ViewModelProvider(register.this).get(LoginViewModel.class);
                loginViewModel.registerUser(user).observe(register.this, token -> {
                    if (token != null) {
                        Toast.makeText(register.this, "Login success", Toast.LENGTH_SHORT).show();

                        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(register.this);
                        sharedPreferencesHelper.saveToken(token.getAccessToken());
                        sharedPreferencesHelper.saveUserId(token.getUserId());
                        sharedPreferencesHelper.saveUserName(token.getUserName());
                        sharedPreferencesHelper.saveUserEmail(token.getUserEmail());

                        solicitarPermiso();
                    } else {
                        Toast.makeText(register.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                    btnregister.setEnabled(true);
                });
                btnregister.setEnabled(false);
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
                    .setPositiveButton("Sí", (dialog, which) -> ActivityCompat.requestPermissions(register.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1))
                    .setNegativeButton("No", (dialog, which) -> Toast.makeText(register.this, "La función de llamada no estará disponible sin el permiso.", Toast.LENGTH_SHORT).show())
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
        Intent intent = new Intent(register.this, Home.class);
        startActivity(intent);
        finish();
    }
}