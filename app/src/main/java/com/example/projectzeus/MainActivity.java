package com.example.projectzeus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.example.projectzeus.ui.register;

public class MainActivity extends AppCompatActivity {
    private TextView txtregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtregister = findViewById(R.id.txtregister);

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
    }
}