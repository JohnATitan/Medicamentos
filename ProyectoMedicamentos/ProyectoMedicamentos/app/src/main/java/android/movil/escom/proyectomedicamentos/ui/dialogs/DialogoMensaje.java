package android.movil.escom.proyectomedicamentos.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.movil.escom.proyectomedicamentos.R;
import android.movil.escom.proyectomedicamentos.interfaces.DialogoAccionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogoMensaje extends Dialog {

    private String mensaje;
    private String titulo;
    private DialogoAccionListener listener;


    public DialogoMensaje(Context context, String mensaje, String titulo, DialogoAccionListener listener) {
        super(context);
        this.mensaje = mensaje;
        this.titulo = titulo;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.espera_mensaje);
        setCanceledOnTouchOutside(false);

        TextView tvMensaje = findViewById(R.id.tvMensaje);
        TextView tvTitulo = findViewById(R.id.tvTitulo);

        tvMensaje.setText(mensaje);
        tvTitulo.setText(titulo);

        Button btnAceptar = findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.accionDialogo();
            }
        });


    }
}
