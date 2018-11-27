package android.movil.escom.proyectomedicamentos.ui.activities;

import android.content.Intent;
import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.model.Paciente;
import android.movil.escom.proyectomedicamentos.persistence.ConfiguracionGeneral;
import android.movil.escom.proyectomedicamentos.persistence.DBMedicamentos;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rlRegistrar;
    private EditText etNombre, etDia, etMes, etAno, etCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rlRegistrar = findViewById(R.id.rlRegistrar);
        etNombre = findViewById(R.id.etNombre);
        etDia = findViewById(R.id.etDia);
        etMes = findViewById(R.id.etMes);
        etAno = findViewById(R.id.etAno);
        etCorreo = findViewById(R.id.etCorreo);


        rlRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlRegistrar:
                if (validarCampos()) {
                    guardarCampos();
                    ConfiguracionGeneral.pacienteRegistrado(this);
                    Intent menuPrincipal = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
                    startActivity(menuPrincipal);
                } else {
                    Snackbar.make(getWindow().getCurrentFocus(), "Completa los campos", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void guardarCampos() {
        Paciente paciente = new Paciente();

        paciente.setNombre(etNombre.getText().toString());
        paciente.setCorreo(etCorreo.getText().toString());
        paciente.setFechaNacimiento(etDia.getText().toString().concat("/").concat(etMes.getText().toString()).concat("/").concat(etAno.getText().toString()));

        DBMedicamentos.getInstance().insertarPaciente(paciente);
    }

    private boolean validarCampos() {
        if (etNombre.length() > 0 && etDia.length() == 2 && etMes.length() == 2 && etAno.length() == 4 && etCorreo.length() > 0) {
            return true;
        }
        return false;
    }

}
