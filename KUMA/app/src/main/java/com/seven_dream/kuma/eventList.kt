package com.example.hanm1_000.kuma

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.seven.kuma.userDB_Adapter_Event
import java.util.*

class eventList : AppCompatActivity() {
    private lateinit var userDB: userDB_Adapter_Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)    // コンストラクタを定義？
        userDB = userDB_Adapter_Event(this) // DBの呼び出し



        //初期のリスト項目を設定
        val arrayAdapter = MyArrayAdapter(this, 0).apply {
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
                29,
                "https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf"
            )
            userDB.addRecordEventStudent(
                3,
                "ロボ研ガンダムファイト大会",
                2019,
                1,
                29,
                "https://www.yahoo.co.jp"
            )

            var tmp = 0//格納する配列の場所
            val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN)
            val nen: Int = calendar.get(Calendar.YEAR)
            val tuki: Int = calendar.get(Calendar.MONTH)
            val hi: Int = calendar.get(Calendar.DAY_OF_MONTH)
            for (date in hi..31) {
                for (cnt in 1..3) {//同じ日にイベントがあった場合
                    val id = userDB.getEvent_id(nen, tuki + 1, date, cnt)
                    if (id != 0) {
                        val eventyear = userDB.getEvent_year(id)
                        val eventsla1:String = "/"
                        val eventmonth = userDB.getEvent_month(id)
                        val eventsla2:String = "/"
                        val eventday = userDB.getEvent_day(id)
                        val eventname = userDB.getEvent_name(id)
                        //取得した情報をarrayAdapterにいれる
                        add(ListItem(eventyear, eventsla1, eventmonth, eventsla2, eventday, eventname))
                        tmp += 1
                    } else {
                        break
                    }
                }

            }
        }
        val listView: ListView = this.findViewById(R.id.myListView)
        listView.adapter = arrayAdapter
    }
}

class ListItem(val yearInfo: Int) {
    var slaInfo1 : String = "No description"
    var monthInfo : Int = 0
    var slaInfo2 : String = "No description"
    var dayInfo : Int = 0
    var nameInfo : String = "No description"

    constructor(yearInfo: Int, slaInfo1: String, monthInfo: Int, slaInfo2: String, dayInfo: Int, nameInfo: String) : this(yearInfo) {
        this.monthInfo = monthInfo
        this.slaInfo1 = slaInfo1
        this.dayInfo = dayInfo
        this.slaInfo2 = slaInfo2
        this.nameInfo = nameInfo
    }
}

data class ViewHolder(
    val yearView: TextView,
    val eventsla1: TextView,
    val monthView: TextView,
    val eventsla2: TextView,
    val dayView: TextView,
    val nameView: TextView
    )

class MyArrayAdapter : ArrayAdapter<ListItem> {
    private var inflater: LayoutInflater? = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    var mContext: Context? = null
    constructor(context: Context, resource: Int) : super(context, resource) {
        this.mContext = context
    }
    // userDB_Adapter_Timetableクラスを定義
    private lateinit var userDB: userDB_Adapter_Event

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder? = null
        var view = convertView
        //DBの呼び出し
        userDB = userDB_Adapter_Event(context)
// 再利用の設定
        if (view == null) {
            view = inflater!!.inflate(R.layout.line_style, parent, false)

            viewHolder = ViewHolder(
                view.findViewById(R.id.yearTextView),
                view.findViewById(R.id.eventsla1),
                view.findViewById(R.id.monthTextView),
                view.findViewById(R.id.eventsla2),
                view.findViewById(R.id.dayTextView),
                view.findViewById(R.id.event_nameTextView)
            )
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

/* 項目の情報を設定*/
        val listItem = getItem(position)
        viewHolder.yearView.text = listItem.yearInfo.toString()
        viewHolder.eventsla1.text = listItem.slaInfo1
        viewHolder.monthView.text = listItem.monthInfo.toString()
        viewHolder.eventsla2.text = listItem.slaInfo2
        viewHolder.dayView.text = listItem.dayInfo.toString()
        viewHolder.nameView.text = listItem.nameInfo

        viewHolder.nameView.setOnClickListener { _ ->
            var eventID = userDB.getEvent_next(
                listItem.yearInfo,
                listItem.monthInfo,
                listItem.dayInfo,
                listItem.nameInfo
            )
            var geturl = userDB.getEvent_url(
                eventID
            )
            //val intent = Intent(mContext,Web_Activity::class.java)
            //intent.putExtra("url",geturl)
            //mContext!!.startActivity(intent)

            var uri = Uri.parse(geturl)
            val intent = Intent(Intent.ACTION_VIEW,uri)
            mContext!!.startActivity(intent)
        }
        return view!!
    }
}