package com.example.myapplication.quanly.quanlygv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.model.TEMP
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.Quanlysvdkmon


class GVqltk : Fragment(),TableRowAdapterGVqltk.callbackGVqltk {
    private lateinit var tableRecyclerView: RecyclerView
    private lateinit var tableRowAdapterGVqltk: TableRowAdapterGVqltk
    var data1:ArrayList<TEMP> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gv_qltk, container, false)
        val db = activity?.createContext(this)
        db?.getTeachInfoDS(object : MYSQLHandler.VolleyCallback1 {
            override fun onSuccess(Data: ArrayList<TEMP>) {
                super.onSuccess(Data)
                data1 = Data
                val layoutManager = LinearLayoutManager(context)
                tableRecyclerView = v.findViewById(R.id.table_recycler_view)
                tableRecyclerView.layoutManager = layoutManager
                tableRecyclerView.setHasFixedSize(true)
                tableRowAdapterGVqltk = TableRowAdapterGVqltk(data1,this@GVqltk)
                tableRecyclerView.adapter = tableRowAdapterGVqltk
            }
        })
        return v
    }
    override fun onitemclickGVqltk(data: TEMP, pos: Int) {
        Toast.makeText(requireContext(),"Click $pos ${data.t1}", Toast.LENGTH_SHORT).show()
        val gvqltkdetail = Gvqltkdetail()
        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
        transaction.replace(R.id.framelayoutqlnav, gvqltkdetail)
        transaction.commit()
    }

}
private fun FragmentActivity?.createContext(gVqltk: GVqltk): MYSQLHandler {
    val db= MYSQLHandler(this!!)
    return db
}