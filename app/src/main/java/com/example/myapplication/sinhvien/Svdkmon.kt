package com.example.myapplication.sinhvien

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.doAsync
import com.example.myapplication.model.TEMP
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.TableRowAdapterSVdsmondk
import com.example.myapplication.quanly.quanlysv.TableRowAdapterSVmondadk


class Svdkmon : Fragment() {
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterSVdsmondk: TableRowAdapterSVdsmondk
    private lateinit var tableRowAdapterSVmondadk: TableRowAdapterSVmondadk
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.svdkmon, container, false)
        var data= ArrayList<TEMP>()
        val db=activity?.createContext(this@Svdkmon)
        db?.getSub(object : MYSQLHandler.VolleyCallback1{
            override fun onSuccess(Data: ArrayList<TEMP>) {
                super.onSuccess(Data)
                data=Data
                for (i in 0..data.size-1) {
                    Log.d("check", Data.get(i).t1 + Data.get(i).t2 + Data.get(i).t3)
                }
                val layoutManager = LinearLayoutManager(context)
                tableRecyclerView = v.findViewById(R.id.table_recycler_view_dsmondk)
                tableRecyclerView.layoutManager = layoutManager
                tableRecyclerView.setHasFixedSize(true)
                tableRowAdapterSVdsmondk = TableRowAdapterSVdsmondk(data)
                tableRecyclerView.adapter = tableRowAdapterSVdsmondk
            }
        })


//        val data1=activity.createContext(this)
//        dataInitlize(data1)
//        val layoutManager2 = LinearLayoutManager(context)
//        tableRecyclerView = v.findViewById(R.id.table_recycler_view_mondadk)
//        tableRecyclerView.layoutManager = layoutManager2
//        tableRecyclerView.setHasFixedSize(true)
//        tableRowAdapterSVmondadk = TableRowAdapterSVmondadk(data1)
//        tableRecyclerView.adapter = tableRowAdapterSVmondadk

        return v
    }
    private fun dataInitlize(array: ArrayList<User>) {
    }
}


private fun FragmentActivity?.createContext(sVdkmon: Svdkmon): MYSQLHandler {
    val db= MYSQLHandler(this!!)
    return db
}