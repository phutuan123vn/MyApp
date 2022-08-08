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

class TableRowAdapterSVdssv(private var userArrayListSV: ArrayList<User>) : RecyclerView.Adapter<TableRowAdapterSVdssv.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.table_row_layout, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.lnamecol.text = userArrayListSV[i].Last_Name
        viewHolder.fnamecol.text = userArrayListSV[i].First_Name
        viewHolder.mssvcol.text = userArrayListSV[i].Email

        viewHolder.morecol.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                val quanlysvdkmon = Quanlysvdkmon()
                activity.supportFragmentManager.beginTransaction().replace(R.id.framelayoutqlnav, quanlysvdkmon).addToBackStack(null).commit()
            }
        })
    }

    override fun getItemCount(): Int {
        return userArrayListSV.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lnamecol: TextView = itemView.findViewById(R.id.lnamecol)
        val fnamecol: TextView = itemView.findViewById(R.id.fnamecol)
        val mssvcol: TextView = itemView.findViewById(R.id.mscol)
        val morecol: TextView = itemView.findViewById(R.id.morecol)
    }
}


