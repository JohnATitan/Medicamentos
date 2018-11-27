package android.movil.escom.proyectomedicamentos.presenter;

import android.content.Context;
import android.movil.escom.proyectomedicamentos.interfaces.NuevoTratamientoListener;
import android.movil.escom.proyectomedicamentos.model.Doctor;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.movil.escom.proyectomedicamentos.persistence.DBMedicamentos;
import android.movil.escom.proyectomedicamentos.util.Utilidades;

import java.util.ArrayList;

public class NuevoTratamientoPresenter {

    private ArrayList<Medicamento> listaMedicamentos;
    private Doctor doctor;
    private NuevoTratamientoListener listener;
    private Context context;


    public NuevoTratamientoPresenter(NuevoTratamientoListener listener, Context context) {
        this.listaMedicamentos = new ArrayList<>();
        this.listener = listener;
        this.context = context;
    }

    public void agregarMedicamento(Medicamento medicamento) {
        listaMedicamentos.add(medicamento);
    }

    public void agregarDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public ArrayList<Medicamento> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void guardarTratamiento() {
        if (listaMedicamentos.size() > 0 && doctor != null) {
            DBMedicamentos.getInstance().insertarTratamiento(listaMedicamentos, doctor);
//            Utilidades.crearAlarmas(context, listaMedicamentos);
            listener.tratamientoGuardado();
        } else {
            listener.tratamientoSinTerminar();
        }
    }

}
