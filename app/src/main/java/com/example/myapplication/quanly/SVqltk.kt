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
import kotlinx.android.synthetic.main.sv_qltk.view.*


class SVqltk : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // chuyen sang fragment chi tiet tai khoan, detail
        val v = inflater.inflate(R.layout.sv_qltk, container, false)
        val edit = v.findViewById<Button>(R.id.detailgiang)
        edit.setOnClickListener {
            val Svqltkdetail = Svqltkdetail()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, Svqltkdetail)
            transaction.commit()
        }
        return v
    }

}