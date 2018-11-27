package android.movil.escom.proyectomedicamentos.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.movil.escom.proyectomedicamentos.model.Doctor;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.movil.escom.proyectomedicamentos.model.Paciente;
import android.movil.escom.proyectomedicamentos.model.Tratamiento;

import java.util.ArrayList;

public class DBMedicamentos extends SQLiteOpenHelper {

    private static DBMedicamentos dbMedicamentos;

    public static void createInstance(Context context) {
        dbMedicamentos = new DBMedicamentos(context);
    }

    public static DBMedicamentos getInstance() {
        return dbMedicamentos;
    }


    public DBMedicamentos(Context context) {
        super(context, ScriptMedicamentos.DB_NOMBRE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptMedicamentos.CREATE_TABLA_PACIENTE);
        db.execSQL(ScriptMedicamentos.CREATE_TABLA_TRATAMIENTO);
        db.execSQL(ScriptMedicamentos.CREATE_TABLA_DOCTOR);
        db.execSQL(ScriptMedicamentos.CREATE_TABLA_MEDICAMENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertarPaciente(Paciente paciente) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ScriptMedicamentos.NOMBRE, paciente.getNombre());
        values.put(ScriptMedicamentos.CORREO, paciente.getCorreo());
        values.put(ScriptMedicamentos.FECHA_NACIMIENTO, paciente.getFechaNacimiento());

        db.insert(ScriptMedicamentos.TABLA_PACIENTE, null, values);
    }

    public Paciente obtenerPaciente() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(ScriptMedicamentos.TABLA_PACIENTE, null, null, null, null, null, null);

        Paciente paciente = new Paciente();
        while (c.moveToNext()) {
            paciente.setNombre(c.getString(c.getColumnIndex(ScriptMedicamentos.NOMBRE)));
            paciente.setCorreo(c.getString(c.getColumnIndex(ScriptMedicamentos.CORREO)));
            paciente.setFechaNacimiento(c.getString(c.getColumnIndex(ScriptMedicamentos.FECHA_NACIMIENTO)));
        }

        return paciente;
    }

    public void insertarTratamiento(ArrayList<Medicamento> listaMedicamentos, Doctor doctor) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values;

        values = new ContentValues();
        values.put(ScriptMedicamentos.NOMBRE, doctor.getNombre());
        values.put(ScriptMedicamentos.DIRECCION, doctor.getDireccion());
        values.put(ScriptMedicamentos.CORREO, doctor.getCorreo());
        values.put(ScriptMedicamentos.TELEFONO, doctor.getTelefono());

        long idDoctor = db.insert(ScriptMedicamentos.TABLA_DOCTOR, null, values);

        values = new ContentValues();
        values.put(ScriptMedicamentos.ID_DOCTOR, idDoctor);

        long idTratramiento = db.insert(ScriptMedicamentos.TABLA_TRATAMIENTO, null, values);

        for (int i = 0; i < listaMedicamentos.size(); i++) {
            listaMedicamentos.get(i).setIdTratamiento((int) idTratramiento);
            values = new ContentValues();
            values.put(ScriptMedicamentos.ID_TRATAMIENTO, listaMedicamentos.get(i).getIdTratamiento());
            values.put(ScriptMedicamentos.NOMBRE, listaMedicamentos.get(i).getNombre());
            values.put(ScriptMedicamentos.DESCRIPCION, listaMedicamentos.get(i).getDescripcion());
            values.put(ScriptMedicamentos.DOSIS, listaMedicamentos.get(i).getDosis());
            values.put(ScriptMedicamentos.RUTA_FOTO_ENVASE, listaMedicamentos.get(i).getRutaFotoEnvase());
            values.put(ScriptMedicamentos.RUTA_FOTO_TIPO_MEDICAMENTO, listaMedicamentos.get(i).getRutaFotoMedicamento());
            values.put(ScriptMedicamentos.NUMERO_DIAS, listaMedicamentos.get(i).getNumeroDias());
            values.put(ScriptMedicamentos.INTERVALO_HORAS, listaMedicamentos.get(i).getInvervaloHoras());
            db.insert(ScriptMedicamentos.TABLA_MEDICAMENTO, null, values);
        }
    }

    public ArrayList<Tratamiento> obtenerTratamientos() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor c;

        c = db.query(ScriptMedicamentos.TABLA_TRATAMIENTO, null, null, null, null, null, null);

        ArrayList<Tratamiento> listaTratamientos = new ArrayList<>();
        Tratamiento tratamiento;

        while (c.moveToNext()) {
            tratamiento = new Tratamiento();
            tratamiento.setIdTratamiento(c.getLong(c.getColumnIndex(ScriptMedicamentos.ID_TRATAMIENTO)));
            tratamiento.getDoctor().setIdDoctor(c.getLong(c.getColumnIndex(ScriptMedicamentos.ID_DOCTOR)));
            listaTratamientos.add(tratamiento);
        }

        String donde;
        String[] argumentos;

        donde = ScriptMedicamentos.ID_DOCTOR.concat(" = ?");

        for (int i = 0; i < listaTratamientos.size(); i++) {
            argumentos = new String[]{String.valueOf(listaTratamientos.get(i).getDoctor().getIdDoctor())};
            c = db.query(ScriptMedicamentos.TABLA_DOCTOR, null, donde, argumentos, null, null, null);

            while (c.moveToNext()) {
                listaTratamientos.get(i).getDoctor().setNombre(c.getString(c.getColumnIndex(ScriptMedicamentos.NOMBRE)));
                listaTratamientos.get(i).getDoctor().setCorreo(c.getString(c.getColumnIndex(ScriptMedicamentos.CORREO)));
                listaTratamientos.get(i).getDoctor().setTelefono(c.getString(c.getColumnIndex(ScriptMedicamentos.TELEFONO)));
                listaTratamientos.get(i).getDoctor().setDireccion(c.getString(c.getColumnIndex(ScriptMedicamentos.DIRECCION)));
            }
        }


        donde = ScriptMedicamentos.ID_TRATAMIENTO.concat(" = ?");

        Medicamento medicamento;

        for (int i = 0; i < listaTratamientos.size(); i++) {
            argumentos = new String[]{String.valueOf(listaTratamientos.get(i).getIdTratamiento())};
            c = db.query(ScriptMedicamentos.TABLA_MEDICAMENTO, null, donde, argumentos, null, null, null);

            while (c.moveToNext()) {

                medicamento = new Medicamento();

                medicamento.setIdTratamiento((int) listaTratamientos.get(i).getIdTratamiento());
                medicamento.setNombre(c.getString(c.getColumnIndex(ScriptMedicamentos.NOMBRE)));
                medicamento.setDescripcion(c.getString(c.getColumnIndex(ScriptMedicamentos.DESCRIPCION)));
                medicamento.setDosis(c.getString(c.getColumnIndex(ScriptMedicamentos.DOSIS)));
                medicamento.setRutaFotoEnvase(c.getString(c.getColumnIndex(ScriptMedicamentos.RUTA_FOTO_ENVASE)));
                medicamento.setRutaFotoMedicamento(c.getString(c.getColumnIndex(ScriptMedicamentos.RUTA_FOTO_TIPO_MEDICAMENTO)));
                medicamento.setIdTratamiento((int) c.getLong(c.getColumnIndex(ScriptMedicamentos.ID_TRATAMIENTO)));
                medicamento.setInvervaloHoras(c.getInt(c.getColumnIndex(ScriptMedicamentos.INTERVALO_HORAS)));
                medicamento.setNumeroDias(c.getInt(c.getColumnIndex(ScriptMedicamentos.NUMERO_DIAS)));

                listaTratamientos.get(i).getListaMedicamento().add(medicamento);
            }
        }


        return listaTratamientos;
    }


    public void actualizarTratamiento(Tratamiento tratamiento) {
        SQLiteDatabase db = getWritableDatabase();

        String donde = ScriptMedicamentos.ID_TRATAMIENTO.concat(" = ?");
        String[] argumentos = new String[]{String.valueOf(tratamiento.getIdTratamiento())};
        db.delete(ScriptMedicamentos.TABLA_MEDICAMENTO, donde, argumentos);

        ContentValues values;

        values = new ContentValues();
        values.put(ScriptMedicamentos.NOMBRE, tratamiento.getDoctor().getNombre());
        values.put(ScriptMedicamentos.DIRECCION, tratamiento.getDoctor().getDireccion());
        values.put(ScriptMedicamentos.CORREO, tratamiento.getDoctor().getCorreo());
        values.put(ScriptMedicamentos.TELEFONO, tratamiento.getDoctor().getTelefono());

        donde = ScriptMedicamentos.ID_DOCTOR.concat(" = ?");
        argumentos = new String[]{String.valueOf(tratamiento.getDoctor().getIdDoctor())};

        db.update(ScriptMedicamentos.TABLA_DOCTOR, values, donde, argumentos);

        for (int i = 0; i < tratamiento.getListaMedicamento().size(); i++) {
            values = new ContentValues();
            values.put(ScriptMedicamentos.ID_TRATAMIENTO, tratamiento.getIdTratamiento());
            values.put(ScriptMedicamentos.NOMBRE, tratamiento.getListaMedicamento().get(i).getNombre());
            values.put(ScriptMedicamentos.DESCRIPCION, tratamiento.getListaMedicamento().get(i).getDescripcion());
            values.put(ScriptMedicamentos.DOSIS, tratamiento.getListaMedicamento().get(i).getDosis());
            values.put(ScriptMedicamentos.RUTA_FOTO_ENVASE, tratamiento.getListaMedicamento().get(i).getRutaFotoEnvase());
            values.put(ScriptMedicamentos.RUTA_FOTO_TIPO_MEDICAMENTO, tratamiento.getListaMedicamento().get(i).getRutaFotoMedicamento());
            values.put(ScriptMedicamentos.NUMERO_DIAS, tratamiento.getListaMedicamento().get(i).getNumeroDias());
            values.put(ScriptMedicamentos.INTERVALO_HORAS, tratamiento.getListaMedicamento().get(i).getInvervaloHoras());
            db.insert(ScriptMedicamentos.TABLA_MEDICAMENTO, null, values);
        }


    }

    public void borrarTratamiento(Tratamiento tratamiento) {
        SQLiteDatabase db = getWritableDatabase();

        String donde = ScriptMedicamentos.ID_TRATAMIENTO.concat(" = ?");
        String[] argumentos = new String[]{String.valueOf(tratamiento.getIdTratamiento())};

        db.delete(ScriptMedicamentos.TABLA_MEDICAMENTO, donde, argumentos);

        donde = ScriptMedicamentos.ID_DOCTOR.concat(" = ?");
        argumentos = new String[]{String.valueOf(tratamiento.getDoctor().getIdDoctor())};

        db.delete(ScriptMedicamentos.TABLA_DOCTOR, donde, argumentos);

        donde = ScriptMedicamentos.ID_TRATAMIENTO.concat(" = ?");
        argumentos = new String[]{String.valueOf(tratamiento.getIdTratamiento())};

        db.delete(ScriptMedicamentos.TABLA_TRATAMIENTO, donde, argumentos);
    }

}
