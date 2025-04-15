package com.example.velozerfit20;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ImcActivity extends AppCompatActivity {

    EditText edtPeso, edtAltura;
    TextView txtResultado, txtRecomendacion;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        txtResultado = findViewById(R.id.txtResultadoIMC);
        txtRecomendacion = findViewById(R.id.txtRecomendacion);
        btnCalcular = findViewById(R.id.btnCalcularIMC);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        btnCalcular.setOnClickListener(v -> {
            String pesoStr = edtPeso.getText().toString();
            String alturaStr = edtAltura.getText().toString();

            if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
                Toast.makeText(this, "Por favor completa los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            float peso = Float.parseFloat(pesoStr);
            float altura = Float.parseFloat(alturaStr);
            float imc = peso / (altura * altura);

            String resultado = String.format("Tu IMC es: %.2f", imc);
            txtResultado.setText(resultado);
            txtRecomendacion.setVisibility(TextView.INVISIBLE);
            txtRecomendacion.setText("");

            new Handler().postDelayed(() -> {
                String mensaje;

                if (imc < 18.5) {
                    mensaje = "💡 *IMC bajo*\n\n" +
                            "• Esto puede indicar desnutrición o bajo peso.\n" +
                            "• Aumenta tu ingesta calórica con alimentos saludables.\n" +
                            "• Realiza ejercicios de fuerza para ganar masa muscular.";
                } else if (imc < 24.9) {
                    mensaje = "✅ *IMC normal*\n\n" +
                            "• ¡Estás en el rango saludable!\n" +
                            "• Mantén tu alimentación equilibrada.\n" +
                            "• Sigue entrenando para mantener tu bienestar.";
                } else if (imc < 29.9) {
                    mensaje = "⚠️ *Sobrepeso*\n\n" +
                            "• Estás por encima del peso saludable.\n" +
                            "• Controla azúcares y grasas procesadas.\n" +
                            "• Haz ejercicio cardiovascular (caminar, bici, natación).";
                } else {
                    mensaje = "🛑 *Obesidad*\n\n" +
                            "• Riesgo elevado de enfermedades cardiovasculares.\n" +
                            "• Se recomienda apoyo nutricional y actividad física.\n" +
                            "• Comienza con caminatas suaves y mejora tu dieta.";
                }

                txtRecomendacion.setText(mensaje);
                txtRecomendacion.setVisibility(TextView.VISIBLE);
                txtRecomendacion.startAnimation(fadeIn);
            }, 2000);
        });
    }
}
