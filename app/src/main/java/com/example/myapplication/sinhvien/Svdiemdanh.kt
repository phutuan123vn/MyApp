package com.example.myapplication.sinhvien

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation
import com.example.myapplication.R
//import com.example.myapplication.databinding.SvdiemdanhBinding
import com.example.myapplication.login.LoginSV
import kotlinx.android.synthetic.main.svdiemdanh.*
import kotlinx.android.synthetic.main.svdiemdanh.view.*

class Svdiemdanh : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        val v = inflater.inflate(R.layout.svdiemdanh, container, false)
        val qmqr = v.findViewById<Button>(R.id.quetmaqr)
        qmqr.setOnClickListener {
            val Svquetmaqr = Svdiemdanhqr()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutsvnav, Svquetmaqr)
            transaction.commit()
        }
        return v
    }

}