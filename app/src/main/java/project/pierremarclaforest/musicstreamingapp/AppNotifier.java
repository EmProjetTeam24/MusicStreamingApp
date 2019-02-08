package project.pierremarclaforest.musicstreamingapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class AppNotifier {
    Context mContext;
    public static final int NOTIFICATION_ID = 5453;
    Notification notif = new Notification();
    NotificationCompat.Builder builder;

    public AppNotifier(Context mContext, Notification notif) {
        this.mContext = mContext;
        this.notif = notif;

        if (notif.type == Notification.Type.CURRENTLY_PLAYING_NOTIF) {
            // display song notification
            createCurrentlyPlayingNotification(notif.artist, notif.track);
        }
    }

    private void createCurrentlyPlayingNotification(String artist, String track) {
        String notificationTextContent =  track + " - " + artist;
        String contentTitle = mContext.getResources().getString(R.string.notification_title);

        createChannel();

        builder = new NotificationCompat.Builder(mContext, "channel1")
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setSmallIcon(android.R.mipmap.sym_def_app_icon)
                        .setContentTitle(contentTitle)
                        .setContentText(notificationTextContent)
                        .setPriority(NotificationCompat.PRIORITY_MIN)
                        .setAutoCancel(true);

        // When the notification is pressed
        Intent actionIntent = new Intent(mContext, MainActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                mContext,
                0,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(actionPendingIntent);

        // First button
        Intent pauseIntent = new Intent(mContext, MainActivity.class);
        PendingIntent pausePendingIntent = PendingIntent.getActivity(
                mContext,
                0,
                pauseIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.mipmap.sym_def_app_icon, "Pause", pausePendingIntent);

        // Second button
        Intent playIntent = new Intent(mContext, MainActivity.class);
        PendingIntent playPendingIntent = PendingIntent.getActivity(
                mContext,
                0,
                playIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.mipmap.sym_def_app_icon, "Play", playPendingIntent);
    }

    public void displayNotification() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = mContext.getString(R.string.channel_name);
            String description = mContext.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel("channel1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
