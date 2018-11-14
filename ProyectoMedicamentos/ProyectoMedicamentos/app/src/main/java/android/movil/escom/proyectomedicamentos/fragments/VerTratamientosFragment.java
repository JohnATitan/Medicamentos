package android.movil.escom.proyectomedicamentos.fragments;

import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.adapters.VerTratamientosPagerAdapter;
import android.movil.escom.proyectomedicamentos.interfaces.MenuPrincipalListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VerTratamientosFragment extends Fragment {

    private ViewPager vpVerTratamientos;
    private MenuPrincipalListener listener;

    public VerTratamientosFragment() {
    }

    public static VerTratamientosFragment newInstance() {
        VerTratamientosFragment fragment = new VerTratamientosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    public void setListener(MenuPrincipalListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ver_tratamientos, container, false);
        vpVerTratamientos = root.findViewById(R.id.vpVerTratamientos);

        VerTratamientosPagerAdapter adapterVerTratamientos = new VerTratamientosPagerAdapter(getChildFragmentManager());
        vpVerTratamientos.setAdapter(adapterVerTratamientos);

        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener.mostrarMenu();
    }
}
