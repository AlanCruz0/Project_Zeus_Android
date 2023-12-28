package com.example.projectzeus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

        // Crear un SpannableString
        SpannableString spannableString = new SpannableString("Si aun no tienes cuenta, Registrate");

        // Crear un ClickableSpan para la palabra "Registrate"
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        };

        // Establecer el ClickableSpan en la palabra "Registrate"
        spannableString.setSpan(clickableSpan, 25, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Establecer el texto en el TextView
        txtregister.setText(spannableString);

        // Permitir la detección de clics en los enlaces del TextView
        txtregister.setMovementMethod(LinkMovementMethod.getInstance());
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                            // Realizar acciones adicionales según sea necesario después del éxito del inicio de sesión
                        } else {
                            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            // Realizar acciones adicionales según sea necesario después del fallo del inicio de sesión
                        }

                        // Habilitar el botón después de recibir la respuesta
                        btnlogin.setEnabled(true);
                    });
                    // Deshabilitar el botón durante la autenticación
                    btnlogin.setEnabled(false);
                }
            }
        });
    }

}