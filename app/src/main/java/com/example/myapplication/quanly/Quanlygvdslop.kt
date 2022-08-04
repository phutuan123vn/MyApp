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
import kotlinx.android.synthetic.main.gv_qltkdetail.view.*


class Quanlygvdslop : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // back ve fragment qltk
        val v = inflater.inflate(R.layout.gv_dslop, container, false)
        //
//        val edit = v.findViewById<Button>(R.id.quanlygvdslopedit)
//        edit.setOnClickListener {
//            val quanlygvdslopchange = Quanlygvdslopchange()
//            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
//            transaction.replace(R.id.framelayoutqlnav, quanlygvdslopchange)
//            transaction.commit()
//        }
        //
        val back = v.findViewById<Button>(R.id.quanlygvdslopback)
        back.setOnClickListener {
            val quanlygvinfo = Quanlygvinfo()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, quanlygvinfo)
            transaction.commit()
        }
        return v
    }

}