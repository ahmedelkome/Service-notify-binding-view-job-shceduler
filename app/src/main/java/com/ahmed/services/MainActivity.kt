package com.ahmed.services

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.ahmed.services.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  bining : ActivityMainBinding
    private final val CHANNEL_ID = "CHANNEL_ID"
    fun displaynotification(){

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            val nc = NotificationChannel(CHANNEL_ID,"CHANNEL_ONE",NotificationManager.IMPORTANCE_DEFAULT)
            nc.description = "Hello notification"

            val nm : NotificationManager = getSystemService(NotificationManager::class.java)
            nm.createNotificationChannel(nc)
        }
        val intent = Intent(this,MainActivity::class.java)
        val pi : PendingIntent = PendingIntent.getActivity(this,0,intent,
            PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
        builder.setSmallIcon(androidx.core.R.drawable.notification_bg).setContentTitle("مرحباااا")
            .setContentText("ahmedelkomy").setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(androidx.fragment.R.drawable.notification_bg,"replay",pi)

        val nmc = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        nmc.notify(10,builder.build())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bining = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bining.root)
        val start : Button = findViewById(R.id.start_id)

        start.setOnClickListener {

            val cn = ComponentName(this,MyJobService::class.java)
            val jobinfo = JobInfo.Builder(10,cn).setMinimumLatency(5000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build()

            val scheduler:JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.schedule(jobinfo)

        }

       /* val stop:Button = findViewById(R.id.stop_id)

        stop.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            stopService(intent)
        }

        bining.notifId.setOnClickListener {
            displaynotification()
        }
*/

    }
}