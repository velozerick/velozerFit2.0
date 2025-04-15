package com.example.velozerfit20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edituserLogin, editPasswordLogin;
    Button btnLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Enlazamos vistas
        edituserLogin = findViewById(R.id.edituserLogin);
        editPasswordLogin = findViewById(R.id.editPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);

        // Instanciamos la base de datos
        dbHelper = new DBHelper(this);

        // Acción al pulsar el botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.button_click);
                btnLogin.startAnimation(anim);

                String username = edituserLogin.getText().toString().trim();
                String password = editPasswordLogin.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    boolean valido = dbHelper.checkUser(username, password);
                    if (valido) {
                        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso ✅", Toast.LENGTH_SHORT).show();

                        // Pasar el nombre de usuario al HomeActivity
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("usuario", username);  // Enviar el nombre de usuario
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos ❌", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
