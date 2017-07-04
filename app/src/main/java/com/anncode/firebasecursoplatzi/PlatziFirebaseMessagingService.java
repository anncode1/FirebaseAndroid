package com.anncode.firebasecursoplatzi;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.anncode.firebasecursoplatzi.model.PlatziNotificacion;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by anahisalgado on 22/04/17.
 */

public class PlatziFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "PlatziMessagingService";
    private static final String KEY_DESCOUNT = "descount_key";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        PlatziNotificacion platziNotificacion = new PlatziNotificacion();
        platziNotificacion.setId(remoteMessage.getFrom());
        platziNotificacion.setTitle(remoteMessage.getNotification().getTitle());
        platziNotificacion.setDescription(remoteMessage.getNotification().getBody());
        platziNotificacion.setDescount(remoteMessage.getData().get(KEY_DESCOUNT));

        showNotification(platziNotificacion);

    }

    private void showNotification(PlatziNotificacion platziNotificacion){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_DESCOUNT, platziNotificacion.getDescount());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent
                = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_star)
                .setContentTitle(platziNotificacion.getTitle())
                .setContentText(platziNotificacion.getDescription())
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager
                = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        notificationManager.notify(0, notificationBuilder.build());

    }
}
