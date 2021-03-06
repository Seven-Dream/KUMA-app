package com.seven_dream.kuma

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_count_list.*

private lateinit var userDB_timetable: userDB_Adapter_Timetable

class Delete_2q: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        userDB_timetable = userDB_Adapter_Timetable(this)//DBの呼び出し

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_list)

        attendlist.text = "講義削除"
        val fileNameI = "id.txt"
        val fileNameQ = "quarter.txt"
        val quarter = 2

        val check = arrayOfNulls<String>(26)
        //2Q
        //月曜日の講義ID
        val mon1 = userDB_timetable.getLecture_id(2018, 2, 1, 1)
        val mon2 = userDB_timetable.getLecture_id(2018, 2, 2, 1)
        val mon3 = userDB_timetable.getLecture_id(2018, 2, 3, 1)
        val mon4 = userDB_timetable.getLecture_id(2018, 2, 4, 1)
        val mon5 = userDB_timetable.getLecture_id(2018, 2, 5, 1)
        //火曜日の講義ID
        val tue1 = userDB_timetable.getLecture_id(2018, 2, 1, 2)
        val tue2 = userDB_timetable.getLecture_id(2018, 2, 2, 2)
        val tue3 = userDB_timetable.getLecture_id(2018, 2, 3, 2)
        val tue4 = userDB_timetable.getLecture_id(2018, 2, 4, 2)
        val tue5 = userDB_timetable.getLecture_id(2018, 2, 5, 2)
        //水曜日の講義ID
        val wed1 = userDB_timetable.getLecture_id(2018, 2, 1, 3)
        val wed2 = userDB_timetable.getLecture_id(2018, 2, 2, 3)
        val wed3 = userDB_timetable.getLecture_id(2018, 2, 3, 3)
        val wed4 = userDB_timetable.getLecture_id(2018, 2, 4, 3)
        val wed5 = userDB_timetable.getLecture_id(2018, 2, 5, 3)
        //木曜日の講義ID
        val thu1 = userDB_timetable.getLecture_id(2018, 2, 1, 4)
        val thu2 = userDB_timetable.getLecture_id(2018, 2, 2, 4)
        val thu3 = userDB_timetable.getLecture_id(2018, 2, 3, 4)
        val thu4 = userDB_timetable.getLecture_id(2018, 2, 4, 4)
        val thu5 = userDB_timetable.getLecture_id(2018, 2, 5, 4)
        //金曜日の講義ID
        val fri1 = userDB_timetable.getLecture_id(2018, 2, 1, 5)
        val fri2 = userDB_timetable.getLecture_id(2018, 2, 2, 5)
        val fri3 = userDB_timetable.getLecture_id(2018, 2, 3, 5)
        val fri4 = userDB_timetable.getLecture_id(2018, 2, 4, 5)
        val fri5 = userDB_timetable.getLecture_id(2018, 2, 5, 5)

        val Lecture = arrayOf(
            "",
            userDB_timetable.getLecture_name(mon1),
            userDB_timetable.getLecture_name(mon2),
            userDB_timetable.getLecture_name(mon3),
            userDB_timetable.getLecture_name(mon4),
            userDB_timetable.getLecture_name(mon5),
            userDB_timetable.getLecture_name(tue1),
            userDB_timetable.getLecture_name(tue2),
            userDB_timetable.getLecture_name(tue3),
            userDB_timetable.getLecture_name(tue4),
            userDB_timetable.getLecture_name(tue5),
            userDB_timetable.getLecture_name(wed1),
            userDB_timetable.getLecture_name(wed2),
            userDB_timetable.getLecture_name(wed3),
            userDB_timetable.getLecture_name(wed4),
            userDB_timetable.getLecture_name(wed5),
            userDB_timetable.getLecture_name(thu1),
            userDB_timetable.getLecture_name(thu2),
            userDB_timetable.getLecture_name(thu3),
            userDB_timetable.getLecture_name(thu4),
            userDB_timetable.getLecture_name(thu5),
            userDB_timetable.getLecture_name(fri1),
            userDB_timetable.getLecture_name(fri2),
            userDB_timetable.getLecture_name(fri3),
            userDB_timetable.getLecture_name(fri4),
            userDB_timetable.getLecture_name(fri5)
        )

        val lecture = arrayOfNulls<String>(26)
        var temp = 1
        for (id in 1..25) {
            if (Lecture[id] != "" && !lecture.contains(Lecture[id])) {
                lecture[temp] = Lecture[id]
                temp++
            }
        }
        //登録した講義だけを表示
        val items = List<Map<String, Any?>>(temp, { i ->
            mapOf(
                "title"
                        to lecture[i], "null" to check[i]
            )
        })

        val adapter = SimpleAdapter(
            this,
            items,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "null"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        myListView.adapter = adapter

        myListView.setOnItemClickListener { _, view, position, _ ->
            val title = view.findViewById<TextView>(android.R.id.text1).text

            if (position != 0 && "$title" != "") {
                Toast.makeText(this, "$title", Toast.LENGTH_SHORT).show()
            }


            if (position == 0) {
                val intent0 = Intent(application, Timetable_2q::class.java)
                startActivity(intent0)
            } else {
                val intent1 = Intent(application, Delete::class.java)
                val id = userDB_timetable.getLectureID("$title")
                saveFile(fileNameI, id)
                saveFile(fileNameQ, quarter)
                startActivity(intent1)
            }
        }

    }

    private fun saveFile(file: String, str: Int) {

        applicationContext.openFileOutput(file, Context.MODE_PRIVATE).use {
            it.write(str)
        }

        //File(applicationContext.filesDir, file).writer().use {
        //    it.write(str)
        //}
    }
}