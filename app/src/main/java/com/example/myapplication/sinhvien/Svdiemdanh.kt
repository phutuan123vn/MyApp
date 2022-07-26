package com.example.myapplication.sinhvien

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
//import com.example.myapplication.databinding.SvdiemdanhBinding
import com.example.myapplication.login.LoginSV
import com.example.myapplication.model.TEMP
import com.example.myapplication.model.User
import com.example.myapplication.quanly.quanlysv.TableRowAdapterSVdiemdanh
import com.example.myapplication.quanly.quanlysv.TableRowAdapterSVdsmondk
import kotlinx.android.synthetic.main.svdiemdanh.*
import kotlinx.android.synthetic.main.svdiemdanh.view.*

class Svdiemdanh : Fragment() {
        var output1 :String?=""
    var data:ArrayList<TEMP> = ArrayList()
    private lateinit var tableRecyclerView : RecyclerView
    private lateinit var tableRowAdapterSVdiemdanh: TableRowAdapterSVdiemdanh
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        data.clear()
        val v = inflater.inflate(R.layout.svdiemdanh, container, false)
        val textView : TextView = v.findViewById(R.id.textviewtest)
        val layoutManager = LinearLayoutManager(context)

        val db=MYSQLHandler(requireContext())
        db.datadiemdanh(object :MYSQLHandler.VolleyCallback1{
            override fun onSuccess(Data: ArrayList<TEMP>) {
                super.onSuccess(Data)
                data=Data
                for (i in 0 until data.size) {
                    Log.d("check", Data.get(i).t1 + Data.get(i).t2 + Data.get(i).t3 + Data[i].t4)
                }
                tableRecyclerView = v.findViewById(R.id.table_recycler_view_diemdanh)
                tableRecyclerView.layoutManager = layoutManager
                tableRecyclerView.setHasFixedSize(true)
                tableRowAdapterSVdiemdanh = TableRowAdapterSVdiemdanh(data)
                tableRecyclerView.adapter = tableRowAdapterSVdiemdanh
                tableRecyclerView.adapter!!.notifyDataSetChanged()
            }
        })
        textView.text = output1
        val data=activity.createContext(this)
        dataInitlize(data)

        val qmqr = v.findViewById<Button>(R.id.quetmaqr)
        qmqr.setOnClickListener {
            val Svquetmaqr = Svdiemdanhqr()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            transaction.replace(R.id.framelayoutsvnav, Svquetmaqr)
            transaction.commit()
        }
        return v
    }
    private fun dataInitlize(array: ArrayList<User>) {
    }

}
private fun FragmentActivity?.createContext(sVdiemdanh: Svdiemdanh): ArrayList<User> {
    val db= DatabaseHandler(this!!)
    return db.getAllData()
}