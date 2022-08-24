package com.example.myapplication.sinhvien

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.TableRowAdapterTietHoc


class thu3 : Fragment() {
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterTietHoc: TableRowAdapterTietHoc

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_thu3, container, false)
        val data=activity.createContext(this)
        dataInitlize(data)
        val layoutManager = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_recycler_view_thu3)
        tableRecyclerView.layoutManager = layoutManager
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterTietHoc = TableRowAdapterTietHoc(data)
        tableRecyclerView.adapter = tableRowAdapterTietHoc
        return v
    }
    private fun dataInitlize(array: ArrayList<User>) {
    }
}

private fun FragmentActivity?.createContext(tHu3: thu3): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}

