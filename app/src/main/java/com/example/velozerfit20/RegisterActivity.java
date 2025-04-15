package com.example.velozerfit20;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    // Constantes para géneros
    public static final String GENERO_MASCULINO = "Masculino";
    public static final String GENERO_FEMENINO = "Femenino";
    public static final String GENERO_OTRO = "Otro";

    private EditText editUsername, editEmail, editPassword, editConfirmPassword;
    private Button btnRegistrar;
    private RadioGroup radioGroupGenero;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Enlazar vistas
        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        radioGroupGenero = findViewById(R.id.radioGroupGenero);

        dbHelper = new DBHelper(this);

        btnRegistrar.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        // Animación
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.button_click);
        btnRegistrar.startAnimation(anim);

        // Datos del formulario
        String username = editUsername.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString();
        String confirm = editConfirmPassword.getText().toString();

        // Validar género
        int selectedId = radioGroupGenero.getCheckedRadioButtonId();
        String genero = getSelectedGender(selectedId);
        if (genero == null) {
            Toast.makeText(this, "Por favor, selecciona tu género", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar campos
        if (!validateInputs(username, email, password, confirm)) {
            return;
        }

        // Registrar usuario
        boolean success = dbHelper.addUser(username, email, password, genero);
        if (success) {
            Toast.makeText(this, "Registro exitoso 💪", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al registrar. Intenta nuevamente.", Toast.LENGTH_SHORT).show();
            Log.e("RegisterActivity", "Error al insertar usuario: " + username);
        }
    }

    private String getSelectedGender(int selectedId) {
        if (selectedId == R.id.radioMasculino) {
            return GENERO_MASCULINO;
        } else if (selectedId == R.id.radioFemenino) {
            return GENERO_FEMENINO;
        } else if (selectedId == R.id.radioOtro) {
            return GENERO_OTRO;
        }
        return null;
    }

    private boolean validateInputs(String username, String email, String password, String confirm) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(confirm)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}