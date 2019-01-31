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
var years: String? = null
var months: String? = null
var dayOfMonths: String? = null
//var plansId: Int =1

class DBHelper(context:Context):SQLiteOpenHelper(context, DATABESE_NAME, null, DATABESE_VER) {


    //internal lateinit var uaerDB : userDB_Helper
    private val db1: SQLiteDatabase
    private val uaerDB : userDB_Helper = userDB_Helper(context)

    //internal lateinit var uaerDB: userDB_Helper
    init {
        // DB生成
       db1 = uaerDB.getWritableDatabase()
        //db1 = uaerDB.writableDatabase
    }

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
        private val lecture_id = "lecture_id"
        private val classroom = "classroom"
        private val comment = "comment"
        private val event_name = "event_name"
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
        try{
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
    } catch (e: SQLiteException) {
        Log.d("opal", "Failed >executeSQL SQLite -- " + e.message)
    }
        //db.close()
        val selectQuery1 = "SELECT * FROM lecture_test WHERE month=? and day =?"
        //val db1 =db1.writableDatabase
       // val db1 = uaerDB.writableDatabase
        //try{
        val kyou =  dayOfMonths
        val cursor1 = db1.rawQuery(selectQuery1, arrayOf("1", kyou))
        var count = 99
        count +=1
        try{
        if(cursor1.moveToFirst()) {

                // NewPlan構造体に格納
            do {
                count +=1
                val plans = NewPlan()
                plans.id = count
                plans.date = date
                plans.title = cursor1.getString(cursor1.getColumnIndex(lecture_id))
                plans.timebegin =""
                plans.timeend = ""
                plans.place = cursor1.getString(cursor1.getColumnIndex(classroom))
                plans.memo = cursor1.getString(cursor1.getColumnIndex(comment))


                lstPlans.add(plans)
            }while(cursor1.moveToNext())
        }
    } catch (e: SQLiteException) {
        Log.d("opal", "Failed >executeSQL SQLite -- " + e.message)
    }
        val selectQuery2 = "SELECT * FROM lecture_cancel WHERE month=? and day =?"
        //val db1 =db1.writableDatabase
        val cursor2 = db1.rawQuery(selectQuery2, arrayOf("1", kyou))
        try{
        if(cursor2.moveToFirst()) {
            do {
                val plans = NewPlan()
                plans.id = 0
                plans.date = date
                plans.title = cursor2.getString(cursor2.getColumnIndex(lecture_id))
                plans.timebegin =""
                plans.timeend = ""
                plans.place = ""
                plans.memo = cursor2.getString(cursor2.getColumnIndex(comment))

                lstPlans.add(plans)
            }while(cursor2.moveToNext()

            )
        }
    } catch (e: SQLiteException) {
        Log.d("opal", "Failed >executeSQL SQLite -- " + e.message)
    }
        val selectQuery3 = "SELECT * FROM lecture_change_class WHERE month=? and day =?"
        //val db1 =db1.writableDatabase
        val cursor3 = db1.rawQuery(selectQuery3, arrayOf("1", "31"))
        try{
        if(cursor3.moveToFirst()) {
            do {
                val plans = NewPlan()
                plans.id = 0
                plans.date = date
                plans.title = cursor3.getString(cursor3.getColumnIndex(lecture_id))
                plans.timebegin =""
                plans.timeend = ""
                plans.place = cursor3.getString(cursor3.getColumnIndex(classroom))
                plans.memo = ""

                lstPlans.add(plans)
            }while(cursor3.moveToNext())
        }
    } catch (e: SQLiteException) {
        Log.d("opal", "Failed >executeSQL SQLite -- " + e.message)
    }

        val selectQuery4 = "SELECT * FROM event_uni WHERE year = ? and month = ? and day =?"
        //val db1 =db1.writableDatabase
        val cursor4 = db1.rawQuery(selectQuery4, arrayOf("2019", "1", oneDate))
        try {
        if(cursor4.moveToFirst()) {
            do {
                val plans = NewPlan()
                plans.id = count
                plans.date = date
                plans.title = cursor4.getString(cursor4.getColumnIndex(event_name))
                plans.timebegin =""
                plans.timeend = ""
                plans.place = ""
                plans.memo = cursor.getString(cursor4.getColumnIndex(comment))

                lstPlans.add(plans)
            }while(cursor4.moveToNext())
        }
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed >executeSQL SQLite -- " + e.message)
        }


        db1.close()
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
    fun getDate1(year:String,month:String, dayOfMonth:String){
        years = year
        months = month
        dayOfMonths =dayOfMonth
    }

}