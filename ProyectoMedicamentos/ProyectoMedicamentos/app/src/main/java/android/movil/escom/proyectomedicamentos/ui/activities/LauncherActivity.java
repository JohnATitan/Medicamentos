package android.movil.escom.proyectomedicamentos.ui.activities;

import android.content.Intent;
import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.persistence.ConfiguracionGeneral;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        if (ConfiguracionGeneral.existePaciente(this)) {
            Intent menu = new Intent(this, MenuPrincipalActivity.class);
            startActivity(menu);
        } else {
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
    }
}
