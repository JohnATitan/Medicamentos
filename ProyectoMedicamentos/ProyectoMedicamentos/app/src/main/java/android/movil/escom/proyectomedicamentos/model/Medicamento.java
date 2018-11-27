package android.movil.escom.proyectomedicamentos.model;

public class Medicamento {

    private int IdMedicamento;
    private int idTratamiento;
    private String nombre;
    private String descripcion;
    private String dosis;
    private String rutaFotoEnvase;
    private String rutaFotoMedicamento;
    private int numeroDias;
    private int invervaloHoras;


    public int getIdMedicamento() {
        return IdMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        IdMedicamento = idMedicamento;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getRutaFotoEnvase() {
        return rutaFotoEnvase;
    }

    public void setRutaFotoEnvase(String rutaFotoEnvase) {
        this.rutaFotoEnvase = rutaFotoEnvase;
    }

    public String getRutaFotoMedicamento() {
        return rutaFotoMedicamento;
    }

    public void setRutaFotoMedicamento(String rutaFotoMedicamento) {
        this.rutaFotoMedicamento = rutaFotoMedicamento;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public int getInvervaloHoras() {
        return invervaloHoras;
    }

    public void setInvervaloHoras(int invervaloHoras) {
        this.invervaloHoras = invervaloHoras;
    }
}
