package android.movil.escom.proyectomedicamentos.ui.dialogs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoMedicamentoListener;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.movil.escom.proyectomedicamentos.util.Utilidades;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Jonathan Jimenéz Bazán
 * 28/10/2018
 * 09:58 AM
 */
public class DialogoVerMedicamento extends DialogFragment implements View.OnClickListener {

    private EditText etNombre, etDescripcion, etDosis, etNumeroDias, etIntervaloHoras;
    private ImageView ivEnvase, ivTipoMedicamento;
    private Button btnGuardar;
    private Medicamento medicamento;

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.agregar_medicamento_dialogo, container, false);

        etNombre = root.findViewById(R.id.etNombre);
        etDescripcion = root.findViewById(R.id.etDescripcion);
        etDosis = root.findViewById(R.id.etDosis);
        etNumeroDias = root.findViewById(R.id.etNumeroDias);
        etIntervaloHoras = root.findViewById(R.id.etIntervaloHoras);
        ivEnvase = root.findViewById(R.id.ivEnvase);
        ivTipoMedicamento = root.findViewById(R.id.ivTipoMedicamento);
        btnGuardar = root.findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(this);

        return root;
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
        etNombre.setText(medicamento.getNombre());
        etNombre.setEnabled(false);
        etDescripcion.setText(medicamento.getDescripcion());
        etDescripcion.setEnabled(false);
        etDosis.setText(medicamento.getDosis());
        etDosis.setEnabled(false);
        etNumeroDias.setText(String.valueOf(medicamento.getNumeroDias()));
        etNumeroDias.setEnabled(false);
        etIntervaloHoras.setText(String.valueOf(medicamento.getInvervaloHoras()));
        etIntervaloHoras.setEnabled(false);
        ivEnvase.setImageBitmap(Utilidades.base64ToBitmap(medicamento.getRutaFotoEnvase()));
        ivTipoMedicamento.setImageBitmap(Utilidades.base64ToBitmap(medicamento.getRutaFotoMedicamento()));

    }

}
