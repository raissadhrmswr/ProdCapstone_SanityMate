package com.novia.sanitymate.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.novia.sanitymate.R
import com.novia.sanitymate.databinding.ActivitySwipeUpBinding

class SwipeUpActivity : AppCompatActivity(){

    private lateinit var binding : ActivitySwipeUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySwipeUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.relativeLayout.setOnClickListener{
            navigateToNextActivity()
            finish()
        }
    }

    private fun navigateToNextActivity() {
        val intent = Intent(this, NavigationActivity::class.java)
        val options = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.slide_up, R.anim.slide_up)
        startActivity(intent, options.toBundle())
    }
}