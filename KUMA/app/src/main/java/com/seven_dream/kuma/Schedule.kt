package com.seven_dream.kuma

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_schedule.*

class Schedule : AppCompatActivity() {
    internal lateinit var db:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        db = DBHelper(this)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            // 日付を取得し、DBHelper.ktで使えるようにDBHelperに格納する
            val date = "" + year + "/" + (month + 1) + "/" + dayOfMonth
            db.getDate(date)

            val intent: Intent = Intent(this, ScheduleOneday::class.java)
            intent.putExtra("date", date)
            //intent.putExtra("year", year)
            //intent.putExtra("month", month)
            //intent.putExtra("dayOfMonth", dayOfMonth)
            startActivity(intent)
        }
    }
}
