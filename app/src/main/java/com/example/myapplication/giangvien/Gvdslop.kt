package com.example.myapplication.giangvien

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.TableRowAdapterDslop
import com.example.myapplication.quanly.quanlysv.TableRowAdapterSVdsmondk
import com.example.myapplication.sinhvien.Svdkmon
import kotlinx.android.synthetic.main.gvdslop.*
import kotlinx.android.synthetic.main.gvdslop.view.*


class Gvdslop : Fragment() {
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterDslop: TableRowAdapterDslop

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gvdslop, container, false)
        val data=activity.createContext(this)
        dataInitlize(data)
        val layoutManager = LinearLayoutManager(context)
        tableRecyclerView = v.findViewById(R.id.table_recycler_view_dslop)
        tableRecyclerView.layoutManager = layoutManager
        tableRecyclerView.setHasFixedSize(true)
        tableRowAdapterDslop = TableRowAdapterDslop(data)
        tableRecyclerView.adapter = tableRowAdapterDslop
        return v
    }

    private fun dataInitlize(array: ArrayList<User>) {
    }
}

private fun FragmentActivity?.createContext(gvdslop: Gvdslop): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}