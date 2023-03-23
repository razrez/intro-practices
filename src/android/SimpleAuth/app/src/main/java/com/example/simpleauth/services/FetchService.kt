package com.example.simpleauth.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import okhttp3.*
import okhttp3.internal.concurrent.Task
import okhttp3.internal.wait
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.random.Random


class FetchService : Service() {

    private val url = "https://jsonplaceholder.typicode.com/todos/"
    private val tag = "FetchService"
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private val client: OkHttpClient = OkHttpClient()
    /*private val moshi = Moshi.Builder().build()
    private val gistJsonAdapter = moshi.adapter(Gist::class.java)*/

    override fun onBind(intent: Intent): IBinder? {
        Log.i(tag, "Service onBind")
        return null
    }

    override fun onCreate() {
        Log.i(tag, "Service onCreate")
        Toast.makeText(applicationContext, "Service started", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i(tag, "Service onStartCommand $startId")
        Toast.makeText(applicationContext, "Service running", Toast.LENGTH_SHORT).show()

        var count = 0
        mHandler = Handler()
        mRunnable = Runnable {
            while (count <= 5){
                fetchData()
                Thread.sleep(5000)
                Toast.makeText(applicationContext, "Service running", Toast.LENGTH_SHORT).show()
                count++
            }
        }
        mHandler.postDelayed(mRunnable,5000)
        //Toast.makeText(applicationContext, fetchData, Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Service is destroyed", Toast.LENGTH_SHORT).show()
        //mHandler.removeCallbacks(mRunnable)
    }


    private fun fetchData() {
        val rnd = Random.nextInt(1,200)
        val request = Request.Builder()
            .url("$url$rnd")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    println(response.body!!.string())
                }
            }
        })

    }

}