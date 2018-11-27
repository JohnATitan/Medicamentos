package android.movil.escom.proyectomedicamentos.persistence;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfiguracionGeneral {

    public static void pacienteRegistrado(Context context) {
        SharedPreferences status = context.getSharedPreferences("pacienteRegistrado", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = status.edit();
        editor.putBoolean("hayPaciente", true);
        editor.commit();
    }

    public static boolean existePaciente(Context context) {
        SharedPreferences status = context.getSharedPreferences("pacienteRegistrado", Context.MODE_PRIVATE);
        return status.getBoolean("hayPaciente", false);
    }


}
