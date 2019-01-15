package com.seven_dream.kuma
/* 講義検索画面での動作 */
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.layout_timetable_search.*
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.view.View
import com.example.androiddev.myapplication.userDB_Helper

//import com.example.androiddev.myapplication.userDB_Helper


class TimetableSearch :  AppCompatActivity() {
    // userDB_Adapter_Timetableクラスを定義
    private lateinit var userDB: DB_Adapter_Search_Timetable

    //開講クウォータプルダウンの選択肢
    private val spinnerQuarters = arrayOf("--", "1Q", "2Q", "3Q","4Q")

    //override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_timetable_search)
        //DBの呼び出し
        userDB = DB_Adapter_Search_Timetable(this)

        /* プルダウン機能 */
        /* 開講クウォータ */
        //ArrayAdapter
        val adapterQuarter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, spinnerQuarters)
        adapterQuarter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //spinnerにadapterをセット
        //Kotlin Android Extensions
        spinner_quarter.adapter = adapterQuarter
        var quarterData: Int? = null
        //リスナーを登録
        spinner_quarter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            //アイテムが選択されたとき
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id:Long) {
                val spinnerParent = parent as Spinner
                val quarterItem = spinnerParent.selectedItem as String
                if (quarterItem != "--") {
                    val selectQuarter: String = quarterItem
                    if(selectQuarter == "1Q") {
                        quarterData = 1
                    } else if(selectQuarter == "2Q") {
                        quarterData = 2
                    } else if(selectQuarter == "3Q") {
                        quarterData = 3
                    } else if(selectQuarter == "4Q") {
                        quarterData = 4
                    } else if(selectQuarter == "1学期") {
                        quarterData = 5
                    } else if(selectQuarter == "2学期") {
                        quarterData = 6
                    } else if(selectQuarter == "通年") {
                        quarterData = 7
                    }
                } else {
                    //「--」が選択されたままのとき、nullとして扱う
                    quarterData = null
                }
            }
        }

        /* 検索機能 */
        //文字入力が終了した状態にて、検索ボタンを使って文字列を取り出す
        search_button.setOnClickListener {
            //DBを取得
            //val db = userDB.writableDatabase //val db = userDB!!.writableDatabase

            //検索結果(lecture_id)を格納する
            var resultLecture: Array<Int> = arrayOf()
            var resultTeacher: Array<Int> = arrayOf()
            var resultQuarter: Array<Int> = arrayOf()
            var resultPrint: Array<Int> = arrayOf()//最終的な検索結果


                /* format1(講義名)の処理 */
                if (format1.text != null) {
                    val i: Int = userDB.countLecture(format1.text.toString())
                    var insertTemp = 0 //結果を入れる配列の場所
                    //講義検索の結果を配列に入れる
                    for (cou in 0..i) {
                        //format1に入力がある場合、講義名にformat1の文字列を含む講義の講義IDをDBから取得
                        val nameLecture: Int = userDB.getLecture_idByName(format1.text, cou)
                        //format1の結果でえられたIDを格納する配列の宣言
                        //val resultLecture = arrayOf(nameLecture) //arrayOfの中にIDをいれる
                        resultLecture[insertTemp] = nameLecture //arrayOfの中にIDをいれる
                        insertTemp += 1
                    }
                } else {
                    //NULLの場合、配列にはデータベース上の講義IDをすべて格納する
                    val nameLecture: Int = userDB.getAllLecture()
                    //val resultLecture: Array<Int> = arrayOf(nameLecture) //arrayOfの中にIDをいれる
                    resultLecture = arrayOf(nameLecture) //arrayOfの中にIDをいれる
                }

                /* format2(教員名)の処理 */
                if (format2.text != null) {
                    val i: Int = userDB.countLecture(format2.text.toString())
                    var insertTemp = 0 //結果を入れる配列の場所
                    for (cou in 0..i) {
                        //format1に入力がある場合、講義名にformat1の文字列を含む講義の講義IDをDBから取得
                        val teacherLecture: Int = userDB.getLecture_idByTeach(format2.text, cou)
                        //format1の結果でえられたIDを格納する配列の宣言
                        //val resultTeacher: Array<Int> = arrayOf(teacherLecture) //arrayOfの中にIDをいれる
                        resultTeacher[insertTemp] = teacherLecture //arrayOfの中にIDをいれる
                        insertTemp += 1
                    }
                } else {
                    //NULLの場合、配列にはデータベース上の講義IDをすべて格納する
                    val teacherLecture: Int = userDB.getAllLectureByTeach()
                    //val resultTeacher: Array<Int> = arrayOf(nameLecture) //arrayOfの中にIDをいれる
                    resultTeacher = arrayOf(teacherLecture) //arrayOfの中にIDをいれる
                }

                /* クウォータ */
                // quarterLectureと講義(lecture)テーブルの開講時期(quarter)が一致するとき
                if (quarterData != null) {
                    val i: Int = userDB.countLecture()
                    for (cou in 0..i) {
                        //format1に入力がある場合、講義名にformat1の文字列を含む講義の講義IDをDBから取得
                        val quarterLecture: Int = userDB.getLecture_idByQuarter(quarterData, cou)
                            userDB.getLecture_idByQuarter(quarterLecture, cou)//i番目のlecture_idをとってくる
                        //format1の結果でえられたIDを格納する配列の宣言
                        //val resultQuarter: Array<Int> = arrayOf(quarterLecture) //arrayOfの中にIDをいれる
                        resultQuarter = arrayOf(quarterLecture) //arrayOfの中にIDをいれる
                    }
                } else {
                    //NULLの場合、配列にはデータベース上の講義IDをすべて格納する
                    val quarterLecture: Int = userDB.getAllLectureByQuarter()
                    //val resultQuarter: Array<Int> = arrayOf(quarterLecture) //arrayOfの中にIDをいれる
                    resultQuarter = arrayOf(quarterLecture) //arrayOfの中にIDをいれる
                }

                /* ①：講義名から取得したID・教員名から取得したIDを比較 */
                /* ②：①で取得したID・開講クウォータから取得したIDを比較 */
                val i: Int = userDB.getMaxLecture()//講義テーブルにあるlecture_idの最大値を取得
                var insertTmp = 0
                for (id in 0..i) {//講義テーブルに入ってるid全部で比較する
                    //resultLecture内にlectureテーブル[lecture_id]のi番目の講義IDが含まれるかどうか
                    val checkLecture = resultLecture.filter { it == id }
                    //resultTeacher内にlectureテーブル[lecture_id]のi番目の講義IDが含まれるかどうか
                    val checkTeacher = resultTeacher.filter { it == id }
                    //resultQuarter内にlectureテーブル[lecture_id]のi番目の講義IDが含まれるかどうか
                    val checkQuarter = resultQuarter.filter { it == id }

                    if (checkLecture[0] == id) {
                        if (checkTeacher[0] == id) {
                            if (checkQuarter[0] == id) {
                                //最終的な結果を入れる配列(resultPrint)にidを格納する
                                resultPrint[insertTmp] = id
                                insertTmp += 1
                            }
                        }
                    }
                }
            }




        /* 戻るボタン */
        return_search.setOnClickListener {
            val intent = Intent(application, TitleController::class.java) // 本当は違うよ、テストだよ
            // val intent = Intent(application, Timetable::class.java)
            startActivity(intent)
        }
    }

}