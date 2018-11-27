package android.movil.escom.proyectomedicamentos.ui.fragments;


import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.CalendarioMedicamentoListener;
import android.movil.escom.proyectomedicamentos.interfaces.CalendarioTratamientoListener;
import android.movil.escom.proyectomedicamentos.interfaces.MenuPrincipalListener;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.movil.escom.proyectomedicamentos.ui.activities.MenuPrincipalActivity;
import android.movil.escom.proyectomedicamentos.ui.adapters.ItemCalendarioMedicamentoAdapter;
import android.movil.escom.proyectomedicamentos.ui.adapters.ItemCalendarioTratamientoAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarioFragment extends Fragment implements CalendarioTratamientoListener, CalendarioMedicamentoListener {

    private MenuPrincipalListener listener;
    private CalendarView calendario;
//    private RelativeLayout rvCalendario;
    private RecyclerView rvTratamiento, rvMedicamentos;
    private ItemCalendarioMedicamentoAdapter adapter;


    public CalendarioFragment() {
    }

    public static CalendarioFragment newInstance() {
        CalendarioFragment fragment = new CalendarioFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public void setListener(MenuPrincipalActivity listener) {
        this.listener = listener;
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
        View root = inflater.inflate(R.layout.fragment_calendario, container, false);

//        rvCalendario = root.findViewById(R.id.rvCalendario);
        calendario = root.findViewById(R.id.calendario);
        rvTratamiento = root.findViewById(R.id.rvTratamiento);
        rvMedicamentos = root.findViewById(R.id.rvMedicamentos);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTratamiento.setLayoutManager(manager);
        rvTratamiento.setAdapter(new ItemCalendarioTratamientoAdapter(getContext(), CalendarioFragment.this));

        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvMedicamentos.setLayoutManager(manager);
        adapter = new ItemCalendarioMedicamentoAdapter(getContext(), this);
        rvMedicamentos.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener.mostrarMenu();
    }


    @Override
    public void mostrarMedicamentos(ArrayList<Medicamento> listaMedicamentos) {
        adapter.setListaMedicamentos(listaMedicamentos);
    }

    @Override
    public void seleccionarDiasMedicamento(int numeroDias) {

        Calendar dia1 = Calendar.getInstance();
        Calendar dia2 = Calendar.getInstance();

        dia1.setTime(new Date());
        dia2.setTime(new Date());

        dia1.add(Calendar.DATE, -1);
        dia2.add(Calendar.DATE, numeroDias + 1);

//        calendario.setMinimumDate(dia1);
//        calendario.setMaximumDate(dia2);

        calendario.setSelectedDates(CalendarUtils.getDatesRange(dia1, dia2));

    }
}
