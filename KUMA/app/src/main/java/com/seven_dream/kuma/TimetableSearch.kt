package com.seven_dream.kuma
/* 講義検索画面での動作 */
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.layout_timetable_search.*
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.view.View


class TimetableSearch :  AppCompatActivity() {

    //対象年次プルダウンの選択肢
    private val spinnerGrades = arrayOf("--", "1年次・回生", "2年次・回生", "3年次・回生", "4年次・回生", "その他")
    //開講クウォータプルダウンの選択肢
    private val spinnerQuarters = arrayOf("--", "1Q", "2Q", "3Q","4Q")
    //対象学群プルダウンの選択肢
    private val spinnerTargets = arrayOf("--", "システム工学群", "環境理工学群", "情報学群", "経済・マネジメント学群", "共通科目")
    //override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_timetable_search)

        /* プルダウン機能 */
        /* 対象学年 */
        //ArrayAdapter
        val adapterGrade = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, spinnerGrades)
        adapterGrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //spinnerにadapterをセット
        //Kotlin Android Extensions
        spinner_grade.adapter = adapterGrade

        //リスナーを登録
        spinner_grade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            //アイテムが選択されたとき
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id:Long) {
                val spinnerParent = parent as Spinner
                val gradeItem = spinnerParent.selectedItem as String
                if(gradeItem != "--") {
                    val selectGrade: String = gradeItem
                    //DBと比較（対象年次）

                    //DBと一致する講義のIDを取得：配列に格納
                    val gradeLecture:Array<Int> = arrayOf() //arrayOfの中にIDをいれる
                } else {
                    //「--」が選択されたままのとき、nullとして扱う
                    val gradeLecture: String? = null
                    //
                }
            }
            /*
            //アイテムが選択されなかったとき
            override fun onNothingSelected(parent: AdapterView<*>?){
                //
            }
            */
        }

        /* 開講クウォータ */
        //ArrayAdapter
        val adapterQuarter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, spinnerQuarters)
        adapterQuarter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //spinnerにadapterをセット
        //Kotlin Android Extensions
        spinner_quarter.adapter = adapterQuarter

        //リスナーを登録
        spinner_quarter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            //アイテムが選択されたとき
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id:Long) {
                val spinnerParent = parent as Spinner
                val quarterItem = spinnerParent.selectedItem as String
                if (quarterItem != "--") {
                    val selectQuarter: String = quarterItem
                    //DBと比較（開講クウォータ）

                    //DBと一致する講義のIDを取得：配列に格納
                    val quarterLecture: Array<Int> = arrayOf() //arrayOfの中にIDをいれる
                } else {
                    //「--」が選択されたままのとき、nullとして扱う
                    val quarterLecture: String? = null
                    //
                }
            }
        }

        /* 対象学群 */
        //ArrayAdapter
        val adapterTarget = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, spinnerTargets)
        adapterTarget.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //spinnerにadapterをセット
        //Kotlin Android Extensions
        spinner_target.adapter = adapterTarget

        //リスナーを登録
        spinner_target.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            //アイテムが選択されたとき
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id:Long) {
                val spinnerParent = parent as Spinner
                val targetItem = spinnerParent.selectedItem as String
                if (targetItem != "--") {
                    val selectTarget: String = targetItem
                    //DBと比較（対象学群）

                    //DBと一致する講義のIDを取得：配列に格納
                    val targetLecture: Array<Int> = arrayOf() //arrayOfの中にIDをいれる
                } else {
                    //「--」が選択されたままのとき、nullとして扱う
                    val targetLecture: String? = null
                    //
                }
            }
        }

        /* 検索機能 */
        //文字入力が終了した状態にて、検索ボタンを使って文字列を取り出す
        search_button.setOnClickListener {
            /* format1(講義名)の処理 */
            if(format1.text != NULL) {
                //format1に入力がある場合、DBと比較

                //DBと一致する講義のIDを取得：配列に格納(入力された文字列が講義名に含まれるものの講義IDを格納)
                //format1の結果でえられたIDを格納する配列の宣言
                val nameLecture:Array<Int> = arrayOf() //arrayOfの中にIDをいれる
            } else {
                //NULLの場合、配列にはデータベース上の講義IDをすべて格納する
                val nameLecture:Array<Int> = arrayOf() //arrayOfの中にIDをいれる
            }

            /* ①：講義名から取得したID・対象年次から取得したIDを比較 */
            /* ②：①で取得したID・開講クウォータから取得したIDを比較 */
            /* ③：②で取得したID・対象学群から取得したIDを比較 */
        }

        /* 戻るボタン */
        /*
        return_search.setOnClickListener {
            val intent = Intent(application, Timetable::class.java)
            startActivity(intent)
        }
        */
    }

}