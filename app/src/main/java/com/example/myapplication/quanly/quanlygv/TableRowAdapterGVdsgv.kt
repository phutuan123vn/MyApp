package com.example.myapplication.quanly.quanlygv

import android.view.LayoutInflater
import android.view.SurfaceControl
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.TEMP
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.TableRowAdapterSVdssv
import kotlin.collections.ArrayList

class TableRowAdapterGVdsgv(private var userArrayListGV: ArrayList<TEMP>,
                            private var cAllbackGVdssv: callbackGVdssv
) : RecyclerView.Adapter<TableRowAdapterGVdsgv.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.table_row_layout, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.lnamecol.text = userArrayListGV[i].t1
        viewHolder.fnamecol.text = userArrayListGV[i].t2
        viewHolder.mssvcol.text = userArrayListGV[i].t3
        viewHolder.bind(userArrayListGV[i],cAllbackGVdssv)

//        viewHolder.morecol.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v: View?) {
//                val activity = v!!.context as AppCompatActivity
//                val quanlygvinfo = Quanlygvinfo()
//                activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
//                activity.supportFragmentManager.beginTransaction().replace(R.id.framelayoutqlnav, quanlygvinfo).addToBackStack(null).commit()
//            }
//        })
    }

    override fun getItemCount(): Int {
        return userArrayListGV.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lnamecol: TextView = itemView.findViewById(R.id.lnamecol)
        val fnamecol: TextView = itemView.findViewById(R.id.fnamecol)
        val mssvcol: TextView = itemView.findViewById(R.id.mscol)
        val morecol: TextView = itemView.findViewById(R.id.morecol)
        fun bind(data:TEMP,callback: callbackGVdssv) {
            morecol.setOnClickListener { callback.onitemclickGVdsgv(data, adapterPosition) }
        }
    }
    interface callbackGVdssv{
        fun onitemclickGVdsgv(data: TEMP, pos:Int)
    }
}


