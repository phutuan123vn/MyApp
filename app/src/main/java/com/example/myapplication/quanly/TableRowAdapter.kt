package com.application.tableviewwithrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.example.myapplication.model.Usersvqldl
import java.util.*
import kotlin.collections.ArrayList

class TableRowAdapter(private var userArrayList: ArrayList<User>) :
    RecyclerView.Adapter<TableRowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val t: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.table_row_layout, viewGroup, false)
        return ViewHolder(t)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.lnamecol.text = userArrayList[i].Last_Name
        viewHolder.fnamecol.text = userArrayList[i].First_Name
        viewHolder.tkcol.text = userArrayList[i].Email
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lnamecol: TextView = itemView.findViewById(R.id.lnamecol)
        val fnamecol: TextView = itemView.findViewById(R.id.fnamecol)
        //val mssvcol: TextView = itemView.findViewById(R.id.mssvcol)
        val tkcol: TextView = itemView.findViewById(R.id.tkcol)


    }
}

