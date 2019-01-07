package com.seven_dream.kuma

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.androiddev.myapplication.userDB_Helper

class userDB_Adapter(mContext: Context) {
    private val db: SQLiteDatabase
    private val uaerDB : userDB_Helper
        init {
            uaerDB = userDB_Helper(mContext)  // DB生成
            db = uaerDB.getWritableDatabase()
        }
//---------------------insert文---------------------------
    //Lecture----------------------
    //lectureにレコードを追加
    fun addRecordLecture(lecture_id:Int,lecture_name:String, teacher:String, classroom:String, year:Int, quarter:Int) {
        val values = ContentValues()
        values.put("lecture_id",lecture_id)
        values.put("lecture_name", lecture_name)
        values.put("teacher", teacher)
        values.put("classroom", classroom)
        values.put("year", year)
        values.put("quarter", quarter)
        //データの追加
        //Log.d("opal","前"+values.toString())
        try {
            db.insertOrThrow("lecture", null, values)
            //Log.d("opal","後"+values.toString())
        }catch(e: SQLiteException){
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }

    //Period_Weekにレコードを追加
    fun addRecordPeriod_Week(lecture_id: Int, week: Int, period:Int) {
        val values = ContentValues()
        values.put("lecture_id", lecture_id)
        values.put("week", week)
        values.put("period",period)
        //データの追加
        Log.d("opal", "前" + values.toString())
        try {
            db.insert("lecture_period_week", null, values)
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }
    //lecture_testにレコードを追加
    fun addRecordTest(lecture_id: Int, month:Int, day:Int, classroom:String, comment:String) {
        val values = ContentValues()
        values.put("lecture_id", lecture_id)
        values.put("month", month)
        values.put("day",day)
        values.put("classroom", classroom)
        values.put("comment", comment)
        //データの追加
        //Log.d("opal", "前" + values.toString())
        try {
            db.insert("lecture_test", null, values)
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }
    //lecture_cancelにレコードを追加
    fun addRecordCancel(lecture_id: Int, month:Int, day:Int, comment:String) {
        val values = ContentValues()
        values.put("lecture_id", lecture_id)
        values.put("month", month)
        values.put("day",day)
        values.put("comment", comment)
        //データの追加
        //Log.d("opal", "前" + values.toString())
        try {
            db.insert("lecture_cancel", null, values)
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }
    //lecture_change_classにレコードを追加
    fun addRecordChange_Class(lecture_id: Int, month:Int, day:Int, classroom:String) {
        val values = ContentValues()
        values.put("lecture_id", lecture_id)
        values.put("month", month)
        values.put("day",day)
        values.put("classroom", classroom)
        //データの追加
        //Log.d("opal", "前" + values.toString())
        try {
            db.insert("lecture_change_class", null, values)
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }
    //Event_Uni-----------------------------------------
    //event_studentにレコードを追加
    fun addRecordUni(id:Int, name:String, year:Int, month:Int, day:Int, comment: String) {
        val values = ContentValues()
        values.put("id", id)
        values.put("event_name", name)
        values.put("year", year)
        values.put("month", month)
        values.put("day",day)
        values.put("comment",comment)
        //データの追加
        Log.d("opal", "前" + values.toString())
        try {
            db.insert("event_uni", null, values)
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }
    //Event_Student-------------------------------------
    //event_studentにレコードを追加
    fun addRecordStudent(id:Int, name:String, year:Int, month:Int, day:Int, url:String) {
        val values = ContentValues()
        values.put("id", id)
        values.put("event_name", name)
        values.put("year", year)
        values.put("month", month)
        values.put("day",day)
        values.put("url",url)
        //データの追加
        Log.d("opal", "前" + values.toString())
        try {
            db.insert("event_student", null, values)
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }
//-------------------Select文-------------------
    //Lecture-------------------------
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
    //lectureIDをとってくる
    fun getLecture_id(lecture_name: String, teacher: String, classroom: String, year: Int, quarter: Int): String {
        val selectSql: String = "select * from lecture where lecture_name = ? and teacher = ? and classroom = ? and year = ? and quarter = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_name.toString(), teacher.toString(), classroom.toString(), year.toString(), quarter.toString()))
        var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(cursor.getColumnIndex("lecture_id"))//教師名のみ
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //現状でのweekテーブルを全て表示
    fun getWeek(): String {
        val selectSql: String = "select * from lecture_period_week"
        //Log.d("opal", "check")
        try {
            val cursor: Cursor = db.rawQuery(selectSql, null)
            var disp: String = ""//最終的に表示
            try {
                if(cursor.moveToFirst()){
                    do{
                        val id = cursor.getInt(cursor.getColumnIndex("lecture_id"))
                        val week = cursor.getInt(cursor.getColumnIndex("week"))
                        val period = cursor.getInt(cursor.getColumnIndex("period"))
                        disp += id.toString() + "," + week.toString() + "," + period.toString() + "\n"
                    }while(cursor.moveToNext())
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
    //Event_Uni------------------------------------

    //Event_Student--------------------------------
    fun getStudent(): String {
        val selectSql: String = "select * from event_student"
        try {
            val cursor: Cursor = db.rawQuery(selectSql, null)
            var disp: String = ""//最終的に表示
            try {
                if(cursor.moveToFirst()){
                    do{
                        val id = cursor.getInt(cursor.getColumnIndex("id"))
                        val name = cursor.getString(cursor.getColumnIndex("event_name"))
                        val year = cursor.getInt(cursor.getColumnIndex("year"))
                        val month = cursor.getInt(cursor.getColumnIndex("month"))
                        val day = cursor.getInt(cursor.getColumnIndex("day"))
                        val url = cursor.getString(cursor.getColumnIndex("url"))
                        disp += "id:" + id.toString() + "," + "name:" + name.toString() + ", year:" + year.toString() + ", month: "+ month.toString() + ", day:" + day.toString() + "url:" + url.toString() + "\n"
                    }while(cursor.moveToNext())
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

    // キー(Type,date)を指定してmemoを修正
    /*
    fun updateMemo(type : Int, day : Int, memo : String ) {
        val values : ContentValues = ContentValues()
        values.put("memo",memo)
        // 第二引数がupdateする条件
        // 第三引数の? に第四引数が置き換わる
        db.update(DB_TABLE_NAME, values, "type=? AND date=? ", arrayOf(type.toString(),day.toString()))
    }*/

    // キーを指定し、１レコード削除
    /*
    fun deleteRecord(type : Int, day : Int) {
        db.delete(DB_TABLE_NAME, "tepe=? AND date=? ", arrayOf(type.toString(),day.toString()))
    }*/



}