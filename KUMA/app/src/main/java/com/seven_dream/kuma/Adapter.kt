package com.seven_dream.kuma

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.row_layout.view.*
import org.w3c.dom.Text

class Adapter(internal var activity: Activity,
              internal var lstNewPlan: List<NewPlan>,
              internal var edt_id:EditText,
              internal var edt_date:EditText,
              internal var edt_title:EditText,
              internal var edt_timeBegin:EditText,
              internal var edt_timeEnd:EditText,
              internal var edt_place:EditText,
              internal var edt_memo:EditText): BaseAdapter() {

    internal var inflater:LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.row_layout, null)

        rowView.txt_id.text = lstNewPlan[position].id.toString()
        //rowView.txt_date.text = lstNewPlan[position].date.toString()
        rowView.txt_title.text = lstNewPlan[position].title.toString()
        rowView.txt_timebegin.text = lstNewPlan[position].timebegin.toString()
        rowView.txt_timeend.text = lstNewPlan[position].timeend.toString()
        rowView.txt_place.text = lstNewPlan[position].place.toString()
        rowView.txt_memo.text = lstNewPlan[position].memo.toString()

        rowView.setOnClickListener {
            edt_id.setText(rowView.txt_id.text.toString())
            //edt_date.setText(rowView.txt_date.text.toString())
            edt_title.setText(rowView.txt_title.text.toString())
            edt_timeBegin.setText(rowView.txt_timebegin.text.toString())
            edt_timeEnd.setText(rowView.txt_timeend.text.toString())
            edt_place.setText(rowView.txt_place.text.toString())
            edt_memo.setText(rowView.txt_memo.text.toString())

        }
        return rowView
    }

    override fun getItem(position: Int): Any {
        return lstNewPlan[position]
    }

    override fun getItemId(position: Int): Long {
        return lstNewPlan[position].id.toLong()
    }

    override fun getCount(): Int {
        return lstNewPlan.size
    }

}