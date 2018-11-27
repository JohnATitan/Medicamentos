package android.movil.escom.proyectomedicamentos.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.movil.escom.proyectomedicamentos.R;
import android.os.Bundle;

public class DialogoEspera extends Dialog {


    public DialogoEspera(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.espera_dialogo);
        setCanceledOnTouchOutside(false);

    }
}
