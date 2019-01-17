package com.example.seven.kuma

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import android.view.ContextThemeWrapper
// import com.example.androiddev.myapplication.userDB_Helper.Companion.DB_TABLE_NAME
//import com.example.hanm1_100.kuma.userDB_Helper.Companion.DB_TABLE_NAME

class userDB_Adapter_Event(mContext: Context) {


    private val db: SQLiteDatabase
    private val userDB: userDB_Helper

    init {
        userDB = userDB_Helper(mContext)    // DB生成
        db = userDB.getWritableDatabase()
    }

    // insert文
    // event_studentにレコードを追加
    fun addRecordEventStudent(id: Int, event_name: String, year: Int, month: Int, day: Int,  url: String) {
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
    //eventIDをとってくる
    /*
    fun getEvent_id(year: Int, month: Int, day: Int): Int {
        val selectSql: String = "select id from event_student where year = ? and month = ? and day = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(year.toString(), month.toString(), day.toString()))
        var id: Int = 0//IDをこの中に入れる
        //var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                id = cursor.getInt(cursor.getColumnIndex("id"))//列名が「eventID」の列番号を取得して、getStringで列番号に対応する文字を取得
            }
        } finally {
            cursor.close()
        }
        return id
    }
    */
    fun getEvent_id(year: Int, month: Int, day: Int, next: Int): Int {
        val selectSql: String = "select id from event_student where year = ? and month = ? and day = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(year.toString(), month.toString(), day.toString()))
        var id: Int = 0//IDをこの中に入れる
        //var disp: String = ""//最終的に表示
        var flag = 0
        try {
/*
            while (cursor.moveToNext()) {
                if(tmp < next) {
                    id = cursor.getInt(cursor.getColumnIndex("id"))//列名が「eventID」の列番号を取得して、getStringで列番号に対応する文字を取得
                }
                tmp += 1
            }*/
            for(i in 1..next) {
                if(!cursor.moveToNext()) flag = 1//最後まで見終わっていた
            }
            if (flag == 0) id = cursor.getInt(cursor.getColumnIndex("id"))//列名が「eventID」の列番号を取得して、getStringで列番号に対応する文字を取得
        } finally {
            cursor.close()
        }
        return id
    }

    /*fun getEvent_year(id: Int): Int {
        val selectSql: String = "select year from event_student where id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(id.toString()))
        var disp: Int = 0//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getInt(cursor.getColumnIndex("year"))
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    fun getEvent_month(id: Int): Int {
        val selectSql: String = "select month from event_student where id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(id.toString()))
        var disp: Int = 0//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getInt(cursor.getColumnIndex("month"))
            }
        } finally {
            cursor.close()
        }
        return disp
    }*/

    fun getEvent_day(id: Int): Int {
        val selectSql: String = "select day from event_student where id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(id.toString()))
        var disp: Int = 0//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getInt(cursor.getColumnIndex("day"))
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    fun getEvent_name(id: Int): String {
        val selectSql: String = "select event_name from event_student where id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(id.toString()))
        var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(cursor.getColumnIndex("event_name"))
            }
        } finally {
            cursor.close()
        }
        return disp
    }
}

/*
fun getEventStudent(id:Int): String {
    val selectSql: String = "select * from event_student where event_id = ?"
    try {
        val cursor: Cursor = db.rawQuery(selectSql, null)
        var disp: String = ""
        try {
            if(cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val year = cursor.getInt(cursor.getColumnIndex("year"))
                    val month = cursor.getInt(cursor.getColumnIndex("month"))
                    val day = cursor.getInt(cursor.getColumnIndex("day"))
                    val name = cursor.getString(cursor.getColumnIndex("event_name"))
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
*/
