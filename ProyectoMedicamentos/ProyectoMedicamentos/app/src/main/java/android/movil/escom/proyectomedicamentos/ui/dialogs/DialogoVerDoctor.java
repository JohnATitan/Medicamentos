package android.movil.escom.proyectomedicamentos.ui.dialogs;

import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoDoctorListener;
import android.movil.escom.proyectomedicamentos.model.Doctor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Jonathan Jimenéz Bazán
 * 27/10/2018
 * 11:38 PM
 */
public class DialogoVerDoctor extends DialogFragment implements View.OnClickListener {

    private Button btnGuardar;
    private EditText etNombre, etDireccion, etTelefono, etCorreo;
    private Doctor doctor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.agregar_doctor_dialogo, container, false);

        btnGuardar = root.findViewById(R.id.btnGuardar);
        etNombre = root.findViewById(R.id.etNombre);
        etDireccion = root.findViewById(R.id.etDireccion);
        etTelefono = root.findViewById(R.id.etTelefono);
        etCorreo = root.findViewById(R.id.etCorreo);

        btnGuardar.setOnClickListener(this);

        return root;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:
                dismiss();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        etNombre.setText(doctor.getNombre());
        etNombre.setEnabled(false);
        etDireccion.setText(doctor.getDireccion());
        etDireccion.setEnabled(false);
        etTelefono.setText(doctor.getTelefono());
        etTelefono.setEnabled(false);
        etCorreo.setText(doctor.getCorreo());
        etCorreo.setEnabled(false);
    }
}
