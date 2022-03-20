package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.TimeUnit

class CountDownFragment : Fragment() {

    private var timeCountInMilliSeconds = 30 * 1000

    private enum class TimerStatus {
        STARTED,STOPPED
    }

    private var timerStatus: TimerStatus = TimerStatus.STOPPED
    private lateinit var progressBarCircle: ProgressBar
    private lateinit var textViewTime: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var btnIr: AppCompatButton



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println(arguments)
        return inflater.inflate(R.layout.fragment_count_down, container,
            false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        startStop()
    }

    private fun initViews(view:View) {
        progressBarCircle = view.findViewById(R.id.progressBarCircle)
        textViewTime = view.findViewById(R.id.textViewTime)
        btnIr = view.findViewById(R.id.btnIr)

    }

    private fun startStop(){
        if(timerStatus == CountDownFragment.TimerStatus.STOPPED){
            timeCountInMilliSeconds =  30 * 1000
            setProgressBarValues()
            timerStatus = CountDownFragment.TimerStatus.STARTED
            startCountDownTimer()
        }else{
            timerStatus = CountDownFragment.TimerStatus.STOPPED
            stopCountDownTimer()
        }
    }


    private fun startCountDownTimer() {
        countDownTimer = object : CountDownTimer(timeCountInMilliSeconds.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                println(millisUntilFinished)
                textViewTime.text = hmsTimeFormatter(millisUntilFinished)
                progressBarCircle.progress = (millisUntilFinished / 1000).toInt()
            }
            override fun onFinish() {
                progressBarCircle.setBackgroundResource(R.drawable.drawable_circle_red)
                textViewTime.setTextColor(ContextCompat.getColor(textViewTime.context,R.color.colorRed))
                stopCountDownTimer()
                btnIr.visibility = View.VISIBLE
            }
        }.start()
        countDownTimer.start()
    }

    private fun stopCountDownTimer() {
        countDownTimer.cancel()
    }

    private fun setProgressBarValues() {
        progressBarCircle.max = timeCountInMilliSeconds / 1000
        progressBarCircle.progress = timeCountInMilliSeconds / 1000
    }

    @SuppressLint("DefaultLocale")
    private fun hmsTimeFormatter(milliSeconds: Long): String {
        return String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(milliSeconds),
            TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    milliSeconds
                )
            ),
            TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    milliSeconds
                )
            )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CountDownFragment()
    }
}