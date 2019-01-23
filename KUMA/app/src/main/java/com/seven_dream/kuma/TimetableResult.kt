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

class TimetableResult : AppCompatActivity() {
    // userDB_Adapter_Timetableクラスを定義
    private lateinit var userDB: DB_Adapter_Search_Timetable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable_result)
        //setContentView(R.layout.list_lecture_layout)
        //DBの呼び出し
        userDB = DB_Adapter_Search_Timetable(this)

        val resultLecture: Array<Int> = arrayOf()
        intent.extras.get("resultArray[0]")
        //初期のリスト項目を設定
            val arrayAdapter = MyArrayAdapter(this, 0).apply {
                add(ListItem("講義名", "教員名"/*, "教室名", "開講クウォータ"*/))
                add(ListItem("ソフトウェア工学", "高田"/*, "教室名", "開講クウォータ"*/))

            }

        //ListViewにリスト項目とArrayAdapterを設定
        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = arrayAdapter

        /*
        // 変更可能なリストを定義
        // 検索結果を入れる配列の定義 : 完成したら、各配列の中身の順番があっているのか確認
        val lecture_array: MutableList<String?> = mutableListOf()
        val teacher_array: MutableList<String?> = mutableListOf()
        val room_array: MutableList<String?> = mutableListOf()
        val quarter_array: MutableList<String?> = mutableListOf()

        fun listArray(a: Array<String?>, b: Array<String?>, c: Array<String?>, d: Array<Int?>/*, e:Array<String?>, f:Array<Int?>)*/ {
            // 講義IDの回数for文を回す
            val length: Int = userDB.countLecture() // テーブルのカラム数

            val arrayAdapter = MyArrayAdapter(this, 0).apply {
                val len: Int = length - 1
                for (i in 0..len) {
                    add(ListItem(a[i].toString(), b[i].toString(), c[i].toString(), d[i].toString()))
                }
            }

            // ListViewにリスト項目とarrayAdapterを設定
            val listView: ListView = findViewById(R.id.listView)
            listView.adapter = arrayAdapter //リスト項目とlistViewにセット
        }
        */

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
        /*
    var roomInfo: String = "No description"
    var quarterInfo: String = "No description"
    //    var weekInfo: String = "No description"
    */
//    var periodInfo: Int? = null
    constructor(lectureInfo: String, teacherInfo: String/*, roomInfo: String, quarterInfo: String/*, weekInfo: String, periodInfo: String*/*/) : this(lectureInfo) {
        this.teacherInfo = teacherInfo
            /*
        this.roomInfo = roomInfo
        this.quarterInfo = quarterInfo
        */
        /*
        this.weekInfo = weekInfo
        this.periodInfo = periodInfo
        */
    }
}

// リスト項目を再利用するためのホルダー
data class ViewHolder(
    val entryIcon: Button,
    val lectureView: TextView,
    val teacherView: TextView/*,
    val roomView:TextView,
    val quarterView:TextView*//*,
    val weekView:TextView,
    val periodView:TextView
    */
)

// 自作のリスト項目データを扱えるようにした ArrayAdapter
class MyArrayAdapter : ArrayAdapter<ListItem> {
    private var inflater : LayoutInflater? = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
    constructor(context : Context, resource : Int) : super(context, resource) {}

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //val toastMessage = "登録しました!"
        var viewHolder : ViewHolder? = null
        var view = convertView

        // 再利用の設定
        if (view == null) {

            view = inflater!!.inflate(R.layout.list_lecture_layout, parent, false)

            viewHolder = ViewHolder(
                view.findViewById(R.id.entry_lecture),
                view.findViewById(R.id.list_lecture),
                view.findViewById(R.id.list_teacher)/*,
                view.findViewById(R.id.list_classroom),
                view.findViewById(R.id.list_quarter)/*,
                view.findViewById(R.id.list_week),
                view.findViewById(R.id.list_period)*/ */
            )
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        /* 項目の情報を設定*/
        val listItem = getItem(position)
        viewHolder.lectureView.text = listItem.lectureInfo //講義名の情報
        viewHolder.teacherView.text = listItem.teacherInfo //教員名の情報
        /*
        viewHolder.roomView.text = listItem.roomInfo //教室の情報
        viewHolder.quarterView.text = listItem.quarterInfo //開講クウォータの情報
        //viewHolder.weekiView.text = listItem.weekInfo //曜日の情報
        //viewHolder.periodView.text = listItem.periodInfo //時限の情報
        */
        /* 登録ボタンをタップしたときの処理*/
        viewHolder.entryIcon.setOnClickListener { _ ->
                    Toast.makeText(context, "登録しました", Toast.LENGTH_LONG).show()
            // 講義IDを使ってテーブルTimetableに、講義名・教員名・教室・開講年・クウォータを格納
        }
        return view!!
    }
}