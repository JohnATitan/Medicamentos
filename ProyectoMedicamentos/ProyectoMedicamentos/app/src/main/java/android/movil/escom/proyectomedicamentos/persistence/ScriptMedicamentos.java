package android.movil.escom.proyectomedicamentos.persistence;

public class ScriptMedicamentos {



    public static final String DB_NOMBRE = "appMedicamentos";
    public static final String TABLA_PACIENTE = "paciente";
    public static final String NOMBRE = "nombre";
    public static final String FECHA_NACIMIENTO = "fechaNacimiento";
    public static final String CORREO = "correo";
    public static final String TABLA_DOCTOR = "doctor";
    public static final String ID_DOCTOR = "idDoctor";
    public static final String TELEFONO = "telefono";
    public static final String DIRECCION = "direccion";
    public static final String TABLA_TRATAMIENTO = "tratamiento";
    public static final String ID_TRATAMIENTO = "idTratamiento";
    public static final String DESCRIPCION = "descripcion";
    public static final String TABLA_MEDICAMENTO = "medicamento";
    public static final String ID_MEDICAMENTO = "idMedicamento";
    public static final String RUTA_FOTO_ENVASE = "rutaFotoEnvase";
    public static final String RUTA_FOTO_TIPO_MEDICAMENTO = "rutaFotoTipoMedicamento";
    public static final String DOSIS = "dosis";
    public static final String NUMERO_DIAS = "numeroDias";
    public static final String INTERVALO_HORAS = "intervaloHoras";

    public static final String CREATE_TABLA_PACIENTE = "CREATE TABLE " + TABLA_PACIENTE + " (" +
            "    " + NOMBRE + "          VARCHAR," +
            "    " + FECHA_NACIMIENTO + " DATE," +
            "    " + CORREO + "          VARCHAR" +
            ");";

    public static final String CREATE_TABLA_DOCTOR = "CREATE TABLE " + TABLA_DOCTOR + " (" +
            "    " + ID_DOCTOR + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    " + NOMBRE + "    VARCHAR," +
            "    " + CORREO + "    VARCHAR," +
            "    " + TELEFONO + "  VARCHAR," +
            "    " + DIRECCION + " VARCHAR" +
            ");";

    public static final String CREATE_TABLA_TRATAMIENTO = "CREATE TABLE " + TABLA_TRATAMIENTO + " (" +
            "    " + ID_TRATAMIENTO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    " + ID_DOCTOR + "              REFERENCES " + TABLA_DOCTOR + " (" + ID_DOCTOR + ")," +
            "    " + DESCRIPCION + "   VARCHAR" +
            ");";

    public static final String CREATE_TABLA_MEDICAMENTO = "CREATE TABLE " + TABLA_MEDICAMENTO + " (" +
            "    " + ID_MEDICAMENTO + "           INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    " + ID_TRATAMIENTO + "           INTEGER REFERENCES " + TABLA_TRATAMIENTO + " (" + ID_TRATAMIENTO + ")," +
            "    " + RUTA_FOTO_ENVASE + "          VARCHAR," +
            "    " + RUTA_FOTO_TIPO_MEDICAMENTO + " VARCHAR," +
            "    " + NOMBRE + "                  VARCHAR," +
            "    " + DESCRIPCION + "             VARCHAR," +
            "    " + DOSIS + "                   VARCHAR," +
            "    " + NUMERO_DIAS + "              INTEGER," +
            "    " + INTERVALO_HORAS + "          INTEGER" +
            ");";
}
