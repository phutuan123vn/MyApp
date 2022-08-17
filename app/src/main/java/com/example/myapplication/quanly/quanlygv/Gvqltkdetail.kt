package com.example.myapplication.quanly.quanlygv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R


class Gvqltkdetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // back ve fragment qltk
        val v = inflater.inflate(R.layout.gv_qltkdetail, container, false)
        val back = v.findViewById<Button>(R.id.GVqltkback)
        back.setOnClickListener {
            val Gvqltk = GVqltk()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_left)
            transaction.replace(R.id.framelayoutqlnav, Gvqltk)
            transaction.commit()
        }
        return v
    }

}