package com.ahmed.services

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyService : Service() {
    private final val CHANNEL_ID = "CHANNEL_ID"

    var mp = MediaPlayer()

    private val TAG = "TAG"
    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(this, R.raw.sound)

        Log.d(TAG, "service started")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startForeground(1,displaynotification())

        if (!mp.isPlaying) {
            mp.start()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        if (mp.isPlaying) {
            mp.stop()
            mp.release()
        }

        Log.d(TAG, "service destroy")
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    fun displaynotification() : Notification  {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val nc = NotificationChannel(
                CHANNEL_ID,
                "CHANNEL_ONE",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            nc.description = "Hello notification"

            val nm: NotificationManager = getSystemService(NotificationManager::class.java)
            nm.createNotificationChannel(nc)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pi: PendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder.setSmallIcon(androidx.core.R.drawable.notification_bg).setContentTitle("مرحباااا")
            .setContentText("ahmedelkomy").setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(androidx.fragment.R.drawable.notification_bg, "replay", pi)

        val nmc = NotificationManagerCompat.from(this)

        return builder.build()
    }
}