package com.seven_dream.kuma

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_counter.*
import java.io.File


class Count_4q : AppCompatActivity() {

    private val fileNameS1 = "count4S1.txt"
    private val fileNameK1 = "count4K1.txt"
    private val fileNameS2 = "count4S2.txt"
    private val fileNameK2 = "count4K2.txt"
    private val fileNameS3 = "count4S3.txt"
    private val fileNameK3 = "count4K3.txt"
    private val fileNameS4 = "count4S4.txt"
    private val fileNameK4 = "count4K4.txt"
    private val fileNameS5 = "count4S5.txt"
    private val fileNameK5 = "count4K5.txt"
    private val fileNameS6 = "count4S6.txt"
    private val fileNameK6 = "count4K6.txt"
    private val fileNameS7 = "count4S7.txt"
    private val fileNameK7 = "count4K7.txt"
    private val fileNameS8 = "count4S8.txt"
    private val fileNameK8 = "count4K8.txt"
    private val fileNameS9 = "count4S9.txt"
    private val fileNameK9 = "count4K9.txt"
    private val fileNameS10 = "count4S10.txt"
    private val fileNameK10 = "count4K10.txt"
    private val fileNameS11 = "count4S11.txt"
    private val fileNameK11 = "count4K11.txt"
    private val fileNameS12 = "count4S12.txt"
    private val fileNameK12 = "count4K12.txt"
    private val fileNameS13 = "count4S13.txt"
    private val fileNameK13 = "count4K13.txt"
    private val fileNameS14 = "count4S14.txt"
    private val fileNameK14 = "count4K14.txt"
    private val fileNameS15 = "count4S15.txt"
    private val fileNameK15 = "count4K15.txt"
    private val fileNameS16 = "count4S16.txt"
    private val fileNameK16 = "count4K16.txt"
    private val fileNameS17 = "count4S17.txt"
    private val fileNameK17 = "count4K17.txt"
    private val fileNameS18 = "count4S18.txt"
    private val fileNameK18 = "count4K18.txt"
    private val fileNameS19 = "count4S19.txt"
    private val fileNameK19 = "count4K19.txt"
    private val fileNameS20 = "count4S20.txt"
    private val fileNameK20 = "count4K20.txt"
    private val fileNameS21 = "count4S21.txt"
    private val fileNameK21 = "count4K21.txt"
    private val fileNameS22 = "count4S22.txt"
    private val fileNameK22 = "count4K22.txt"
    private val fileNameS23 = "count4S23.txt"
    private val fileNameK23 = "count4K23.txt"
    private val fileNameS24 = "count4S24.txt"
    private val fileNameK24 = "count4K24.txt"
    private val fileNameS25 = "count4S25.txt"
    private val fileNameK25 = "count4K25.txt"

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
            val intent = Intent(this, AttendCheck_4q::class.java)
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
            }else if(p == 11) {
                saveFile(fileNameS21, s1)
                saveFile(fileNameK21, k1)
            }else if(p == 12){
                saveFile(fileNameS22, s1)
                saveFile(fileNameK22, k1)
            }else if(p == 13){
                saveFile(fileNameS23, s1)
                saveFile(fileNameK23, k1)
            }else if(p == 14){
                saveFile(fileNameS24, s1)
                saveFile(fileNameK24, k1)
            }else if(p == 15){
                saveFile(fileNameS25, s1)
                saveFile(fileNameK25, k1)
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