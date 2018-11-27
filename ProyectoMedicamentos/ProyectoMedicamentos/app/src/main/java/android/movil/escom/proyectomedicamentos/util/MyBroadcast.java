package android.movil.escom.proyectomedicamentos.util;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.movil.escom.proyectomedicamentos.R;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);


        String nombre = intent.getExtras().getString("nombre");
        String dosis = intent.getExtras().getString("dosis");
        String descripcion = intent.getExtras().getString("descripcion");


        NotificationCompat.Builder mBuilder;
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        int icono = R.mipmap.ic_launcher;

        mBuilder = new NotificationCompat.Builder(context, "canal")
                .setSmallIcon(icono)
                .setContentTitle("Es hora de tomar ".concat(nombre))
                .setContentText(dosis.concat(" ").concat(descripcion))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        mNotifyMgr.notify(1, mBuilder.build());

    }
}
