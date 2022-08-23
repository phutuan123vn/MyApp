package com.example.myapplication.quanly.quanlysv

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.TEMP
import kotlinx.android.synthetic.main.tablerowmondk.view.*
import kotlin.collections.ArrayList

class TableRowAdapterSVdsmondk(private var userArrayListSV: ArrayList<TEMP>,
                               private var cAllbackdsmon: callbackdsmon) : RecyclerView.Adapter<TableRowAdapterSVdsmondk.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.tablerowmondk, viewGroup, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.mmhcol.text = userArrayListSV[i].t1
//        Log.d("T1",userArrayListSV.get(i).t1)
        viewHolder.tenmhcol.text = userArrayListSV[i].t2
//        Log.d("T2",userArrayListSV.get(i).t2)
        viewHolder.tietcol.text = userArrayListSV[i].t3
//        Log.d("T3",userArrayListSV.get(i).t3)
//        viewHolder.thucol.text = userArrayListSV[i].First_Name
        viewHolder.bind(userArrayListSV[i],cAllbackdsmon)

    }

    override fun getItemCount(): Int {
        return userArrayListSV.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mmhcol: TextView = itemView.findViewById(R.id.mmhcol)
        val tenmhcol: TextView = itemView.findViewById(R.id.tenmhcol)
        val tietcol: TextView = itemView.findViewById(R.id.tietcol)
        val thucol: TextView = itemView.findViewById(R.id.thucol)
        val choncol: TextView = itemView.findViewById(R.id.choncol)
        fun bind(data:TEMP,callback:callbackdsmon){
            choncol.setOnClickListener { callback.onitemclickdsmon(data,adapterPosition) }
        }
    }
    interface callbackdsmon{
        fun onitemclickdsmon(data: TEMP, pos:Int)
    }
}


