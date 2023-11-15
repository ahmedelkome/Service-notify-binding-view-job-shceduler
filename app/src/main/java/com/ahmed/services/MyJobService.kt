package com.ahmed.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast

class MyJobService : JobService(){
    override fun onStartJob(params: JobParameters?): Boolean {

        Toast.makeText(this,"onstartjob",Toast.LENGTH_SHORT).show()
        Log.d("jma","started job")
            var mp = MediaPlayer()
        mp = MediaPlayer.create(this, R.raw.sound)
        mp.start()
        mp.setOnCompletionListener {
            jobFinished(params,false)
        }

        return false

    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Toast.makeText(this,"onstopjob",Toast.LENGTH_SHORT).show()
        Log.d("jma","stop job")

        return false
    }
}