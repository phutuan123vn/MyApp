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
        var Daat=TEMP()
        val v = inflater.inflate(R.layout.svinfo, container, false)
        val edit = v.findViewById<Button>(R.id.svinfoedit)
        val db=MYSQLHandler(requireContext())
        db.getStuInfo(MYSQLHandler.user.id,object :MYSQLHandler.VolleyCallback1{
            override fun onSuccess(Data: ArrayList<TEMP>) {
                super.onSuccess(Data)
                Daat=Data[0]
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
            val bundle=Bundle()
            bundle.putString("Ho",Daat.t1)
            bundle.putString("Ten",Daat.t2)
            bundle.putString("Address",Daat.t4)
            bundle.putString("PersonID",Daat.t5)
            bundle.putString("Phone",Daat.t6)
            bundle.putString("Email",Daat.t7)
            val svinfochange = Svinfochange()
            svinfochange.arguments=bundle
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            transaction.replace(R.id.framelayoutsvnav, svinfochange)
            transaction.commit()
        }
        return v
    }

}