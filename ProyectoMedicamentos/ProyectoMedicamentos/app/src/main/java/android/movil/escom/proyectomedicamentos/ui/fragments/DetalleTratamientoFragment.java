package android.movil.escom.proyectomedicamentos.ui.fragments;


import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoDoctorListener;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoMedicamentoListener;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoTratamientoListener;
import android.movil.escom.proyectomedicamentos.interfaces.VerDetallesMedicamentoListener;
import android.movil.escom.proyectomedicamentos.model.Doctor;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.movil.escom.proyectomedicamentos.model.Tratamiento;
import android.movil.escom.proyectomedicamentos.presenter.EditarTratamientoPresenter;
import android.movil.escom.proyectomedicamentos.ui.adapters.MedicamentoAdapter;
import android.movil.escom.proyectomedicamentos.ui.dialogs.DialogoAgregarDoctor;
import android.movil.escom.proyectomedicamentos.ui.dialogs.DialogoAgregarMedicamento;
import android.movil.escom.proyectomedicamentos.ui.dialogs.DialogoVerDoctor;
import android.movil.escom.proyectomedicamentos.ui.dialogs.DialogoVerMedicamento;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DetalleTratamientoFragment extends Fragment implements View.OnClickListener, NuevoMedicamentoListener, NuevoDoctorListener, VerDetallesMedicamentoListener {

    private RelativeLayout rlAgregarDoctor, rlAgregarMedicamento;
    private TextView tvNombreDoctor;
    private RecyclerView rvMedicamentos;
    private Tratamiento tratamiento;
    private ImageView ivMedicamento;
    private boolean editar;
    private int indiceMedicamento;
    private EditarTratamientoPresenter presenter;
    private MedicamentoAdapter adapter;

    public DetalleTratamientoFragment() {
    }

    public static DetalleTratamientoFragment newInstance() {
        DetalleTratamientoFragment fragment = new DetalleTratamientoFragment();
        return fragment;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        editar = false;
        indiceMedicamento = -1;
        presenter = new EditarTratamientoPresenter(getActivity().getApplicationContext());
        presenter.setListaMedicamentos(tratamiento.getListaMedicamento());
        presenter.setDoctor(tratamiento.getDoctor());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editar_tratamiento, container, false);

        rlAgregarDoctor = root.findViewById(R.id.rlAgregarDoctor);
        tvNombreDoctor = root.findViewById(R.id.tvNombreDoctor);
        ivMedicamento = root.findViewById(R.id.ivMedicamento);
        rlAgregarMedicamento = root.findViewById(R.id.rlAgregarMedicamento);

        rlAgregarDoctor.setOnClickListener(this);
        rlAgregarMedicamento.setOnClickListener(this);

        ivMedicamento.setVisibility(View.GONE);

        tvNombreDoctor.setText(tratamiento.getDoctor().getNombre());

        rvMedicamentos = root.findViewById(R.id.rvMedicamentos);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMedicamentos.setLayoutManager(manager);


        adapter = new MedicamentoAdapter(getContext());
        adapter.setListaMedicamentos(presenter.getListaMedicamentos());
        adapter.setListener(this);

        rvMedicamentos.setAdapter(adapter);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlAgregarDoctor:
                if (editar) {
                    DialogoAgregarDoctor agregarDoctor = new DialogoAgregarDoctor();
                    agregarDoctor.setDoctor(tratamiento.getDoctor());
                    agregarDoctor.setListener(this);
                    agregarDoctor.show(getChildFragmentManager(), "");
                } else {
                    DialogoVerDoctor verDoctor = new DialogoVerDoctor();
                    verDoctor.setDoctor(tratamiento.getDoctor());
                    verDoctor.show(getChildFragmentManager(), "");
                }
                break;
            case R.id.rlAgregarMedicamento: {
                if (editar) {
                    DialogoAgregarMedicamento agregarMedicamento = new DialogoAgregarMedicamento();
                    agregarMedicamento.setListener(this);
                    agregarMedicamento.show(getChildFragmentManager(), "");
                }
            }
        }
    }

    public void habilitarEdicion(boolean habilitar) {
        ivMedicamento.setVisibility(habilitar ? View.VISIBLE : View.GONE);
        editar = true;

    }


    @Override
    public void agregarDoctor(Doctor doctor) {
        presenter.agregarDoctor(doctor);
        tvNombreDoctor.setText(doctor.getNombre());
    }

    @Override
    public void agregarMedicamento(Medicamento medicamento) {
        if (indiceMedicamento >= 0) {
            presenter.medicamentoEditado(medicamento, indiceMedicamento);
            adapter.setListaMedicamentos(presenter.getListaMedicamentos());
            indiceMedicamento = -1;
        } else {
            presenter.agregarMedicamento(medicamento);
            adapter.setListaMedicamentos(presenter.getListaMedicamentos());
        }
    }

    @Override
    public void verMedicamento(int indice) {
        if (editar) {
            indiceMedicamento = indice;
            DialogoAgregarMedicamento agregarMedicamento = new DialogoAgregarMedicamento();
            agregarMedicamento.setMedicamento(tratamiento.getListaMedicamento().get(indice));
            agregarMedicamento.setListener(this);
            agregarMedicamento.show(getChildFragmentManager(), "");
        } else {
            DialogoVerMedicamento verMedicamento = new DialogoVerMedicamento();
            verMedicamento.setMedicamento(tratamiento.getListaMedicamento().get(indice));
            verMedicamento.show(getChildFragmentManager(), "");
        }
    }

    public void actualizarTratamiento() {
        presenter.actualizarTratamiento(tratamiento.getIdTratamiento());
    }

    public void borrarTratamiento() {
        presenter.borrarTratamiento(tratamiento);
    }


}
