package android.movil.escom.proyectomedicamentos.adapters;

import android.movil.escom.proyectomedicamentos.fragments.DetalleTratamientoFragment;
import android.movil.escom.proyectomedicamentos.fragments.NuevoTratamientoFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Jonathan Jimenéz Bazán
 * 28/10/2018
 * 10:53 AM
 */
public class VerTratamientosPagerAdapter extends FragmentPagerAdapter {

    public VerTratamientosPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new DetalleTratamientoFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
