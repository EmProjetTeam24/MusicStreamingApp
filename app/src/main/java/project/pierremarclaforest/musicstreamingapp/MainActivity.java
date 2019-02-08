package project.pierremarclaforest.musicstreamingapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNotificationButton(View view) {
        Notification notif = new Notification();
        notif.type = Notification.Type.CURRENTLY_PLAYING_NOTIF;
        notif.artist = "Bob Marley";
        notif.track = "Could you be loved ?";

        AppNotifier appNotifier = new AppNotifier(this, notif);
        appNotifier.displayNotification();
    }
}
