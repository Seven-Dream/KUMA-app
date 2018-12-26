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
var studentJson: String? = null

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
        insertLecture() //Lecture
        insertEvent_student()
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

    fun insertEvent_student(){
        studentJson = "[{\"id\":1, \"event_name\":\"大学祭\", \"year\":2018, \"month\":10, \"day\":14, \"url\":\"https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf\" }" +
                ",{\"id\":2, \"event_name\":\"卒業式\", \"year\":2018, \"month\":10, \"day\":14, \"url\":\"https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf\" }" +
                ",{\"id\":3, \"event_name\":\"大学説明会\", \"year\":2018, \"month\":10, \"day\":14, \"url\":\"https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf\" }]"
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

