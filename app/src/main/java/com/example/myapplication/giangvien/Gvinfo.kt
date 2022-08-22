package com.example.myapplication.giangvien

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import androidx.navigation.Navigation
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.model.TEMP
import kotlinx.android.synthetic.main.gvinfo.*
import kotlinx.android.synthetic.main.gvinfo.view.*


class Gvinfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var Daat = TEMP()
        val v = inflater.inflate(R.layout.gvinfo, container, false)
        val edit = v.findViewById<Button>(R.id.gvinfoedit)
        val db=MYSQLHandler(requireContext())
        db.getTeachInfo(MYSQLHandler.user.id,object :MYSQLHandler.VolleyCallback1{
            override fun onSuccess(Data: ArrayList<TEMP>) {
                super.onSuccess(Data)
                Daat=Data[0]
                GVINfoLName.text=Data.get(0).t1
                GVInfoFName.text=Data.get(0).t2
                GVInfoMS.text=Data.get(0).t3
                GVInfoSub.text=Data.get(0).t4
                GVInfoAddress.text=Data.get(0).t5
                GVInfoPersonID.text=Data.get(0).t6
                GVInfoPhone.text=Data.get(0).t7
                GVInfoEmail.text=Data.get(0).t8
            }
        })
        edit.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("Ho",Daat.t1)
            bundle.putString("Ten",Daat.t2)
            bundle.putString("Address",Daat.t5)
            bundle.putString("PersonID",Daat.t6)
            bundle.putString("Phone",Daat.t7)
            bundle.putString("Email",Daat.t8)
            val gvinfochange = Gvinfochange()
            gvinfochange.arguments=bundle
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            transaction.replace(R.id.framelayoutgvnav, gvinfochange)
            transaction.commit()
        }
        return v
    }

}