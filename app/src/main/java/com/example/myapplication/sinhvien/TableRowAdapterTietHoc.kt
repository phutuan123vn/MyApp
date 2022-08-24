package com.example.myapplication.quanly.quanlysv

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User
import kotlin.collections.ArrayList

class TableRowAdapterTietHoc(private var userArrayListTietHoc: ArrayList<User>) : RecyclerView.Adapter<TableRowAdapterTietHoc.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.tablerowtiethoc, viewGroup, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
//        viewHolder.tiettkbcol.text = userArrayListTietHoc[i].First_Name
//        viewHolder.mmhtkbcol.text = userArrayListTietHoc[i].First_Name
                if((i % 2) != 0){
           viewHolder.itemView.setBackgroundColor(Color.parseColor("#800A7E8C"));
        }

    }

    override fun getItemCount(): Int {
        return userArrayListTietHoc.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tiettkbcol: TextView = itemView.findViewById(R.id.tiettkbcol)
        val mmhtkbcol: TextView = itemView.findViewById(R.id.mmhtkbcol)
    }
}


