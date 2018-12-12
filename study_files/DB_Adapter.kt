package com.example.androiddev.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.androiddev.myapplication.userDB_Helper.Companion.DB_TABLE_NAME

class userDB_Adapter(mContext: Context) {
    private val db: SQLiteDatabase
    private val uaerDB : userDB_Helper
    init {
        uaerDB = userDB_Helper(mContext)  // DB生成
        db = uaerDB.getWritableDatabase()
    }

    // 指定 キーのデータあり?
    fun isRecord( type:Int ,day:Int ) :Boolean {
        val selectQql : String = "SELECT count(*) as cnt FROM " + DB_TABLE_NAME + " where type = ? and date= ? "

        val cursor: Cursor = db.rawQuery(selectQql, arrayOf(type.toString(),day.toString()))
        cursor.moveToFirst()
        val count = cursor.getInt(cursor.getColumnIndex("cnt"))
        cursor.close()
        return if ( 0 < count ) { true } else { false }
    }

    // １レコード 追加
    fun addRecord(type:Int , day:Int ,memo:String) {
    val values = ContentValues()
    values.put("memo", memo)
    values.put("date", day )
    values.put("type", type)

    // insertOrThrow()
    // 第1引数はテーブル名
    // 第2引数はデータを挿入する際にnull値が許可されていないカラムに代わりに利用される値を指定(?)
    // 第3引数は ContentValue(データ)
    db.insertOrThrow(DB_TABLE_NAME, null, values)
}

    // キー(Type,date)を指定してmemoを取得
    fun getMemo( type : Int, day : Int) : String {
        val selectQql : String = "select * from " + DB_TABLE_NAME + " where type = ? and date= ? "
        // 第一引数にある?の部分に
        // 第二引数が入る（複数可能、先に指定した順)
        val cursor: Cursor = db.rawQuery(selectQql, arrayOf(type.toString(),day.toString()))
        var Result : String = ""
        try {
            if (cursor.moveToNext()) {
                Result = cursor.getString(cursor.getColumnIndex("memo"))
            }
        } finally {
            cursor.close()
        }
        return Result
    }

    // キー(Type,date)を指定してmemoを修正
    fun updateMemo(type : Int, day : Int, memo : String ) {
        val values : ContentValues = ContentValues()
        values.put("memo",memo)
        // 第二引数がupdateする条件
        // 第三引数の? に第四引数が置き換わる
        db.update(DB_TABLE_NAME, values, "type=? AND date=? ", arrayOf(type.toString(),day.toString()))
    }

    // キーを指定し、１レコード削除
    fun deleteRecord(type : Int, day : Int) {
        db.delete(DB_TABLE_NAME, "tepe=? AND date=? ", arrayOf(type.toString(),day.toString()))
    }
}