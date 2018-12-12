package com.example.androiddev.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //DBの追加
    lateinit private var userDB : userDB_Adapter//遅延初期化→プロパティ内でインスタンスにアクセス可能？

    override fun onCreate(savedInstanceState: Bundle?) {
        //DBの追加
        userDB = userDB_Adapter(this)//DBの呼び出し
        //val memo = userDB.getMemo(type ,day)//レコードの呼び出し
        //if ( false == userDB.isRecord(("aaa",day )) ){
            // 指定キーのデータなければ追加
            userDB.addRecord(12, 12, "af")
        //}
        val memo = userDB.getMemo(12,12)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapHere.setOnClickListener{
            //textView.text = "タップされたで"
            textView.text = memo
        }
    }
}
