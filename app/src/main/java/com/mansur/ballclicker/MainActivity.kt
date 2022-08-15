package com.mansur.ballclicker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.mansur.ballclicker.databinding.WinDialogLayoutBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var timeLimit : Long = 10 // game is going on
    private var score = 0 //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        tv_time.text = "Start"

        tv_time.setOnClickListener {
            if (tv_time.text.equals("Start"))
                startGame()
        }

        iv_1.setOnClickListener {
            isClicked(iv_1)
        }

        iv_2.setOnClickListener {
            isClicked(iv_2)
        }

        iv_3.setOnClickListener {
            isClicked(iv_3)
        }

        iv_4.setOnClickListener {
            isClicked(iv_4)
        }

        iv_5.setOnClickListener {
            isClicked(iv_5)
        }

        iv_6.setOnClickListener {
            isClicked(iv_6)
        }

        iv_7.setOnClickListener {
            isClicked(iv_7)
        }

        iv_8.setOnClickListener {
            isClicked(iv_8)
        }

        iv_9.setOnClickListener {
            isClicked(iv_9)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun isClicked(imageView: View){
        if (imageView.isPressed){
            score++
            tv_score.text = "Your score $score"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun resetGame() {
        tv_score.text = "Your score : 0"
        timeLimit = 10
        score = 0
    }

    private fun startGame() {
        timeLimit *= 2
        val timer = object : CountDownTimer(timeLimit / 2 * 1000,500){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                if(timeLimit.toInt() % 2 == 0){
                    tv_time.text = "Time : ${timeLimit/2}"
                }
                timeLimit--
                showBall()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                tv_time.text = "Time's Up!"
                hideBalls()
                showWinDialog(score)
            }
        }
        timer.start()
    }

    private fun hideBalls() {
        iv_1.visibility = View.INVISIBLE
        iv_2.visibility = View.INVISIBLE
        iv_3.visibility = View.INVISIBLE
        iv_4.visibility = View.INVISIBLE
        iv_5.visibility = View.INVISIBLE
        iv_6.visibility = View.INVISIBLE
        iv_7.visibility = View.INVISIBLE
        iv_8.visibility = View.INVISIBLE
        iv_9.visibility = View.INVISIBLE

    }

    private fun showBall() {
        val randomNum = (1..9).shuffled().last()
        hideBalls()

        when(randomNum){
            1 -> iv_1.visibility = View.VISIBLE
            2 -> iv_2.visibility = View.VISIBLE
            3 -> iv_3.visibility = View.VISIBLE
            4 -> iv_4.visibility = View.VISIBLE
            5 -> iv_5.visibility = View.VISIBLE
            6 -> iv_6.visibility = View.VISIBLE
            7 -> iv_7.visibility = View.VISIBLE
            8 -> iv_8.visibility = View.VISIBLE
            9 -> iv_9.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showWinDialog(score : Int) {
        val dialogLayoutBinding = WinDialogLayoutBinding.inflate(LayoutInflater.from(this))
        dialogLayoutBinding.textView.text = "Your score $score"

        val dialog = AlertDialog.Builder(this, R.style.RoundedDialog)
            .setCancelable(false)
            .setView(dialogLayoutBinding.root)
            .create()

        dialogLayoutBinding.button.setOnClickListener{
            resetGame()
            startGame()
            dialog.dismiss()
        }

        dialog.show()
    }
}