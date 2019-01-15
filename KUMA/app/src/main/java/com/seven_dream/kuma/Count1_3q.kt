package com.kuma.timetable

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_third.*
import java.io.File


class Count1_3q : AppCompatActivity() {

    private val fileNameS = "countS.txt"
    private val fileNameK = "countK.txt"
    private val fileNameP = "position.txt"
    private val fileNameS1 = "countS1.txt"
    private val fileNameK1 = "countK1.txt"
    private val fileNameS2 = "countS2.txt"
    private val fileNameK2 = "countK2.txt"
    private val fileNameS3 = "countS3.txt"
    private val fileNameK3 = "countK3.txt"
    private val fileNameS4 = "countS4.txt"
    private val fileNameK4 = "countK4.txt"
    private val fileNameS5 = "countS5.txt"
    private val fileNameK5 = "countK5.txt"
    private val fileNameS6 = "countS6.txt"
    private val fileNameK6 = "countK6.txt"
    private val fileNameS7 = "countS7.txt"
    private val fileNameK7 = "countK7.txt"
    private val fileNameS8 = "countS8.txt"
    private val fileNameK8 = "countK8.txt"
    private val fileNameS9 = "countS9.txt"
    private val fileNameK9 = "countK9.txt"
    private val fileNameS10 = "countS10.txt"
    private val fileNameK10 = "countK10.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        var s1 = readFiles("countS.txt")
        var k1 = readFiles("countK.txt")
        val p = readFiles("position.txt")

        count.text = "$k1"
        count_attend.text = "$s1"
        // リスナーをボタンに登録
        absent.setOnClickListener {
            k1++
            count.text = "$k1"
        }
        attend.setOnClickListener {
            s1++
            k1++
            count.text = "$k1"
            count_attend.text = "$s1"
        }
        minus_attend.setOnClickListener {
            k1--
            if (k1 < 0) {
                k1 = 0
            }
            count.text = "$k1"
        }
        minus_absent.setOnClickListener {
            s1--
            k1--
            if (k1 < 0) {
                k1 = 0
            }
            if (s1 < 0) {
                s1 = 0
            }
            count.text = "$k1"
            count_attend.text = "$s1"
        }


        button_save.setOnClickListener {
            //Intentのインスタンスを作成
            val intent = Intent(this, AttendCheck_3q::class.java)
            //intent変数をつなげる(第一引数はキー，第二引数は渡したい変数)
            if(p == 1) {
                saveFile(fileNameS1, s1)
                saveFile(fileNameK1, k1)
            }else if(p == 2){
                saveFile(fileNameS2, s1)
                saveFile(fileNameK2, k1)
            }else if(p == 3){
                saveFile(fileNameS3, s1)
                saveFile(fileNameK3, k1)
            }else if(p == 4){
                saveFile(fileNameS4, s1)
                saveFile(fileNameK4, k1)
            }else if(p == 5){
                saveFile(fileNameS5, s1)
                saveFile(fileNameK5, k1)
            }else if(p == 6){
                saveFile(fileNameS6, s1)
                saveFile(fileNameK6, k1)
            }else if(p == 7){
                saveFile(fileNameS7, s1)
                saveFile(fileNameK7, k1)
            }else if(p == 8){
                saveFile(fileNameS8, s1)
                saveFile(fileNameK8, k1)
            }else if(p == 9){
                saveFile(fileNameS9, s1)
                saveFile(fileNameK9, k1)
            }else if(p == 10){
                saveFile(fileNameS10, s1)
                saveFile(fileNameK10, k1)
            }
            //画面遷移を開始
            startActivity(intent)
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