package com.seven_dream.kuma

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.activity_schedule_oneday.*

class Schedule : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal var lstNewPlan: List<NewPlan> = ArrayList<NewPlan>()

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
            startActivity(intent)
        }

        // 時間割画面に飛ぶ
        val timeSchedule = findViewById<Button>(R.id.timeTable)
        timeSchedule.setOnClickListener {
            val intent1 = Intent(this,Timetable_1q::class.java)
            startActivity(intent1)
        }
    }
}