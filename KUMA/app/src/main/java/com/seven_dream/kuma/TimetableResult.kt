package com.seven_dream.kuma
/* 講義検索結果画面の動作 */
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CpuUsageInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_timetable_result.*
import kotlinx.android.synthetic.main.layout_timetable_search.*
import kotlinx.android.synthetic.main.list_lecture_layout.view.*
import org.w3c.dom.Text
import kotlin.concurrent.timer

class TimetableResult : AppCompatActivity() {
    // userDB_Adapter_Timetableクラスを定義
    private lateinit var userDB: DB_Adapter_Search_Timetable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable_result)
        //setContentView(R.layout.list_lecture_layout)
        //DBの呼び出し
        userDB = DB_Adapter_Search_Timetable(this)

        val resultReceive: ArrayList<Int> = intent.extras.getIntegerArrayList("resultArray") //前の画面からデータ:配列resultPrintに入れてたものを受け取る
        var max:Int = resultReceive.size // resultReceiveの要素数
        max -= 1

        //初期のリスト項目を設定
        val arrayAdapter = MyArrayAdapter(this, 0).apply {
            //add(ListItem("講義名", "教員名", "教室名", "開講クウォータ", "曜日", "時限", "開講年度"))

            for(pnt in 0..max) {    //pntはresultReceiveのポインタ
                //resultReceiveに格納されたpnt番目の講義IDの情報を取得
                val id: Int = resultReceive[pnt]
                val lectureName : String = userDB.getLectureNameById(id) //講義名を取得
                val teacher : String = userDB.getTeacherNameById(id) //教員名を取得
                val room : String = userDB.getClasroomById(id) //教室を取得
                val quarterNum : Int = userDB.getQuarterById(id) //開講クウォータ(Int型)を取得
                var quarter : String = "" // Int型:dを文字列に置き換えて格納
                if(quarterNum == 1) {
                    quarter = "1Q"
                } else if(quarterNum == 2) {
                    quarter = "2Q"
                } else if(quarterNum == 3) {
                    quarter = "3Q"
                } else if(quarterNum == 4) {
                    quarter = "4Q"
                } else if(quarterNum == 5) {
                    quarter = "1学期"
                } else if(quarterNum == 6) {
                    quarter = "2学期"
                } else if(quarterNum == 7) {
                    quarter = "通年"
                }
                val weekNum : Int = userDB.getWeekById(id) //曜日(Int型)を取得
                var weekDay : String = "" // Int型:gを文字列に置き換えて格納
                if(weekNum == 1) {
                    weekDay = "月"
                }else if (weekNum == 2) {
                    weekDay = "火"
                }else if (weekNum == 3) {
                    weekDay = "水"
                }else if (weekNum == 4) {
                    weekDay = "木"
                }else if (weekNum == 5) {
                    weekDay = "金"
                }else if (weekNum == 6) {
                    weekDay = "土"
                }
                val period : String = userDB.getPeriodById(id).toString() //時限(Int型)をString型として取得
                val year : String = userDB.getYearById(id).toString() //開講年度(Int型)をString型として取得

                //取得した情報をarrayAdapterにいれる
                add(ListItem(lectureName, teacher, room, quarter, weekDay, period, year))
            }
        }

        //ListViewにリスト項目とArrayAdapterを設定
        val listView: ListView = this.findViewById(R.id.listView)
        listView.adapter = arrayAdapter//リスト項目とlistViewにセット

        /* 戻るボタンをタップしたときの処理*/
        return_result.setOnClickListener {
            finish()
        }
    }
}

/* リスト表示 */
// リスト項目のデータ
class ListItem(val lectureInfo: String) {
    var teacherInfo : String = "No description"
    var roomInfo : String = "No description"
    var quarterInfo : String = "No description"
    var weekInfo : String = "No description"
    var periodInfo : String = "No description"
    var yearInfo : String = "No description"

    constructor(lectureInfo: String, teacherInfo: String, roomInfo: String, quarterInfo: String, weekInfo: String, periodInfo: String, yearInfo: String) : this(lectureInfo) {
        this.teacherInfo = teacherInfo
        this.roomInfo = roomInfo
        this.quarterInfo = quarterInfo
        this.weekInfo = weekInfo
        this.periodInfo = periodInfo
        this.yearInfo = yearInfo
    }
}

// リスト項目を再利用するためのホルダー
data class ViewHolder(
    val entryIcon: Button,
    val lectureView: TextView,
    val teacherView: TextView,
    val roomView: TextView,
    val quarterView: TextView,
    val weekView: TextView,
    val periodView: TextView,
    val yearView: TextView)

// 自作のリスト項目データを扱えるようにした ArrayAdapter
class MyArrayAdapter : ArrayAdapter<ListItem> {
    private var inflater: LayoutInflater? = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
    constructor(context: Context, resource: Int) : super(context, resource) {}
    // userDB_Adapter_Timetableクラスを定義
    private lateinit var userDB: DB_Adapter_Search_Timetable

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder? = null
        var view = convertView
        //DBの呼び出し
        userDB = DB_Adapter_Search_Timetable(context)

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
                view.findViewById(R.id.list_period),
                view.findViewById(R.id.list_year)
            )
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        /* 項目の情報を設定*/
        val listItem = getItem(position)
        viewHolder.lectureView.text = listItem.lectureInfo //講義名の情報
        viewHolder.teacherView.text = listItem.teacherInfo //教員名の情報
        viewHolder.roomView.text = listItem.roomInfo //教室の情報
        viewHolder.quarterView.text = listItem.quarterInfo //開講クウォータの情報
        viewHolder.weekView.text = listItem.weekInfo //曜日の情報
        viewHolder.periodView.text = listItem.periodInfo //時限の情報
        viewHolder.yearView.text = listItem.yearInfo //開講年度の情報

        /* 登録ボタンをタップしたときの処理*/
        viewHolder.entryIcon.setOnClickListener { _ ->
            Toast.makeText(context, "登録しました", Toast.LENGTH_LONG).show()
            /*開講クウォータの情報をString型からInt型に*/
            var quarterNum :Int = 0
            var quarter : String = viewHolder.quarterView.text.toString()
            if(quarter == "1Q"){
                quarterNum = 1
            }else if(quarter == "2Q"){
                quarterNum = 2
            }else if(quarter == "3Q"){
                quarterNum = 3
            }else if(quarter == "4Q"){
                quarterNum = 4
            }else if(quarter == "1学期"){
                quarterNum = 5
            }else if(quarter == "2学期"){
                quarterNum = 6
            }else if(quarter == "通年"){
                quarterNum = 7
            }

            //講義ID取得
            var entryID = userDB.getLecture_id(
                viewHolder.lectureView.text.toString(),
                viewHolder.teacherView.text.toString(),
                viewHolder.roomView.text.toString(),
                viewHolder.yearView.text.toString().toInt(),
                quarterNum)

            // テーブルTimetableに、講義名・教員名・教室・開講年・クウォータを格納
            userDB.addRecordTimetable(
                entryID,
                viewHolder.lectureView.text.toString(),
                viewHolder.teacherView.text.toString(),
                viewHolder.roomView.text.toString(),
                viewHolder.yearView.text.toString().toInt(),
                quarterNum)
            // 登録した講義の情報の表示を消去
            this.remove(listItem)
            this.notifyDataSetChanged()
        }
        return view!!
    }
}