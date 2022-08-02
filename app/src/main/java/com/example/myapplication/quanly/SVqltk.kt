package com.example.myapplication.quanly

import android.content.Context
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
import com.application.tableviewwithrecyclerview.adapter.TableRowAdapter
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.example.myapplication.model.Usersvqldl


class SVqltk : Fragment() {
    private lateinit var tableRecyclerView : RecyclerView
    private var userList = ArrayList<Usersvqldl>()
    private lateinit var tableRowAdapter: TableRowAdapter
    private lateinit var user : Usersvqldl

    lateinit var Firstname : Array<String>
    lateinit var Lastname : Array<String>
    lateinit var Rolee : Array<String>
    lateinit var Emaill : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.sv_qltk, container, false)
        val data=activity.createContext(this)



//        fun dataInitlize() {
//
//            userList.add(Usersvqldl("Huỳnh Khả", "Giang", "SV191", ""))
//            userList.add(Usersvqldl(data, "Tuấn", "SV192", ""))
//            userList.add(Usersvqldl("Huỳnh Khả", "Giang", "SV193", ""))
//            userList.add(Usersvqldl("Phú Thiên", "Tuấn", "SV194", ""))
//            userList.add(Usersvqldl("Huỳnh Khả", "Giang", "SV195", ""))
//        }
            dataInitlize(data)
            val layoutManager = LinearLayoutManager(context)
            tableRecyclerView = v.findViewById(R.id.table_recycler_view)
            tableRecyclerView.layoutManager = layoutManager
            tableRecyclerView.setHasFixedSize(true)
            tableRowAdapter = TableRowAdapter(data)
            tableRecyclerView.adapter = tableRowAdapter

            val edit = v.findViewById<Button>(R.id.SVqltkchange)


            edit.setOnClickListener {
                val SVqltk = Svqltkdetail()
                val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.framelayoutqlnav, SVqltk)
                transaction.commit()
            }
            return v
        }

    private fun dataInitlize(array: ArrayList<User>) {

    }
}

private fun FragmentActivity?.createContext(sVqltk: SVqltk): ArrayList<User> {
    val db=DatabaseHandler(this!!)
    return db.getAllData()
}

