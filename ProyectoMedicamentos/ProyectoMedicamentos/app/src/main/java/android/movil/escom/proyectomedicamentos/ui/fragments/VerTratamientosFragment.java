package android.movil.escom.proyectomedicamentos.ui.fragments;

import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.MenuPrincipalListener;
import android.movil.escom.proyectomedicamentos.ui.adapters.VerTratamientosPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class VerTratamientosFragment extends Fragment implements View.OnClickListener, PopupMenu.OnMenuItemClickListener, ViewPager.OnPageChangeListener {

    private ViewPager vpVerTratamientos;
    private ImageView ivAccion;
    private MenuPrincipalListener listener;
    private RelativeLayout rlAccion, rlCerrar;
    private PopupMenu menu;
    private VerTratamientosPagerAdapter adapter;
    private int editar;

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
        editar = -1;
    }

    public void setListener(MenuPrincipalListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ver_tratamientos, container, false);
        vpVerTratamientos = root.findViewById(R.id.vpVerTratamientos);
        rlCerrar = root.findViewById(R.id.rlCerrar);
        rlAccion = root.findViewById(R.id.rlAccion);
        ivAccion = root.findViewById(R.id.ivAccion);

        rlAccion.setOnClickListener(this);
        rlCerrar.setOnClickListener(this);

        rlAccion.setTag("menu");

        adapter = new VerTratamientosPagerAdapter(getChildFragmentManager());
        vpVerTratamientos.setAdapter(adapter);
        vpVerTratamientos.addOnPageChangeListener(this);

        menu = new PopupMenu(getActivity(), rlAccion);
        menu.getMenuInflater().inflate(R.menu.opciones_tratamiento, menu.getMenu());
        menu.setOnMenuItemClickListener(this);

        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener.mostrarMenu();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlAccion:
                if (rlAccion.getTag() == "menu") {
                    menu.show();
                } else if (rlAccion.getTag() == "guardar") {
                    adapter.actualizarTratamiento(editar);
                    listener.mostrarMenu();
                }
                break;
            case R.id.rlCerrar:
                listener.mostrarMenu();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.opcionEditar:
                editar = vpVerTratamientos.getCurrentItem();
                adapter.editarTratamiento(editar);
                ivAccion.setImageDrawable(getResources().getDrawable(R.drawable.ic_save));
                rlAccion.setTag("guardar");
                break;
            case R.id.opcionEliminar:
                editar = vpVerTratamientos.getCurrentItem();
                adapter.borrarTratamiento(editar);
                listener.mostrarMenu();
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

        if (editar > -1) {
            vpVerTratamientos.setCurrentItem(editar);
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
