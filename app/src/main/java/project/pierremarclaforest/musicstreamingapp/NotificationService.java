package project.pierremarclaforest.musicstreamingapp;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationService extends IntentService {
    public static final int NOTIFICATION_ID = 5453;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        createNotification();
    }

    public void createNotification()  {

        //Create a notification builder
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.sym_def_app_icon)
                        .setContentTitle("Main title")
                        .setContentText("Content")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setVibrate(new long[] {0, 1000})
                        .setAutoCancel(true);

        //Create an action
        Intent actionIntent = new Intent(this, MainActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(actionPendingIntent);

        //Issue the notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
