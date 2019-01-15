package com.seven_dream.kuma

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_schedule_oneday.*
import com.seven_dream.kuma.R.layout.activity_schedule_oneday


class ScheduleOneday : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal var lstNewPlan: List<NewPlan> = ArrayList<NewPlan>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_schedule_oneday)

        db = DBHelper(this)
        refreshData()

        val textView = findViewById(R.id.edt_date) as TextView
        textView.text = intent.extras.get("date").toString()

        val textView1 = findViewById(R.id.edt_id) as TextView
        textView1.text = (db.getId()+1).toString()

        btn_add.setOnClickListener {
            val plans = NewPlan (
                Integer.parseInt(edt_id.text.toString()),
                edt_date.text.toString(),
                edt_title.text.toString(),
                edt_timeBegin.text.toString(),
                edt_timeEnd.text.toString(),
                edt_place.text.toString(),
                edt_memo.text.toString()
            )
            db.addPlans(plans)
            finish()
            //refreshData()
        }

        btn_update.setOnClickListener {
            val plans = NewPlan (
                Integer.parseInt(edt_id.text.toString()),
                edt_date.text.toString(),
                edt_title.text.toString(),
                edt_timeBegin.text.toString(),
                edt_timeEnd.text.toString(),
                edt_place.text.toString(),
                edt_memo.text.toString()
            )
            db.updatePlans(plans)
            finish()
            //refreshData()
        }

        btn_delete.setOnClickListener {
            val plans = NewPlan (
                Integer.parseInt(edt_id.text.toString()),
                edt_date.text.toString(),
                edt_title.text.toString(),
                edt_timeBegin.text.toString(),
                edt_timeEnd.text.toString(),
                edt_place.text.toString(),
                edt_memo.text.toString()
            )
            db.deletePlans(plans)
            finish()
            //refreshData()
        }


    }

    private fun refreshData() {
        lstNewPlan = db.allNewPlan
        val adapter = Adapter(this@ScheduleOneday, lstNewPlan, edt_id, edt_date, edt_title, edt_timeBegin, edt_timeEnd, edt_place,edt_memo)
        list_plan.adapter = adapter
    }
}
