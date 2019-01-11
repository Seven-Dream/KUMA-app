package com.seven_dream.kuma
/* 講義検索画面での動作 */
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.layout_timetable_search.*
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.view.View


class TimetableSearch :  AppCompatActivity() {

    //対象学群プルダウンの選択肢
    private val spinnerItems = arrayOf("--", "システム工学群", "環境理工学群", "情報学群", "経済・マネジメント学群", "共通科目")
    //override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_timetable_search)

        /* プルダウン機能 */
        //ArrayAdapter
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //spinnerにadapterをセット
        //Kotlin Android Extensions
        spinner_target.adapter = adapter

        //リスナーを登録
        spinner_target.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            //アイテムが選択されたとき
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id:Long) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                //Kotlin Android Extensions
                spinner_target.text = item       //"spinner_target.text"であっているのか確認する
            }
            //「--」が選択されたままのとき、nullとして扱う

            //アイテムが選択されなかったとき
            override fun onNothingSelected(parent: AdapterView<*>?){
                //
            }
        }

        /* 検索機能 */
        //文字入力が終了した状態にて、検索ボタンを使って文字列を取り出す
        search_button.setOnClickListener {}
    }

}