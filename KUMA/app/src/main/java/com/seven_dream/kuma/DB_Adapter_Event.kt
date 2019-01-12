package com.example.kawak.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import android.view.ContextThemeWrapper
// import com.example.androiddev.myapplication.userDB_Helper.Companion.DB_TABLE_NAME
import com.example.kawak.myapplication.userDB_Helper.Companion.DB_TABLE_NAME

class userDB_Adapter_Event(mContext: Context) {
    private val db:SQLiteDatabase
    private val userDB : userDB_Helper
    init {
        userDB = userDB_Helper(mContext)    // DB生成
        db = userDB.getWritableDatabase()
    }

    // insert文
    // event_studentにレコードを追加
    fun addRecordEventStudent(id:Int, event_name:String, year:Int, month:Int, day:Int, url:String) {
        val values = ContentValues()
        values.put("id", id)
        values.put("event_name", event_name)
        values.put("year", year)
        values.put("month", month)
        values.put("day", day)
        values.put("url", url)
        // データの追加
        Log.d("opal", "前" + values.toString())
        try {
            // insertOrThrow()
            // 第1引数はテーブル名
            // 第2引数はデータを挿入する際にnull値が許可されていないカラムに代わりに利用される値を指定(?)
            // 第3引数は ContentValue(データ)
            db.insertOrThrow("event_student", null, values)
        } catch (e: SQLException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }

    // select文
    // event_studentを
    fun getEventStudent(): String {
        val selectSql: String = "select * from event_student"
        try {
            val cursor: Cursor = db.rawQuery(selectSql, null)
            var disp: String = ""
            try {
                if(cursor.moveToFirst()) {
                    do {
                        val id = cursor.getInt(cursor.getColumnIndex("id"))
                        val name = cursor.getString(cursor.getColumnIndex("event_name"))
                        val year = cursor.getInt(cursor.getColumnIndex("year"))
                        val month = cursor.getInt(cursor.getColumnIndex("month"))
                        val day = cursor.getInt(cursor.getColumnIndex("day"))
                        val url = cursor.getString(cursor.getColumnIndex("url"))
                        disp += "id:" + id.toString() + "," + "name:" + name.toString() + ", year:" + year.toString() + ", month: "+ month.toString() + ", day:" + day.toString() + "url:" + url.toString() + "\n"
                    } while(cursor.moveToNext())
                }
            } finally {
                cursor.close()
            }
            return disp
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
            return "Failed executeSQL SQLite -- " + e.message
        }
    }
}