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
import com.example.myapplication.giangvien.Gvdslop
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.TableRowAdapterDslop


class Quanlygvdslop : Fragment() {
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterDslop: TableRowAdapterDslop

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // back ve fragment qltk
        val v = inflater.inflate(R.layout.gv_dslop, container, false)
        //
//        val edit = v.findViewById<Button>(R.id.quanlygvdslopedit)
//        edit.setOnClickListener {
//            val quanlygvdslopchange = Quanlygvdslopchange()
//            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
//            transaction.replace(R.id.framelayoutqlnav, quanlygvdslopchange)
//            transaction.commit()
//        }
        //
        val back = v.findViewById<Button>(R.id.quanlygvdslopback)
        back.setOnClickListener {
            val quanlygvinfo = Quanlygvinfo()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, quanlygvinfo)
            transaction.commit()
        }
        val data=activity.createContext(this)
        dataInitlize(data)
        val layoutManager = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_recycler_view_qldslop)
        tableRecyclerView.layoutManager = layoutManager
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterDslop = TableRowAdapterDslop(data)
        tableRecyclerView.adapter = tableRowAdapterDslop
        return v
    }
    private fun dataInitlize(array: ArrayList<User>) {
    }
}
private fun FragmentActivity?.createContext(quanlygvdslop: Quanlygvdslop): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}