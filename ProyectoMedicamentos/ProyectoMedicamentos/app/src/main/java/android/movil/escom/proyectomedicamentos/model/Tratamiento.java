package android.movil.escom.proyectomedicamentos.model;

import java.util.ArrayList;

public class Tratamiento {
    private long idTratamiento;
    private Doctor doctor;
    private ArrayList<Medicamento> listaMedicamento;

    public Tratamiento() {
        doctor = new Doctor();
        listaMedicamento = new ArrayList<>();
    }

    public long getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public ArrayList<Medicamento> getListaMedicamento() {
        return listaMedicamento;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setListaMedicamento(ArrayList<Medicamento> listaMedicamento) {
        this.listaMedicamento = listaMedicamento;
    }
}
