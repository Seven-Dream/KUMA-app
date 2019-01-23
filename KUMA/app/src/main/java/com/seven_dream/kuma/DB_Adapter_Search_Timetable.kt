package com.seven_dream.kuma

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.text.Editable
import android.util.Log
import com.example.androiddev.myapplication.userDB_Helper

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

    //-------------------Select文-------------------
    //Lecture-------------------------
    //lecture_nameを指定して一列を取得
    fun getLecture(lecture_name: String): String {
        val selectSql: String = "select * from lecture where lecture_name = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_name.toString()))
        //Log.d("opal",cursor.toString())
        var disp: String = ""//最終的に表示
        var rowdata: String = ""
        try {
            if (cursor.moveToNext()) {
                //disp = cursor.getString(cursor.getColumnIndex("teacher"))//教師名のみ
                for (i in 0..5) {
                    rowdata += cursor.getString(i).toString() + " , "
                }
                disp += rowdata + "\n"
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //lectureIDをとってくる
    fun getLecture_id(lecture_name: String, teacher: String, classroom: String, year: Int, quarter: Int): String {
        val selectSql: String =
            "select * from lecture where lecture_name = ? and teacher = ? and classroom = ? and year = ? and quarter = ?"
        val cursor: Cursor = db.rawQuery(
            selectSql,
            arrayOf(
                lecture_name.toString(),
                teacher.toString(),
                classroom.toString(),
                year.toString(),
                quarter.toString()
            )
        )
        var disp: String = ""//最終的に表示
        try {
            if (cursor.moveToNext()) {
                disp = cursor.getString(cursor.getColumnIndex("lecture_id"))//Selectした結果の一文から、lecture_idという列名を取得
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //lecture_nameとnumを指定してlectureIDを取得
    fun getLectureIdByName(lecture_name: Editable, max: Int): Int {
        val selectSql: String = "select lecture_id from lecture where lecture_name = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_name.toString()))
        //Log.d("opal",cursor.toString())
        var id: Int = 0//最終的に表示
        var count = 0
        try {
            if (cursor.moveToNext()) {
                if (count <= max) {
                    id = cursor.getInt(0)
                }
                count += 1
            }
        } finally {
            cursor.close()
        }
        return id
    }

    // lecture_nameを使って全講義のlectureIDを取得
    fun getAllLecture(): Int {
        //全講義の講義IDを取得
        val selectSql: String = "select lecture_id from lecture"
        val cursor: Cursor = db.rawQuery(selectSql, null)
        //Log.d("opal",cursor.toString())
        var id: Int = 0//最終的に表示
        try {
            if (cursor.moveToNext()) {
                id = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }
        return id
    }

    //teacharを指定してlectureIDを取得
    fun getLecture_idByTeach(teachar: Editable, num: Int): Int {
        val selectSql: String = "select lecture_id from lecture where teachar = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(teachar.toString()))
        //Log.d("opal",cursor.toString())
        var id: Int = 0//最終的に表示
        var count = 0
        try {
            if (cursor.moveToNext()) {
                if (count <= num) {
                    id = cursor.getInt(0)
                }
                count += 1
            }
        } finally {
            cursor.close()
        }
        return id
    }

    // teacharを使って全講義のlectureIDを取得
    fun getAllLectureByTeach(): Int {
        //全講義の講義IDを取得
        val selectSql: String = "select lecture_id from lecture"
        val cursor: Cursor = db.rawQuery(selectSql, null)
        //Log.d("opal",cursor.toString())
        var id: Int = 0//最終的に表示
        try {
            if (cursor.moveToNext()) {
                id = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }
        return id
    }

    //quarterを指定してlectureIDを取得
    fun getLectureIdByQuarter(quarter: Int?, num: Int): Int {
        val selectSql: String = "select lecture_id from lecture where quarter = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(quarter.toString()))
        //Log.d("opal",cursor.toString())
        var id: Int = 0//最終的に表示
        var count = 0
        try {
            if (cursor.moveToNext()) {
                if (count <= num) {
                    id = cursor.getInt(0)
                }
                count += 1
            }
        } finally {
            cursor.close()
        }
        return id
    }

    // quarterを使って全講義のlectureIDを取得
    fun getAllLectureByQuarter(): Int {
        //全講義の講義IDを取得
        val selectSql: String = "select lecture_id from lecture"
        val cursor: Cursor = db.rawQuery(selectSql, null)
        //Log.d("opal",cursor.toString())
        var id: Int = 0//最終的に表示
        try {
            if (cursor.moveToNext()) {
                id = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }
        return id
    }

    //登録されている 講義の個数を取得
    fun countLecture(): Int {
        val selectSql: String = "select * from lecture where lecture_name = ?"
        val cursor: Cursor = db.rawQuery(selectSql, null)
        var cou: Int = 0
        try {
            if (cursor.moveToNext()) {
                cou += 1
            }
        } finally {
            cursor.close()
        }
        return cou
    }

    //登録されている講義 IDの最大値を取得
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

    //講義IDを指定して一列を取得
    fun getLectureById(lecture_id: Int): String {
        val selectSql: String = "select * from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: String = ""//最終的に表示
        var rowdata: String = ""
        try {
            if (cursor.moveToNext()) {
                for (i in 0..5) {
                    rowdata += cursor.getString(i).toString() + " , "
                }
                disp += rowdata + "\n"
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //講義IDを指定して曜日を取得
    fun getWeekById(lecture_id: Int): String {
        val selectSql: String = "select week from lecture_period_week where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: String = ""//最終的に表示
        var rowdata: String = ""
        try {
            if (cursor.moveToNext()) {
                for (i in 0..5) {
                    rowdata += cursor.getString(i).toString() + " , "
                }
                disp += rowdata + "\n"
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //講義IDを指定して時限を取得
    fun getPeriodById(lecture_id: Int): String {
        val selectSql: String = "select period from lecture_period_week where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: String = ""//最終的に表示
        var rowdata: String = ""
        try {
            if (cursor.moveToNext()) {
                for (i in 0..5) {
                    rowdata += cursor.getString(i).toString() + " , "
                }
                disp += rowdata + "\n"
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //講義IDを指定して講義名を取得
    fun getLectureNameById(lecture_id: Int): String {
        val selectSql: String = "select lecture_name from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: String = ""//最終的に表示
        var rowdata: String = ""
        try {
            if (cursor.moveToNext()) {
                for (i in 0..5) {
                    rowdata += cursor.getString(i).toString() + " , "
                }
                disp += rowdata + "\n"
            }
        } finally {
            cursor.close()
        }
        return disp
    }

    //講義IDを指定して教員名を取得
    fun getTeacherNameById(lecture_id: Int): String {
        val selectSql: String = "select teacher_name from lecture where lecture_id = ?"
        val cursor: Cursor = db.rawQuery(selectSql, arrayOf(lecture_id.toString()))
        //Log.d("opal",cursor.toString())
        var disp: String = ""//最終的に表示
        var rowdata: String = ""
        try {
            if (cursor.moveToNext()) {
                for (i in 0..5) {
                    rowdata += cursor.getString(i).toString() + " , "
                }
                disp += rowdata + "\n"
            }
        } finally {
            cursor.close()
        }
        return disp
    }

}
