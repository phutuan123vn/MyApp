package com.example.myapplication.giangvien

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
import kotlinx.android.synthetic.main.gvinfochange.*
import kotlinx.android.synthetic.main.gvinfochange.view.*
import kotlinx.android.synthetic.main.svinfochange.*
import kotlinx.android.synthetic.main.svinfochange.hoedit
import kotlinx.android.synthetic.main.svinfochange.noiedit
import kotlinx.android.synthetic.main.svinfochange.tenedit


class Gvinfochange : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gvinfochange, container, false)
        val back = v.findViewById<Button>(R.id.gvinfochangeback)
        val db= MYSQLHandler(requireContext())
        db.getTeachInfo(MYSQLHandler.user.id,object : MYSQLHandler.VolleyCallback1{
            override fun onSuccess(Data: ArrayList<TEMP>) {
                super.onSuccess(Data)
                var lname=Data.get(0).t1
                var fname=Data.get(0).t2
                var addr=Data.get(0).t5
                var pid=Data.get(0).t6
                var phone=Data.get(0).t7
                var emaill=Data.get(0).t8
                GVhoedit.setText(lname)
                GVtenedit.setText(fname)
                GVnoiedit.setText(addr)
                GVcmndedit.setText(pid)
                GVsdtedit.setText(phone)
                GVmailedit.setText(emaill)
            }
        })
        back.setOnClickListener {
            val Gvinfo = Gvinfo()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_left)
            transaction.replace(R.id.framelayoutgvnav, Gvinfo)
            transaction.commit()
        }
        return v
    }

}