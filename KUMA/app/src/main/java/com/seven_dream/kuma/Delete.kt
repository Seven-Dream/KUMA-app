package com.seven_dream.kuma

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_delete.*
import java.io.File

private lateinit var userDB_timetable: userDB_Adapter_Timetable

class Delete : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        userDB_timetable = userDB_Adapter_Timetable(this)//DBの呼び出し
        val id = readFiles("id.txt")
        val quarter = readFiles("quarter.txt")

        yesbutton.setOnClickListener {
            userDB_timetable.deleteTimetable(id)
            if(quarter == 1) {
                val intent = Intent(application, Delete_1q::class.java)
                startActivity(intent)
            }else if(quarter == 2) {
                val intent = Intent(application, Delete_2q::class.java)
                startActivity(intent)
            }else if(quarter == 3) {
                val intent = Intent(application, Delete_3q::class.java)
                startActivity(intent)
            }else if(quarter == 4) {
                val intent = Intent(application, Delete_4q::class.java)
                startActivity(intent)
            }
        }
        nobutton.setOnClickListener {
            finish()
        }
        textview.text = userDB_timetable.getLecture_name(id) + "\n" + "を削除しますか?"
    }


    private fun readFiles(file: String): Int {

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