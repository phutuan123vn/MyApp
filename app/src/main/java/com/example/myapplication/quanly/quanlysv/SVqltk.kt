package com.example.myapplication.quanly.quanlysv

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


class SVqltk : Fragment(),TableRowAdapterSVqltk.callbackSVqltk {
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterSVqltk: TableRowAdapterSVqltk
    var data1:ArrayList<TEMP> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.sv_qltk, container, false)
        val db=activity?.createContext(this)
        db?.getStuInfoDS(object : MYSQLHandler.VolleyCallback1 {
            override fun onSuccess(Data: ArrayList<TEMP>) {
                super.onSuccess(Data)
                data1 = Data
                val layoutManager = LinearLayoutManager(context)
                tableRecyclerView = v.findViewById(R.id.table_recycler_view)
                tableRecyclerView.layoutManager = layoutManager
                tableRecyclerView.setHasFixedSize(true)
                tableRowAdapterSVqltk = TableRowAdapterSVqltk(data1,this@SVqltk)
                tableRecyclerView.adapter = tableRowAdapterSVqltk
            }
        })
        return v
    }
    override fun onitemclickSVqltk(data: TEMP, pos: Int) {
        Toast.makeText(requireContext(),"Click $pos ${data.t1}", Toast.LENGTH_SHORT).show()
        val bundle=Bundle()
        bundle.putString("Last_name",data.t1)
        bundle.putString("First_name",data.t2)
        bundle.putString("Email",data.t7)
        val svqltkdetail = Svqltkdetail()
        svqltkdetail.arguments=bundle
        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
        transaction.replace(R.id.framelayoutqlnav, svqltkdetail)
        transaction.commit()
    }
}

private fun FragmentActivity?.createContext(sVqltk: SVqltk): MYSQLHandler {
    val db=MYSQLHandler(this!!)
    return db
}


