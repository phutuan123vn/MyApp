package com.example.myapplication.sinhvien

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.doAsync
import com.example.myapplication.model.TEMP
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.TableRowAdapterSVdsmondk
import com.example.myapplication.quanly.quanlysv.TableRowAdapterSVmondadk


class Svdkmon : Fragment(),TableRowAdapterSVdsmondk.callbackclick {
    private lateinit var tableRecyclerView1 : RecyclerView
    private lateinit var tableRecyclerView2 : RecyclerView
    private lateinit var tableRowAdapterSVdsmondk: TableRowAdapterSVdsmondk
    private lateinit var tableRowAdapterSVmondadk: TableRowAdapterSVmondadk
    val data1:ArrayList<TEMP> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        data1.clear()
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
                tableRecyclerView1 = v.findViewById(R.id.table_recycler_view_dsmondk)
                tableRecyclerView1.layoutManager = layoutManager
                tableRecyclerView1.setHasFixedSize(true)
                tableRowAdapterSVdsmondk = TableRowAdapterSVdsmondk(data,this@Svdkmon)
                tableRecyclerView1.adapter = tableRowAdapterSVdsmondk
            }
        })

        val layoutManager2 = LinearLayoutManager(context)
        tableRecyclerView2 = v.findViewById(R.id.table_recycler_view_mondadk)
        tableRecyclerView2.layoutManager = layoutManager2
        tableRecyclerView2.setHasFixedSize(true)
        tableRowAdapterSVmondadk = TableRowAdapterSVmondadk(data1)
        tableRecyclerView2.adapter = tableRowAdapterSVmondadk
        tableRecyclerView2.adapter!!.notifyDataSetChanged()

        return v
    }
    private fun dataInitlize(data: TEMP) {
        data1.add(data)
        tableRowAdapterSVmondadk = TableRowAdapterSVmondadk(data1)
        tableRecyclerView2.adapter = tableRowAdapterSVmondadk
        tableRecyclerView2.adapter!!.notifyDataSetChanged()
    }

    override fun onitemclick(data: TEMP, pos: Int) {
        Toast.makeText(requireContext(),"Click $pos ${data.t1}",Toast.LENGTH_SHORT).show()
        dataInitlize(data)

    }
}


private fun FragmentActivity?.createContext(sVdkmon: Svdkmon): MYSQLHandler {
    val db= MYSQLHandler(this!!)
    return db
}