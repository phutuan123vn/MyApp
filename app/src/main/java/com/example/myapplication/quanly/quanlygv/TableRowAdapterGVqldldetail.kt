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

class TableRowAdapterGVqldldetail(private var userArrayListSV: ArrayList<User>) : RecyclerView.Adapter<TableRowAdapterGVqldldetail.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.tablerowqlgvdslop, viewGroup, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
//        viewHolder.hosvcol.text = userArrayListSV[i].First_Name
//        viewHolder.tensvcol.text = userArrayListSV[i].First_Name
//        viewHolder.mssvcol.text = userArrayListSV[i].First_Name
    }

    override fun getItemCount(): Int {
        return userArrayListSV.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hosvcol: TextView = itemView.findViewById(R.id.hosvcol)
        val tensvcol: TextView = itemView.findViewById(R.id.tensvcol)
        val mssvcol: TextView = itemView.findViewById(R.id.mssvcol)
    }
}


