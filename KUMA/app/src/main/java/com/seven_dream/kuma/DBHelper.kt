package com.seven_dream.kuma

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

var oneDate: String? = null//Scheduleから取得した日付
//var plansId: Int =1

class DBHelper(context:Context):SQLiteOpenHelper(context, DATABESE_NAME, null, DATABESE_VER) {


    companion object {
        private val DATABESE_VER = 1
        private val DATABESE_NAME = "SAMPLEDB.db"
        private val TABLE_NAME ="NewPlan"
        private val id ="id"
        private val date="date"
        private val title ="title"
        private val timebegin ="timebegin"
        private val timeend ="timeend"
        private val place ="place"
        private val memo = "memo"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val plans:String = ("CREATE TABLE NewPlan (" +
                "id INTEGER PRIMARY KEY, " +
                "date VARCHAR(11), " +
                "title VARCHAR(20), " +
                "timebegin VARCHAR(5), " +
                "timeend VARCHAR(5), " +
                "place VARCHAR(20), " +
                "memo VARCHAR(30)" +
                ");")
        db!!.execSQL(plans)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    // 配列を作成し、get()でテーブル内のデータを格納していく
    val allNewPlan:List<NewPlan>//ここに飛んでくる

    get() {
        val lstPlans = ArrayList<NewPlan>()
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $date = ?"
        val db =this.writableDatabase
        val cursor = db.rawQuery(selectQuery, arrayOf(oneDate))
        if(cursor.moveToFirst()) {
            do {
                val plans = NewPlan()
                plans.id = cursor.getInt(cursor.getColumnIndex(id))
                plans.date = cursor.getString(cursor.getColumnIndex(date))
                plans.title = cursor.getString(cursor.getColumnIndex(title))
                plans.timebegin = cursor.getString(cursor.getColumnIndex(timebegin))
                plans.timeend = cursor.getString(cursor.getColumnIndex(timeend))
                plans.place = cursor.getString(cursor.getColumnIndex(place))
                plans.memo = cursor.getString(cursor.getColumnIndex(memo))

                lstPlans.add(plans)
            }while(cursor.moveToNext())
        }
        db.close()
        return lstPlans
    }


    fun addPlans(plans:NewPlan) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", plans.id)
        values.put("date", plans.date)
        values.put("title", plans.title)
        values.put("timebegin", plans.timebegin)
        values.put("timeend", plans.timeend)
        values.put("place", plans.place)
        values.put("memo", plans.memo)

        try {
            db.insert(TABLE_NAME, null, values)
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed >executeSQL SQLite -- " + e.message)
        }
        db.close()
    }

    fun updatePlans(plans:NewPlan) :Int{
        val db = this.writableDatabase
        //getId(plans.date.toString(), plans.title.toString())
        val values = ContentValues()
        values.put("id", plans.id)
        values.put("date", plans.date)
        values.put("title", plans.title)
        values.put("timebegin", plans.timebegin)
        values.put("timeend", plans.timeend)
        values.put("place", plans.place)
        values.put("memo", plans.memo)
        try {
            return db.update(TABLE_NAME, values, "$id=?", arrayOf(plans.id.toString()))
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed >executeSQL SQLite -- " + e.message)
            return 0
        }

    }

    fun deletePlans(plans:NewPlan) {
        val db = this.writableDatabase
        try {
            db.delete(TABLE_NAME,"$id=?", arrayOf(plans.id.toString()))
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed >executeSQL SQLite -- " + e.message)
        }


        db.close()
    }

    // テーブル内のデータをカウントし、idとしてreturnする
    fun getId(): Long {
        val db = this.writableDatabase
        val recordcount = DatabaseUtils.queryNumEntries(db, TABLE_NAME)

        /*横田さんの解決策用
        val selectQuery = "SELECT max(id) FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        var max = 0
        try{
            if (cursor.moveToNext())
                max = cursor.getInt(0)
        } finally {
            cursor.close()
        }
        */
        return recordcount

    }

    fun isRecord(id:Int) :Boolean {
        val db = this.writableDatabase
        val selectQql : String = "SELECT id FROM " + TABLE_NAME + " where id = ?"
        val cursor: Cursor = db.rawQuery(selectQql, arrayOf(id.toString()))
        var count = 0
        if(cursor.moveToFirst()) {
             count = cursor.getInt(cursor.getColumnIndex("id"))
        }
        cursor.close()
        return if ( 0 < count ) { true } else { false }
    }

    // DBHelperで日付を使えるようにする
    fun getDate(date:String){
        oneDate = date
    }

}