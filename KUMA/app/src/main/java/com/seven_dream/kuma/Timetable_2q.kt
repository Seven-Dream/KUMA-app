package com.kuma.timetable

import android.content.Intent
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

private lateinit var userDB_Timetable: userDB_Adapter_Timetable//遅延初期化→プロパティ内でインスタンスにアクセス可能？
private lateinit var userDB: userDB_Adapter_Timetable

class Timetable_2q : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
       userDB_Timetable = userDB_Adapter_Timetable(this)//DBの呼び出し
        userDB = userDB_Adapter_Timetable(this)//DBの呼び出し

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2q)
        userDB.deleteTimetable(0)
        userDB.deleteTimetable(1)
        userDB.deleteTimetable(2)
        userDB.deleteTimetable(3)
        userDB.deleteTimetable(4)
        userDB.deleteTimetable(5)
        userDB.deleteTimetable(6)
        userDB.deleteTimetable(7)
        userDB.deleteTimetable(8)
        userDB.deleteTimetable(9)
        userDB.deleteTimetable(10)
     userDB.deleteWeek_Period(0)
     userDB.deleteWeek_Period(1)
     userDB.deleteWeek_Period(2)
     userDB.deleteWeek_Period(3)
     userDB.deleteWeek_Period(4)
     userDB.deleteWeek_Period(5)
     userDB.deleteWeek_Period(6)
     userDB.deleteWeek_Period(7)
     userDB.deleteWeek_Period(8)
     userDB.deleteWeek_Period(9)
     userDB.deleteWeek_Period(10)

        //userDB.addRecordTimetable(0, "学習と推論", "門田"," A106",  2018,1)
        userDB.addRecordTimetable(1, "情報セキュリティ", "清水"," A106",  2018,1)
        userDB.addRecordTimetable(2, "情報学群実験第3i", "吉田,植田"," 情報実験室",  2018,1)
        userDB.addRecordTimetable(3, "情報学群実験第3c", "栗原,高田"," A-WS",  2018,2)
        userDB.addRecordTimetable(4, "データベースシステム", "横山"," A106",  2018,3)
        userDB.addRecordTimetable(5, "オペレーティングシステム", "横山"," A106",  2018,4)
        userDB.addRecordTimetable(6, "HCI概論", "任"," A106",  2018,4)
        userDB.addRecordTimetable(7, "認知心理学", "繁枡"," A106",  2018,3)
        userDB.addRecordTimetable(8, "ソフトウェア工学", "高田,松崎"," A106",  2018,3)
        userDB.addRecordTimetable(9, "ソフトウェア工学", "高田,松崎"," A106",  2018,4)
        userDB.addRecordTimetable(10, "キャリアプラン2", "村上"," K-HALL",  2018,4)


        //userDB.addRecordWeek(0,2,2)
        userDB.addRecordWeek(1,1,1)
        userDB.addRecordWeek(2,1,3)
        userDB.addRecordWeek(3,1,3)
        userDB.addRecordWeek(4,1,1)
        userDB.addRecordWeek(5,1,2)
        userDB.addRecordWeek(6,2,3)
        userDB.addRecordWeek(7,2,3)
        userDB.addRecordWeek(8,1,3)
        userDB.addRecordWeek(9,1,3)
        userDB.addRecordWeek(10,3,3)

        button.setOnClickListener {
            val intent = Intent(application, TimetableSearch::class.java)
            startActivity(intent)
        }

        button_at.setOnClickListener {
            val intent = Intent(application, AttendCheck_2q::class.java)
            startActivity(intent)
        }
        button1Q.setOnClickListener {
            val intent = Intent(application, Timetable_1q::class.java)
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
        val mon1 = userDB.getLecture_id(2018,2,1,1)
        val mon2 = userDB.getLecture_id(2018,2,2,1)
        val mon3 = userDB.getLecture_id(2018,2,3,1)
        val mon4 = userDB.getLecture_id(2018,2,4,1)
        val mon5 = userDB.getLecture_id(2018,2,5,1)
        //火曜日の講義ID
        val tue1 = userDB.getLecture_id(2018,2,1,2)
        val tue2 = userDB.getLecture_id(2018,2,2,2)
        val tue3 = userDB.getLecture_id(2018,2,3,2)
        val tue4 = userDB.getLecture_id(2018,2,4,2)
        val tue5 = userDB.getLecture_id(2018,2,5,2)
        //水曜日の講義ID
        val wed1 = userDB.getLecture_id(2018,2,1,3)
        val wed2 = userDB.getLecture_id(2018,2,2,3)
        val wed3 = userDB.getLecture_id(2018,2,3,3)
        val wed4 = userDB.getLecture_id(2018,2,4,3)
        val wed5 = userDB.getLecture_id(2018,2,5,3)
        //木曜日の講義ID
        val thu1 = userDB.getLecture_id(2018,2,1,4)
        val thu2 = userDB.getLecture_id(2018,2,2,4)
        val thu3 = userDB.getLecture_id(2018,2,3,4)
        val thu4 = userDB.getLecture_id(2018,2,4,4)
        val thu5 = userDB.getLecture_id(2018,2,5,4)
        //金曜日の講義ID
        val fri1 = userDB.getLecture_id(2018,2,1,5)
        val fri2 = userDB.getLecture_id(2018,2,2,5)
        val fri3 = userDB.getLecture_id(2018,2,3,5)
        val fri4 = userDB.getLecture_id(2018,2,4,5)
        val fri5 = userDB.getLecture_id(2018,2,5,5)


//--------------------------------------------------------------------------------------
        //月,木曜日の講義名、教授名、教室名を表示
        if(mon1 >= 0) {
            Mon1.text = userDB.getLecture_name(mon1) + "\n" +userDB.getTeacher(mon1) + "\n" +userDB.getClassroom(mon1)
            Thu1.text = userDB.getLecture_name(mon1) + "\n" +userDB.getTeacher(mon1) + "\n" +userDB.getClassroom(mon1)
        }else {
            Mon1.text = ""
            Thu1.text = ""}
        if(mon2 >= 0) {
            Mon2.text = userDB.getLecture_name(mon2) + "\n" +userDB.getTeacher(mon2) + "\n" +userDB.getClassroom(mon2)
            Thu2.text = userDB.getLecture_name(mon2) + "\n" +userDB.getTeacher(mon2) + "\n" +userDB.getClassroom(mon2)
        }else {
            Mon2.text = ""
            Thu2.text = ""}
        if(mon3 >= 0) {
            Mon3.text = userDB.getLecture_name(mon3) + "\n" +userDB.getTeacher(mon3) + "\n" +userDB.getClassroom(mon3)
            Thu3.text = userDB.getLecture_name(mon3) + "\n" +userDB.getTeacher(mon3) + "\n" +userDB.getClassroom(mon3)
        }else {
            Mon3.text = ""
            Thu3.text = ""}
        if(mon4 >= 0) {
            Mon4.text = userDB.getLecture_name(mon4) + "\n" +userDB.getTeacher(mon4) + "\n" +userDB.getClassroom(mon4)
            Thu4.text = userDB.getLecture_name(mon4) + "\n" +userDB.getTeacher(mon4) + "\n" +userDB.getClassroom(mon4)
        }else {
            Mon4.text = ""
            Thu4.text = ""}
        if(mon5 >= 0) {
            Mon5.text = userDB.getLecture_name(mon5) + "\n" +userDB.getTeacher(mon5) + "\n" +userDB.getClassroom(mon5)
            Thu5.text = userDB.getLecture_name(mon5) + "\n" +userDB.getTeacher(mon5) + "\n" +userDB.getClassroom(mon5)
        }else {
            Mon5.text = ""
            Thu5.text = ""}


        //火,金曜日の講義名、教授名、教室名を表示
        if(tue1 >= 0) {
            Tue1.text = userDB.getLecture_name(tue1) + "\n" +userDB.getTeacher(tue1) + "\n" +userDB.getClassroom(tue1)
            Fri1.text = userDB.getLecture_name(tue1) + "\n" +userDB.getTeacher(tue1) + "\n" +userDB.getClassroom(tue1)
        }else {
            Tue1.text = ""
            Fri1.text = ""}
        if(tue2 >= 0) {
            Tue2.text = userDB.getLecture_name(tue2) + "\n" +userDB.getTeacher(tue2) + "\n" +userDB.getClassroom(tue2)
            Fri2.text = userDB.getLecture_name(tue2) + "\n" +userDB.getTeacher(tue2) + "\n" +userDB.getClassroom(tue2)
        }else {
            Tue2.text = ""
            Fri2.text = ""}
        if(tue3 >= 0) {
            Tue3.text = userDB.getLecture_name(tue3) + "\n" +userDB.getTeacher(tue3) + "\n" +userDB.getClassroom(tue3)
            Fri3.text = userDB.getLecture_name(tue3) + "\n" +userDB.getTeacher(tue3) + "\n" +userDB.getClassroom(tue3)
        }else {
            Tue3.text = ""
            Fri3.text = ""}
        if(tue4 >= 0) {
            Tue4.text = userDB.getLecture_name(tue4) + "\n" +userDB.getTeacher(tue4) + "\n" +userDB.getClassroom(tue4)
            Fri4.text = userDB.getLecture_name(tue4) + "\n" +userDB.getTeacher(tue4) + "\n" +userDB.getClassroom(tue4)
        }else {
            Tue4.text = ""
            Fri4.text = ""}
        if(tue5 >= 0) {
            Tue5.text = userDB.getLecture_name(tue5) + "\n" +userDB.getTeacher(tue5) + "\n" +userDB.getClassroom(tue5)
            Fri5.text = userDB.getLecture_name(tue5) + "\n" +userDB.getTeacher(tue5) + "\n" +userDB.getClassroom(tue5)
        }else {
            Tue5.text = ""
            Fri5.text = ""}

        //水曜日の講義名、教授名、教室名を表示
        if(wed1 >= 0) {Wed1.text = userDB.getLecture_name(wed1) + "\n" +userDB.getTeacher(wed1) + "\n" +userDB.getClassroom(wed1)
        }else {Wed1.text = ""}
        if(wed2 >= 0) {Wed2.text = userDB.getLecture_name(wed2) + "\n" +userDB.getTeacher(wed2) + "\n" +userDB.getClassroom(wed2)
        }else {Wed2.text = ""}
        if(wed3 >= 0) {Wed3.text = userDB.getLecture_name(wed3) + "\n" +userDB.getTeacher(wed3) + "\n" +userDB.getClassroom(wed3)
        }else {Wed1.text = ""}
        if(wed4 >= 0) {Wed4.text = userDB.getLecture_name(wed4) + "\n" +userDB.getTeacher(wed4) + "\n" +userDB.getClassroom(wed4)
        }else {Wed4.text = ""}
        if(wed5 >= 0) {Wed5.text = userDB.getLecture_name(wed5) + "\n" +userDB.getTeacher(wed5) + "\n" +userDB.getClassroom(wed5)
        }else {Wed5.text = ""}
    }






// Viewを継承したクラス
    internal inner class MyView(context: Context) : View(context) {
        private var paint: Paint = Paint()

        // 描画するラインの太さ
        private val lineStrokeWidth = 20f

        init {
        }

        override fun onDraw(canvas: Canvas){

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

