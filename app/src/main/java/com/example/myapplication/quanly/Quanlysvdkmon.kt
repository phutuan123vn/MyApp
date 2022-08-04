package com.example.myapplication.quanly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import kotlinx.android.synthetic.main.sv_dkmh.view.*


class Quanlysvdkmon : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.sv_dkmh, container, false)
        val back = v.findViewById<Button>(R.id.quanlysvdkmonback)
        back.setOnClickListener {
            val svdssv = SVdssv()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, svdssv)
            transaction.commit()
        }
        val edit = v.findViewById<Button>(R.id.quanlysvdkmonedit)
        edit.setOnClickListener{
            val svdkmonchange = Quanlysvdkmonchange()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, svdkmonchange)
            transaction.commit()
        }
        return v
    }

}