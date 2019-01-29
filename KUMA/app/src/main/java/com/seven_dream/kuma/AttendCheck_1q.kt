package com.kuma.timetable

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_count_list.*
import java.io.File

private lateinit var userDB_timetable: userDB_Adapter_Timetable

class AttendCheck_1q: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        userDB_timetable = userDB_Adapter_Timetable(this)//DBの呼び出し

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_list)

        attendlist.text = "出欠チェック"

        val fileNameS = "countS.txt"
        val fileNameK = "countK.txt"
        val fileNameP = "position.txt"

        val at1 = readFiles("countS1.txt")
        val ab1 = readFiles("countK1.txt")
        val at2 = readFiles("countS2.txt")
        val ab2 = readFiles("countK2.txt")
        val at3 = readFiles("countS3.txt")
        val ab3 = readFiles("countK3.txt")
        val at4 = readFiles("countS4.txt")
        val ab4 = readFiles("countK4.txt")
        val at5 = readFiles("countS5.txt")
        val ab5 = readFiles("countK5.txt")
        val at6 = readFiles("countS6.txt")
        val ab6 = readFiles("countK6.txt")
        val at7 = readFiles("countS7.txt")
        val ab7 = readFiles("countK7.txt")
        val at8 = readFiles("countS8.txt")
        val ab8 = readFiles("countK8.txt")
        val at9 = readFiles("countS9.txt")
        val ab9 = readFiles("countK9.txt")
        val at10 = readFiles("countS10.txt")
        val ab10 = readFiles("countK10.txt")

        val at11 = readFiles("countS11.txt")
        val ab11 = readFiles("countK11.txt")
        val at12 = readFiles("countS12.txt")
        val ab12 = readFiles("countK12.txt")
        val at13 = readFiles("countS13.txt")
        val ab13 = readFiles("countK13.txt")
        val at14 = readFiles("countS14.txt")
        val ab14 = readFiles("countK14.txt")
        val at15 = readFiles("countS15.txt")
        val ab15 = readFiles("countK15.txt")
        val at16 = readFiles("countS16.txt")
        val ab16 = readFiles("countK16.txt")
        val at17 = readFiles("countS17.txt")
        val ab17 = readFiles("countK17.txt")
        val at18 = readFiles("countS18.txt")
        val ab18 = readFiles("countK18.txt")
        val at19 = readFiles("countS19.txt")
        val ab19 = readFiles("countK19.txt")
        val at20 = readFiles("countS20.txt")
        val ab20 = readFiles("countK20.txt")
        val at21 = readFiles("countS21.txt")
        val ab21 = readFiles("countK21.txt")
        val at22 = readFiles("countS22.txt")
        val ab22 = readFiles("countK22.txt")
        val at23 = readFiles("countS23.txt")
        val ab23 = readFiles("countK23.txt")
        val at24 = readFiles("countS24.txt")
        val ab24 = readFiles("countK24.txt")
        val at25 = readFiles("countS25.txt")
        val ab25 = readFiles("countK25.txt")




        val check = arrayOf("","$at1 / $ab1", "$at2 / $ab2","$at3 / $ab3",
            "$at4 / $ab4","$at5 / $ab5","$at6 / $ab6","$at7 / $ab7","$at8 / $ab8",
            "$at9 / $ab9","$at10 / $ab10","$at11 / $ab11", "$at12 / $ab12","$at13 / $ab13",
            "$at14 / $ab14","$at15 / $ab15","$at16 / $ab16","$at17 / $ab17","$at18 / $ab18",
            "$at19 / $ab19","$at20 / $ab20","$at21 / $ab21", "$at22 / $ab22","$at23 / $ab23",
            "$at24 / $ab24","$at25 / $ab25")


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


        val Lecture = arrayOf("",
            userDB_timetable.getLecture_name(mon1), userDB_timetable.getLecture_name(mon2), userDB_timetable.getLecture_name(mon3), userDB_timetable.getLecture_name(mon4), userDB_timetable.getLecture_name(mon5),
            userDB_timetable.getLecture_name(tue1), userDB_timetable.getLecture_name(tue2), userDB_timetable.getLecture_name(tue3), userDB_timetable.getLecture_name(tue4), userDB_timetable.getLecture_name(tue5),
            userDB_timetable.getLecture_name(wed1), userDB_timetable.getLecture_name(wed2), userDB_timetable.getLecture_name(wed3), userDB_timetable.getLecture_name(wed4), userDB_timetable.getLecture_name(wed5),
            userDB_timetable.getLecture_name(thu1), userDB_timetable.getLecture_name(thu2), userDB_timetable.getLecture_name(thu3), userDB_timetable.getLecture_name(thu4), userDB_timetable.getLecture_name(thu5),
            userDB_timetable.getLecture_name(fri1), userDB_timetable.getLecture_name(fri2), userDB_timetable.getLecture_name(fri3), userDB_timetable.getLecture_name(fri4), userDB_timetable.getLecture_name(fri5))

        val lecture = arrayOfNulls<String>(26)
        var temp = 1
        for(id in 1..25){
            if(Lecture[id] != ""  && !lecture.contains(Lecture[id])) {
                lecture[temp] = Lecture[id]
                temp++
            }
        }

        //登録した講義だけを表示
        val items = List<Map<String, String?>>(temp, { i -> mapOf("title"
                to lecture[i], "count" to check[i])})

        val adapter = SimpleAdapter(
            this,
            items,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "count"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        myListView.adapter = adapter
        myListView.setOnItemClickListener {_, view, position, _ ->
            val title = view.findViewById<TextView>(android.R.id.text1).text

            if(position != 0 && "$title" != "") {
                Toast.makeText(this, "$title", Toast.LENGTH_SHORT).show()
            }


            if (position == 0) {
                val intent0 = Intent(application, Timetable_1q::class.java)
                startActivity(intent0)
            }
            if (position == 1) {
                val intent1 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at1)
                saveFile(fileNameK, ab1)
                startActivity(intent1)
            } else if (position == 2) {
                val intent2 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at2)
                saveFile(fileNameK, ab2)
                startActivity(intent2)
            } else if (position == 3) {
                val intent3 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at3)
                saveFile(fileNameK, ab3)
                startActivity(intent3)
            } else if (position == 4) {
                val intent4 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at4)
                saveFile(fileNameK, ab4)
                startActivity(intent4)
            } else if (position == 5) {
                val intent5 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at5)
                saveFile(fileNameK, ab5)
                startActivity(intent5)
            } else if (position == 6) {
                val intent6 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at6)
                saveFile(fileNameK, ab6)
                startActivity(intent6)
            } else if (position == 7) {
                val intent7 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at7)
                saveFile(fileNameK, ab7)
                startActivity(intent7)
            } else if (position == 8) {
                val intent8 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at8)
                saveFile(fileNameK, at8)
                startActivity(intent8)
            } else if (position == 9) {
                val intent9 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at9)
                saveFile(fileNameK, ab9)
                startActivity(intent9)
            } else if (position == 10) {
                val intent10 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at10)
                saveFile(fileNameK, ab10)
                startActivity(intent10)
            } else if (position == 11) {
                val intent11 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at11)
                saveFile(fileNameK, ab11)
                startActivity(intent11)
            } else if (position == 12) {
                val intent12 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at12)
                saveFile(fileNameK, ab12)
                startActivity(intent12)
            } else if (position == 13) {
                val intent13 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at13)
                saveFile(fileNameK, ab13)
                startActivity(intent13)
            } else if (position == 14) {
                val intent14 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at14)
                saveFile(fileNameK, ab14)
                startActivity(intent14)
            } else if (position == 15) {
                val intent15 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at15)
                saveFile(fileNameK, ab15)
                startActivity(intent15)
            } else if (position == 16) {
                val intent16 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at16)
                saveFile(fileNameK, ab16)
                startActivity(intent16)
            } else if (position == 17) {
                val intent17 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at17)
                saveFile(fileNameK, ab17)
                startActivity(intent17)
            } else if (position == 18) {
                val intent18 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at18)
                saveFile(fileNameK, at18)
                startActivity(intent18)
            } else if (position == 19) {
                val intent19 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at19)
                saveFile(fileNameK, ab19)
                startActivity(intent19)
            } else if (position == 20) {
                val intent20 = Intent(application, Count_1q::class.java)
                saveFile(fileNameP, position)
                saveFile(fileNameS, at20)
                saveFile(fileNameK, ab20)
                startActivity(intent20)
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



    private fun readFiles(file: String): Int {

        // to check whether file exists or not
        val readFile = File(applicationContext.filesDir, file)

        if(!readFile.exists()){
            Log.d("debug","No file exists")
            return 0
        }
        else{
            return readFile.bufferedReader().read()
        }
    }
}