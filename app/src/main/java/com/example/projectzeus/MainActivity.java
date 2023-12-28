package com.example.projectzeus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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
        String savedToken = preferences.getToken();
        Log.d("TAG", "onCreate: " + savedToken);

        if (savedToken != null) {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            finish();
        }

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
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                        btnlogin.setEnabled(true);
                    });
                    btnlogin.setEnabled(false);
                }
            }
        });
    }

}