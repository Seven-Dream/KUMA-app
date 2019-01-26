package com.example.hanm1_000.kuma



import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SimpleAdapter
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


        userDB.addRecordEventStudent(
            1,
            "軽音ライブ",
            2019,
            1,
            24,
            "https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf"
        )
        userDB.addRecordEventStudent(
            2,
            "アカペラライブ",
            2019,
            1,
            25,
            "https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf"
        )
        userDB.addRecordEventStudent(
            3,
            "ロボ研ガンダムファイト大会",
            2019,
            1,
            25,
            "https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf"
        )

        var tmp = 0//格納する配列の場所
        val eventyear_array: Array<Int?> = arrayOfNulls(50)
        val eventmonth_array: Array<Int?> = arrayOfNulls(50)
        val eventday_array: Array<Int?> = arrayOfNulls(50)
        val eventname_array: Array<String?> = arrayOfNulls(50)

        for (date in hi..31) {
            for (cnt in 1..3) {//同じ日にイベントがあった場合
                val id = userDB.getEvent_id(nen, tuki + 1, date, cnt)
                if (id != 0) {
                    val eventyear = userDB.getEvent_year(id)
                    val eventmonth = userDB.getEvent_month(id)
                    val eventday = userDB.getEvent_day(id)
                    val eventname = userDB.getEvent_name(id)
                    //配列に挿入
                    eventyear_array[tmp] = eventyear
                    eventmonth_array[tmp] = eventmonth
                    eventday_array[tmp] = eventday
                    eventname_array[tmp] = eventname
                    tmp += 1
                } else {
                    break
                }
            }
        }

        val items = List<Map<String?, Any?>>(10)
        { i ->
            mapOf(
                "eventyear" to eventyear_array[i],
                "eventmonth" to eventmonth_array[i],
                "eventday" to eventday_array[i],
                "eventname" to eventname_array[i]
            )
        }

        /*val adapter = ArrayAdapter<Map<String?, Any?>>(this, android.R.layout.simple_list_item_1, items)
        myListView.adapter = adapter*/

        val adapter = SimpleAdapter(
                this,
                items,
                android.R.layout.simple_list_item_2,
                arrayOf("eventday", "eventname"),
                intArrayOf(android.R.id.text1, android.R.id.text2)
        )//R.id.yearTextView, R.id.monthTextView, R.id.dayTextView, R.id.event_nameTextView
        myListView.adapter = adapter
        /*val listView = ListView(this)
        setContentView(listView)
        val arrayAdapter = ArrayAdapter(this, R.layout.line_style,R.id.yearTextView, items)
        listView.setAdapter(arrayAdapter)*/
    }
}