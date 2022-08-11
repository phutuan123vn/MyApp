package com.example.myapplication.quanly.quanlygv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import com.example.myapplication.giangvien.Quanlygvinfochange


class Quanlygvinfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gv_info, container, false)
        // button xem dslop của gv
        val dslop = v.findViewById<Button>(R.id.quanlygvdslop)
        dslop.setOnClickListener {
            val quanlygvdslop = Quanlygvdslop()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, quanlygvdslop)
            transaction.commit()
        }
         //button edit thông tin giảng viên
        val edit = v.findViewById<Button>(R.id.quanlygvinfoedit)
        edit.setOnClickListener {
            val quanlygvinfochange = Quanlygvinfochange()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, quanlygvinfochange)
            transaction.commit()
        }
        // button back từ info gv về lại dsgv
        val back = v.findViewById<Button>(R.id.quanlygvinfoback)
        back.setOnClickListener {
            val gvdsgv = GVdsgv()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, gvdsgv)
            transaction.commit()
        }
        return v
    }

}

