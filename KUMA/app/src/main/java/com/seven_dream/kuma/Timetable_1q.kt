package com.kuma.timetable

import android.content.Intent
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_1q.*

private lateinit var userDB_timetable: userDB_Adapter_Timetable

class Timetable_1q : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        userDB_timetable = userDB_Adapter_Timetable(this)//DBの呼び出し

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1q)
        button.setOnClickListener {
            val intent = Intent(application, TimetableSearch::class.java)
            startActivity(intent)
        }
        delete.setOnClickListener {
            val intent = Intent(application, Delete_1q::class.java)
            startActivity(intent)
        }
        schedule.setOnClickListener {
            val intent = Intent(application, Schedule::class.java)
            startActivity(intent)
        }
        button_at.setOnClickListener {
            val intent = Intent(application, AttendCheck_1q::class.java)
            startActivity(intent)
        }
        button2Q.setOnClickListener {
            val intent = Intent(application, Timetable_2q::class.java)
            startActivity(intent)
        }
        button3Q.setOnClickListener {
            val intent = Intent(application, Timetable_3q::class.java)
            startActivity(intent)
        }
        button4Q.setOnClickListener {
            val intent = Intent(application, Timetable_4q::class.java)
            startActivity(intent)
        }

        //1Q
        //月曜日の講義ID
        val mon1 = userDB_timetable.getLecture_id(2018, 1, 1, 1)
        val mon2 = userDB_timetable.getLecture_id(2018, 1, 2, 1)
        val mon3 = userDB_timetable.getLecture_id(2018, 1, 3, 1)
        val mon4 = userDB_timetable.getLecture_id(2018, 1, 4, 1)
        val mon5 = userDB_timetable.getLecture_id(2018, 1, 5, 1)
        //火曜日の講義ID
        val tue1 = userDB_timetable.getLecture_id(2018, 1, 1, 2)
        val tue2 = userDB_timetable.getLecture_id(2018, 1, 2, 2)
        val tue3 = userDB_timetable.getLecture_id(2018, 1, 3, 2)
        val tue4 = userDB_timetable.getLecture_id(2018, 1, 4, 2)
        val tue5 = userDB_timetable.getLecture_id(2018, 1, 5, 2)
        //水曜日の講義ID
        val wed1 = userDB_timetable.getLecture_id(2018, 1, 1, 3)
        val wed2 = userDB_timetable.getLecture_id(2018, 1, 2, 3)
        val wed3 = userDB_timetable.getLecture_id(2018, 1, 3, 3)
        val wed4 = userDB_timetable.getLecture_id(2018, 1, 4, 3)
        val wed5 = userDB_timetable.getLecture_id(2018, 1, 5, 3)
        //木曜日の講義ID
        val thu1 = userDB_timetable.getLecture_id(2018, 1, 1, 4)
        val thu2 = userDB_timetable.getLecture_id(2018, 1, 2, 4)
        val thu3 = userDB_timetable.getLecture_id(2018, 1, 3, 4)
        val thu4 = userDB_timetable.getLecture_id(2018, 1, 4, 4)
        val thu5 = userDB_timetable.getLecture_id(2018, 1, 5, 4)
        //金曜日の講義ID
        val fri1 = userDB_timetable.getLecture_id(2018, 1, 1, 5)
        val fri2 = userDB_timetable.getLecture_id(2018, 1, 2, 5)
        val fri3 = userDB_timetable.getLecture_id(2018, 1, 3, 5)
        val fri4 = userDB_timetable.getLecture_id(2018, 1, 4, 5)
        val fri5 = userDB_timetable.getLecture_id(2018, 1, 5, 5)

//--------------------------------------------------------------------------------------
        //月曜日の講義名、教授名、教室名を表示
        if (mon1 >= 0) {
            Mon1.text = userDB_timetable.getLecture_name(mon1) + "\n" + userDB_timetable.getTeacher(mon1) + "\n" +
                    userDB_timetable.getClassroom(mon1)
        } else {
            Mon1.text = ""
        }
        if (mon2 >= 0) {
            Mon2.text = userDB_timetable.getLecture_name(mon2) + "\n" + userDB_timetable.getTeacher(mon2) + "\n" +
                    userDB_timetable.getClassroom(mon2)
        } else {
            Mon2.text = ""
        }
        if (mon3 >= 0) {
            Mon3.text = userDB_timetable.getLecture_name(mon3) + "\n" + userDB_timetable.getTeacher(mon3) + "\n" +
                    userDB_timetable.getClassroom(mon3)
        } else {
            Mon3.text = ""
        }
        if (mon4 >= 0) {
            Mon4.text = userDB_timetable.getLecture_name(mon4) + "\n" + userDB_timetable.getTeacher(mon4) + "\n" +
                    userDB_timetable.getClassroom(mon4)
        } else {
            Mon4.text = ""
        }
        if (mon5 >= 0) {
            Mon5.text = userDB_timetable.getLecture_name(mon5) + "\n" + userDB_timetable.getTeacher(mon5) + "\n" +
                    userDB_timetable.getClassroom(mon5)
        } else {
            Mon5.text = ""
        }

        //火曜日の講義名、教授名、教室名を表示
        if (tue1 >= 0) {
            Tue1.text = userDB_timetable.getLecture_name(tue1) + "\n" + userDB_timetable.getTeacher(tue1) + "\n" +
                    userDB_timetable.getClassroom(tue1)
        } else {
            Tue1.text = ""
        }
        if (tue2 >= 0) {
            Tue2.text = userDB_timetable.getLecture_name(tue2) + "\n" + userDB_timetable.getTeacher(tue2) + "\n" +
                    userDB_timetable.getClassroom(tue2)
        } else {
            Tue2.text = ""
        }
        if (tue3 >= 0) {
            Tue3.text = userDB_timetable.getLecture_name(tue3) + "\n" + userDB_timetable.getTeacher(tue3) + "\n" +
                    userDB_timetable.getClassroom(tue3)
        } else {
            Tue3.text = ""
        }
        if (tue4 >= 0) {
            Tue4.text = userDB_timetable.getLecture_name(tue4) + "\n" + userDB_timetable.getTeacher(tue4) + "\n" +
                    userDB_timetable.getClassroom(tue4)
        } else {
            Tue4.text = ""
        }
        if (tue5 >= 0) {
            Tue5.text = userDB_timetable.getLecture_name(tue5) + "\n" + userDB_timetable.getTeacher(tue5) + "\n" +
                    userDB_timetable.getClassroom(tue5)
        } else {
            Tue5.text = ""
        }

        //水曜日の講義名、教授名、教室名を表示
        if (wed1 >= 0) {
            Wed1.text = userDB_timetable.getLecture_name(wed1) + "\n" + userDB_timetable.getTeacher(wed1) + "\n" +
                    userDB_timetable.getClassroom(wed1)
        } else {
            Wed1.text = ""
        }
        if (wed2 >= 0) {
            Wed2.text = userDB_timetable.getLecture_name(wed2) + "\n" + userDB_timetable.getTeacher(wed2) + "\n" +
                    userDB_timetable.getClassroom(wed2)
        } else {
            Wed2.text = ""
        }
        if (wed3 >= 0) {
            Wed3.text = userDB_timetable.getLecture_name(wed3) + "\n" + userDB_timetable.getTeacher(wed3) + "\n" +
                    userDB_timetable.getClassroom(wed3)
        } else {
            Wed3.text = ""
        }
        if (wed4 >= 0) {
            Wed4.text = userDB_timetable.getLecture_name(wed4) + "\n" + userDB_timetable.getTeacher(wed4) + "\n" +
                    userDB_timetable.getClassroom(wed4)
        } else {
            Wed4.text = ""
        }
        if (wed5 >= 0) {
            Wed5.text = userDB_timetable.getLecture_name(wed5) + "\n" + userDB_timetable.getTeacher(wed5) + "\n" +
                    userDB_timetable.getClassroom(wed5)
        } else {
            Wed5.text = ""
        }

        //木曜日の講義名、教授名、教室名を表示
        if (thu1 >= 0) {
            Thu1.text = userDB_timetable.getLecture_name(thu1) + "\n" + userDB_timetable.getTeacher(thu1) + "\n" +
                    userDB_timetable.getClassroom(thu1)
        } else {
            Thu1.text = ""
        }
        if (thu2 >= 0) {
            Thu2.text = userDB_timetable.getLecture_name(thu2) + "\n" + userDB_timetable.getTeacher(thu2) + "\n" +
                    userDB_timetable.getClassroom(thu2)
        } else {
            Thu2.text = ""
        }
        if (thu3 >= 0) {
            Thu3.text = userDB_timetable.getLecture_name(thu3) + "\n" + userDB_timetable.getTeacher(thu3) + "\n" +
                    userDB_timetable.getClassroom(thu3)
        } else {
            Thu3.text = ""
        }
        if (thu4 >= 0) {
            Thu4.text = userDB_timetable.getLecture_name(thu4) + "\n" + userDB_timetable.getTeacher(thu4) + "\n" +
                    userDB_timetable.getClassroom(thu4)
        } else {
            Thu4.text = ""
        }
        if (thu5 >= 0) {
            Thu5.text = userDB_timetable.getLecture_name(thu5) + "\n" + userDB_timetable.getTeacher(thu5) + "\n" +
                    userDB_timetable.getClassroom(thu5)
        } else {
            Thu5.text = ""
        }

        //金曜日の講義名、教授名、教室名を表示
        if (fri1 >= 0) {
            Fri1.text = userDB_timetable.getLecture_name(fri1) + "\n" + userDB_timetable.getTeacher(fri1) + "\n" +
                    userDB_timetable.getClassroom(fri1)
        } else {
            Fri1.text = ""
        }
        if (fri2 >= 0) {
            Fri2.text = userDB_timetable.getLecture_name(fri2) + "\n" + userDB_timetable.getTeacher(fri2) + "\n" +
                    userDB_timetable.getClassroom(fri2)
        } else {
            Fri2.text = ""
        }
        if (fri3 >= 0) {
            Fri3.text = userDB_timetable.getLecture_name(fri3) + "\n" + userDB_timetable.getTeacher(fri3) + "\n" +
                    userDB_timetable.getClassroom(fri3)
        } else {
            Fri3.text = ""
        }
        if (fri4 >= 0) {
            Fri4.text = userDB_timetable.getLecture_name(fri4) + "\n" + userDB_timetable.getTeacher(fri4) + "\n" +
                    userDB_timetable.getClassroom(fri4)
        } else {
            Fri4.text = ""
        }
        if (fri5 >= 0) {
            Fri5.text = userDB_timetable.getLecture_name(fri5) + "\n" + userDB_timetable.getTeacher(fri5) + "\n" +
                    userDB_timetable.getClassroom(fri5)
        } else {
            Fri5.text = ""
        }
    }


// Viewを継承したクラス
    internal inner class MyView(context: Context) : View(context) {
        private var paint: Paint = Paint()

        // 描画するラインの太さ
        private val lineStrokeWidth = 20f

        init {
        }

        override fun onDraw(canvas: Canvas){

            userDB_timetable = userDB_Adapter_Timetable(context)//DBの呼び出し
            // ペイントする色の設定
            paint.color = Color.argb(255, 255, 0, 255)

            // ペイントストロークの太さを設定
            paint.strokeWidth = lineStrokeWidth

            // Styleのストロークを設定する
            paint.style = Paint.Style.STROKE

            // drawRectを使って矩形を描画する、引数に座標を設定
            // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
            canvas.drawRect(300f, 300f, 600f, 600f, paint)
        }
    }
}


