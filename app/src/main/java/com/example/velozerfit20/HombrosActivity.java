package com.example.velozerfit20;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HombrosActivity extends AppCompatActivity {

    // Declarar elementos de la interfaz
    private TextView txtTitulo;
    private ImageView iconoEjercicio, iconoEjercicio1, iconoEjercicio2, iconoEjercicio3, iconoEjercicio4;
    private TextView txtNombre, txtNombre1, txtNombre2, txtNombre3, txtNombre4;
    private TextView txtDescripcion, txtDescripcion1, txtDescripcion2, txtDescripcion3, txtDescripcion4;
    private Button btnIniciarRutina, btnSiguienteRutina, btnFinalizarRutina;
    private TextView cronometro;
    private Button btnRestar, btnSumar;
    private TextView txtRepeticionesView;
    private int repeticiones = 10;
    private int rutinaActual = 1;
    private int totalSeries = 3;
    private Handler handler;
    private Handler messageHandler;
    private int segundos = 0;
    private boolean isRunning = false;
    private TextView txtSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hombros);

        // Inicializar todas las vistas
        inicializarVistas();

        // Configurar el estado inicial de la aplicación
        configurarEstadoInicial();

        // Configurar listeners para los botones
        configurarListeners();

        // Configurar listeners para los iconos de ejercicios
        configurarListenersIconos();
    }

    private void inicializarVistas() {
        // Obtener referencias a todas las vistas del layout
        txtTitulo = findViewById(R.id.txtTitulo);
        cronometro = findViewById(R.id.cronometro);
        txtSerie = findViewById(R.id.txtSerie);
        txtRepeticionesView = findViewById(R.id.txtRepeticiones);

        // Obtener referencias a los iconos y textos de ejercicios
        iconoEjercicio = findViewById(R.id.iconoEjercicio);
        txtNombre = findViewById(R.id.txtNombre);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        iconoEjercicio1 = findViewById(R.id.iconoEjercicio1);
        txtNombre1 = findViewById(R.id.txtNombre1);
        txtDescripcion1 = findViewById(R.id.txtDescripcion1);

        iconoEjercicio2 = findViewById(R.id.iconoEjercicio2);
        txtNombre2 = findViewById(R.id.txtNombre2);
        txtDescripcion2 = findViewById(R.id.txtDescripcion2);

        iconoEjercicio3 = findViewById(R.id.iconoEjercicio3);
        txtNombre3 = findViewById(R.id.txtNombre3);
        txtDescripcion3 = findViewById(R.id.txtDescripcion3);

        iconoEjercicio4 = findViewById(R.id.iconoEjercicio4);
        txtNombre4 = findViewById(R.id.txtNombre4);
        txtDescripcion4 = findViewById(R.id.txtDescripcion4);

        // Obtener referencias a los botones
        btnIniciarRutina = findViewById(R.id.btnIniciarRutina);
        btnSiguienteRutina = findViewById(R.id.btnSiguienteRutina);
        btnFinalizarRutina = findViewById(R.id.btnFinalizarRutina);
        btnRestar = findViewById(R.id.btnRestar);
        btnSumar = findViewById(R.id.btnSumar);
    }

    private void configurarListenersIconos() {
        // Configurar OnClickListener para cada icono de ejercicio

        iconoEjercicio.setOnClickListener(v -> {
            abrirURL("https://www.youtube.com/watch?v=mbIhJZ2Sbcc");
        });

        iconoEjercicio1.setOnClickListener(v -> {
            abrirURL("https://www.youtube.com/watch?v=Cz-WjozscH4");
        });

        iconoEjercicio2.setOnClickListener(v -> {
            abrirURL("https://www.youtube.com/watch?v=O0n4ITO_288");
        });

        iconoEjercicio3.setOnClickListener(v -> {
            abrirURL("https://www.youtube.com/watch?v=l1M_4jzO65w");
        });

        iconoEjercicio4.setOnClickListener(v -> {
            abrirURL("https://www.youtube.com/watch?v=bheSKL7AhGY");
        });
    }

    private void abrirURL(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "No se pudo abrir el enlace", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void configurarEstadoInicial() {
        // Establecer estado inicial de la aplicación
        isRunning = false;
        btnIniciarRutina.setText("Iniciar Rutina");
        btnSiguienteRutina.setEnabled(false);
        btnFinalizarRutina.setVisibility(View.GONE);
        actualizarTextoSeries();
        actualizarContadorRepeticiones();

        // Mostrar controles de repeticiones
        mostrarControlesRepeticiones(true);
    }

    private void configurarListeners() {
        // Configurar acciones para los botones

        // Botón para disminuir repeticiones
        btnRestar.setOnClickListener(v -> {
            if (repeticiones > 1) {
                repeticiones--;
                actualizarContadorRepeticiones();
                actualizarTextoSeries();
            } else {
                mostrarToast("No se pueden tener menos de 1 repetición");
            }
        });

        // Botón para aumentar repeticiones
        btnSumar.setOnClickListener(v -> {
            repeticiones++;
            actualizarContadorRepeticiones();
            actualizarTextoSeries();
        });

        // Botón para iniciar/pausar rutina
        btnIniciarRutina.setOnClickListener(v -> {
            if (!isRunning) {
                iniciarRutina();
            } else {
                pausarRutina();
            }
        });

        // Botón para siguiente rutina
        btnSiguienteRutina.setOnClickListener(v -> siguienteRutina());

        // Botón para finalizar rutina
        btnFinalizarRutina.setOnClickListener(v -> finalizarRutina());
    }

    private void actualizarContadorRepeticiones() {
        // Actualizar el texto que muestra las repeticiones
        txtRepeticionesView.setText(String.valueOf(repeticiones));
    }

    private void actualizarTextoSeries() {
        // Actualizar el texto que muestra la serie actual
        totalSeries = repeticiones;
        txtSerie.setText("Serie " + rutinaActual);
    }

    private void mostrarControlesRepeticiones(boolean mostrar) {
        // Mostrar u ocultar los controles de repeticiones
        int visibility = mostrar ? View.VISIBLE : View.GONE;
        btnRestar.setVisibility(visibility);
        btnSumar.setVisibility(visibility);
        txtRepeticionesView.setVisibility(visibility);
        findViewById(R.id.txtRepeticiones).setVisibility(visibility);
    }

    private void iniciarRutina() {
        // Iniciar la rutina de ejercicios
        if (rutinaActual == 1 && segundos == 0) {
            segundos = 0;
            cronometro.setText("00:00");
        }

        isRunning = true;
        btnIniciarRutina.setText("Pausar Rutina");
        btnSiguienteRutina.setEnabled(true);

        // Ocultar controles de repeticiones al iniciar
        mostrarControlesRepeticiones(false);

        // Iniciar el cronómetro
        startCronometro();
        mostrarToast("Rutina Iniciada");
    }

    private void pausarRutina() {
        // Pausar la rutina de ejercicios
        isRunning = false;
        btnIniciarRutina.setText("Retomar Rutina");
        mostrarToast("Rutina Pausada");
    }

    private void siguienteRutina() {
        // Avanzar a la siguiente rutina
        if (rutinaActual < totalSeries) {
            rutinaActual++;
            actualizarTextoSeries();

            isRunning = false;
            btnIniciarRutina.setText("Iniciar Rutina");
            btnSiguienteRutina.setEnabled(false);

            mostrarToast("Preparado para la serie " + rutinaActual);

            // Mostrar botón finalizar si es la última serie
            if (rutinaActual == totalSeries) {
                btnFinalizarRutina.setVisibility(View.VISIBLE);
                btnSiguienteRutina.setVisibility(View.GONE);
            }
        }
    }

    private void finalizarRutina() {
        // Finalizar todas las rutinas
        isRunning = false;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }

        // Calcular tiempo total
        int minutos = segundos / 60;
        int seg = segundos % 60;
        String tiempoTotal = String.format("%02d:%02d", minutos, seg);

        // Crear diálogo de felicitación
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Felicidades!");
        builder.setMessage("Has completado " + totalSeries + " series\nTiempo total: " + tiempoTotal);
        builder.setCancelable(false);
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            // Restaurar estado inicial al aceptar
            restaurarEstadoInicial();
        });

        // Mostrar diálogo
        AlertDialog dialog = builder.create();
        dialog.show();

        // Configurar auto-cierre después de 4 segundos
        new Handler().postDelayed(() -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
                restaurarEstadoInicial();
            }
        }, 4000);
    }

    private void restaurarEstadoInicial() {
        // Restaurar el estado inicial de la aplicación
        segundos = 0;
        cronometro.setText("00:00");
        rutinaActual = 1;
        mostrarControlesRepeticiones(true);
        configurarEstadoInicial();
        btnSiguienteRutina.setVisibility(View.VISIBLE);
    }

    private void startCronometro() {
        // Iniciar el cronómetro
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    segundos++;
                    int minutos = segundos / 60;
                    int seg = segundos % 60;
                    String time = String.format("%02d:%02d", minutos, seg);
                    cronometro.setText(time);
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    private void mostrarToast(String mensaje) {
        // Mostrar un mensaje Toast
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        // Limpiar handlers al destruir la actividad
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (messageHandler != null) {
            messageHandler.removeCallbacksAndMessages(null);
        }
    }
}