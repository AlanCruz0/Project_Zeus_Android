package com.example.projectzeus.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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

        // Crear un ClickableSpan para la palabra "Registrate"
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, MainActivity.class);
                startActivity(intent);
            }
        };

        // Establecer el ClickableSpan en la palabra "Registrate"
        spannableString.setSpan(clickableSpan, 21, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Establecer el texto en el TextView
        txtlogin.setText(spannableString);

        // Permitir la detección de clics en los enlaces del TextView
        txtlogin.setMovementMethod(LinkMovementMethod.getInstance());

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                            Intent intent = new Intent(register.this, Home.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                        btnregister.setEnabled(true);
                    });
                    btnregister.setEnabled(false);
                }
            }
        });
    }
}