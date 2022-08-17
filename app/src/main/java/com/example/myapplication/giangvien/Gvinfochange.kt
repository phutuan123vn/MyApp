package com.example.myapplication.giangvien

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import androidx.navigation.Navigation
import com.example.myapplication.sinhvien.Svinfo
import com.example.myapplication.sinhvien.Svinfochange
import kotlinx.android.synthetic.main.gvinfochange.view.*


class Gvinfochange : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gvinfochange, container, false)
        val back = v.findViewById<Button>(R.id.gvinfochangeback)
        back.setOnClickListener {
            val Gvinfo = Gvinfo()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_left)
            transaction.replace(R.id.framelayoutgvnav, Gvinfo)
            transaction.commit()
        }
        return v
    }

}