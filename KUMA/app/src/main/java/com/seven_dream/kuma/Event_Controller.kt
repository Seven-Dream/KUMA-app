package com.example.seven.kuma

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.os.AsyncTask
import android.util.Log
import com.example.hanm1_000.kuma.R
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

var eventJson: String? = null

class EventController : AppCompatActivity() {
    private lateinit var userDB_Event: userDB_Adapter_Event

    override fun onCreate(savedInstanceState: Bundle?) {
        userDB_Event = userDB_Adapter_Event(this)   // DB呼び出し

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_event_list)
        MyAsyncTask().execute()//APIからJSONを取得→データベース格納を行う
    }

    //API
    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTask : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg p0: Void?): String? {
            //getHtml("https://www.nicovideo.jp")
            //各グローバル変数にJSONデータを格納する
            //lectureJson = getHtml("http://172.21.34.153/json")
            getHtml()//ここですべてのグローバル変数にJSONを入れる
            Log.d("opall", "eventJson=$eventJson")
            return "true"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d("opal", "insert:Event_Student")
            insertEvent_student()//Event_Student
        }
    }

    //学生イベントテーブルの挿入
    private fun insertEvent_student() {
        eventJson = "[{\"id\":1, \"event_name\":\"軽音ライブ\", \"year\":2018, \"month\":4, \"day\":14, \"url\":\"https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf\" }" +
                ",{\"id\":2, \"event_name\":\"アカペラライブ\", \"year\":2018, \"month\":10, \"day\":26, \"url\":\"https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf\" }" +
               ",{\"id\":3, \"event_name\":\"ロボ研ガンダムファイト大会\", \"year\":2018, \"month\":12, \"day\":1, \"url\":\"https://www.neurology-jp.org/Journal/public_pdf/058010015.pdf\" }]"

        Log.d("opal", "読み込んだもの：$eventJson")

        val json = JSONArray(eventJson)
        //id自動加算をなくした
        for (i in 0..(json.length() - 1)) {
            //
            val jsonObj = json.getJSONObject(i)
            val id = jsonObj.getInt("id")
            val name = jsonObj.getString("event_name")
            val year = jsonObj.getInt("year")
            val month = jsonObj.getInt("month")
            val day = jsonObj.getInt("day")
            val url = jsonObj.getString("url")
            //userDB.addRecordLecture(id, name, teacher, classroom, year, quarter)
            userDB_Event.addRecordEventStudent( id, name, year, month, day,  url)
            //Log.d("opal","insert:"+ userDB.getStudent())
        }
    }

    private fun getHtml() {
        //resp.body()!!.string()は一回読み込むと消える
        val client = OkHttpClient()
        val req = Request.Builder().url("http://172.21.34.153/json").get().build()
        val resp = client.newCall(req).execute()
        eventJson = resp.body()!!.string()
        Log.d("opal", "EventJson=$eventJson")
    }
}