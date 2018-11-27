package android.movil.escom.proyectomedicamentos.ui.adapters;

import android.movil.escom.proyectomedicamentos.model.Tratamiento;
import android.movil.escom.proyectomedicamentos.persistence.DBMedicamentos;
import android.movil.escom.proyectomedicamentos.ui.fragments.DetalleTratamientoFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Jonathan Jimenéz Bazán
 * 28/10/2018
 * 10:53 AM
 */
public class VerTratamientosPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Tratamiento> listaTratamientos;
    private ArrayList<DetalleTratamientoFragment> listaFragmentos;

    public VerTratamientosPagerAdapter(FragmentManager fm) {
        super(fm);
        this.listaTratamientos = DBMedicamentos.getInstance().obtenerTratamientos();

        listaFragmentos = new ArrayList<>();

        DetalleTratamientoFragment fragment;
        for (int i = 0; i < listaTratamientos.size(); i++) {
            fragment = DetalleTratamientoFragment.newInstance();
            fragment.setTratamiento(listaTratamientos.get(i));
            listaFragmentos.add(fragment);
        }

    }

    @Override
    public Fragment getItem(int i) {
        return listaFragmentos.get(i);
    }

    @Override
    public int getCount() {
        return listaFragmentos.size();
    }

    public void editarTratamiento(int indice) {
        listaFragmentos.get(indice).habilitarEdicion(true);
    }

    public void actualizarTratamiento(int indice) {
        listaFragmentos.get(indice).actualizarTratamiento();
    }

    public void borrarTratamiento(int indice) {
        listaFragmentos.get(indice).borrarTratamiento();
    }
}
