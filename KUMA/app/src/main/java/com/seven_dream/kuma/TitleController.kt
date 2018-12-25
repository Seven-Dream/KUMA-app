package com.seven_dream.kuma

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.os.AsyncTask
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

var stringJson: String? = null//Jsonデータを入れるグローバル変数

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
            return getHtml()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            //Log.d("opal", stringJson)
            //val txt: TextView = findViewById(R.id.textView)
            //Jsonをパースする
            //stringJson=  "[{\"lecture_id\":\"1\",\"lecture_name\":\"データベース\",\"teachar\":\"横山\",\"classroom\":\"A106\",\"year\":2018,\"quarter\":3}]"
            stringJson ="[{\"lecture_id\":1,\"lecture_name\":\"データベース\",\"teacher\":\"横山\",\"classroom\":\"A106\",\"year\":2018,\"quarter\":3} , {\"lecture_id\":2,\"lecture_name\":\"オペレーティング\",\"teacher\":\"横山\",\"classroom\":\"A106\",\"year\":2018,\"quarter\":3}]"
            //val parentJsonObj = JSONObject(stringJson)//実際はこっち
            Log.d("opal", "読み込んだもの：$stringJson")
            val json = JSONArray(stringJson)//読み込んだJSONを配列として受け取る

            //lecture_id順にデータベースに挿入する
            for (i in 0..(json.length() - 1)) {
                val jsonObj = json.getJSONObject(i)
                val id = jsonObj.getInt("lecture_id")
                val name = jsonObj.getString("lecture_name")
                val teacher = jsonObj.getString("teacher")
                val classroom = jsonObj.getString("classroom")
                val year = jsonObj.getInt("year")
                val quarter = jsonObj.getInt("quarter")
                userDB.addRecordLecture(id,name, teacher, classroom, year, quarter)
                //Log.d("opal", id.toString() + ":" + userDB.getLecture(name))
            }
        }
    }
}


fun getHtml(): String {

    val client = OkHttpClient()
    val req = Request.Builder().url("https://www.nicovideo.jp").get().build()
    val resp = client.newCall(req).execute()
    stringJson = resp.body()!!.string()
    return resp.body()!!.toString()
}
