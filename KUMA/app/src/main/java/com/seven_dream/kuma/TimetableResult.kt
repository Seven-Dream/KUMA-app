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
import kotlinx.android.synthetic.main.list_lecture_layout.view.*
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
            add(ListItem("講義名", "教員名", "教室名", "開講クウォータ"))

            for(pnt in 0..max) {    //pntはresultReceiveのポインタ
                //resultReceiveに格納されたpnt番目の講義IDの情報を取得
                val id: Int = resultReceive[pnt]
                val a : String = userDB.getLectureNameById(id) //講義名を取得
                val b : String = userDB.getTeacherNameById(id) //教員名を取得
                val c : String = userDB.getClasroomById(id) //教室を取得
                val d : Int = userDB.getQuarterById(id) //開講クウォータ(Int型)を取得
                var e : String = "" // Int型:dを文字列に置き換えて格納
                if(d == 1) {
                    e = "1Q"
                } else if(d == 2) {
                    e = "2Q"
                } else if(d == 3) {
                    e = "3Q"
                } else if(d == 4) {
                    e = "4Q"
                } else if(d == 5) {
                    e = "1学期"
                } else if(d == 6) {
                    e = "2学期"
                } else if(d == 7) {
                    e = "通年"
                }
                //取得した情報をarrayAdapterにいれる
                add(ListItem(a, b, c, e))

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

    constructor(lectureInfo: String, teacherInfo: String, roomInfo: String, quarterInfo: String) : this(lectureInfo) {
        this.teacherInfo = teacherInfo
        this.roomInfo = roomInfo
        this.quarterInfo = quarterInfo
    }
}

// リスト項目を再利用するためのホルダー
data class ViewHolder(
    val entryIcon: Button,
    val lectureView: TextView,
    val teacherView: TextView,
    val roomView: TextView,
    val quarterView: TextView)

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
                view.findViewById(R.id.list_quarter)
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

        /* 登録ボタンをタップしたときの処理*/
        viewHolder.entryIcon.setOnClickListener { _ ->
            Toast.makeText(context, "登録しました", Toast.LENGTH_LONG).show()
            /*開講クウォータの情報をString型からInt型に*/
            var quarterNum :Int = 0
            if(viewHolder.quarterView.text.toString() == "1Q"){
                quarterNum = 1
            }else if(viewHolder.quarterView.text.toString() == "2Q"){
                quarterNum = 2
            }else if(viewHolder.quarterView.text.toString() == "3Q"){
                quarterNum = 3
            }else if(viewHolder.quarterView.text.toString() == "4Q"){
                quarterNum = 4
            }else if(viewHolder.quarterView.text.toString() == "1学期"){
                quarterNum = 5
            }else if(viewHolder.quarterView.text.toString() == "2学期"){
                quarterNum = 6
            }else if(viewHolder.quarterView.text.toString() == "通年"){
                quarterNum = 7
            }

            //講義ID取得
            var entryID = userDB.getLecture_id(
                viewHolder.lectureView.text.toString(),
                viewHolder.teacherView.text.toString(),
                viewHolder.roomView.text.toString(),
                quarterNum)

            // テーブルTimetableに、講義名・教員名・教室・開講年・クウォータを格納
            userDB.addRecordTimetable(
                entryID,
                viewHolder.lectureView.text.toString(),
                viewHolder.teacherView.text.toString(),
                viewHolder.roomView.text.toString(),
                quarterNum)
            // 登録した講義の情報の表示を消去
            this.remove(listItem)
            this.notifyDataSetChanged()
        }
        return view!!
    }
}