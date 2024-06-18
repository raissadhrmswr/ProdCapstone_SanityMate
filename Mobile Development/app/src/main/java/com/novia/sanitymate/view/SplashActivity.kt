package com.novia.sanitymate.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.novia.sanitymate.R

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 1000 // 1 second

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // Start navigation activity
            startActivity(Intent(this, SwipeUpActivity::class.java))
            // Close splash activity
            finish()
        }, splashTimeOut)
    }
}
