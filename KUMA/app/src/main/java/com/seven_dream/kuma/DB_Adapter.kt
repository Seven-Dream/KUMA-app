package com.seven_dream.kuma

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class userDB_Adapter(mContext: Context) {
    private val db: SQLiteDatabase
    private val uaerDB : userDB_Helper
    init {
        uaerDB = userDB_Helper(mContext)  // DB生成
        db = uaerDB.getWritableDatabase()
    }

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
        db.insertOrThrow("lecture", null, values)
        //Log.d("opal","後"+values.toString())
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