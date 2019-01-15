package com.example.shiota.kotlinandroidsqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shiota.kotlinandroidsqlite.Adapter.Adapter
import com.example.shiota.kotlinandroidsqlite.DBHelper.DBHelper
import com.example.shiota.kotlinandroidsqlite.Model.NewPlan
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal var lstNewPlan: List<NewPlan> = ArrayList<NewPlan>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        btn_add.setOnClickListener {
            val plans = NewPlan (
                Integer.parseInt(edt_id.text.toString()),
                edt_title.text.toString(),
                edt_timeBegin.text.toString(),
                edt_timeEnd.text.toString(),
                edt_place.text.toString(),
                edt_memo.text.toString()
                )
            db.addPlans(plans)
            refreshData()
        }

        btn_update.setOnClickListener {
            val plans = NewPlan (
                Integer.parseInt(edt_id.text.toString()),
                edt_title.text.toString(),
                edt_timeBegin.text.toString(),
                edt_timeEnd.text.toString(),
                edt_place.text.toString(),
                edt_memo.text.toString()
            )
            db.updatePlans(plans)
            refreshData()
        }

        btn_delete.setOnClickListener {
            val plans = NewPlan (
                Integer.parseInt(edt_id.text.toString()),
                edt_title.text.toString(),
                edt_timeBegin.text.toString(),
                edt_timeEnd.text.toString(),
                edt_place.text.toString(),
                edt_memo.text.toString()
            )
            db.deletePlans(plans)
            refreshData()
        }


    }

    private fun refreshData() {
        lstNewPlan = db.allNewPlan
        val adapter = Adapter(this@MainActivity, lstNewPlan, edt_id, edt_title, edt_timeBegin, edt_timeEnd, edt_place,edt_memo)
        list_plan.adapter = adapter
    }
}
