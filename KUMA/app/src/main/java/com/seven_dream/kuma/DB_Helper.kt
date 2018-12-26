package com.example.androiddev.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

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
        db?.execSQL(Period_Week)
        db?.execSQL(Test)
        db?.execSQL(Cancel)
        db?.execSQL(Change)
        db?.execSQL(Uni)
        db?.execSQL(Student)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // バージョンが変わった時に実行される
        Log.d("opal","onUpgrade")
        db?.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME + ";")
        db?.execSQL("DROP TABLE IF EXISTS lecture ;")
        db?.execSQL("DROP TABLE IF EXISTS lecture_period_week ;")
        db?.execSQL("DROP TABLE IF EXISTS lecture_test ;")
        db?.execSQL("DROP TABLE IF EXISTS lecture_cancel ;")
        db?.execSQL("DROP TABLE IF EXISTS lecture_change_class ;")
        db?.execSQL("DROP TABLE IF EXISTS event_uni ;")
        db?.execSQL("DROP TABLE IF EXISTS event_student ;")
        onCreate(db)
        // 今回は,一度消して、作り直ししてます

    }
    companion object {
        private const val Lecture = "CREATE TABLE lecture ( " +
                //"lecture_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lecture_id INTEGER PRIMARY KEY, " +
                "lecture_name VARCHAR(64), " +
                "teacher VARCHAR(64), " +
                "classroom VARCHAR(64), " +
                "year INTEGER, " +
                "quarter INTEGER );"

        private const val Period_Week = "CREATE TABLE lecture_period_week ( " +
                "lecture_id INTEGER PRIMARY KEY, " +
                "period INTEGER, " +
                "week INTEGER, " +
                "FOREIGN KEY (lecture_id) REFERENCES lecture (lecture_id));"

        private val Test = "CREATE TABLE lecture_test ( " +
                "lecture_id INTEGER PRIMARY KEY, " +
                "month INTEGER, " +
                "day INTEGER, " +
                "classroom VARCHAR(64), " +
                "comment TEXT, " +
                "FOREIGN KEY (lecture_id) REFERENCES lecture (lecture_id) );"

        private val Cancel = "CREATE TABLE lecture_cancel ( " +
                "lecture_id INTEGER PRIMARY KEY, " +
                "month INTEGER, " +
                "day INTEGER, " +
                "comment TEXT, " +
                "FOREIGN KEY (lecture_id) REFERENCES lecture (lecture_id) );"

        private val Change = "CREATE TABLE lecture_change_class ( " +
                "lecture_id INTEGER PRIMARY KEY, " +
                "month INTEGER, " +
                "day INTEGER, " +
                "classroom VARCHAR(64), " +
                "FOREIGN KEY (lecture_id) REFERENCES lecture (lecture_id) );"

        private val Uni = "CREATE TABLE event_uni ( " +
                "id INTEGER PRIMARY KEY, " +
                "event_name VARCHAR(64), " +
                "year INTEGER, " +
                "month INTEGER, " +
                "day INTEGER, " +
                "comment TEXT );"

        private val Student = "CREATE TABLE event_student ( " +
                "id INTEGER PRIMARY KEY, " +
                "event_name VARCHAR(64), " +
                "year INTEGER, " +
                "month INTEGER, " +
                "day INTEGER, " +
                "url VARCHAR(64) );"

        private val DB_VERSION = 1
        private val DB_NAME = "sampleDB"
        internal val DB_TABLE_NAME = "sampletable"
    }

}