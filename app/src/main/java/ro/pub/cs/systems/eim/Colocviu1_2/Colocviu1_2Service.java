package ro.pub.cs.systems.eim.Colocviu1_2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class Colocviu1_2Service extends Service {
    private static final String TAG = "Colocviu1_2Service";
    private static final String CHANNEL_ID = "11";

    private static final String CHANNEL_NAME = "ForegroundServiceChannel";

    private ProcessThread processThread = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void dummyNotification() {
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
        }
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(getApplicationContext(),CHANNEL_ID).build();
        }
        startForeground(1, notification);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dummyNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() callback method was invoked.");
        Log.d(TAG, "The message is: "
                + intent.getIntExtra(Constants.SUM, 0));

        processThread = new ProcessThread(
                Colocviu1_2Service.this,
                intent.getIntExtra(Constants.SUM, 0)
        );

        processThread.start();

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        processThread.stopThread();
        Log.d(TAG, "onDestroyCommand() callback method was invoked.");
        super.onDestroy();
    }

}
