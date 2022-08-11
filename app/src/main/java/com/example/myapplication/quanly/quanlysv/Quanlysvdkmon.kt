package com.example.myapplication.quanly.quanlysv

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


class Quanlysvdkmon : Fragment() {
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterQLSVmondadk: TableRowAdapterQLSVmondadk

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.sv_dkmh, container, false)
        val back = v.findViewById<Button>(R.id.quanlysvdkmonback)
        back.setOnClickListener {
            val svdssv = SVdssv()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, svdssv)
            transaction.commit()
        }
        val edit = v.findViewById<Button>(R.id.quanlysvdkmonedit)
        edit.setOnClickListener{
            val svdkmonchange = Quanlysvdkmonchange()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, svdkmonchange)
            transaction.commit()
        }
        val data1=activity.createContext(this)
        dataInitlize(data1)
        val layoutManager2 = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_qlsvmondadk)
        tableRecyclerView.layoutManager = layoutManager2
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterQLSVmondadk = TableRowAdapterQLSVmondadk(data1)
        tableRecyclerView.adapter = tableRowAdapterQLSVmondadk

        return v
    }

    private fun dataInitlize(array: ArrayList<User>) {
    }
}


private fun FragmentActivity?.createContext(quanLysvdkmon: Quanlysvdkmon): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}