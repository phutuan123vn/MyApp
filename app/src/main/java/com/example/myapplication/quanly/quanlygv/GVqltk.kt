package com.example.myapplication.quanly.quanlygv

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


class GVqltk : Fragment() {
    private lateinit var tableRecyclerView: RecyclerView
    private lateinit var tableRowAdapterGVqltk: TableRowAdapterGVqltk

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gv_qltk, container, false)
        val data = activity.createContext(this)
        dataInitlize(data)
        val layoutManager = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_recycler_view)
        tableRecyclerView.layoutManager = layoutManager
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterGVqltk = TableRowAdapterGVqltk(data)
        tableRecyclerView.adapter = tableRowAdapterGVqltk
        return v
    }

    private fun dataInitlize(array: ArrayList<User>) {
    }
}
private fun FragmentActivity?.createContext(gVqltk: GVqltk): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}