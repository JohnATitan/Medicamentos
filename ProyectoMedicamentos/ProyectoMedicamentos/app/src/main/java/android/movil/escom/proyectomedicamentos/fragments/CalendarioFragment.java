package android.movil.escom.proyectomedicamentos.fragments;


import android.movil.escom.proyectomedicamentos.MenuPrincipalActivity;
import android.movil.escom.proyectomedicamentos.interfaces.MenuPrincipalListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.movil.escom.proyectomedicamentos.R;

public class CalendarioFragment extends Fragment {

    private MenuPrincipalListener listener;


    public CalendarioFragment() {
        // Required empty public constructor
    }

    public static CalendarioFragment newInstance() {
        CalendarioFragment fragment = new CalendarioFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendario, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener.mostrarMenu();
    }

    public void setListener(MenuPrincipalActivity listener) {
        this.listener = listener;
    }
}
