package com.example.myapplication.quanly.quanlysv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.TEMP
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.tablerowmondadk.view.*
import kotlin.collections.ArrayList

class TableRowAdapterSVmondadk(private var userArrayListSV: ArrayList<TEMP>,private var callbak :callbackmon) : RecyclerView.Adapter<TableRowAdapterSVmondadk.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.tablerowmondadk, viewGroup, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
//        viewHolder.mmhcol.text = userArrayListSV[i].First_Name
//        viewHolder.tenmhcol.text = userArrayListSV[i].First_Name
//        viewHolder.tietcol.text = userArrayListSV[i].First_Name
//        viewHolder.thucol.text = userArrayListSV[i].First_Name
        viewHolder.mmhcol.text=userArrayListSV[i].t1
        viewHolder.tenmhcol.text=userArrayListSV[i].t2
        viewHolder.tietcol.text=userArrayListSV[i].t3
        viewHolder.bind(userArrayListSV[i],callbak)
    }

    override fun getItemCount(): Int {
        return userArrayListSV.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mmhcol: TextView = itemView.findViewById(R.id.mmhcol)
        val tenmhcol: TextView = itemView.findViewById(R.id.tenmhcol)
        val tietcol: TextView = itemView.findViewById(R.id.tietcol)
        val thucol: TextView = itemView.findViewById(R.id.thucol)
        val xoacol: TextView = itemView.findViewById(R.id.xoacol)
        fun bind(data:TEMP,callbackmon: callbackmon){
            xoacol.setOnClickListener{callbackmon.onItemClickmondadk(data,adapterPosition)}
        }
    }
    interface callbackmon{
        fun onItemClickmondadk(Data:TEMP,pos:Int)
    }
}


