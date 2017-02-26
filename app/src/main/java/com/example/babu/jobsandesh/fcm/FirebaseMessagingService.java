package com.example.babu.jobsandesh.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.babu.jobsandesh.MainActivity;
import com.example.babu.jobsandesh.R;
import com.google.firebase.messaging.RemoteMessage;

/*
 * Created by Alok on 29/Jan/17.
*/


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    // Sets an ID for the notification, so it can be updated
    public static final int notifyID = 9001;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData().get("message"));

    }

    private void showNotification(String message) {

        Intent i = new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager mNotificationManager;

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("Placement Alert")
                .setContentText(message)
                .setSmallIcon(R.drawable.splash_img)
                //.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent);


        // Set pending intent
        builder.setContentIntent(pendingIntent);

        // Set Vibrate, Sound and Light
        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        builder.setDefaults(defaults);
        // Set the content for Notification
        builder.setContentTitle("Placement Alert");
        builder.setContentText("New message from Placement Co-ordinator");
        // Set autocancel
        builder.setAutoCancel(true);
        // Post a notification
        mNotificationManager.notify(notifyID, builder.build());

    }
}
