package android.movil.escom.proyectomedicamentos;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.movil.escom.proyectomedicamentos.fragments.CalendarioFragment;
import android.movil.escom.proyectomedicamentos.fragments.MapaFarmaciasFragment;
import android.movil.escom.proyectomedicamentos.fragments.NuevoTratamientoFragment;
import android.movil.escom.proyectomedicamentos.fragments.VerTratamientosFragment;
import android.movil.escom.proyectomedicamentos.interfaces.MenuPrincipalListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuPrincipalActivity extends AppCompatActivity implements View.OnClickListener, MenuPrincipalListener {

    private RelativeLayout rlNuevoTratamiento, rlVerTratamientos, rlCalendario, rlFarmaciasCercanas;
    private RelativeLayout rlMiPerfil;
    private LinearLayout llMenu;
    private String ultimoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        rlNuevoTratamiento = findViewById(R.id.rlNuevoTratamiento);
        rlVerTratamientos = findViewById(R.id.rlVerTratamientos);
        rlCalendario = findViewById(R.id.rlCalendario);
        rlFarmaciasCercanas = findViewById(R.id.rlFarmaciasCercanas);
        rlMiPerfil = findViewById(R.id.rlMiPerfil);
        llMenu = findViewById(R.id.llMenu);

        ultimoFragment = "";

        rlNuevoTratamiento.setOnClickListener(this);
        rlVerTratamientos.setOnClickListener(this);
        rlCalendario.setOnClickListener(this);
        rlFarmaciasCercanas.setOnClickListener(this);

        getWindow().setStatusBarColor(getResources().getColor(R.color.azul_fondo));
    }

    @Override
    public void onClick(View view) {
        getWindow().setStatusBarColor(getResources().getColor(R.color.azul_encabezado));

        switch (view.getId()) {
            case R.id.rlNuevoTratamiento:
                ultimoFragment = "NuevoTratamientoFragment";
                NuevoTratamientoFragment fragment = NuevoTratamientoFragment.newInstance();
                fragment.setListener(this);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flContenedor, fragment, ultimoFragment);
                transaction.addToBackStack("");
                transaction.commit();
                rlMiPerfil.setVisibility(View.GONE);
                llMenu.setVisibility(View.GONE);
                break;

            case R.id.rlVerTratamientos:
                ultimoFragment = "VerTratamientosFragment";
                VerTratamientosFragment fragment2 = VerTratamientosFragment.newInstance();
                fragment2.setListener(this);
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.flContenedor, fragment2, ultimoFragment);
                transaction2.addToBackStack("");
                transaction2.commit();
                rlMiPerfil.setVisibility(View.GONE);
                llMenu.setVisibility(View.GONE);
                break;

            case R.id.rlCalendario:
                ultimoFragment = "CalendarioFragment";
                CalendarioFragment fragment3 = CalendarioFragment.newInstance();
                fragment3.setListener(this);
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.replace(R.id.flContenedor, fragment3, ultimoFragment);
                transaction3.addToBackStack("");
                transaction3.commit();
                rlMiPerfil.setVisibility(View.GONE);
                llMenu.setVisibility(View.GONE);
                break;

            case R.id.rlFarmaciasCercanas:
                ultimoFragment = "MapaFarmaciasFragment";
                MapaFarmaciasFragment fragment4 = MapaFarmaciasFragment.newInstance();
                fragment4.setListener(this);
                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                transaction4.replace(R.id.flContenedor, fragment4, ultimoFragment);
                transaction4.addToBackStack("");
                transaction4.commit();
                rlMiPerfil.setVisibility(View.GONE);
                llMenu.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void mostrarMenu() {
        rlMiPerfil.setVisibility(View.VISIBLE);
        llMenu.setVisibility(View.VISIBLE);
        getWindow().setStatusBarColor(getResources().getColor(R.color.azul_fondo));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(getSupportFragmentManager().findFragmentByTag(ultimoFragment));
        transaction.commit();
        ultimoFragment = "";
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
