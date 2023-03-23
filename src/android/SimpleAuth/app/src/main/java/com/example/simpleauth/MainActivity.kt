package com.example.simpleauth

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleauth.helpers.PreferenceHelper.isAuth
import com.example.simpleauth.databinding.ActivityMainBinding
import com.example.simpleauth.services.FetchService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var manager = supportFragmentManager

    companion object{
        const val AUTH_DATA = "Auth_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // check if user is auth already
        val sharedPreferences = getSharedPreferences(AUTH_DATA, Context.MODE_PRIVATE)
        if (sharedPreferences.isAuth){
            manager.beginTransaction().add(R.id.frameLayout, SecondFragment()).commit()

            // start FetchService
            val serviceClass = FetchService::class.java
            val intent = Intent(this, serviceClass)
            if (!isServiceRunning(serviceClass)){
                startService(intent)
            }
            else {
                Toast.makeText(this, "Service already running", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            manager.beginTransaction().add(R.id.frameLayout, FirstFragment()).commit()
        }


    }

    // Custom method to determine whether a service is running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }
}

