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
import com.example.myapplication.sinhvien.Svinfochange
import kotlinx.android.synthetic.main.sv_qltkdetail.view.*


class Svqltkdetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // back ve fragment qltk
        val v = inflater.inflate(R.layout.sv_qltkdetail, container, false)
        val edit = v.findViewById<Button>(R.id.SVqltkback)
        edit.setOnClickListener {
            val SVqltk = SVqltk()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, SVqltk)
            transaction.commit()
        }
        return v
    }

}