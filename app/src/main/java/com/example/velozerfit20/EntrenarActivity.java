package com.example.velozerfit20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EntrenarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenar);

        // Referencias a los TextViews
        TextView txtHombros = findViewById(R.id.txtHombros);
        TextView txtBiceps = findViewById(R.id.txtBiceps);
        TextView txtPecho = findViewById(R.id.txtPecho);
        TextView txtEspalda = findViewById(R.id.txtEspalda);
        TextView txtAbdomen = findViewById(R.id.txtAbdomen);
        TextView txtPiernas = findViewById(R.id.txtPiernas);

        // Ocultar todos al inicio
        txtHombros.setVisibility(View.INVISIBLE);
        txtBiceps.setVisibility(View.INVISIBLE);
        txtPecho.setVisibility(View.INVISIBLE);
        txtEspalda.setVisibility(View.INVISIBLE);
        txtAbdomen.setVisibility(View.INVISIBLE);
        txtPiernas.setVisibility(View.INVISIBLE);

        // Animación
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Mostrar los elementos poco a poco con retraso
        txtHombros.postDelayed(() -> {
            txtHombros.setVisibility(View.VISIBLE);
            txtHombros.startAnimation(fadeIn);
        }, 500);

        txtBiceps.postDelayed(() -> {
            txtBiceps.setVisibility(View.VISIBLE);
            txtBiceps.startAnimation(fadeIn);
        }, 1000);

        txtPecho.postDelayed(() -> {
            txtPecho.setVisibility(View.VISIBLE);
            txtPecho.startAnimation(fadeIn);
        }, 1500);

        txtEspalda.postDelayed(() -> {
            txtEspalda.setVisibility(View.VISIBLE);
            txtEspalda.startAnimation(fadeIn);
        }, 2000);

        txtAbdomen.postDelayed(() -> {
            txtAbdomen.setVisibility(View.VISIBLE);
            txtAbdomen.startAnimation(fadeIn);
        }, 2500);

        txtPiernas.postDelayed(() -> {
            txtPiernas.setVisibility(View.VISIBLE);
            txtPiernas.startAnimation(fadeIn);
        }, 3000);

        // OnClickListener para "Hombros"
        txtHombros.setOnClickListener(v -> {
            Intent intent = new Intent(EntrenarActivity.this, HombrosActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        // OnClickListener para "Bíceps"
        txtBiceps.setOnClickListener(v -> {
            Intent intent = new Intent(EntrenarActivity.this, Biceps.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        // OnClickListener para "Espalda"
        txtEspalda.setOnClickListener(v -> {
            Intent intent = new Intent(EntrenarActivity.this, EspaldaActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        // OnClickListener para "Pecho"
        txtPecho.setOnClickListener(v -> {
            Intent intent = new Intent(EntrenarActivity.this, PechoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        // OnClickListener para "Abdomen"
        txtAbdomen.setOnClickListener(v -> {
            Intent intent = new Intent(EntrenarActivity.this, AbdomenActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        // OnClickListener para "Piernas"
        txtPiernas.setOnClickListener(v -> {
            Intent intent = new Intent(EntrenarActivity.this, PiernasActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
}
