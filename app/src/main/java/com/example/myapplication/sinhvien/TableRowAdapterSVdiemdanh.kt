package com.example.myapplication.quanly.quanlysv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.TEMP
import com.example.myapplication.model.User
import kotlin.collections.ArrayList

class TableRowAdapterSVdiemdanh(private var userArrayListSV: ArrayList<TEMP>) : RecyclerView.Adapter<TableRowAdapterSVdiemdanh.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.tablerowdiemdanh, viewGroup, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.mmhcol.text = userArrayListSV[i].t1
        viewHolder.tenmhcol.text = userArrayListSV[i].t2
        viewHolder.lopcol.text=userArrayListSV[i].t3
        viewHolder.checkbox.isChecked = userArrayListSV[i].t4==1.toString()
    }

    override fun getItemCount(): Int {
        return userArrayListSV.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mmhcol: TextView = itemView.findViewById(R.id.mmhcol)
        val tenmhcol: TextView = itemView.findViewById(R.id.tenmhcol)
        val lopcol: TextView = itemView.findViewById(R.id.lopcol)
//        val thucol: TextView = itemView.findViewById(R.id.thucol)
        val checkbox:CheckBox=itemView.findViewById(R.id.attend)
    }
}


