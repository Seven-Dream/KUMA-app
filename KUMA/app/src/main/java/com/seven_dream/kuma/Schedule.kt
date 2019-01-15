package com.seven_dream.kuma

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_schedule.*

class Schedule : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            val msg = "" + year + "/" + (month + 1) + "/" + dayOfMonth
            val intent: Intent = Intent(this, ScheduleOneday::class.java)
            intent.putExtra("date", msg)
            //intent.putExtra("year", year)
            //intent.putExtra("month", month)
            //intent.putExtra("dayOfMonth", dayOfMonth)
            startActivity(intent)
        }
    }
}
