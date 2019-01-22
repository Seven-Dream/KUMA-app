package com.kuma.timetable

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_counter.*
import java.io.File


class Count_3q : AppCompatActivity() {

    private val fileNameS1 = "count3S1.txt"
    private val fileNameK1 = "count3K1.txt"
    private val fileNameS2 = "count3S2.txt"
    private val fileNameK2 = "count3K2.txt"
    private val fileNameS3 = "count3S3.txt"
    private val fileNameK3 = "count3K3.txt"
    private val fileNameS4 = "count3S4.txt"
    private val fileNameK4 = "count3K4.txt"
    private val fileNameS5 = "count3S5.txt"
    private val fileNameK5 = "count3K5.txt"
    private val fileNameS6 = "count3S6.txt"
    private val fileNameK6 = "count3K6.txt"
    private val fileNameS7 = "count3S7.txt"
    private val fileNameK7 = "count3K7.txt"
    private val fileNameS8 = "count3S8.txt"
    private val fileNameK8 = "count3K8.txt"
    private val fileNameS9 = "count3S9.txt"
    private val fileNameK9 = "count3K9.txt"
    private val fileNameS10 = "count3S10.txt"
    private val fileNameK10 = "count3K10.txt"
    private val fileNameS11 = "count3S11.txt"
    private val fileNameK11 = "count3K11.txt"
    private val fileNameS12 = "count3S12.txt"
    private val fileNameK12 = "count3K12.txt"
    private val fileNameS13 = "count3S13.txt"
    private val fileNameK13 = "count3K13.txt"
    private val fileNameS14 = "count3S14.txt"
    private val fileNameK14 = "count3K14.txt"
    private val fileNameS15 = "count3S15.txt"
    private val fileNameK15 = "count3K15.txt"
    private val fileNameS16 = "count3S16.txt"
    private val fileNameK16 = "count3K16.txt"
    private val fileNameS17 = "count3S17.txt"
    private val fileNameK17 = "count3K17.txt"
    private val fileNameS18 = "count3S18.txt"
    private val fileNameK18 = "count3K18.txt"
    private val fileNameS19 = "count3S19.txt"
    private val fileNameK19 = "count3K19.txt"
    private val fileNameS20 = "count3S20.txt"
    private val fileNameK20 = "count3K20.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

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
            }else if(p == 11) {
                saveFile(fileNameS11, s1)
                saveFile(fileNameK11, k1)
            }else if(p == 12){
                saveFile(fileNameS12, s1)
                saveFile(fileNameK12, k1)
            }else if(p == 13){
                saveFile(fileNameS13, s1)
                saveFile(fileNameK13, k1)
            }else if(p == 14){
                saveFile(fileNameS14, s1)
                saveFile(fileNameK14, k1)
            }else if(p == 15){
                saveFile(fileNameS15, s1)
                saveFile(fileNameK15, k1)
            }else if(p == 16){
                saveFile(fileNameS16, s1)
                saveFile(fileNameK16, k1)
            }else if(p == 17){
                saveFile(fileNameS17, s1)
                saveFile(fileNameK17, k1)
            }else if(p == 18){
                saveFile(fileNameS18, s1)
                saveFile(fileNameK18, k1)
            }else if(p == 19){
                saveFile(fileNameS19, s1)
                saveFile(fileNameK19, k1)
            }else if(p == 20){
                saveFile(fileNameS20, s1)
                saveFile(fileNameK20, k1)
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