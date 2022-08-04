package com.example.myapplication.quanly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.tableviewwithrecyclerview.adapter.TableRowAdapterSVdssv
import com.application.tableviewwithrecyclerview.adapter.TableRowAdapterSVqltk
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.sv_dssv.view.*


class SVdssv : Fragment() {
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterSVdssv: TableRowAdapterSVdssv
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.sv_dssv, container, false)
        val data=activity.createContext(this)
        dataInitlize(data)
        val layoutManager = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_recycler_view_svdssv)
        tableRecyclerView.layoutManager = layoutManager
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterSVdssv = TableRowAdapterSVdssv(data)
        tableRecyclerView.adapter = tableRowAdapterSVdssv
        return v
    }
    private fun dataInitlize(array: ArrayList<User>) {
    }
}

private fun FragmentActivity?.createContext(sVdssv: SVdssv): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}
