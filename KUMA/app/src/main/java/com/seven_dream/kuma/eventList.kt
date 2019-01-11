package com.example.kawak.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.kawak.myapplication.R.layout.line_style

class  eventList: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_list)


        val liststyle =
        val listView = findViewById<ListView>(R.id.eventList)

        val adapter = ArrayAdapter<String>(this, R.layout.line_style,)

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val eventName = adapter.getItem(position)
            Toast.makeText(this, eventName, Toast.LENGTH_SHORT).show()
        }
    }
}
