package android.movil.escom.proyectomedicamentos.ui.dialogs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoMedicamentoListener;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.movil.escom.proyectomedicamentos.presenter.NuevoTratamientoPresenter;
import android.movil.escom.proyectomedicamentos.util.Utilidades;
import android.os.Bundle;
import android.os.Handler;
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

import com.squareup.picasso.Picasso;

/**
 * Jonathan Jimenéz Bazán
 * 28/10/2018
 * 09:58 AM
 */
public class DialogoAgregarMedicamento extends DialogFragment implements View.OnClickListener {

    private EditText etNombre, etDescripcion, etDosis, etNumeroDias, etIntervaloHoras;
    private ImageView ivEnvase, ivTipoMedicamento;
    private Button btnGuardar;
    private RelativeLayout rlPadre;
    private NuevoMedicamentoListener listener;
    private Medicamento medicamento;
    private View root;
    private String rutaFotoMedicamento;
    private String rutaFotoEnvase;


    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setListener(NuevoMedicamentoListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.agregar_medicamento_dialogo, container, false);

        etNombre = root.findViewById(R.id.etNombre);
        etDescripcion = root.findViewById(R.id.etDescripcion);
        etDosis = root.findViewById(R.id.etDosis);
        etNumeroDias = root.findViewById(R.id.etNumeroDias);
        etIntervaloHoras = root.findViewById(R.id.etIntervaloHoras);
        ivEnvase = root.findViewById(R.id.ivEnvase);
        ivTipoMedicamento = root.findViewById(R.id.ivTipoMedicamento);
        btnGuardar = root.findViewById(R.id.btnGuardar);
        rlPadre = root.findViewById(R.id.rlPadre);

        ivEnvase.setOnClickListener(this);
        ivTipoMedicamento.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivEnvase:
                Intent fotoEnvase = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (fotoEnvase.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivityForResult(fotoEnvase, 1);
                }
                break;
            case R.id.ivTipoMedicamento:
                Intent fotoTipoMedicamento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (fotoTipoMedicamento.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivityForResult(fotoTipoMedicamento, 2);
                }
                break;
            case R.id.btnGuardar:
                if (validarCampos()) {
                    Medicamento medicamento = new Medicamento();
                    medicamento.setNombre(etNombre.getText().toString());
                    medicamento.setDescripcion(etDescripcion.getText().toString());
                    medicamento.setDosis(etDosis.getText().toString());
                    medicamento.setNumeroDias(new Integer(etNumeroDias.getText().toString()));
                    medicamento.setInvervaloHoras(new Integer(etIntervaloHoras.getText().toString()));
                    medicamento.setRutaFotoEnvase(rutaFotoEnvase);
                    medicamento.setRutaFotoMedicamento(rutaFotoMedicamento);
                    listener.agregarMedicamento(medicamento);
                    dismiss();
                } else {
                    Snackbar.make(rlPadre, "Completa los campos", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (data != null) {
            if (requestCode == 1) {
                ivEnvase.setImageBitmap((Bitmap) data.getExtras().get("data"));
                rutaFotoEnvase = Utilidades.bitmapToBase64((Bitmap) data.getExtras().get("data"));
            } else if (requestCode == 2) {
                ivTipoMedicamento.setImageBitmap((Bitmap) data.getExtras().get("data"));
                rutaFotoMedicamento = Utilidades.bitmapToBase64((Bitmap) data.getExtras().get("data"));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (medicamento != null) {
            etNombre.setText(medicamento.getNombre());
            etDescripcion.setText(medicamento.getDescripcion());
            etDosis.setText(medicamento.getDosis());
            etNumeroDias.setText(String.valueOf(medicamento.getNumeroDias()));
            etIntervaloHoras.setText(String.valueOf(medicamento.getInvervaloHoras()));
            ivEnvase.setImageBitmap(Utilidades.base64ToBitmap(medicamento.getRutaFotoEnvase()));
            ivTipoMedicamento.setImageBitmap(Utilidades.base64ToBitmap(medicamento.getRutaFotoMedicamento()));
            rutaFotoEnvase = medicamento.getRutaFotoEnvase();
            rutaFotoMedicamento = medicamento.getRutaFotoMedicamento();
            medicamento = null;
        }
    }

    private boolean validarCampos() {

        if (etNombre.length() == 0) {
            return false;
        }

        if (etDescripcion.length() == 0) {
            return false;
        }

        if (etDosis.length() == 0) {
            return false;
        }

        if (etIntervaloHoras.length() == 0 || Integer.valueOf(etIntervaloHoras.getText().toString()) > 24) {
            return false;
        }

        if (etNumeroDias.length() == 0 || Integer.valueOf(etIntervaloHoras.getText().toString()) > 365) {
            return false;
        }

        if (rutaFotoEnvase.length() == 0) {
            return false;
        }

        if (rutaFotoMedicamento.length() == 0) {
            return false;
        }

        return true;
    }

}
