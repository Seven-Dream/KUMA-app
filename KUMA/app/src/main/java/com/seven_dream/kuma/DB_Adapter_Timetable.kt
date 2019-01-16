package com.kuma.timetable


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log

class userDB_Adapter_Timetable(mContext: Context) {
    private val db: SQLiteDatabase
    private val uaerDB : userDB_Helper
    init {
        uaerDB = userDB_Helper(mContext)  // DB生成
        db = uaerDB.getWritableDatabase()
    }
    //---------------------insert文---------------------------
    //Timetableにレコードを追加
    fun addRecordTimetable(lecture_id:Int,lecture_name:String,
                           teacher:String, classroom:String, year:Int, quarter: Int) {
        val values = ContentValues()
        values.put("lecture_id",lecture_id)
        values.put("lecture_name", lecture_name)
        values.put("teacher", teacher)
        values.put("classroom", classroom)
        values.put("year", year)
        values.put("quarter", quarter)
        //データの追加
        Log.d("opal","前"+values.toString())
        try {
            db.insertOrThrow("timetable", null, values)
            //Log.d("opal","後"+values.toString())
        }catch(e: SQLiteException){
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }

    fun addRecordWeek(lecture_id:Int,week:Int, period:Int) {
        val values = ContentValues()
        values.put("lecture_id",lecture_id)
        values.put("week", week)
        values.put("period", period)

        //データの追加
        Log.d("opal","前"+values.toString())
        try {
            db.insertOrThrow("lecture_period_week", null, values)
            //Log.d("opal","後"+values.toString())
        }catch(e: SQLiteException){
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }
    //-------------------Select文-------------------
    //year,quarter,period,weekをもとにlectureIDをとってくる
    fun getLecture_id(year: Int, quarter: Int,period: Int,week: Int): Int {
        val selectSql: String = "select timetable.lecture_id from timetable, lecture_period_week where timetable.lecture_id = lecture_period_week.lecture_id and timetable.year = ? and timetable.quarter = ? and lecture_period_week.period = ? and lecture_period_week.week = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(year.toString(), quarter.toString(), period.toString(), week.toString()))
        var id: Int = 0//IDをこの中に入れる
        try {
            if (cursor.moveToNext()) {
                id = cursor.getInt(cursor.getColumnIndex("lecture_id"))//列名が「lectureID」の列番号を取得して、getStringで列番号に対応する文字を取得
            }
        } finally {
            cursor.close()
        }
        return id
    }
    //lecture_IDをもとに講義名を取得する
    fun getLecture_name(id: Int): String {
        val selectSql: String = "select lecture_name from timetable where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(id.toString()))
        var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(cursor.getColumnIndex("lecture_name"))
            }
        } finally {
            cursor.close()
        }
        return disp
    }
    fun getClassroom(id: Int): String {
        val selectSql: String = "select classroom from timetable where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(id.toString()))
        var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(cursor.getColumnIndex("classroom"))
            }
        } finally {
            cursor.close()
        }
        return disp
    }
    fun getTeacher(id: Int): String {
        val selectSql: String = "select teacher from timetable where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(id.toString()))
        var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(cursor.getColumnIndex("teacher"))
            }
        } finally {
            cursor.close()
        }
        return disp
    }
    //lecture_nameを指定して一列を取得
    fun getLecture(lecture_name:String) :String{
        val selectSql : String = "select * from lecture where lecture_name = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_name.toString()))
        //Log.d("opal",cursor.toString())
        var disp : String = ""//最終的に表示
        var rowdata : String = ""
        try{
            if(cursor.moveToNext()) {
                //disp = cursor.getString(cursor.getColumnIndex("teacher"))//教師名のみ
                for (i in 0 .. 5) {
                    rowdata += cursor.getString(i).toString() + " , "
                }
                disp += rowdata + "\n"
            }
        } finally{
            cursor.close()
        }
        return disp
    }
    //lecture_nameを指定して一列を取得
    fun getQuarter(id:Int) :Int{
        val selectSql : String = "select quarter from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(id.toString()))
        var id: Int = 0//IDをこの中に入れる
        try {
            if (cursor.moveToNext()) {
                id = cursor.getInt(cursor.getColumnIndex("lecture_id"))//列名が「lectureID」の列番号を取得して、getStringで列番号に対応する文字を取得
            }
        } finally {
            cursor.close()
        }
        return id
    }
    // キー(Type,date)を指定してmemoを修正
    /*
    fun updateMemo(type : Int, day : Int, memo : String ) {
        val values : ContentValues = ContentValues()
        values.put("memo",memo)
        // 第二引数がupdateする条件
        // 第三引数の? に第四引数が置き換わる
        db.update(DB_TABLE_NAME, values, "type=? AND date=? ", arrayOf(type.toString(),day.toString()))
    }*/
    //Lecture_idをもとに、対応するIDのレコードを削除
    fun deleteTimetable(id:Int){
        db.delete("timetable", "lecture_id = ?", arrayOf(id.toString()))
    }
    fun deleteWeek_Period(id:Int){
        db.delete("lecture_period_week", "lecture_id = ?", arrayOf(id.toString()))
    }


    // キーを指定し、１レコード削除
    /*
    fun deleteRecord(type : Int, day : Int) {
        db.delete(DB_TABLE_NAME, "tepe=? AND date=? ", arrayOf(type.toString(),day.toString()))
    }*/
}