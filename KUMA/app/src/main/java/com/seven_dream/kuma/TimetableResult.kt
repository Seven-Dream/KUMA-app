package com.seven_dream.kuma
/* 講義検索結果画面の動作 */
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CpuUsageInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_timetable_result.*
import kotlinx.android.synthetic.main.list_lecture_layout.view.*

class TimetableResult : AppCompatActivity() {
    // userDB_Adapter_Timetableクラスを定義
    private lateinit var userDB: DB_Adapter_Search_Timetable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable_result)
        setContentView(R.layout.list_lecture_layout)
        //DBの呼び出し
        userDB = DB_Adapter_Search_Timetable(this)
        //DBを取得
        //val db = userDB.writableDatabase //val db = userDB!!.writableDatabase

        // 初期のリスト項目を設定
        val arrayAdapter = MyArrayAdapter(this, 0).apply {
            add(ListItem("講義名", "教員名", "教室", "開講クウォータ", "曜日", "1"))
        }
        // ListView にリスト項目と ArrayAdapter を設定
        val listView : ListView = findViewById(R.id.listView)
        listView.adapter = arrayAdapter

        /* 講義登録ボタン */
        //講義登録ボタンを押した時の動作
        entry_lecture.setOnClickListener {
            // 該当する講義IDを取得
            userDB.getLecture_id(lectureInfo, teacherInfo, roomInfo, 2018, quarterInfo)
            // DBに該当する講義IDの講義情報を時間割登録に入れる
        }

        /* 戻るボタン */
        //戻るボタンを押したら検索結果画面の表示を終了（講義検索画面へ遷移）
        return_result.setOnClickListener {
            finish()
        }

    }
}

/* リスト表示 */
// リスト項目のデータ
class ListItem(val lectureID : String) {
    var lectureInfo : String = "No description"
    var teacherInfo : String = "No description"
    var roomInfo: String = "No description"
    var quarterInfo: String = "No description"
    var weekInfo: String = "No description"
    var periodInfo: Int? = null

    constructor(lectureInfo: String, teacherInfo: String, roomInfo: String, quarterInfo: String, weekInfo: String, periodInfo: Int) : this(lectureID) {
        this.lectureInfo = lectureInfo
        this.teacherInfo = teacherInfo
        this.roomInfo = roomInfo
        this.quarterInfo = quarterInfo
        this.weekInfo = weekInfo
        this.periodInfo = periodInfo
    }
}

// リスト項目を再利用するためのホルダー
data class ViewHolder(val entryIcon: Button, val lectureView: TextView, val teacherView: TextView, val roomView:TextView, val quarterView:TextView, val weekView:TextView, val periodView:TextView)

// 自作のリスト項目データを扱えるようにした ArrayAdapter
class MyArrayAdapter : ArrayAdapter<ListItem> {
    private var inflater : LayoutInflater? = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
    constructor(context : Context, resource : Int) : super(context, resource) {}
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var viewHolder : ViewHolder? = null
        var view = convertView

        // 再利用の設定
        if (view == null) {

            view = inflater!!.inflate(R.layout.list_lecture_layout, parent, false)

            viewHolder = ViewHolder(
                view.findViewById(R.id.entry_lecture),
                view.findViewById(R.id.list_lecture),
                view.findViewById(R.id.list_teacher),
                view.findViewById(R.id.list_classroom),
                view.findViewById(R.id.list_quarter),
                view.findViewById(R.id.list_week),
                view.findViewById(R.id.list_period)
            )
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        // 項目の情報を設定
        val listItem = getItem(position)
        viewHolder.entryIcon.setOnClickListener { _ ->
            // 登録ボタンをタップしたときの処理
            /*this.remove(listItem)
            this.notifyDataSetChanged()*/
        }
        viewHolder.lectureView.text = listItem.lectureInfo
        viewHolder.teacherView.text = listItem.teacherInfo
        viewHolder.roomView.text = listItem.roomInfo
        viewHolder.quarterView.text = listItem.quarterInfo
        viewHolder.weekiView.text = listItem.weekInfo
        viewHolder.periodView.text = listItem.periodInfo


        return view!!
    }
}
