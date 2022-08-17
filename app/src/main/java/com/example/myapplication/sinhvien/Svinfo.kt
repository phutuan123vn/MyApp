package com.example.myapplication.sinhvien

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.myapplication.R
import kotlinx.android.synthetic.main.svinfo.view.*


class Svinfo : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.svinfo, container, false)
        val edit = v.findViewById<Button>(R.id.svinfoedit)
        edit.setOnClickListener {
            val Svinfochange = Svinfochange()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            transaction.replace(R.id.framelayoutsvnav, Svinfochange)
            transaction.commit()
        }
        return v
    }

}