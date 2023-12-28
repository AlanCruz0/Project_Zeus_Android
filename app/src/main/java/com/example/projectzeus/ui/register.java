package com.example.projectzeus.ui;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.projectzeus.MainActivity;
import com.example.projectzeus.R;
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
    }
}