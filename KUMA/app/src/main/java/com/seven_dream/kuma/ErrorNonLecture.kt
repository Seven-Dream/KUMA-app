package com.seven_dream.kuma

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_timetable_result.*
import kotlinx.android.synthetic.main.layout_error_lecture.*

class ErrorNonLecture: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_error_lecture)

        /* 戻るボタンをタップしたときの処理*/
        return_error.setOnClickListener {
            finish()
        }
    }
}