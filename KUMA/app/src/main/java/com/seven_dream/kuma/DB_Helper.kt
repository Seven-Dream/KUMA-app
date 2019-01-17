package com.example.seven.kuma

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// データベースの生成を管理する
class userDB_Helper (Context: Context) : SQLiteOpenHelper(Context, DB_NAME, null, DB_VERSION) {
    //  SQLiteOpenHelper
    // 第１引数 :
    // 第２引数 : データベースの名称
    // 第３引数 : null
    // 第４引数 : データベースのバージョン


    override fun onCreate(db: SQLiteDatabase?) {
        // テーブルがなかったときに が呼ばれる
        // execSQL で　クエリSQL文を実行 これでDBの構造が決定
        db?.execSQL(
            "CREATE TABLE " + DB_TABLE_NAME + " ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "event_name text not null, " +
                    "year integer not null, " +
                    "month integer not null, " +
                    "day integer not null, " +
                    "url text not null " +
                    ");")
        // db?.execSQL(event_student)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // バージョンが変わった時に実行される
        db?.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME + ";")
        onCreate(db);
        // 今回は,一度消して、作り直ししてます　
    }

    companion object {
        private val event_student = "CREATE TABLE event_student ( " +
                "id INTEGER PRIMARY KEY, " +
                "event_name VARCHAR(64), " +
                "year INTEGER, " +
                "month INTEGER, " +
                "day INTEGER, " +
                "url VARCHAR(64) )"

        private val DB_VERSION = 1
        private val DB_NAME = "KUMADB"
        internal val DB_TABLE_NAME = "event_student"
    }
}
