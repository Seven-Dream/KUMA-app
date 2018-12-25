package com.seven_dream.kuma

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
        db?.execSQL(Lecture)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // バージョンが変わった時に実行される
        db?.execSQL("DROP TABLE IF EXISTS lecture ;")
        onCreate(db)
        // 今回は,一度消して、作り直ししてます　
    }
    companion object {
        private val Lecture = "CREATE TABLE lecture ( " +
                //"lecture_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lecture_id INTEGER PRIMARY KEY, " +
                "lecture_name VARCHAR(64), " +
                "teacher VARCHAR(64), " +
                "classroom VARCHAR(64), " +
                "year INTEGER, " +
                "quarter INTEGER )"

        private val period = "CREATE TABLE lecture_period ( " +
                "lecture_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "period INTEGER " +
                "FOREIGN KEY (lecture_id) REFERENCES lecture (lecture_id))"

        private val week = "CREATE TABLE lecture_period ( " +
                "lecture_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "week INTEGER " +
                "FOREIGN KEY (lecture_id) REFERENCES lecture (lecture_id) )"

        private val test = "CREATE TABLE lecture_period ( " +
                "lecture_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "month INTEGER " +
                "day INTEGER " +
                "classroom VARCHAR(64) " +
                "comment TEXT " +
                "FOREIGN KEY (lecture_id) REFERENCES lecture (lecture_id) )"


        private val DB_VERSION = 1
        private val DB_NAME = "KUMA_DB"
        internal val DB_TABLE_NAME = "sampletable"
    }

}
