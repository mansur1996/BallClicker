package com.mansur.ballclicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initViews()
    }
    private fun initViews(){
        val timer = object : CountDownTimer(2500,1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                openMainActivity()
            }
        }
        timer.start()
    }

    private fun openMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}