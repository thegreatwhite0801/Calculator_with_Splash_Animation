package com.example.mycalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.airbnb.lottie.LottieAnimationView
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    private lateinit var executor: ScheduledExecutorService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       installSplashScreen()
        // Set the layout for the splash screen
        setContentView(R.layout.activity_splash_screen)

        // Get the Lottie animation view
        val animationView = findViewById<LottieAnimationView>(R.id.anime1)

        // Set the animation and start it
        animationView.setAnimation(R.raw.greencalculator)
        animationView.playAnimation()

        executor = Executors.newSingleThreadScheduledExecutor()
        executor.schedule({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3, TimeUnit.SECONDS)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Stop the executor to prevent memory leaks
        executor.shutdownNow()
    }
}
