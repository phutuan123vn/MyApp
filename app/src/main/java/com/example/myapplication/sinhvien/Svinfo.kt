package com.example.myapplication.sinhvien

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.model.TEMP
import kotlinx.android.synthetic.main.svinfo.*
import kotlinx.android.synthetic.main.svinfo.view.*


class Svinfo : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.svinfo, container, false)
        val edit = v.findViewById<Button>(R.id.svinfoedit)
        val db=MYSQLHandler(requireContext())
        db.getInfo(MYSQLHandler.user.id,object :MYSQLHandler.VolleyCallback1{
            override fun onSuccess(Data: ArrayList<TEMP>) {
                super.onSuccess(Data)
                InfoLName.text=Data.get(0).t1
                InfoFName.text=Data.get(0).t2
                InfoMS.text=Data.get(0).t3
                InfoAddress.text=Data.get(0).t4
                InfoCCCD.text=Data.get(0).t5
                InfoPhone.text=Data.get(0).t6
                InfoE.text=Data.get(0).t7
            }
        })
        edit.setOnClickListener {
            val Svinfochange = Svinfochange()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutsvnav, Svinfochange)
            transaction.commit()
        }
        return v
    }

}