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
public class DialogoAgregarDoctor extends DialogFragment implements View.OnClickListener {

    private Button btnGuardar;
    private EditText etNombre, etDireccion, etTelefono, etCorreo;
    private RelativeLayout rlPadre;
    private NuevoDoctorListener listener;
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
        rlPadre = root.findViewById(R.id.rlPadre);

        btnGuardar.setOnClickListener(this);

        return root;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setListener(NuevoDoctorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:
                if (validarCampos()) {
                    Doctor doctor = new Doctor();
                    doctor.setNombre(etNombre.getText().toString());
                    doctor.setDireccion(etDireccion.getText().toString());
                    doctor.setTelefono(etTelefono.getText().toString());
                    doctor.setCorreo(etCorreo.getText().toString());
                    listener.agregarDoctor(doctor);
                    dismiss();
                } else {
                    Snackbar.make(getDialog().getCurrentFocus(), "Completa los campos", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (doctor != null) {
            etNombre.setText(doctor.getNombre());
            etDireccion.setText(doctor.getDireccion());
            etTelefono.setText(doctor.getTelefono());
            etCorreo.setText(doctor.getCorreo());
        }
    }

    private boolean validarCampos() {

        if (etNombre.length() == 0) {
            return false;
        }
        if (etDireccion.length() == 0) {
            return false;
        }
        if (etTelefono.length() == 0) {
            return false;
        }
        if (etCorreo.length() == 0) {
            return false;
        }
        return true;

    }
}
