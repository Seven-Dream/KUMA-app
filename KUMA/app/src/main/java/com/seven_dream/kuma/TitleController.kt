package com.seven_dream.kuma

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.os.AsyncTask
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import kotlinx.android.synthetic.main.titlecontroller.*


var lectureJson: String? = null//Jsonデータを入れるグローバル変数
var testJson: String? = null
var cancelJson: String? = null
var classJson: String? = null
var studentJson: String? = null
var uniJson: String? = null

class TitleController : AppCompatActivity() {

    private lateinit var userDB_Title: userDB_Adapter_Title//遅延初期化→プロパティ内でインスタンスにアクセス可能？
    private lateinit var userDB_Timetable: userDB_Adapter_Timetable//遅延初期化→プロパティ内でインスタンスにアクセス可能？

    override fun onCreate(savedInstanceState: Bundle?) {
        userDB_Title = userDB_Adapter_Title(this)//DBの呼び出し
        userDB_Timetable = userDB_Adapter_Timetable(this)//DBの呼び出し

        super.onCreate(savedInstanceState)
        setContentView(R.layout.titlecontroller)
        MyAsyncTask().execute()//APIからJSONを取得→データベース格納を行う

        button.setOnClickListener {
            Hello.text = userDB_Timetable.getClassroom(userDB_Timetable.getLecture_id(2018,3,3,3)!!)
            //Hello.text = "Happy"
        }
    }

    //API
    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTask : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg p0: Void?): String? {
            //getHtml("https://www.nicovideo.jp")
            //各グローバル変数にJSONデータを格納する
            //lectureJson = getHtml("http://172.21.34.153/json")
            Log.d("opall", "start get HTML")
            getHtml()
            Log.d("opall", "lectureJson=$lectureJson")
            //testJson = getHtml("URL")
            //cancelJson = getHtml("URL")
            //classJson = getHtml("URL")
            //uniJsonJson = getHtml("URL")
            //studentJson = getHtml("URL")
            return "true"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d("opal", "insert:Lecture")
            insertLecture() //Lecture
            Log.d("opal", "insert:Lecture_Test")
            insertTest()
            Log.d("opal", "insert:Lecture_Cancel")
            insertCancel()
            Log.d("opal", "insert:LectureChange")
            insertChangeClass()
            Log.d("opal", "insert:Event_uni")
            insertEvent_Uni()
            Log.d("opal", "insert:Event_Student")
            insertEvent_student()//Event_Student

            //-------画面遷移する-------
        }
    }

    private fun insertLecture() {
        lectureJson = "[{\"lecture_id\":1,\"lecture_name\":\"データベース\",\"teacher\":\"横山\",\"classroom\":\"A106\",\"year\":2018,\"quarter\":3, " +
                "\"week\":[{\"lecture_id\":1,\"week\":1,\"period\":3}, {\"lecture_id\":1,\"week\":3,\"period\":3}]} , " +
                "{\"lecture_id\":2,\"lecture_name\":\"オペレーティング\",\"teacher\":\"横山\",\"classroom\":\"A106\",\"year\":2018,\"quarter\":3, " +
                "\"week\":[{\"lecture_id\":1,\"week\":2,\"period\":4}, {\"lecture_id\":1,\"week\":4,\"period\":4}]}]"

        Log.d("opal", "Lec読み込んだもの：$lectureJson")

        val json = JSONArray(lectureJson)
        //id自動加算をなくした
        for (i in 0..(json.length() - 1)) {
            //lectureテーブルに挿入するためのレコードを取得
            val jsonObj = json.getJSONObject(i)
            /*本番用
            val id = jsonObj.getInt("Id")
            val name = jsonObj.getString("LectureName")
            val teacher = jsonObj.getString("Teachar")
            val classroom = jsonObj.getString("ClassRoom")
            val year = jsonObj.getInt("Year")
            val quarter = jsonObj.getInt("Quarter")
            */
            val id = jsonObj.getInt("lecture_id")
            val name = jsonObj.getString("lecture_name")
            val teacher = jsonObj.getString("teacher")
            val classroom = jsonObj.getString("classroom")
            val year = jsonObj.getInt("year")
            val quarter = jsonObj.getInt("quarter")
            Log.d("opal",name.toString())
            Log.d("opal", year.toString())
            userDB_Title.addRecordLecture(id, name, teacher, classroom, year, quarter)
            userDB_Timetable.addRecordTimetable(id, name, teacher, classroom, year, quarter)
            Log.d("opal","insert:"+ userDB_Title.getLecture(name))
            //week
            //val weekJson = jsonObj.getJSONArray("WeekTimes")
            val weekJson = jsonObj.getJSONArray("week")
            for (j in 0..(weekJson.length() - 1)) {
                val weekJsonObj = weekJson.getJSONObject(j)
                //val week_id = userDB.getLecture_id(name, teacher, classroom, year, quarter).toInt()
                //Log.d("opal", "weekID = "+ week_id)
                /*
                val week = weekJsonObj.getInt("Week")
                val period = weekJsonObj.getInt("Time")
                */
                val week = weekJsonObj.getInt("week")
                val period = weekJsonObj.getInt("period")
                userDB_Title.addRecordPeriod_Week(id, week, period)
                Log.d("opal","addWeek:"+ userDB_Title.getWeek())
            }
        }
    }

    //試験情報テーブルの挿入
    private fun insertTest() {
        testJson = "[{\"lecture_id\":1, \"month\":10, \"day\":10, \"classroom\":\"C101\", \"comment\":\"持ち込み可です\"}," +
                "{\"lecture_id\":2, \"month\":10, \"day\":12, \"classroom\":\"C102\", \"comment\":\"持ち込み不可です\"}," +
                "{\"lecture_id\":2, \"month\":12, \"day\":31, \"classroom\":\"C101\", \"comment\":\"持ち込み不可です\"}]"
        //Log.d("opal", "読み込んだもの：${testJson}")
        val json = JSONArray(testJson)
        for (i in 0..(json.length() - 1)) {
            //lecture
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("lecture_id")
            val month = jsonObj.getInt("month")
            val day = jsonObj.getInt("day")
            val classroom = jsonObj.getString("classroom")
            val comment = jsonObj.getString("comment")
            userDB_Title.addRecordTest(id, month, day, classroom, comment)
            //Log.d("opal","insert:${month}/${day}")
        }
    }

    //休講情報テーブルの挿入
    private fun insertCancel() {
        cancelJson = "[{\"lecture_id\":1, \"month\":11, \"day\":11,\"comment\":\"宿題はありません\"}," +
                "{\"lecture_id\":2, \"month\":12, \"day\":12, \"comment\":\"しっかり休んでください\"}," +
                "{\"lecture_id\":2, \"month\":1, \"day\":1, \"comment\":\"がんばれ\"}]"
        //Log.d("opal", "読み込んだもの：${cancelJson}")
        val json = JSONArray(cancelJson)
        for (i in 0..(json.length() - 1)) {
            //lecture
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("lecture_id")
            val month = jsonObj.getInt("month")
            val day = jsonObj.getInt("day")
            val comment = jsonObj.getString("comment")
            userDB_Title.addRecordCancel(id, month, day, comment)
            //Log.d("opal","insert:${month}/${day}")
        }
    }

    //教室変更テーブルの挿入
    private fun insertChangeClass() {
        classJson = "[{\"lecture_id\":1, \"month\":7, \"day\":7, \"classroom\":\"C101\"}," +
                "{\"lecture_id\":2, \"month\":8, \"day\":8, \"classroom\":\"C102\"}," +
                "{\"lecture_id\":2, \"month\":9, \"day\":9, \"classroom\":\"C101\"}]"
        //Log.d("opal", "読み込んだもの：$classJson}")
        val json = JSONArray(classJson)
        for (i in 0..(json.length() - 1)) {
            //lecture
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("lecture_id")
            val month = jsonObj.getInt("month")
            val day = jsonObj.getInt("day")
            val classroom = jsonObj.getString("classroom")
            userDB_Title.addRecordChange_Class(id, month, day, classroom)
            //Log.d("opal","insert:${month}/${day}")
        }
    }

    //大学予定テーブルの挿入
    private fun insertEvent_Uni() {
        uniJson = "[{\"id\":1, \"event_name\":\"大学祭\", \"year\":2018, \"month\":10, \"day\":14, \"comment\":\"単位を分け与えてもいいのよ\" }" +
                ",{\"id\":2, \"event_name\":\"卒業式\", \"year\":2018, \"month\":10, \"day\":14, \"comment\":\"着物の予約は早めにしておきましょう\" }" +
                ",{\"id\":3, \"event_name\":\"大学説明会\", \"year\":2018, \"month\":10, \"day\":14, \"comment\":\"企業さんには挨拶をしましょう\" }]"
        //Log.d("opal", "読み込んだもの：${uniJson}")

        val json = JSONArray(uniJson)
        //id自動加算をなくした
        for (i in 0..(json.length() - 1)) {
            //lecture
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("id")

            val name = jsonObj.getString("event_name")
            val year = jsonObj.getInt("year")
            val month = jsonObj.getInt("month")
            val day = jsonObj.getInt("day")
            val comment = jsonObj.getString("comment")
            //userDB.addRecordLecture(id, name, teacher, classroom, year, quarter)
            userDB_Title.addRecordUni(id, name, year, month, day, comment)
            //Log.d("opal","insert:"+ name)
        }

    }

    //学生イベントテーブルの挿入
    private fun insertEvent_student() {
        studentJson = "[{\"id\":1, \"event_name\":\"軽音ライブ\", \"year\":2018, \"month\":10, \"day\":14, \"url\":\"https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf\" }" +
                ",{\"id\":2, \"event_name\":\"アカペラライブ\", \"year\":2018, \"month\":10, \"day\":14, \"url\":\"https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf\" }" +
                ",{\"id\":3, \"event_name\":\"ロボ研ガンダムファイト大会\", \"year\":2018, \"month\":10, \"day\":14, \"url\":\"https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf\" }]"
        //Log.d("opal", "読み込んだもの：${studentJson}")

        val json = JSONArray(studentJson)
        //id自動加算をなくした
        for (i in 0..(json.length() - 1)) {
            //lecture
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("id")
            val name = jsonObj.getString("event_name")
            val year = jsonObj.getInt("year")
            val month = jsonObj.getInt("month")
            val day = jsonObj.getInt("day")
            val url = jsonObj.getString("url")
            //userDB.addRecordLecture(id, name, teacher, classroom, year, quarter)
            userDB_Title.addRecordStudent(id, name, year, month, day, url)
            //Log.d("opal","insert:"+ userDB.getStudent())
        }

    }

    private fun getHtml() {
        //resp.body()!!.string()は一回読み込むと消える
        val client = OkHttpClient()
        //val req = Request.Builder().url("http://172.21.34.153/json").get().build()
        val req = Request.Builder().url("https://www.google.com").get().build()
        val resp = client.newCall(req).execute()
        lectureJson = resp.body()!!.string()
        Log.d("opal", "LectureJson=$lectureJson")
    }
}

