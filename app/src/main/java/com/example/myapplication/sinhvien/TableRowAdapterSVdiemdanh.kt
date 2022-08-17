package com.example.myapplication.quanly.quanlysv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User
import kotlin.collections.ArrayList

class TableRowAdapterSVdiemdanh(private var userArrayListSV: ArrayList<User>) : RecyclerView.Adapter<TableRowAdapterSVdiemdanh.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.tablerowdiemdanh, viewGroup, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
//        viewHolder.mmhcol.text = userArrayListSV[i].First_Name
//        viewHolder.tenmhcol.text = userArrayListSV[i].First_Name
//        viewHolder.tietcol.text = userArrayListSV[i].First_Name
//        viewHolder.thucol.text = userArrayListSV[i].First_Name
    }

    override fun getItemCount(): Int {
        return userArrayListSV.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mmhcol: TextView = itemView.findViewById(R.id.mmhcol)
        val tenmhcol: TextView = itemView.findViewById(R.id.tenmhcol)
        val lopcol: TextView = itemView.findViewById(R.id.lopcol)
//        val thucol: TextView = itemView.findViewById(R.id.thucol)
    }
}


