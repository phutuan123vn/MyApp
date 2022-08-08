package com.example.myapplication.quanly.quanlygv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R


class GVqldldetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gv_qldldetail, container, false)
        val back = v.findViewById<Button>(R.id.Gvqldlback)
        back.setOnClickListener {
            val GVqldl = GVqldl()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, GVqldl)
            transaction.commit()
        }
        return v
    }

}