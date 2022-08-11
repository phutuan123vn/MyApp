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
import com.example.myapplication.sinhvien.Svdkmon


class Quanlysvdkmonchange : Fragment() {
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterSVdsmondk: TableRowAdapterSVdsmondk
    private lateinit var tableRowAdapterSVmondadk: TableRowAdapterSVmondadk

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.sv_dkmhchange, container, false)
        val back = v.findViewById<Button>(R.id.quanlysvdkmonchangeback)
        back.setOnClickListener {
            val quanlysvdkmon = Quanlysvdkmon()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, quanlysvdkmon)
            transaction.commit()
        }
        val data=activity.createContext(this)
        dataInitlize(data)
        val layoutManager = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_recycler_view_dsmondk)
        tableRecyclerView.layoutManager = layoutManager
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterSVdsmondk = TableRowAdapterSVdsmondk(data)
        tableRecyclerView.adapter = tableRowAdapterSVdsmondk

        val data1=activity.createContext(this)
        dataInitlize(data1)
        val layoutManager2 = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_recycler_view_qlsvmondadkchange)
        tableRecyclerView.layoutManager = layoutManager2
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterSVmondadk = TableRowAdapterSVmondadk(data1)
        tableRecyclerView.adapter = tableRowAdapterSVmondadk
        return v
    }
    private fun dataInitlize(array: ArrayList<User>) {
    }
}

private fun FragmentActivity?.createContext(quanlysvdkmonchange: Quanlysvdkmonchange): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}