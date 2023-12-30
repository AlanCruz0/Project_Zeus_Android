package com.example.projectzeus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();
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
                    Log.d("MainActivity", "Token: " + token.getUserId());
                    if (token != null) {
                        Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(MainActivity.this);
                        sharedPreferencesHelper.saveToken(token.getAccessToken());
                        sharedPreferencesHelper.saveUserId(token.getUserId());
                        sharedPreferencesHelper.saveUserName(token.getUserName());
                        sharedPreferencesHelper.saveUserEmail(token.getUserEmail());

                        Intent intent = new Intent(MainActivity.this, Home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                    btnlogin.setEnabled(true);
                });
                btnlogin.setEnabled(false);
            }
        });
    }

}