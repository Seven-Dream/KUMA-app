package com.seven_dream.kuma

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.text.Editable
import android.util.Log

class DB_Adapter_Search_Timetable(mContext: Context) {
    private val db: SQLiteDatabase
    private val uaerDB: userDB_Helper

    init {
        uaerDB = userDB_Helper(mContext)  // DB生成
        db = uaerDB.getWritableDatabase()
    }

    //---------------------insert文---------------------------
    //Lecture----------------------
    //lectureにレコードを追加
    fun addRecordLecture(
        lecture_id: Int,
        lecture_name: String,
        teacher: String,
        classroom: String,
        year: Int,
        quarter: Int
    ) {
        val values = ContentValues()
        values.put("lecture_id", lecture_id)
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
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }

    //Timetable-------------------------
    //timetableにレコードを追加 (登録動作がされた講義の講義情報をTimetableテーブルに格納)
    fun addRecordTimetable(
        lecture_id: Int,
        lecture_name: String,
        teacher: String,
        classroom: String,
        year: Int,
        quarter: Int
    ) {
        val values = ContentValues()
        values.put("lecture_id", lecture_id)
        values.put("lecture_name", lecture_name)
        values.put("teacher", teacher)
        values.put("classroom", classroom)
        values.put("year", year)
        values.put("quarter", quarter)
        //データの追加
        //Log.d("opal","前"+values.toString())
        try {
            db.insertOrThrow("timetable", null, values)
            //Log.d("opal","後"+values.toString())
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }

    /* 実際の実装には不要 */
    //lecture_period_weekにレコードを追加
    fun addRecordLecturePeriodWeek(
        lecture_id: Int,
        period: Int,
        week: Int
    ) {
        val values = ContentValues()
        values.put("lecture_id", lecture_id)
        values.put("period", period)
        values.put("week", week)
        //データの追加
        //Log.d("opal","前"+values.toString())
        try {
            db.insertOrThrow("lecture_period_week", null, values)
            //Log.d("opal","後"+values.toString())
        } catch (e: SQLiteException) {
            Log.d("opal", "Failed executeSQL SQLite -- " + e.message)
        }
    }

    //-------------------Select文-------------------
    //Lecture-------------------------
    /* 講義IDを取得 */
    //lectureIDをとってくる
    fun getLecture_id(lecture_name: String, teacher: String, classroom: String, year: Int, quarter: Int): Int {
        val selectSql: String =
            "select * from lecture where lecture_name = ? and teacher = ? and classroom = ? and year = ? and quarter = ?"
        val cursor: Cursor = db.rawQuery(
            selectSql,
            arrayOf(
                lecture_name,
                teacher,
                classroom,
                year.toString(),
                quarter.toString()
            )
        )
        var disp = 0//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getInt(cursor.getColumnIndex("lecture_id"))//Selectした結果の一文から、lecture_idという列名を取得
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //quarterを指定してlectureIDを取得
    fun getLectureIdByQuarter(quarter: Int?, num: Int): Int {
        val selectSql: String = "select lecture_id from lecture where quarter = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(quarter.toString()))
        //Log.d("opal",cursor.toString())
        var id: Int = 0 //最終的に表示
        try {
            for (i in 0..num) {
                if (cursor.moveToNext()) {
                    id = cursor.getInt(0)
                }
            }
        } finally {
            cursor.close()
        }
        return id
    }

    /* 講義の個数を取得 */
    //登録されている講義のうち、pulldownで選択された開講Qと一致する講義の個数を取得
    fun countLectureByQuarter(quarter: Int?): Int {
        val selectSql: String = "select * from lecture where quarter =?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(quarter.toString()))
        var cou: Int = 0
        try {
            cou = cursor.count
        } finally {
            cursor.close()
        }
        return cou
    }

    /* 講義IDの最大値の取得 */
    //引数なしで登録されている講義 IDの最大値を取得
    fun getMaxLecture(): Int {
        val selectSql = "select max(lecture_id) from lecture"
        val cursor: Cursor = db.rawQuery(selectSql, null)
        var max = 0
        try {
            if (cursor.moveToNext()) {
                max = cursor.getInt(0)//0番目にあるデータ=max(lecture_id)
            }
        } finally {
            cursor.close()
        }
        return max
    }

    /* 講義IDを引数に講義に関する情報を取得 */
    //講義IDを指定して講義名を取得
    fun getLectureNameById(lecture_id: Int): String {
        val selectSql: String = "select lecture_name from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(0)
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //講義IDを指定して教員名を取得
    fun getTeacherNameById(lecture_id: Int): String {
        val selectSql: String = "select teacher from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(0)
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //講義IDを指定して教室を取得
    fun getClasroomById(lecture_id: Int): String {
        val selectSql: String = "select classroom from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(0)
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //講義IDを指定して開講クウォータを取得
    fun getQuarterById(lecture_id: Int): Int {
        val selectSql: String = "select quarter from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: Int =0 //最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //講義IDを指定して開講年度を取得
    fun getYearById(lecture_id: Int): Int {
        val selectSql: String = "select year from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: Int =0 //最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }
        return disp
    }


    //Lecture_period_week---------------
    //講義IDを指定して曜日を取得
    fun getWeekById(lecture_id: Int): Int {
        val selectSql: String = "select week from lecture_period_week where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: Int =0 //最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //講義IDを指定して時限を取得
    fun getPeriodById(lecture_id: Int): Int {
        val selectSql: String = "select period from lecture_period_week where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: Int =0 //最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }
        return disp
    }
    //なんか
    //講義IDを指定して開講クウォータを取得
    fun getLectureById(lecture_id: Int): String {
        val selectSql: String = "select teacher from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp  = "" //最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(0)
            }
        } finally {
            cursor.close()
        }
        return disp
    }
    //timetableに入ってるIDを取得
    fun getTimetableId(cont: Int): Int{
        val selectSql: String = "select lecture_id from timetable"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf())
        //Log.d("opal",cursor.toString())
        var id = 0 //最終的に表示
        try {
            for (i in 0..cont) {
                if (cursor.moveToNext()) {
                    id = cursor.getInt(0)
                }
            }
        } finally {
            cursor.close()
        }
        return id
    }
    //timetableに入ってるレコード数を取得
    fun getTimetableRecordCount(): Int{
        val selectSql: String = "select lecture_id from timetable"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf())
        return cursor.count
    }
}

