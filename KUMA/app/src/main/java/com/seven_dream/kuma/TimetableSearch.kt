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
import java.util.Optional.empty
import kotlin.math.max

class TimetableSearch :  AppCompatActivity() {
    // userDB_Adapter_Timetableクラスを定義
    private lateinit var userDB: DB_Adapter_Search_Timetable
    //DBと比較するための開講クウォータ
    var quarterData: Int? = null

    //開講クウォータプルダウンの選択肢
    private val spinnerQuarters = arrayOf("--", "1Q", "2Q", "3Q","4Q","1学期", "2学期","通年")

    //override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_timetable_search)
        //DBの呼び出し
        userDB = DB_Adapter_Search_Timetable(this)

      /* プルダウン機能:開講クウォータ */
        //ArrayAdapter
        val adapterQuarter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, spinnerQuarters)
        adapterQuarter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) //ドロップダウンのレイアウトの指定

        //spinnerにadapterをセット
        //Kotlin Android Extensions
        spinner_quarter.adapter = adapterQuarter

        //リスナーを登録
        spinner_quarter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            //アイテムが選択されたとき
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id:Long) {
                val spinnerParent = parent as Spinner
                val quarterItem = spinnerParent.selectedItem as String //選択された文字列を取得
                if (quarterItem != "--") {
                    val selectQuarter: String = quarterItem //新しい変数に選択された文字列を格納
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
            //検索結果(lecture_id)を格納する
            val resultLecture: Array<Int> = arrayOf(0).copyOfRange(0, userDB.getMaxLecture()) // 講義名検索の結果
            val resultTeacher: Array<Int> = arrayOf(0).copyOfRange(0, userDB.getMaxLecture()) // 教員名検索の結果
            var resultQuarter: Array<Int> = arrayOf(0).copyOfRange(0, userDB.getMaxLecture()) // 開講クウォータ検索の結果
            //val resultPrint: Array<Int> = arrayOf(0).copyOfRange(0, userDB.getMaxLecture())//最終的な検索結果
             val resultPrint: ArrayList<Int> = arrayListOf()//最終的な検索結果

            /* format1(講義名)の処理 */
            if (format1.text.toString() != "") {
                var num: Int = userDB.countLectureByName(format1.text) //検索に引っかかった行数を取得(format1と講義名が一致する講義の数)
                var insertTemp = 0 //結果を入れる配列の場所
                num -= 1
                //講義検索の結果を配列に入れる
                for (cou in 0..num) {
                    //format1に入力がある場合、講義名にformat1の文字列を含む講義の講義IDをDBから取得
                    val nameLecture: Int = userDB.getLectureIdByName(format1.text, cou)
                    //format1の結果でえられたIDを格納する配列の宣言
                    resultLecture.set(insertTemp,nameLecture)
                    insertTemp += 1
                }
            } else {
                //NULLの場合、配列にはデータベース上の講義IDをすべて格納する
                var max: Int = userDB.getMaxLecture() //登録されている講義の最大値
                var insertTemp = 0 //結果を入れる配列の場所
                for(cou in 1..max) {
                    //講義ID
                    //lecture_idに対する講義が入っていなければ入れない
                    if(userDB.getLectureById(cou) != "") {
                        resultLecture.set(insertTemp, cou)
                        insertTemp += 1
                    }
                }
            }

            /* format2(教員名)の処理 */
            if (format2.text.toString() != "") {
                var num: Int = userDB.countLectureByTeacher(format2.text) //検索に引っかかった行数を取得(format2と教員名が一致する講義の数)
                num -= 1
                var insertTemp = 0 //結果を入れる配列の場所
                for (cou in 0..num) {
                    //format2に入力がある場合、講義名にformat2の文字列を含む講義の講義IDをDBから取得
                    val teacherLecture: Int = userDB.getLectureIdByTeach(format2.text, cou)
                    //format1の結果でえられたIDを格納する配列の宣言
                    resultTeacher.set(insertTemp, teacherLecture)
                    insertTemp += 1
                }
            } else {
                //NULLの場合、配列にはデータベース上の講義IDをすべて格納する
                var max: Int = userDB.getMaxLecture() //登録されている講義の最大値
                var insertTemp = 0 //結果を入れる配列の場所
                for(cou in 1..max) {
                    //講義ID
                    //lecture_idに対する講義が入っていなければ入れない
                    if(userDB.getLectureById(cou) != "") {
                        resultTeacher.set(insertTemp, cou)
                        insertTemp += 1
                    }
                }
            }

            /* クウォータ */
            // quarterLectureと講義(lecture)テーブルの開講時期(quarter)が一致するとき
            if (quarterData != null) {
                var num: Int = userDB.countLectureByQuarter(quarterData) //検索に引っかかった行数を取得(選択肢とQが一致する講義の数)
                num -= 1
                var insertTemp = 0 //結果を入れる配列の場所
                for (cou in 0..num) {
                    //選択された開講Qが一致する講義の講義IDをDBから取得
                    val quarterLecture: Int = userDB.getLectureIdByQuarter(quarterData, cou)
                    //format1の結果でえられたIDを格納する配列の宣言
                    resultQuarter.set(insertTemp, quarterLecture)
                    insertTemp += 1
                }               
            } else {
                //NULLの場合、配列にはデータベース上の講義IDをすべて格納する
                var max: Int = userDB.getMaxLecture() //登録されている講義の最大値
                var insertTemp = 0 //結果を入れる配列の場所
                for(cou in 1..max) {
                    //講義ID
                    //lecture_idに対する講義が入っていなければ入れない
                    if(userDB.getLectureById(cou) != "") {
                        resultQuarter.set(insertTemp, cou)
                        insertTemp += 1
                    }
                }
            }

            val max: Int = userDB.getMaxLecture()//講義テーブルにあるlecture_idの最大値を取得
            var insertTmp = 0
            for (id  in 1..max) {//講義テーブルに入ってるid全部で比較する
                //講義名から取得したID・教員名から取得したID・開講クウォータから取得したIDすべてに"id"が含まれていれば、resultPrintに格納
                if (resultLecture.contains(id)) {
                    if (resultTeacher.contains(id)) {
                        if (resultQuarter.contains(id)) {
                            //最終的な結果を入れる配列(resultPrint)にidを格納する
                            resultPrint.add(id)
                            //resultPrint[insertTmp] = id //結果で表示すべき講義の講義IDを格納
                            insertTmp += 1
                        }
                    }
                }
            }

            /* 検索ボタンによる画面遷移*/
            // resultPrintに何も格納されなかったとき、エラー画面へ遷移
            if(resultPrint.size == 0){
                val intent = Intent(this, ErrorNonLecture::class.java)
                startActivity(intent)
            } else {
                //resultPrintに1つ以上の値が格納されたとき、結果表示画面へ遷移
                val intent = Intent(this, TimetableResult::class.java)
                intent.putExtra("resultArray", resultPrint) //TimetableResultに引き渡す値の設定
                startActivity(intent)
            }
        }

        /* 戻るボタン */
        return_search.setOnClickListener {
            // val intent = Intent(application, Timetable::class.java)
            finish()
            //startActivity(intent)
        }
    }
}
