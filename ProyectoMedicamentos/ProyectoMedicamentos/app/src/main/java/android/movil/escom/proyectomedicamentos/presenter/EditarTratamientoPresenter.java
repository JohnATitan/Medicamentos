package android.movil.escom.proyectomedicamentos.presenter;

import android.content.Context;
import android.movil.escom.proyectomedicamentos.model.Doctor;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.movil.escom.proyectomedicamentos.model.Tratamiento;
import android.movil.escom.proyectomedicamentos.persistence.DBMedicamentos;
import android.movil.escom.proyectomedicamentos.util.Utilidades;

import java.util.ArrayList;

public class EditarTratamientoPresenter {

    private ArrayList<Medicamento> listaMedicamentos;
    private Doctor doctor;
    private Context context;


    public EditarTratamientoPresenter(Context context) {
        this.context = context;
    }

    public void medicamentoEditado(Medicamento medicamento, int indiceMedicamento) {
        listaMedicamentos.set(indiceMedicamento, medicamento);
    }

    public void agregarMedicamento(Medicamento medicamento) {
        listaMedicamentos.add(medicamento);
    }

    public void agregarDoctor(Doctor doctor) {
        doctor.setIdDoctor(this.doctor.getIdDoctor());
        this.doctor = doctor;
    }

    public ArrayList<Medicamento> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(ArrayList<Medicamento> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void actualizarTratamiento(long idTratamiento) {
        if (listaMedicamentos.size() > 0 && doctor != null) {
            Tratamiento tratamiento = new Tratamiento();
            tratamiento.setIdTratamiento(idTratamiento);
            tratamiento.setDoctor(doctor);
            tratamiento.setListaMedicamento(listaMedicamentos);
            DBMedicamentos.getInstance().actualizarTratamiento(tratamiento);
            Utilidades.crearAlarmas(context, listaMedicamentos);
        }
    }

    public void borrarTratamiento(Tratamiento tratamiento) {
        DBMedicamentos.getInstance().borrarTratamiento(tratamiento);
    }

}
