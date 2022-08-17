package com.example.myapplication.giangvien

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.gvinfo.view.*


class Gvinfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gvinfo, container, false)
        val edit = v.findViewById<Button>(R.id.gvinfoedit)
        edit.setOnClickListener {
            val Gvinfochange = Gvinfochange()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            transaction.replace(R.id.framelayoutgvnav, Gvinfochange)
            transaction.commit()
        }
        return v
    }

}