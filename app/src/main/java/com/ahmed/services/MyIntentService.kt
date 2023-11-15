package com.ahmed.services

import android.app.IntentService
import android.content.Intent
import android.util.Log



class MyIntentService : IntentService("MyIntentService") {


    @Deprecated("Deprecated in Java")
    override fun onCreate() {
        super.onCreate()
        Log.d("jms","intent service created")

    }
    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {

        Log.d("jms", "intent service started")

        try {
            Thread.sleep(6000)

        }
        catch (e:InterruptedException){
            e.printStackTrace()

        }

    }

    @Deprecated("Deprecated in Java")
    override fun onDestroy() {
        super.onDestroy()
        Log.d("jms", "intent service destroyed")
    }
}