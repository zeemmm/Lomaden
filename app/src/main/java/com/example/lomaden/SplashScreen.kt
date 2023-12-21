package com.example.lomaden

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    private lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        shared = getSharedPreferences("users", MODE_PRIVATE)
        val email = shared.getString("email", "")
        val password = shared.getString("password", "")
        val targetActivity = if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            MainActivity::class.java
        } else {
            HomePageActivity::class.java
        }
        threadTime(targetActivity)
    }

    private fun threadTime(targetActivity: Class<*>) {
        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, targetActivity)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
