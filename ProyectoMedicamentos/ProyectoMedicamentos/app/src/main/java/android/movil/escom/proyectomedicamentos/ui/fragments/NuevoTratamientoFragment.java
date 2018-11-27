package android.movil.escom.proyectomedicamentos.ui.fragments;


import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.movil.escom.proyectomedicamentos.interfaces.DialogoAccionListener;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoDoctorListener;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoMedicamentoListener;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoTratamientoListener;
import android.movil.escom.proyectomedicamentos.model.Doctor;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.movil.escom.proyectomedicamentos.presenter.NuevoTratamientoPresenter;
import android.movil.escom.proyectomedicamentos.ui.adapters.MedicamentoAdapter;
import android.movil.escom.proyectomedicamentos.ui.dialogs.DialogoAgregarDoctor;
import android.movil.escom.proyectomedicamentos.ui.dialogs.DialogoAgregarMedicamento;
import android.movil.escom.proyectomedicamentos.interfaces.MenuPrincipalListener;
import android.movil.escom.proyectomedicamentos.ui.dialogs.DialogoEspera;
import android.movil.escom.proyectomedicamentos.ui.dialogs.DialogoMensaje;
import android.movil.escom.proyectomedicamentos.util.MyBroadcast;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.movil.escom.proyectomedicamentos.R;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class NuevoTratamientoFragment extends Fragment implements View.OnClickListener, NuevoMedicamentoListener, NuevoDoctorListener, NuevoTratamientoListener, DialogoAccionListener {

    private RelativeLayout rlAgregarDoctor, rlAgregarMedicamento, rlGuardar, rlCerrar, rlPadre;
    private TextView tvTitulo, tvNombreDoctor;
    private RecyclerView rvMedicamentos;
    private NuevoTratamientoPresenter presenter;
    private MedicamentoAdapter adapter;
    private ImageView ivIconoDoctor;


    private MenuPrincipalListener listener;

    public NuevoTratamientoFragment() {
        // Required empty public constructor
    }

    public static NuevoTratamientoFragment newInstance() {
        NuevoTratamientoFragment fragment = new NuevoTratamientoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        presenter = new NuevoTratamientoPresenter(this, getActivity().getApplicationContext());
        adapter = new MedicamentoAdapter(getContext());
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "test";
            String description = "prueba";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("canal", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void setListener(MenuPrincipalListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nuevo_tratamiento, container, false);

        rlAgregarDoctor = root.findViewById(R.id.rlAgregarDoctor);
        rlAgregarMedicamento = root.findViewById(R.id.rlAgregarMedicamento);
        rlGuardar = root.findViewById(R.id.rlGuardar);
        rlCerrar = root.findViewById(R.id.rlCerrar);
        rlPadre = root.findViewById(R.id.rlPadre);
        tvTitulo = root.findViewById(R.id.tvTitulo);
        tvNombreDoctor = root.findViewById(R.id.tvNombreDoctor);
        ivIconoDoctor = root.findViewById(R.id.ivIconoDoctor);

        rlAgregarDoctor.setOnClickListener(this);
        rlAgregarMedicamento.setOnClickListener(this);
        rlCerrar.setOnClickListener(this);
        rlGuardar.setOnClickListener(this);

        rvMedicamentos = root.findViewById(R.id.rvMedicamentos);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMedicamentos.setLayoutManager(manager);
        rvMedicamentos.setAdapter(adapter);


        tvTitulo.setText("Nuevo tratamiento");

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlAgregarDoctor:
                DialogoAgregarDoctor agregarDoctor = new DialogoAgregarDoctor();
                agregarDoctor.setListener(this);
                agregarDoctor.show(getChildFragmentManager(), "");
                break;
            case R.id.rlAgregarMedicamento:
                DialogoAgregarMedicamento agregarMedicamento = new DialogoAgregarMedicamento();
                agregarMedicamento.setListener(this);
                agregarMedicamento.show(getChildFragmentManager(), "");
                break;
            case R.id.rlGuardar:
                crearAlarmas(presenter.getListaMedicamentos());
                presenter.guardarTratamiento();
                break;
            case R.id.rlCerrar:
                if (listener != null)
                    listener.mostrarMenu();
                break;
        }
    }

    public void crearAlarmas(ArrayList<Medicamento> listaMedicamentos) {

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        for (int i = 0; i < listaMedicamentos.size(); i++) {

            Date date = new Date();
            Calendar cal_now = Calendar.getInstance();

            cal_now.setTime(date);
//            cal_now.add(Calendar.HOUR_OF_DAY, listaMedicamentos.get(i).getInvervaloHoras());
            cal_now.add(Calendar.MINUTE, i + 1);

            Intent intent = new Intent(getActivity(), MyBroadcast.class);
            intent.putExtra("nombre", listaMedicamentos.get(i).getNombre());
            intent.putExtra("dosis", listaMedicamentos.get(i).getDosis());
            intent.putExtra("descripcion", listaMedicamentos.get(i).getDescripcion());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), i, intent, 0);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal_now.getTimeInMillis(), pendingIntent);

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (listener != null)
            listener.mostrarMenu();
    }

    @Override
    public void agregarMedicamento(Medicamento medicamento) {
        presenter.agregarMedicamento(medicamento);
        adapter.setListaMedicamentos(presenter.getListaMedicamentos());
    }

    @Override
    public void agregarDoctor(Doctor doctor) {
        presenter.agregarDoctor(doctor);
        tvNombreDoctor.setText(doctor.getNombre());
        ivIconoDoctor.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
        rlAgregarDoctor.setBackground(getResources().getDrawable(R.drawable.boton_doctor));

    }

    @Override
    public void tratamientoGuardado() {
        new DialogoMensaje(getContext(), "Tu tratamiento fue guardado", "Nuevo tratamiento", this).show();
    }

    @Override
    public void tratamientoSinTerminar() {
        Snackbar.make(rlPadre, "Agrega elementos faltantes", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void accionDialogo() {
        listener.mostrarMenu();
    }
}
