package android.movil.escom.proyectomedicamentos.fragments;

import android.movil.escom.proyectomedicamentos.MenuPrincipalActivity;
import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.MenuPrincipalListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapaFarmaciasFragment extends Fragment implements OnMapReadyCallback {

    public MapaFarmaciasFragment() {
        // Required empty public constructor
    }

    GoogleMap mMap;
    private MenuPrincipalListener listener;

    public static MapaFarmaciasFragment newInstance() {
        MapaFarmaciasFragment fragment = new MapaFarmaciasFragment();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mapa_farmacias, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener.mostrarMenu();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void setListener(MenuPrincipalActivity listener) {
        this.listener = listener;
    }
}
