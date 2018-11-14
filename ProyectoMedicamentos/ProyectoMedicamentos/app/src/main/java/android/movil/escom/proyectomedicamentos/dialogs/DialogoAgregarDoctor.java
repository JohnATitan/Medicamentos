package android.movil.escom.proyectomedicamentos.dialogs;

import android.movil.escom.proyectomedicamentos.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Jonathan Jimenéz Bazán
 * 27/10/2018
 * 11:38 PM
 */
public class DialogoAgregarDoctor extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.agregar_doctor_dialogo, container, false);

        return root;
    }
}
