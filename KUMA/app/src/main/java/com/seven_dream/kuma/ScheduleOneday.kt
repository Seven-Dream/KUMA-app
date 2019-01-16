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

        // 登録画面のIDと日付に初期状態から入力させておく
        val textView = findViewById(R.id.edt_date) as TextView
        textView.text = intent.extras.get("date").toString()
        val textView1 = findViewById(R.id.edt_id) as TextView
        textView1.text = (db.getId()+1).toString()

        /* addボタンをクリックすると構造体NewPlanにEditTextで入力した値を格納し、
         データベース内のテーブルに構造体を格納する*/
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

        //指定したIDを元にデータベース内のテーブルの内容を変更する
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

        //指定したIDを元にデータベース内のテーブルの内容を削除する
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

    /*DBHelper.ktのget()からデータベース内のテーブルを参照し、日詳細時のデータを配列に格納する。
    また、配列の情報をlistViewで表示する*/
    private fun refreshData() {
        lstNewPlan = db.allNewPlan
        val adapter = Adapter(this@ScheduleOneday, lstNewPlan, edt_id, edt_date, edt_title, edt_timeBegin, edt_timeEnd, edt_place,edt_memo)
        list_plan.adapter = adapter
    }
}
