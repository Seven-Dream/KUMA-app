package com.seven_dream.kuma
/* 講義検索結果画面の動作 */
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_timetable_result.*

class TimetableResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable_result)

        /* 講義登録ボタン */
        //講義登録ボタンを押した時の動作
        /*
        regist_button.setOnClickListener {

        }
         */

        /* 戻るボタン */
        //戻るボタンを押したら検索結果画面の表示を終了（講義検索画面へ遷移）
        return_result.setOnClickListener {
            finish()
        }

    }
}
