package com.example.myapplication.quanly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.myapplication.R
import kotlinx.android.synthetic.main.sv_qldl.view.*


class SVqldl : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.sv_qldl, container, false)
        val detail = v.findViewById<Button>(R.id.qldldetailgiang)
        detail.setOnClickListener {
            val SVqldldetail = SVqldldetail()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, SVqldldetail)
            transaction.commit()
        }
        return v
    }

}