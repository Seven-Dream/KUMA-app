package com.example.hanm1_000.kuma;

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
//import android.widget.ArrayAdapter
//import android.widget.ListView
import android.widget.SimpleAdapter
import com.example.hanm1_000.kuma.R
import com.example.seven.kuma.userDB_Adapter_Event
import kotlinx.android.synthetic.main.activity_event_list.*
import java.util.*

class eventList : AppCompatActivity() {
private lateinit var userDB: userDB_Adapter_Event

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)    // コンストラクタを定義？
        userDB = userDB_Adapter_Event(this) // DBの呼び出し

        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN)
        val nen: Int = calendar.get(Calendar.YEAR)
        val tuki: Int = calendar.get(Calendar.MONTH)
        val hi: Int = calendar.get(Calendar.DAY_OF_MONTH)
                
        userDB.addRecordEventStudent(1, "軽音ライブ", nen, tuki, hi,"url")
        userDB.addRecordEventStudent(2, "アカペラライブ",nen, tuki, hi,  "url")
        userDB.addRecordEventStudent(4, "ロボ研ガンダムファイト大会",nen, tuki, hi+1, "url")

        var tmp = 0//格納する配列の場所
        /*val eventyear_array: Array<Int?> = arrayOfNulls(50)
        val eventmonth_array: Array<Int?> = arrayOfNulls(50)*/
        val eventday_array: Array<Int?> = arrayOfNulls(50)
        val eventname_array: Array<String?> = arrayOfNulls(50)

        for (date in hi ..31) {
        //for (date in 1 ..31) {

                for (cnt in 1..1000) {//同じ日にイベントがあった場合
                        val id = userDB.getEvent_id(nen, tuki, date, cnt)
                        if(id == 0) break
                        /*val eventyear = userDB.getEvent_year(id)
                    val eventmonth = userDB.getEvent_month(id)*/
                        val eventday = userDB.getEvent_day(id)
                        val eventname = userDB.getEvent_name(id)
                        //配列に挿入
                        //eventyear_array[tmp] = eventyear
                        //eventmonth_array[tmp] = eventmonth
                        eventday_array[tmp] = eventday
                        eventname_array[tmp] = eventname
                        tmp += 1
                }
        }

        //val items = List<Map<String, String, String, String>>(10, {i -> mapOf("eventyear" to eventyear_array[i], "eventmonth" to eventmonth_array[i], "eventday" to eventday_array[i], "eventname" to eventname_array[i])})
        val items = List<Map<String?, Any?>>(10) {i -> mapOf("eventday" to eventday_array[i], "eventname" to eventname_array[i])}

        val adapter = SimpleAdapter (
        this,
        items,
        android.R.layout.simple_list_item_2,
        arrayOf("eventday", "eventname"),
        intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        myListView.adapter = adapter

        /*val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
        val eventList : ListView = findViewById(R.id.eventList)
        eventList.adapter = adapter*/


        }
        }