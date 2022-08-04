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
import com.application.tableviewwithrecyclerview.adapter.TableRowAdapterGVdsgv
import com.application.tableviewwithrecyclerview.adapter.TableRowAdapterSVdssv
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.gv_dsgv.view.*


class GVdsgv : Fragment() {
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterGVdsgv: TableRowAdapterGVdsgv
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gv_dsgv, container, false)
        val data=activity.createContext(this)
        dataInitlize(data)
        val layoutManager = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_recycler_view_gvdsgv)
        tableRecyclerView.layoutManager = layoutManager
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterGVdsgv = TableRowAdapterGVdsgv(data)
        tableRecyclerView.adapter = tableRowAdapterGVdsgv
        return v
    }
    private fun dataInitlize(array: ArrayList<User>) {
    }
}

private fun FragmentActivity?.createContext(gVdssv: GVdsgv): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}