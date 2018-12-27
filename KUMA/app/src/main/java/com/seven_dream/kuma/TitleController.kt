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

var lectureJson: String? = null//Jsonデータを入れるグローバル変数
var testJson: String? = null
var cancelJson: String? = null
var classJson: String? = null
var studentJson: String? = null
var uniJson: String? = null

class TitleController : AppCompatActivity() {

    private lateinit var userDB: userDB_Adapter//遅延初期化→プロパティ内でインスタンスにアクセス可能？

    override fun onCreate(savedInstanceState: Bundle?) {
        userDB = userDB_Adapter(this)//DBの呼び出し

        super.onCreate(savedInstanceState)
        setContentView(R.layout.titlecontroller)
        MyAsyncTask().execute()//APIからJSONを取得→データベース格納を行う
    }
//API
    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTask : AsyncTask<Void, Void, String>() {

    override fun doInBackground(vararg p0: Void?): String? {
        getHtml("https://www.nicovideo.jp")
        return "true"
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.d("opal","insert:Lecture")
        insertLecture() //Lecture
        Log.d("opal","insert:Lecture_Test")
        insertTest()
        Log.d("opal","insert:Lecture_Cancel")
        insertCancel()
        Log.d("opal","insert:LectureChange")
        insertChangeClass()
        Log.d("opal","insert:Event_uni")
        insertEvent_Uni()
        Log.d("opal","insert:Event_Student")
        insertEvent_student()//Event_Student
    }
}
    fun insertLecture(){
        lectureJson = "[{\"lecture_id\":1,\"lecture_name\":\"データベース\",\"teacher\":\"横山\",\"classroom\":\"A106\",\"year\":2018,\"quarter\":3, " +
                "\"week\":[{\"lecture_id\":1,\"week\":1,\"period\":3}, {\"lecture_id\":1,\"week\":3,\"period\":3}]} , " +
                "{\"lecture_id\":2,\"lecture_name\":\"オペレーティング\",\"teacher\":\"横山\",\"classroom\":\"A106\",\"year\":2018,\"quarter\":3, " +
                "\"week\":[{\"lecture_id\":1,\"week\":2,\"period\":4}, {\"lecture_id\":1,\"week\":4,\"period\":4}]}]"
        //Log.d("opal", "読み込んだもの：$lectureJson")

        val json = JSONArray(lectureJson)
        //id自動加算をなくした
        for (i in 0..(json.length() - 1)) {
            //lecture
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("lecture_id")
            val name = jsonObj.getString("lecture_name")
            val teacher = jsonObj.getString("teacher")
            val classroom = jsonObj.getString("classroom")
            val year = jsonObj.getInt("year")
            val quarter = jsonObj.getInt("quarter")
            userDB.addRecordLecture(id, name, teacher, classroom, year, quarter)
            //userDB.addRecordLecture(name, teacher, classroom, year, quarter)
            //Log.d("opal","insert:"+ userDB.getLecture(name))
            //week
            val weekJson = jsonObj.getJSONArray("week")
            for (j in 0..(weekJson.length() - 1)) {
                val weekJsonObj = weekJson.getJSONObject(j)
                val week_id = userDB.getLecture_id(name, teacher, classroom, year, quarter).toInt()
                //Log.d("opal", "weekID = "+ week_id)
                val week = weekJsonObj.getInt("week")
                val period = weekJsonObj.getInt("period")
                userDB.addRecordPeriod_Week(week_id, week,period)
                //Log.d("opal","addWeek:"+ userDB.getWeek())
            }
        }
    }
    fun insertTest(){
        testJson = "[{\"lecture_id\":1, \"month\":10, \"day\":10, \"classroom\":\"C101\", \"comment\":\"持ち込み可です\"}," +
                "{\"lecture_id\":2, \"month\":10, \"day\":12, \"classroom\":\"C102\", \"comment\":\"持ち込み不可です\"}," +
                "{\"lecture_id\":2, \"month\":12, \"day\":31, \"classroom\":\"C101\", \"comment\":\"持ち込み不可です\"}]"
        Log.d("opal", "読み込んだもの：${testJson}")
        val json = JSONArray(testJson)
        for (i in 0..(json.length() - 1)) {
            //lecture
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("lecture_id")
            val month = jsonObj.getInt("month")
            val day = jsonObj.getInt("day")
            val classroom = jsonObj.getString("classroom")
            val comment = jsonObj.getString("comment")
            userDB.addRecordTest(id,month, day, classroom,comment)
            Log.d("opal","insert:${month}/${day}")
        }
    }

    fun insertCancel(){
        cancelJson = "[{\"lecture_id\":1, \"month\":11, \"day\":11,\"comment\":\"宿題はありません\"}," +
                "{\"lecture_id\":2, \"month\":12, \"day\":12, \"comment\":\"しっかり休んでください\"}," +
                "{\"lecture_id\":2, \"month\":1, \"day\":1, \"comment\":\"がんばれ\"}]"
        Log.d("opal", "読み込んだもの：${cancelJson}")
        val json = JSONArray(cancelJson)
        for (i in 0..(json.length() - 1)) {
            //lecture
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("lecture_id")
            val month = jsonObj.getInt("month")
            val day = jsonObj.getInt("day")
            val comment = jsonObj.getString("comment")
            userDB.addRecordCancel(id,month, day, comment)
            Log.d("opal","insert:${month}/${day}")
        }
    }
    fun insertChangeClass(){
        classJson = "[{\"lecture_id\":1, \"month\":7, \"day\":7, \"classroom\":\"C101\"}," +
                "{\"lecture_id\":2, \"month\":8, \"day\":8, \"classroom\":\"C102\"}," +
                "{\"lecture_id\":2, \"month\":9, \"day\":9, \"classroom\":\"C101\"}]"
        Log.d("opal", "読み込んだもの：$classJson}")
        val json = JSONArray(classJson)
        for (i in 0..(json.length() - 1)) {
            //lecture
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("lecture_id")
            val month = jsonObj.getInt("month")
            val day = jsonObj.getInt("day")
            val classroom = jsonObj.getString("classroom")
            userDB.addRecordChange_Class(id,month, day, classroom)
            Log.d("opal","insert:${month}/${day}")
        }
    }
    fun insertEvent_Uni(){
        uniJson = "[{\"id\":1, \"event_name\":\"大学祭\", \"year\":2018, \"month\":10, \"day\":14, \"comment\":\"単位を分け与えてもいいのよ\" }" +
                ",{\"id\":2, \"event_name\":\"卒業式\", \"year\":2018, \"month\":10, \"day\":14, \"comment\":\"着物の予約は早めにしておきましょう\" }" +
                ",{\"id\":3, \"event_name\":\"大学説明会\", \"year\":2018, \"month\":10, \"day\":14, \"comment\":\"企業さんには挨拶をしましょう\" }]"
        Log.d("opal", "読み込んだもの：${uniJson}")

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
            userDB.addRecordUni(id,name, year, month, day, comment)
            Log.d("opal","insert:"+ name)
        }

    }

    fun insertEvent_student(){
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
            userDB.addRecordStudent(id,name, year, month, day, url)
            //Log.d("opal","insert:"+ userDB.getStudent())
        }

    }

    fun getHtml(url:String): String {
        val client = OkHttpClient()
        val req = Request.Builder().url(url).get().build()
        val resp = client.newCall(req).execute()
        lectureJson = resp.body()!!.string()
        return resp.body()!!.toString()
    }
}

