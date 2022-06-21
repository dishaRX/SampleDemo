package com.example.sampledemo.ui.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.sampledemo.databinding.ActSplashBinding
import com.example.sampledemo.extensions.hideToolbar

/**
 * Created by Disha Shah
 */

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActSplashBinding

    /**
     * Initial onCreate method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActSplashBinding.inflate(layoutInflater)
        val view = binding.root
        this.hideToolbar()
        setContentView(view)
        navigateToDashboard()
    }

    /**
     * After 3000 ms redirected to dashboard
     */
    private fun navigateToDashboard() {
        Handler(Looper.getMainLooper()).postDelayed({
            val dashboardIntent = Intent(this, AppMainActivity::class.java)
            startActivity(dashboardIntent)
            finish()
        }, 3000)

    }
}