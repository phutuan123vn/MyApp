package com.example.myapplication.quanly.quanlysv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R


class Svqltkdetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // back ve fragment qltk
        val v = inflater.inflate(R.layout.sv_qltkdetail, container, false)
        val back = v.findViewById<Button>(R.id.SVqltkback)
        back.setOnClickListener {
            val SVqltk = SVqltk()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_left)
            transaction.replace(R.id.framelayoutqlnav, SVqltk)
            transaction.commit()
        }
        return v
    }

}