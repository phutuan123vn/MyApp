package com.example.myapplication.quanly.quanlygv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.TEMP
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.TableRowAdapterSVqltk
import kotlin.collections.ArrayList

class TableRowAdapterGVqltk(private var userArrayListGV: ArrayList<TEMP>, private var cAllbackGVqltk:callbackGVqltk) : RecyclerView.Adapter<TableRowAdapterGVqltk.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.table_row_layout, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.lnamegvcol.text = userArrayListGV[i].t1
        viewHolder.fnamegvcol.text = userArrayListGV[i].t2
        viewHolder.msgvcol.text = userArrayListGV[i].t3
        viewHolder.bind(userArrayListGV[i],cAllbackGVqltk)

//        viewHolder.morecol.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v: View?) {
//                val activity = v!!.context as AppCompatActivity
//                val gvqltkdetail = Gvqltkdetail()
//                activity.supportFragmentManager.beginTransaction().replace(R.id.framelayoutqlnav, gvqltkdetail).addToBackStack(null).commit()
//            }
//        })
    }

    override fun getItemCount(): Int {
        return userArrayListGV.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lnamegvcol: TextView = itemView.findViewById(R.id.lnamecol)
        val fnamegvcol: TextView = itemView.findViewById(R.id.fnamecol)
        val msgvcol: TextView = itemView.findViewById(R.id.mscol)
        val morecol: TextView = itemView.findViewById(R.id.morecol)
        fun bind(data:TEMP,callback: callbackGVqltk){
            morecol.setOnClickListener { callback.onitemclickGVqltk(data,adapterPosition) }
        }
    }
    interface callbackGVqltk{
        fun onitemclickGVqltk(data:TEMP, pos:Int)
    }
}


