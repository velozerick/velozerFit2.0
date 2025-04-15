package com.example.velozerfit20;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnEntrenar, btnIMC;
    TextView homeTitle;
    ImageView homeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeTitle = findViewById(R.id.homeTitle);
        homeImage = findViewById(R.id.homeImage);
        btnEntrenar = findViewById(R.id.btnEntrenar);
        btnIMC = findViewById(R.id.btnIMC);

        // Obtener nombre de usuario desde el Intent
        String username = getIntent().getStringExtra("usuario");
        Log.d("HomeActivity", "Usuario recibido: " + username);

        if (username != null && !username.isEmpty()) {
            DBHelper dbHelper = new DBHelper(this);
            try {
                String gender = dbHelper.getUserGender(username);
                Log.d("HomeActivity", "Género obtenido: " + gender);

                // Personalizar saludo
                if (gender == null) {
                    homeTitle.setText("¡Hola, " + username + "! 👋");
                } else {
                    switch (gender) {
                        case "Masculino":
                            homeTitle.setText("Bienvenido, " + username + " 💪");
                            break;
                        case "Femenino":
                            homeTitle.setText("Bienvenida, " + username + " 💪");
                            break;
                        default:
                            homeTitle.setText("¡Bienvenido/a, " + username + "! 💪");
                    }
                }
            } catch (Exception e) {
                Log.e("HomeActivity", "Error al obtener género: " + e.getMessage());
                homeTitle.setText("¡Hola, " + username + "! 👋");
            } finally {
                dbHelper.close();
            }
        }

        // Botón Entrenar
        btnEntrenar.setOnClickListener(v -> {
            startActivity(new Intent(this, EntrenarActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        // Botón IMC
        btnIMC.setOnClickListener(v -> {
            startActivity(new Intent(this, ImcActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
}