package com.example.myapplication.sinhvien

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.login.LoginSV
import kotlinx.android.synthetic.main.svdiemdanhqr.*
import kotlinx.android.synthetic.main.svdiemdanhqr.view.*


class Svdiemdanhqr : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.svdiemdanhqr, container, false)
        val back = v.findViewById<Button>(R.id.quetmaqrback)
        back.setOnClickListener {
            val Svdiemdanh = Svdiemdanh()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutsvnav, Svdiemdanh)
            transaction.commit()
        }
        return v
    }
}