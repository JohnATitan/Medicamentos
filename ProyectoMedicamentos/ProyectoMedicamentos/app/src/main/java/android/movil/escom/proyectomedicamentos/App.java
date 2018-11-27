package android.movil.escom.proyectomedicamentos;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.movil.escom.proyectomedicamentos.persistence.DBMedicamentos;
import android.os.Build;

import com.facebook.stetho.Stetho;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBMedicamentos.createInstance(getApplicationContext());
        Stetho.initializeWithDefaults(this);

        createNotificationChannel();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "test";
            String description = "prueba";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("canal", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
