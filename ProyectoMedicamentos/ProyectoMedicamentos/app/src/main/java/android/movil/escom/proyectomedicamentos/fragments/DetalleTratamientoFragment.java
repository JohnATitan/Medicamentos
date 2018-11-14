package android.movil.escom.proyectomedicamentos.fragments;


import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.dialogs.DialogoAgregarDoctor;
import android.movil.escom.proyectomedicamentos.dialogs.DialogoAgregarMedicamento;
import android.movil.escom.proyectomedicamentos.interfaces.MenuPrincipalListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class DetalleTratamientoFragment extends Fragment implements View.OnClickListener {

    private Button btnAgregarDoctor;
    private Button btnGuardar;
    private TextView tvNuevoMedicamento;

    private MenuPrincipalListener listener;

    public DetalleTratamientoFragment() {
        // Required empty public constructor
    }

    public static DetalleTratamientoFragment newInstance() {
        DetalleTratamientoFragment fragment = new DetalleTratamientoFragment();
        Bundle args = new Bundle();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_nuevo_tratamiento, container, false);

        btnAgregarDoctor = root.findViewById(R.id.btnAgregarDoctor);
        btnGuardar = root.findViewById(R.id.btnGuardar);
        tvNuevoMedicamento = root.findViewById(R.id.tvNuevoMedicamento);

        btnAgregarDoctor.setOnClickListener(this);
        tvNuevoMedicamento.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAgregarDoctor:
                new DialogoAgregarDoctor().show(getChildFragmentManager(), "");
                break;
            case R.id.tvNuevoMedicamento:
                new DialogoAgregarMedicamento().show(getChildFragmentManager(), "");
                break;
            case R.id.btnGuardar:
                if (listener != null)
                    listener.mostrarMenu();
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (listener != null)
            listener.mostrarMenu();
    }

}
