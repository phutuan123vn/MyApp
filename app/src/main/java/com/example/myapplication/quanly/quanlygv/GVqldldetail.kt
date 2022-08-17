package com.example.myapplication.quanly.quanlygv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.TableRowAdapterGVqldldetail


class GVqldldetail : Fragment() {
    private lateinit var tableRecyclerView: RecyclerView
    private lateinit var tableRowAdapterGVdldetail: TableRowAdapterGVqldldetail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gv_qldldetail, container, false)
        val back = v.findViewById<Button>(R.id.Gvqldlback)
        back.setOnClickListener {
            val GVqldl = GVqldl()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_left)
            transaction.replace(R.id.framelayoutqlnav, GVqldl)
            transaction.commit()
        }
        val data = activity.createContext(this)
        dataInitlize(data)
        val layoutManager = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_recycler_view_qlgvdslop)
        tableRecyclerView.layoutManager = layoutManager
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterGVdldetail = TableRowAdapterGVqldldetail(data)
        tableRecyclerView.adapter = tableRowAdapterGVdldetail
        return v
    }
    private fun dataInitlize(array: ArrayList<User>) {
    }
}
private fun FragmentActivity?.createContext(gVqldldetail: GVqldldetail): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}