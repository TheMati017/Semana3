package com.example.semana3;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etApellido;
    private TableLayout tablaRegistros;
    private TextView tvMensaje;
    private int contadorId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        tablaRegistros = findViewById(R.id.tablaRegistros);
        tvMensaje = findViewById(R.id.tvMensaje);

        Button btnAgregar = findViewById(R.id.btnAgregar);
        Button btnLimpiar = findViewById(R.id.btnLimpiar);

        btnAgregar.setOnClickListener(v -> agregarRegistro());
        btnLimpiar.setOnClickListener(v -> limpiarTabla());
    }

    private void agregarRegistro() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellido)) {
            tvMensaje.setText(getString(R.string.error_campos_vacios));
            return;
        }

        tvMensaje.setText("");

        TableRow fila = new TableRow(this);
        fila.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        fila.addView(crearCelda(String.valueOf(contadorId)));
        fila.addView(crearCelda(nombre));
        fila.addView(crearCelda(apellido));

        tablaRegistros.addView(fila);
        contadorId++;

        etNombre.setText("");
        etApellido.setText("");
        etNombre.requestFocus();
    }

    private TextView crearCelda(String texto) {
        TextView celda = new TextView(this);
        celda.setText(texto);
        celda.setPadding(8, 8, 8, 8);
        celda.setTextColor(Color.BLACK);
        celda.setGravity(Gravity.START);
        return celda;
    }

    private void limpiarTabla() {
        // Deja solo la primera fila (el encabezado) y elimina el resto
        while (tablaRegistros.getChildCount() > 1) {
            tablaRegistros.removeViewAt(1);
        }
        contadorId = 1;
        tvMensaje.setText(getString(R.string.aviso_tabla_vacia));
    }
}
