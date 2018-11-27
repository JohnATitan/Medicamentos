package android.movil.escom.proyectomedicamentos.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.movil.escom.proyectomedicamentos.model.Medicamento;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utilidades {

    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }


    public static void crearAlarmas(Context context, ArrayList<Medicamento> listaMedicamentos) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        for (int i = 0; i < listaMedicamentos.size(); i++) {

            Date date = new Date();
            Calendar cal_now = Calendar.getInstance();

            cal_now.setTime(date);
//            cal_now.add(Calendar.HOUR_OF_DAY, listaMedicamentos.get(i).getInvervaloHoras());
            cal_now.add(Calendar.MINUTE, i + 1);

            Intent intent = new Intent(context, MyBroadcast.class);
            intent.putExtra("nombre", listaMedicamentos.get(i).getNombre());
            intent.putExtra("dosis", listaMedicamentos.get(i).getDosis());
            intent.putExtra("descripcion", listaMedicamentos.get(i).getDescripcion());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i, intent, 0);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal_now.getTimeInMillis(), pendingIntent);

        }

    }


}
